package com.example.ticketsearchandpurchasesystem.application.DTO;

import com.example.ticketsearchandpurchasesystem.domain.entities.Event;

public class TicketResponse {
    private int ticketId;
    private int seatNumber;
    private String eventName;
    public TicketResponse() {}
    public TicketResponse(int ticketId, int seatNumber, String eventName) {
        this.ticketId = ticketId;
        this.seatNumber = seatNumber;
        this.eventName = eventName;
    }
    public int getTicketId() {
        return ticketId;
    }
    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }
    public int getSeatNumber() {
        return seatNumber;
    }
    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }
    public String getEventName() {
        return eventName;
    }
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
}
