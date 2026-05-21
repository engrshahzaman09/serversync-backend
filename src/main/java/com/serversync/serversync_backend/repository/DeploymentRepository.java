package com.serversync.serversync_backend.repository;

import com.serversync.serversync_backend.model.Deployment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DeploymentRepository extends JpaRepository<Deployment, Long> {
    List<Deployment> findByServerIdOrderByCreatedAtDesc(Long serverId);
    List<Deployment> findAllByOrderByCreatedAtDesc();
}