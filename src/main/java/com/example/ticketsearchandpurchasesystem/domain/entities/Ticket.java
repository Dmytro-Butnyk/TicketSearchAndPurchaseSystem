package com.example.ticketsearchandpurchasesystem.domain.entities;

import com.example.ticketsearchandpurchasesystem.domain.entities.shared.BaseEntity;
import com.example.ticketsearchandpurchasesystem.domain.enums.Status;

/**
 * Ticket entity representing a ticket for some Event in the system.<p>
 * int id - unique identifier for the ticket<p>
 * int seatNumber - the seat number associated with the ticket<p>
 * Customer customer - the customer who purchased the ticket<p>
 * Event event - the event for which the ticket is purchased<p>
 * status (enum: Free/Sold) - the status of the ticket, indicating whether it is available or sold
  */
public class Ticket extends BaseEntity
{
    private int seatNumber;
    private Customer customer;
    private Event event;
    private Status status;

    public Ticket(int id) {
        super(id);
    }

    // todo: Add getters and setters for the fields
}
