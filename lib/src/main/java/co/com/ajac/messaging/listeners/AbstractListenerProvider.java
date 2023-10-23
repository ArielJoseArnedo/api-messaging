package co.com.ajac.messaging.listeners;

import co.com.ajac.messaging.listeners.models.ListenerModel;
import co.com.ajac.messaging.listeners.models.ListenerState;
import io.vavr.collection.List;
import io.vavr.concurrent.Future;

public interface AbstractListenerProvider {

    Future<List<ListenerModel>> getAllListeners();
    Future<List<ListenerModel>> getListeners(ListenerState listenerState);
}
