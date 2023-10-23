package co.com.ajac.messaging.listeners;

import co.com.ajac.base.errors.AppError;
import co.com.ajac.messaging.events.Event;
import co.com.ajac.messaging.publishers.PublisherProvider;
import com.fasterxml.jackson.databind.JsonNode;
import io.vavr.collection.List;
import io.vavr.control.Option;

import java.util.function.Consumer;

public interface ListenerProcessor<T> extends AbstractListenerProcessor, Consumer<Option<T>> {

    @Override
    default void accept(Option<T> messageOpt) {
        messageOpt
          .map(message -> execute(getMessage(message))
            .fold(
              appError -> List.of(makeErrorEvent(appError, message)),
              events -> events
            )
            .onSuccess(events -> publisher().publish(events))
          );
    }

    PublisherProvider publisher();

    Event makeErrorEvent(AppError appError, T message);

    JsonNode getMessage(T message);
}
