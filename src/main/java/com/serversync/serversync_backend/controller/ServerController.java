package com.serversync.serversync_backend.controller;

import com.serversync.serversync_backend.dto.ServerRequest;
import com.serversync.serversync_backend.model.Server;
import com.serversync.serversync_backend.service.ServerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servers")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ServerController {

    private final ServerService serverService;

    @GetMapping
    public ResponseEntity<List<Server>> getAllServers() {
        return ResponseEntity.ok(serverService.getAllServers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Server> getServerById(@PathVariable Long id) {
        return ResponseEntity.ok(serverService.getServerById(id));
    }

    @PostMapping
    public ResponseEntity<Server> addServer(@RequestBody ServerRequest request) {
        return ResponseEntity.ok(serverService.addServer(request));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Server> updateStatus(@PathVariable Long id,
                                               @RequestParam Server.Status status) {
        return ResponseEntity.ok(serverService.updateStatus(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServer(@PathVariable Long id) {
        serverService.deleteServer(id);
        return ResponseEntity.ok().build();
    }
}