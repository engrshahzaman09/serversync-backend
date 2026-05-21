package com.serversync.serversync_backend.repository;

import com.serversync.serversync_backend.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ServerRepository extends JpaRepository<Server, Long> {
    List<Server> findByStatus(Server.Status status);
}