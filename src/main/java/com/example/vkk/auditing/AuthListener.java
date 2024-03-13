package com.example.vkk.auditing;

import com.example.vkk.auditing.entity.AccessStatus;
import com.example.vkk.auditing.entity.Audit;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authorization.event.AuthorizationEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AuthListener {
    private final AuditService auditService;
    private final HttpServletRequest httpServletRequest;

    @Autowired
    public AuthListener(AuditService auditService, HttpServletRequest httpServletRequest) {
        this.auditService = auditService;
        this.httpServletRequest = httpServletRequest;
    }

    @EventListener
    public void auditEventHappened(
            AuthorizationEvent auditApplicationEvent) {
        if (!httpServletRequest.getRequestURI().equals("/error")) {
            new Audit();
            auditService.create(Audit
                    .builder()
                    .localDateTime(LocalDateTime.now())
                    .method(httpServletRequest.getMethod())
                    .endpoint(httpServletRequest.getRequestURI())
                    .status(auditApplicationEvent.getAuthorizationDecision()
                            .isGranted() ? AccessStatus.AUTHENTICATED : AccessStatus.FORBIDDEN)
                    .userName(auditApplicationEvent.getAuthentication().get().getName())
                    .ip(httpServletRequest.getRemoteAddr()).build()
            );
        }
    }
}
