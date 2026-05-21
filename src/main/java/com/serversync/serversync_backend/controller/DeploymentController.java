package com.serversync.serversync_backend.controller;

import com.serversync.serversync_backend.dto.DeploymentRequest;
import com.serversync.serversync_backend.model.Deployment;
import com.serversync.serversync_backend.service.DeploymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deployments")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DeploymentController {

    private final DeploymentService deploymentService;

    @GetMapping
    public ResponseEntity<List<Deployment>> getAllDeployments() {
        return ResponseEntity.ok(deploymentService.getAllDeployments());
    }

    @GetMapping("/server/{serverId}")
    public ResponseEntity<List<Deployment>> getByServer(@PathVariable Long serverId) {
        return ResponseEntity.ok(deploymentService.getDeploymentsByServer(serverId));
    }

    @PostMapping
    public ResponseEntity<Deployment> createDeployment(
            @RequestBody DeploymentRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(
                deploymentService.createDeployment(request, userDetails.getUsername()));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Deployment> updateStatus(
            @PathVariable Long id,
            @RequestParam Deployment.Status status,
            @RequestParam String logs) {
        return ResponseEntity.ok(deploymentService.updateStatus(id, status, logs));
    }
}