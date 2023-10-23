package co.com.ajac.messaging.listeners.models;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class ListenerModel {
    private final String name;
    private final String path;
    private final ListenerState listenerState;
}
