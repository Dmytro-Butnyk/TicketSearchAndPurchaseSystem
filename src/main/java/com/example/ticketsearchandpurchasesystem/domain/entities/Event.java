package com.example.ticketsearchandpurchasesystem.domain.entities;

import com.example.ticketsearchandpurchasesystem.domain.entities.shared.BaseEntity;

import java.time.LocalDate;
import java.util.List;

public class Event extends BaseEntity
{
    private LocalDate date;
    private String name;
    private Place place;
    private List<Ticket> tickets;

    public Event(int id) {
        super(id);
    }
    // todo: Add getters and setters for the fields

}
