package co.com.ajac.messaging.listeners;

import co.com.ajac.base.errors.AppError;
import co.com.ajac.concurrency.FutureEither;
import co.com.ajac.messaging.events.Event;
import co.com.ajac.messaging.events.Header;
import co.com.ajac.messaging.events.Message;
import io.vavr.collection.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListenerTest implements Listener<Message>{


    @Override
    public String getName() {
        return "ListenerTest";
    }

    @Override
    public Class<Message> getClassMessage() {
        return Message.class;
    }

    @Test
    void getNameTest() {
        final String name = getName();
        assertEquals("ListenerTest", name);
    }

    @Test
    void onMessageTest() {
        final Event build = Event.builder()
          .build();
    }
}