package com.example.ticketsearchandpurchasesystem.infrastructure.repositories;

import com.example.ticketsearchandpurchasesystem.domain.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findTicketsByEventId(int eventId);
}