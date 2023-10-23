package co.com.ajac.messaging.listeners;

import co.com.ajac.base.errors.AppError;
import co.com.ajac.concurrency.FutureEither;
import co.com.ajac.messaging.events.Event;
import com.fasterxml.jackson.databind.JsonNode;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.Tuple3;
import io.vavr.collection.List;
import io.vavr.control.Option;

import static co.com.ajac.messaging.util.StgMonad.getOption;


public interface AbstractListenerProcessor extends ListenerUtil {

    AbstractListenerManager getListenerManager();

    default FutureEither<AppError, List<Event>> execute(JsonNode message) {
        return identifyEvent(message)
          .flatMap(this::identifyListener)
          .flatMap(listenerEventTuple2 -> listenerEventTuple2
            .apply(this::toExecuteListener)
          );
    }

    default FutureEither<AppError, Event> identifyEvent(JsonNode message) {
        return findJsonEvent((FutureEither<AppError, Tuple3<Option<String>, JsonNode, JsonNode>> eventStructureJson) ->
            eventStructureJson.
              flatMap(eventData -> eventData.apply((Option<String> listenerNameOpt, JsonNode header, JsonNode messageBody) ->
                  findEvent(listenerNameOpt, getOption(header), getOption(messageBody), getListenerManager())
                )
              )
          ,
          message);
    }

    default FutureEither<AppError, Tuple2<Listener, Event>> identifyListener(Event event) {
        return findListener(event.getHeader().name(), getListenerManager())
          .map(listener -> Tuple.of(listener, event));
    }

    @SuppressWarnings("unchecked")
    default FutureEither<AppError, List<Event>> toExecuteListener(Listener listener, Event event) {
        return ((FutureEither<AppError, List<Event>>) listener.onMessage(event.getMessage(), event.getHeader()));
    }
}
