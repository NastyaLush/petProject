package com.example.vkk.auditing;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.aop.framework.AopProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authorization.event.AuthorizationEvent;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;

@Component
public class LoginAttemptsLogger {
    private final AuditService auditService;
    private HttpServletRequest httpServletRequest;

    @Autowired
    public LoginAttemptsLogger(AuditService auditService, HttpServletRequest httpServletRequest) {
        this.auditService = auditService;
        this.httpServletRequest = httpServletRequest;
    }

    @EventListener
    public void auditEventHappened(
            AuthorizationEvent auditApplicationEvent) {
        System.out.println("listener");
        ;
        if(!httpServletRequest.getRequestURI().equals("/error")) {
            auditService.create(new AuditDTO()
                    .builder()
                    .localDateTime(LocalDateTime.now())
                    .method(httpServletRequest.getMethod())
                    .endpoint(httpServletRequest.getRequestURI())
                    .status(auditApplicationEvent.getAuthorizationDecision().isGranted() ? "AUTHENTICATED" : "FORBIDDEN")
                    .role(auditApplicationEvent.getAuthentication().get().getName())
                    .ip(httpServletRequest.getRemoteAddr()).build()
            );
        }
    }
}
