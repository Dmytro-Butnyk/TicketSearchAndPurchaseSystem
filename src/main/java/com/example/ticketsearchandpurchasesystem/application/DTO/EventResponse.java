package com.example.ticketsearchandpurchasesystem.application.DTO;

import com.example.ticketsearchandpurchasesystem.domain.entities.Place;

import java.time.LocalDate;

public class EventResponse {
    private int event_id;
    private LocalDate date;
    private String name;

    private Place place;

    public EventResponse() {}
    public EventResponse(int event_id, LocalDate date, String name, Place place) {
        this.event_id = event_id;
        this.date = date;
        this.name = name;
        this.place = place;
    }
    public int getEvent_id() {
        return event_id;
    }
    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Place getPlace() {
        return place;
    }
    public void setPlace(Place place) {
        this.place = place;
    }
}
