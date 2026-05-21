package com.serversync.serversync_backend.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "deployments")
public class Deployment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String projectName;

    @Column(nullable = false)
    private String repoUrl;

    @Column(nullable = false)
    private String branch;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(columnDefinition = "TEXT")
    private String logs;

    @ManyToOne
    @JoinColumn(name = "server_id")
    private Server server;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User deployedBy;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public enum Status {
        RUNNING, SUCCESS, FAILED
    }
}