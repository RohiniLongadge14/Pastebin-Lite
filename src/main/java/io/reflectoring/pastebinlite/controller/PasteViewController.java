package io.reflectoring.pastebinlite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import io.reflectoring.pastebinlite.entity.Paste;
import io.reflectoring.pastebinlite.service.PasteService;
import io.reflectoring.pastebinlite.util.TimeProvider;
import jakarta.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
public class PasteViewController {


    private final PasteService service;
    private final TimeProvider timeProvider;

    public PasteViewController(PasteService service, TimeProvider timeProvider) {
        this.service = service;
        this.timeProvider = timeProvider;
    }

    @GetMapping("/p/{id}")
    public String viewPaste(@PathVariable String id,
                            Model model,
                            HttpServletRequest request) {
        try {
            Paste paste = service.getPaste(id, timeProvider.now(request));
            model.addAttribute("content", paste.getContent());
            return "paste";
        } catch (Exception e) {
            throw new ResponseStatusException(NOT_FOUND);
        }
    }
}
