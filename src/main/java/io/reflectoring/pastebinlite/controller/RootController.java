package io.reflectoring.pastebinlite.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

	 @GetMapping("/")
	    public Map<String, String> home() {
	        return Map.of(
	            "message", "Pastebin Lite Backend is running",
	            "status", "OK",
	            "health", "/api/healthz"
	        );
	    }
	 
}
