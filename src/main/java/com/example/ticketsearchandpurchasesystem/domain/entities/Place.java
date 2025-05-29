package com.example.ticketsearchandpurchasesystem.domain.entities;

import com.example.ticketsearchandpurchasesystem.domain.entities.shared.BaseEntity;
import jakarta.persistence.*;

@Entity
public class Place extends BaseEntity {

    private String address;
    private String name;

    public Place() {}

    public Place(int id) {
        super(id);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("Address cannot be null or empty");
        }
        if (address.length() < 5 || address.length() > 200) {
            throw new IllegalArgumentException("Address must be between 5 and 200 characters");
        }
        this.address = address.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Place name cannot be null or empty");
        }
        if (name.length() < 2 || name.length() > 100) {
            throw new IllegalArgumentException("Place name must be between 2 and 100 characters");
        }
        this.name = name.trim();
    }
}

