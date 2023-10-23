package co.com.ajac.messaging.publishers;

import co.com.ajac.base.events.IEvent;
import co.com.ajac.base.events.IHeader;
import co.com.ajac.base.events.IMessage;
import co.com.ajac.base.events.INotice;
import io.vavr.collection.List;
import io.vavr.concurrent.Future;

public interface  Publisher<H extends IHeader, M extends IMessage, N extends INotice, E extends IEvent<H, M , N >> {
    Future<List<E>> publish(List<E> events);
}
