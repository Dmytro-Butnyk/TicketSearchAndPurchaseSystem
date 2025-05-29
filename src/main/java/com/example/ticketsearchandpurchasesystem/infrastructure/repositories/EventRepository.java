package com.example.ticketsearchandpurchasesystem.infrastructure.repositories;

import com.example.ticketsearchandpurchasesystem.domain.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface EventRepository extends JpaRepository<Event, Integer> {
    Event findByName(String name);
    Event findFirstByDateAfterOrderByDateAsc(LocalDate currentDate);
}
