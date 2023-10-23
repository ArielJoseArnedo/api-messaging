package co.com.ajac.messaging.events;

import co.com.ajac.base.events.IEvent;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
public class Event implements IEvent<Header, Message, Notice> {

    private final Header header;
    private final Message message;

    @Override
    public Header getHeader() {
        return null;
    }

    @Override
    public Message getMessage() {
        return null;
    }
}
