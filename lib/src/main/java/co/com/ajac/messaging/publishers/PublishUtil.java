package co.com.ajac.messaging.publishers;


import co.com.ajac.base.modules.JacksonModulo;
import co.com.ajac.messaging.events.Event;
import com.fasterxml.jackson.databind.JsonNode;
import io.vavr.collection.List;

public interface PublishUtil extends JacksonModulo {


    default List<JsonNode> transform(List<Event> events) {
        return events
          .map(this::transformEvent);
    }

    default JsonNode transformEvent(Event event) {
        return getMapper()
          .convertValue(event, JsonNode.class);
    }

    default String getTopic(Event event) {
        return event.getHeader().topic();
    }
}
