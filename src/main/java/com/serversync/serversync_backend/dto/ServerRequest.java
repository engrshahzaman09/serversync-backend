package com.serversync.serversync_backend.dto;

import lombok.Data;

@Data
public class ServerRequest {
    private String name;
    private String ipAddress;
    private String description;
}