package com.example.ticketsearchandpurchasesystem.application.services;

import com.example.ticketsearchandpurchasesystem.application.DTO.TicketResponse;
import com.example.ticketsearchandpurchasesystem.domain.entities.Customer;
import com.example.ticketsearchandpurchasesystem.domain.entities.Event;
import com.example.ticketsearchandpurchasesystem.domain.entities.Ticket;
import com.example.ticketsearchandpurchasesystem.domain.enums.Status;
import com.example.ticketsearchandpurchasesystem.infrastructure.repositories.CustomerRepository;
import com.example.ticketsearchandpurchasesystem.infrastructure.repositories.EventRepository;
import com.example.ticketsearchandpurchasesystem.infrastructure.repositories.TicketRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final EventRepository eventRepository;
    private final CustomerRepository customerRepository;

    public TicketService(
            TicketRepository ticketRepository,
            EventRepository eventRepository,
            CustomerRepository customerRepository) {
        this.ticketRepository = ticketRepository;
        this.eventRepository = eventRepository;
        this.customerRepository = customerRepository;
    }

    @Async
    public CompletableFuture<List<TicketResponse>> getFreeTicketsForEvent(String eventName) {
        try {
            Event event = eventRepository.findByName(eventName);
            if (event == null) {
                throw new RuntimeException("Event not found: " + eventName);
            }

            List<Ticket> tickets = ticketRepository.findTicketsByEventId(event.getId());

            List<TicketResponse> freeTicketDtos = tickets.stream()
                    .filter(ticket -> "FREE".equalsIgnoreCase(ticket.getStatus().toString()))
                    .map(ticket -> new TicketResponse(
                            ticket.getId(),
                            ticket.getSeatNumber(),
                            event.getName()
                    ))
                    .toList();

            return CompletableFuture.completedFuture(
                    freeTicketDtos.isEmpty() ? null : freeTicketDtos
            );

        } catch (Exception ex) {
            return CompletableFuture.failedFuture(ex);
        }
    }

    @Async
    public CompletableFuture<Boolean> assignTicketToCustomer(String email, int ticketId) {
        try {
            if (email == null || email.isBlank()) {
                throw new IllegalArgumentException("Email cannot be null or blank");
            }

            Ticket ticket = ticketRepository.findById(ticketId)
                    .orElseThrow(() -> new IllegalArgumentException("Ticket not found: " + ticketId));

            if (!Status.FREE.equals(ticket.getStatus())) {
                throw new IllegalStateException("Ticket is not free: " + ticketId);
            }

            Customer customer = customerRepository.findByEmail(email);
            if (customer == null) {
                throw new IllegalArgumentException("Customer not found: " + email);
            }

            ticket.setCustomer(customer);
            ticket.setStatus(Status.SOLD);
            ticketRepository.save(ticket);

            return CompletableFuture.completedFuture(true);
        } catch (Exception ex) {
            return CompletableFuture.failedFuture(ex);
        }
    }


}
