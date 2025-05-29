package com.example.ticketsearchandpurchasesystem.domain.entities;

import com.example.ticketsearchandpurchasesystem.domain.entities.shared.BaseEntity;

public class Place extends BaseEntity
{
    private String address;
    private String name;

    public Place(int id) {
        super(id);
    }
    // todo: Add getters and setters for the fields

}
