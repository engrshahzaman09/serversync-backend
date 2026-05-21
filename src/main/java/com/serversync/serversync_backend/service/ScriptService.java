package com.serversync.serversync_backend.service;

import com.serversync.serversync_backend.model.Script;
import com.serversync.serversync_backend.model.User;
import com.serversync.serversync_backend.repository.ScriptRepository;
import com.serversync.serversync_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScriptService {

    private final ScriptRepository scriptRepository;
    private final UserRepository userRepository;

    public List<Script> getAllScripts() {
        return scriptRepository.findAll();
    }

    public Script saveScript(String name, String content,
                             String description, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Script script = new Script();
        script.setName(name);
        script.setContent(content);
        script.setDescription(description);
        script.setCreatedBy(user);

        return scriptRepository.save(script);
    }

    public String executeScript(Long id) {
        Script script = scriptRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Script not found"));

        StringBuilder output = new StringBuilder();

        try {
            // Write script to temp file and execute
            java.io.File tempFile = java.io.File.createTempFile("script", ".sh");
            tempFile.setExecutable(true);

            java.nio.file.Files.writeString(tempFile.toPath(), script.getContent());

            ProcessBuilder pb = new ProcessBuilder("bash", tempFile.getAbsolutePath());
            pb.redirectErrorStream(true);
            Process process = pb.start();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            process.waitFor();
            tempFile.delete();

        } catch (Exception e) {
            output.append("Error executing script: ").append(e.getMessage());
        }

        return output.toString();
    }

    public void deleteScript(Long id) {
        scriptRepository.deleteById(id);
    }
}