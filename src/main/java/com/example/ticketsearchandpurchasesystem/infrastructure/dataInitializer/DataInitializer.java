package com.example.ticketsearchandpurchasesystem.infrastructure.dataInitializer;

import com.example.ticketsearchandpurchasesystem.domain.entities.*;
import com.example.ticketsearchandpurchasesystem.domain.enums.Status;
import com.example.ticketsearchandpurchasesystem.infrastructure.repositories.*;
import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Component
public class DataInitializer {

    private final CustomerRepository customerRepository;
    private final EventRepository eventRepository;
    private final PlaceRepository placeRepository;
    private final TicketRepository ticketRepository;
    private final Faker faker = new Faker();

    public DataInitializer(
            CustomerRepository customerRepository,
            EventRepository eventRepository,
            PlaceRepository placeRepository,
            TicketRepository ticketRepository
    ) {
        this.customerRepository = customerRepository;
        this.eventRepository = eventRepository;
        this.placeRepository = placeRepository;
        this.ticketRepository = ticketRepository;
    }

    @PostConstruct
    @Transactional
    public void initData() {
        if (customerRepository.count() > 0 || eventRepository.count() > 0 || ticketRepository.count() > 0) {
            System.out.println("Test data already initialized. Skipping initialization.");
            return;
        }

        // Створення Customers
        List<Customer> customers = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Customer customer = new Customer();
            customer.setEmail(faker.internet().emailAddress());
            customer.setPhoneNumber(faker.phoneNumber().cellPhone());
            customer = customerRepository.save(customer); // гарантовано збереження з ID
            customers.add(customer);
        }

        // Створення Places
        List<Place> places = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Place place = new Place();
            place.setName(faker.company().name());
            place.setAddress(faker.address().fullAddress());
            place = placeRepository.save(place); // збереження з ID
            places.add(place);
        }

        // Створення Events
        List<Event> events = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Event event = new Event();
            event.setName(faker.book().title());
            event.setDate(LocalDate.now().plusDays(faker.number().numberBetween(1, 30)));

            // Вибір збереженого Place
            Place place = places.get(faker.random().nextInt(places.size()));
            event.setPlace(place);

            event = eventRepository.save(event); // збереження з ID
            events.add(event);
        }

        // Створення Tickets
        for (Event event : events) {
            for (int seat = 1; seat <= 5; seat++) {
                Ticket ticket = new Ticket();
                ticket.setSeatNumber(seat);
                ticket.setEvent(event); // Event точно має ID

                // Випадковий статус
                Status status = faker.options().option(Status.class);
                ticket.setStatus(status);

                // Якщо статус SOLD, встановити випадкового Customer
                if (status == Status.SOLD) {
                    Customer randomCustomer = customers.get(faker.random().nextInt(customers.size()));
                    ticket.setCustomer(randomCustomer); // Customer точно має ID
                }

                ticketRepository.save(ticket);
            }
        }

        System.out.println("Test data initialized.");
    }

}
