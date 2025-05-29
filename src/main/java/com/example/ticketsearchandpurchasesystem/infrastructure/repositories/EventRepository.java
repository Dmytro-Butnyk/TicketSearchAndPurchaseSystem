package com.example.ticketsearchandpurchasesystem.infrastructure.repositories;

import com.example.ticketsearchandpurchasesystem.domain.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {
}
