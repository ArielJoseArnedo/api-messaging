package co.com.ajac.messaging.listeners;

import co.com.ajac.base.errors.AppError;
import co.com.ajac.concurrency.FutureEither;
import co.com.ajac.messaging.events.Event;
import co.com.ajac.messaging.events.Header;
import co.com.ajac.messaging.events.Message;
import io.vavr.collection.List;

public interface Listener<M extends Message> {
    String getName();
    Class<M> getClassMessage();
    default FutureEither<AppError, List<Event>> onMessage(M message, Header header) {
        return FutureEither.right(List.empty());
    }
}
