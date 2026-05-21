package com.serversync.serversync_backend.dto;

import lombok.Data;

@Data
public class DeploymentRequest {
    private String projectName;
    private String repoUrl;
    private String branch;
    private Long serverId;
}