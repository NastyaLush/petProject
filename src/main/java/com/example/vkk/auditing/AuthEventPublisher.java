package com.example.vkk.auditing;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationEventPublisher;
import org.springframework.security.authorization.event.AuthorizationDeniedEvent;
import org.springframework.security.authorization.event.AuthorizationGrantedEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthEventPublisher implements AuthorizationEventPublisher {

    private final ApplicationEventPublisher publisher;
    private final HttpServletRequest httpServletRequest;

    private static final String AUTH_LIST = "/auth";
    private static final String WEBSOCKET_LIST = "/ws";

    @Override
    public <T> void publishAuthorizationEvent(Supplier<Authentication> authentication,
                                              T object, AuthorizationDecision decision) {
        if (decision == null) {
            return;
        }
        if (!authentication.getClass()
                .getName()
                .startsWith("org.springframework.security.authorization.method.AuthorizationManagerBeforeMethodInterceptor") && !httpServletRequest.getRequestURI()
                .startsWith(WEBSOCKET_LIST) && !httpServletRequest.getRequestURI().startsWith(AUTH_LIST)) {
            return;
        }
        if (decision.isGranted()) {
            AuthorizationGrantedEvent<T> granted = new AuthorizationGrantedEvent<>(authentication, object, decision);
            publisher.publishEvent(granted);
        } else {
            AuthorizationDeniedEvent<T> failure = new AuthorizationDeniedEvent<>(authentication, object, decision);
            publisher.publishEvent(failure);
        }
    }


}
