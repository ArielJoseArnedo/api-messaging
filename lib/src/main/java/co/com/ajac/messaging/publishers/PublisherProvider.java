package co.com.ajac.messaging.publishers;


import co.com.ajac.messaging.events.Event;
import co.com.ajac.messaging.events.Header;
import co.com.ajac.messaging.events.Message;
import co.com.ajac.messaging.events.Notice;
import io.vavr.collection.List;
import io.vavr.concurrent.Future;

public interface PublisherProvider extends Publisher<Header, Message, Notice, Event> {

    Future<List<Event>> publish(List<Event> events);
}
