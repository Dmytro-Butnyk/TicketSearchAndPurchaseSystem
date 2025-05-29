package com.example.ticketsearchandpurchasesystem.domain.entities;

import com.example.ticketsearchandpurchasesystem.domain.entities.shared.BaseEntity;

import java.util.List;

/**
 * Customer entity representing a customer in the system.<p>
 * int id - unique identifier for the customer<p>
 * String email - email address of the customer<p>
 * String phoneNumber - phone number of the customer<p>
 * List<Ticket> tickets - list of tickets purchased by the customer
 */
public class Customer extends BaseEntity
{
    private String email;
    private String phoneNumber;
    private List<Ticket> tickets;

    public Customer(int id) {
        super(id);
    }

    // todo: Add getters and setters for the fields
}
