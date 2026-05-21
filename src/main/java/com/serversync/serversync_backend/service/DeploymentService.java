package com.serversync.serversync_backend.service;

import com.serversync.serversync_backend.dto.DeploymentRequest;
import com.serversync.serversync_backend.model.Deployment;
import com.serversync.serversync_backend.model.Server;
import com.serversync.serversync_backend.model.User;
import com.serversync.serversync_backend.repository.DeploymentRepository;
import com.serversync.serversync_backend.repository.ServerRepository;
import com.serversync.serversync_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeploymentService {

    private final DeploymentRepository deploymentRepository;
    private final ServerRepository serverRepository;
    private final UserRepository userRepository;

    public List<Deployment> getAllDeployments() {
        return deploymentRepository.findAllByOrderByCreatedAtDesc();
    }

    public List<Deployment> getDeploymentsByServer(Long serverId) {
        return deploymentRepository.findByServerIdOrderByCreatedAtDesc(serverId);
    }

    public Deployment createDeployment(DeploymentRequest request, String username) {
        Server server = serverRepository.findById(request.getServerId())
                .orElseThrow(() -> new RuntimeException("Server not found"));

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Deployment deployment = new Deployment();
        deployment.setProjectName(request.getProjectName());
        deployment.setRepoUrl(request.getRepoUrl());
        deployment.setBranch(request.getBranch());
        deployment.setStatus(Deployment.Status.RUNNING);
        deployment.setServer(server);
        deployment.setDeployedBy(user);
        deployment.setLogs("Deployment started...");

        return deploymentRepository.save(deployment);
    }

    public Deployment updateStatus(Long id, Deployment.Status status, String logs) {
        Deployment deployment = deploymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Deployment not found"));
        deployment.setStatus(status);
        deployment.setLogs(logs);
        return deploymentRepository.save(deployment);
    }
}