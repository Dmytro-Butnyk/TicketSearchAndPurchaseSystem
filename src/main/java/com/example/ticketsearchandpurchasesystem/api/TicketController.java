package com.example.ticketsearchandpurchasesystem.api;

import com.example.ticketsearchandpurchasesystem.application.services.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/getFreeTickets")
    public CompletableFuture<ResponseEntity<?>> getFreeTickets(String eventName) {

        return ticketService.getFreeTicketsForEvent(eventName)
                .thenApplyAsync(tickets -> {
                    if (tickets == null || tickets.isEmpty()) {
                        return ResponseEntity.notFound().build();
                    }
                    return ResponseEntity.ok(tickets);
                });
    }

    @GetMapping("/assignTicketToCustomer")
    public CompletableFuture<ResponseEntity<String>> assignTicketToCustomer(@RequestParam int ticketId) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        return ticketService.assignTicketToCustomer(email, ticketId)
                .thenApply(isAssigned -> {
                    if (isAssigned) {
                        return ResponseEntity.ok("Ticket assigned successfully.");
                    } else {
                        return ResponseEntity.status(400).body("Failed to assign ticket.");
                    }
                })
                .exceptionally(ex -> {
                    System.err.println("Error during ticket assignment: " + ex.getMessage());
                    return ResponseEntity.status(500).body("An error occurred: " + ex.getMessage());
                });
    }

}
