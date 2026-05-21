package com.serversync.serversync_backend.repository;

import com.serversync.serversync_backend.model.Script;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ScriptRepository extends JpaRepository<Script, Long> {
    List<Script> findByCreatedById(Long userId);
}