package co.com.ajac.messaging.listeners;

import co.com.ajac.messaging.events.Event;
import co.com.ajac.messaging.events.Header;
import co.com.ajac.messaging.events.Message;
import com.fasterxml.jackson.databind.JsonNode;
import io.vavr.collection.List;
import io.vavr.control.Option;
import io.vavr.control.Try;

public abstract class AbstractListenerManager {


    protected List<Listener> listeners;

    public abstract Option<Listener> provide(String name);

    public abstract Try<Event> deserialize(JsonNode header, JsonNode message, String name);

    protected Event buildEvent(Header header, Message message) {
        return Event.builder()
          .header(header)
          .message(message)
          .build();
    }
}
