package com.example.ticketsearchandpurchasesystem.api;

import com.example.ticketsearchandpurchasesystem.application.services.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/event")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/getNearestEventsByDate")
    public CompletableFuture<ResponseEntity<?>> getNearestEventsByDate() {
        return eventService.getNearestEventsByDate()
                .thenApplyAsync(eventResponse -> {
                    if (eventResponse == null) {
                        return ResponseEntity.notFound().build();
                    }
                    return ResponseEntity.ok(eventResponse);
                });
    }
}
