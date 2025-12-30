package io.reflectoring.pastebinlite.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

	 @GetMapping("/")
	    public String home() {
	        return "Pastebin Lite Backend is running";
	    }
	 
}
