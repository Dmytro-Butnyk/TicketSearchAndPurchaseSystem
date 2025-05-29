package com.example.ticketsearchandpurchasesystem.domain.entities;

import com.example.ticketsearchandpurchasesystem.domain.entities.shared.BaseEntity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Event extends BaseEntity {

    private LocalDate date;
    private String name;

    @ManyToOne
    private Place place;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Ticket> tickets;

    public Event() {}

    public Event(int id) {
        super(id);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Event date cannot be null");
        }
        if (date.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Event date cannot be in the past");
        }
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Event name cannot be null or empty");
        }
        if (name.length() < 3 || name.length() > 100) {
            throw new IllegalArgumentException("Event name must be between 3 and 100 characters");
        }
        this.name = name.trim();
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        if (place == null) {
            throw new IllegalArgumentException("Event must be associated with a place");
        }
        this.place = place;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        if (tickets == null) {
            throw new IllegalArgumentException("Tickets list cannot be null");
        }
        this.tickets = tickets;
    }
}
