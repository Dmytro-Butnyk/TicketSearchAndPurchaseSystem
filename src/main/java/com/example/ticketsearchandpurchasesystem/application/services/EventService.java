package com.example.ticketsearchandpurchasesystem.application.services;

import com.example.ticketsearchandpurchasesystem.application.DTO.EventResponse;
import com.example.ticketsearchandpurchasesystem.domain.entities.Event;
import com.example.ticketsearchandpurchasesystem.infrastructure.repositories.EventRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Async
    public CompletableFuture<EventResponse> getNearestEventsByDate() {
        try {
            LocalDate today = LocalDate.now();
            Event event = eventRepository.findFirstByDateAfterOrderByDateAsc(today);
            if (event == null) {
                throw new RuntimeException("No events found.");
            }

            EventResponse eventResponse = new EventResponse(
                    event.getId(),
                    event.getDate(),
                    event.getName(),
                    event.getPlace()
            );

            return CompletableFuture.completedFuture(eventResponse);
        } catch (Exception ex) {
            return CompletableFuture.failedFuture(ex);
        }
    }
}
