package com.serversync.serversync_backend.service;

import com.serversync.serversync_backend.dto.ServerRequest;
import com.serversync.serversync_backend.model.Server;
import com.serversync.serversync_backend.repository.ServerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServerService {

    private final ServerRepository serverRepository;

    public List<Server> getAllServers() {
        return serverRepository.findAll();
    }

    public Server getServerById(Long id) {
        return serverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Server not found: " + id));
    }

    public Server addServer(ServerRequest request) {
        Server server = new Server();
        server.setName(request.getName());
        server.setIpAddress(request.getIpAddress());
        server.setDescription(request.getDescription());
        server.setStatus(Server.Status.ONLINE);
        return serverRepository.save(server);
    }

    public Server updateStatus(Long id, Server.Status status) {
        Server server = getServerById(id);
        server.setStatus(status);
        return serverRepository.save(server);
    }

    public void deleteServer(Long id) {
        serverRepository.deleteById(id);
    }
}