package co.com.ajac.messaging.events;

import co.com.ajac.base.events.IHeader;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Header implements IHeader {

    private String id;
    private String transactionId;
    private String name;
    private String topic;
    private String origin;

    @Override
    public String id() {
        return null;
    }

    @Override
    public String transactionId() {
        return null;
    }

    @Override
    public String name() {
        return null;
    }

    @Override
    public String topic() {
        return null;
    }

    @Override
    public String origin() {
        return null;
    }
}
