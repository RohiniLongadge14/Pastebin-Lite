package io.reflectoring.pastebinlite.controller;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.reflectoring.pastebinlite.dto.PasteRequest;
import io.reflectoring.pastebinlite.entity.Paste;
import io.reflectoring.pastebinlite.repository.PasteRepository;
import io.reflectoring.pastebinlite.service.PasteService;
import io.reflectoring.pastebinlite.util.TimeProvider;

import jakarta.servlet.http.HttpServletRequest;

@RestController
/*
 * @CrossOrigin( origins = { "http://localhost:3000",
 * "https://pastebin-lite-frontend-hkz7jopzg-rohinis-projects-c95122e8.vercel.app"
 * }, allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST,
 * RequestMethod.OPTIONS} )
 */
public class PasteApiController {

    private final PasteRepository repository;
    private final PasteService service;
    private final TimeProvider timeProvider;

    public PasteApiController(
            PasteRepository repository,
            PasteService service,
            TimeProvider timeProvider
    ) {
        this.repository = repository;
        this.service = service;
        this.timeProvider = timeProvider;
    }

    @PostMapping("/api/pastes")
    public ResponseEntity<?> createPaste(@RequestBody PasteRequest request) {

        if (request.getContent() == null || request.getContent().isBlank()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Invalid content"));
        }

        Paste paste = new Paste();
        paste.setId(UUID.randomUUID().toString());
        paste.setContent(request.getContent());
        paste.setViews(0);
        paste.setCreatedAt(Instant.now());
        paste.setMaxViews(request.getMaxViews());

        repository.save(paste);

        return ResponseEntity.ok(
            Map.of(
                "id", paste.getId(),
                "url", "/p/" + paste.getId()
            )
        );
    }

    @GetMapping("/api/pastes/{id}")
    public ResponseEntity<?> fetchPaste(
            @PathVariable String id,
            HttpServletRequest request
    ) {
        try {
            Instant now = timeProvider.now(request);
            Paste paste = service.getPaste(id, now);

            return ResponseEntity.ok(
                Map.of("content", paste.getContent())
            );
        } catch (Exception e) {
            return ResponseEntity.status(404)
                    .body(Map.of("error", "Not found"));
        }
    }
}
