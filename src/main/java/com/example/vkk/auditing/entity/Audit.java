package com.example.vkk.auditing.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "audit")
public class Audit {
    @Id
    @GeneratedValue
    private Integer id;
    private LocalDateTime localDateTime;
    private String method;
    private String endpoint;
    @Enumerated(EnumType.STRING)
    private AccessStatus status;
    private String userName;
    private String ip;
}
