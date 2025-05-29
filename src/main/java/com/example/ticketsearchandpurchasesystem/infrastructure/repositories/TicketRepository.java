package com.example.ticketsearchandpurchasesystem.infrastructure.repositories;

import com.example.ticketsearchandpurchasesystem.domain.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}