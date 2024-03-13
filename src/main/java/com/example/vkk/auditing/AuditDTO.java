package com.example.vkk.auditing;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "_audit")
public class AuditDTO {
    @Id
    @GeneratedValue
    private Integer id;
    private LocalDateTime localDateTime;
    private String method;

    private String endpoint;

    private String status;

    private String role;
    private String ip;
}
