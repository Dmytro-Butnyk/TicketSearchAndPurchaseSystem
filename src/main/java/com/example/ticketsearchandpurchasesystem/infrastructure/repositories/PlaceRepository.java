package com.example.ticketsearchandpurchasesystem.infrastructure.repositories;

import com.example.ticketsearchandpurchasesystem.domain.entities.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Integer> {
}
