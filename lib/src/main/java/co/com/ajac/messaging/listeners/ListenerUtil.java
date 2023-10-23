package co.com.ajac.messaging.listeners;

import co.com.ajac.base.errors.AppError;
import co.com.ajac.concurrency.FutureEither;
import co.com.ajac.messaging.events.Event;
import co.com.ajac.messaging.events.Header;
import co.com.ajac.messaging.events.Message;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import io.vavr.Function1;
import io.vavr.Tuple;
import io.vavr.Tuple3;
import io.vavr.concurrent.Future;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;

import static io.vavr.API.For;

import static co.com.ajac.messaging.util.StgMonad.getOption;

public interface ListenerUtil {

    default FutureEither<AppError, Event> findJsonEvent(Function1<FutureEither<AppError, Tuple3<Option<String>, JsonNode, JsonNode>>, FutureEither<AppError, Event>> function, JsonNode messageJson) {
        final Future<Either<AppError, Tuple3<Option<String>, JsonNode, JsonNode>>> futureFunction = Future.of(() -> Try.of(() -> {
                final JsonNode header = messageJson.path("header");
                final JsonNode message = messageJson.path("message");
              final Option<String> nameOpt = getOption(header).map(h -> h.path("name")).map(JsonNode::asText);
              return Tuple.of(nameOpt, header, message);
            })
            .toEither(ListenerError.MESSAGE_STRUCTURE_NOT_FOUNT)
        );

        return function.apply(FutureEither.of(futureFunction));
    }

    default FutureEither<AppError, Listener> findListener(String name, AbstractListenerManager abstractListenerManager) {
        return FutureEither.of(Future.of(() ->
          abstractListenerManager
            .provide(name)
            .toEither(ListenerError.LISTENER_NOT_FOUNT)
        ));
    }

    default FutureEither<AppError, Event> findEvent(Option<String> listenerNameOpt, Option<JsonNode> headerOpt, Option<JsonNode> messageBodyOpt, AbstractListenerManager abstractListenerManager) {
        return FutureEither.fromEither(listenerNameOpt
          .toEither((AppError) ListenerError.LISTENER_NOT_FOUNT)
          .flatMap(commandName -> For(
                  headerOpt,
                  messageBodyOpt
                ).yield((header, message) -> abstractListenerManager.deserialize(header, message, commandName))
                  .toEither(ListenerError.LISTENER_MESSAGE_NOT_FOUNT)
          )
          .flatMap(tryRequest -> tryRequest.toEither(ListenerError.MESSAGE_NOT_DESERIALIZED))
        );
    }

    default Event buildEvent(Header header, Message message) {
        return Event.builder()
          .header(header)
          .message(message)
          .build();
    }

    default JsonNode makeResponseError(AppError appError) {
        final JsonNodeFactory factory = JsonNodeFactory.instance;
        return factory.objectNode()
          .put("code", appError.code())
          .put("causal", appError.message())
          .put("description", appError.description());
    }
}
