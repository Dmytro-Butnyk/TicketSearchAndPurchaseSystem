package com.example.ticketsearchandpurchasesystem;

import com.example.ticketsearchandpurchasesystem.application.DTO.EventResponse;
import com.example.ticketsearchandpurchasesystem.application.services.EventService;
import com.example.ticketsearchandpurchasesystem.domain.entities.Event;
import com.example.ticketsearchandpurchasesystem.domain.entities.Place;
import com.example.ticketsearchandpurchasesystem.infrastructure.repositories.EventRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventService eventService;

    @Test
    void getNearestEventsByDate_shouldReturnEvent_whenExists() throws Exception {
        Event mockEvent = new Event(1);
        mockEvent.setName("Rock Concert");
        mockEvent.setDate(LocalDate.now().plusDays(1));
        mockEvent.setPlace(new Place(1));

        Mockito.when(eventRepository.findFirstByDateAfterOrderByDateAsc(Mockito.any()))
                .thenReturn(mockEvent);

        CompletableFuture<EventResponse> result = eventService.getNearestEventsByDate();

        assertEquals("Rock Concert", result.get().getName());
    }

    @Test
    void getNearestEventsByDate_shouldThrowException_whenNoEvent() {
        Mockito.when(eventRepository.findFirstByDateAfterOrderByDateAsc(Mockito.any()))
                .thenReturn(null);

        CompletableFuture<EventResponse> result = eventService.getNearestEventsByDate();

        assertThrows(ExecutionException.class, result::get);
    }
}
