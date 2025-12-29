package io.reflectoring.pastebinlite.service;

import java.time.Instant;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.reflectoring.pastebinlite.entity.Paste;
import io.reflectoring.pastebinlite.repository.PasteRepository;


@Service
public class PasteService {

    private final PasteRepository repository;

    public PasteService(PasteRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Paste getPaste(String id, Instant now) {
        Paste paste = repository.findById(id)
                .orElseThrow();

        if (paste.getExpiresAt() != null &&
                now.isAfter(paste.getExpiresAt())) {
            throw new RuntimeException();
        }

        if (paste.getMaxViews() != null) {
            if (paste.getViews() >= paste.getMaxViews()) {
                throw new RuntimeException();
            }
            paste.setViews(paste.getViews() + 1);
        }

        return paste;
    }
}
