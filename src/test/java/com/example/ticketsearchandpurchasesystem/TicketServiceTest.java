package com.example.ticketsearchandpurchasesystem;

import com.example.ticketsearchandpurchasesystem.application.DTO.TicketResponse;
import com.example.ticketsearchandpurchasesystem.application.services.TicketService;
import com.example.ticketsearchandpurchasesystem.domain.entities.Customer;
import com.example.ticketsearchandpurchasesystem.domain.entities.Event;
import com.example.ticketsearchandpurchasesystem.domain.entities.Ticket;
import com.example.ticketsearchandpurchasesystem.domain.enums.Status;
import com.example.ticketsearchandpurchasesystem.infrastructure.repositories.CustomerRepository;
import com.example.ticketsearchandpurchasesystem.infrastructure.repositories.EventRepository;
import com.example.ticketsearchandpurchasesystem.infrastructure.repositories.TicketRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;
    @Mock private EventRepository eventRepository;
    @Mock private CustomerRepository customerRepository;

    @InjectMocks
    private TicketService ticketService;

    @Test
    void getFreeTicketsForEvent_shouldReturnFreeTickets() throws Exception {
        Event event = new Event(1);
        event.setName("Concert");

        Ticket ticket = new Ticket(10);
        ticket.setStatus(Status.FREE);
        ticket.setSeatNumber(5);
        ticket.setEvent(event);

        Mockito.when(eventRepository.findByName("Concert")).thenReturn(event);
        Mockito.when(ticketRepository.findTicketsByEventId(1)).thenReturn(List.of(ticket));

        CompletableFuture<List<TicketResponse>> result = ticketService.getFreeTicketsForEvent("Concert");

        assertEquals(1, result.get().size());
        assertEquals(5, result.get().get(0).getSeatNumber());
    }

    @Test
    void assignTicketToCustomer_shouldAssign_whenValid() throws Exception {
        Ticket ticket = new Ticket(1);
        ticket.setStatus(Status.FREE);

        Customer customer = new Customer(100);
        Mockito.when(ticketRepository.findById(1)).thenReturn(Optional.of(ticket));
        Mockito.when(customerRepository.findByEmail("user@example.com")).thenReturn(customer);

        CompletableFuture<Boolean> result = ticketService.assignTicketToCustomer("user@example.com", 1);

        assertTrue(result.get());
        assertEquals(Status.SOLD, ticket.getStatus());
        assertEquals(customer, ticket.getCustomer());
    }

    @Test
    void assignTicketToCustomer_shouldFail_whenTicketIsNotFree() {
        Ticket ticket = new Ticket(1);
        ticket.setStatus(Status.SOLD);

        Mockito.when(ticketRepository.findById(1)).thenReturn(Optional.of(ticket));

        CompletableFuture<Boolean> result = ticketService.assignTicketToCustomer("user@example.com", 1);

        assertThrows(ExecutionException.class, result::get);
    }
}

