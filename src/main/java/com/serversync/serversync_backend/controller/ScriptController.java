package com.serversync.serversync_backend.controller;

import com.serversync.serversync_backend.model.Script;
import com.serversync.serversync_backend.service.ScriptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scripts")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ScriptController {

    private final ScriptService scriptService;

    @GetMapping
    public ResponseEntity<List<Script>> getAllScripts() {
        return ResponseEntity.ok(scriptService.getAllScripts());
    }

    @PostMapping
    public ResponseEntity<Script> saveScript(
            @RequestParam String name,
            @RequestParam String content,
            @RequestParam String description,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(
                scriptService.saveScript(name, content, description,
                        userDetails.getUsername()));
    }

    @PostMapping("/{id}/execute")
    public ResponseEntity<String> executeScript(@PathVariable Long id) {
        return ResponseEntity.ok(scriptService.executeScript(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScript(@PathVariable Long id) {
        scriptService.deleteScript(id);
        return ResponseEntity.ok().build();
    }
}