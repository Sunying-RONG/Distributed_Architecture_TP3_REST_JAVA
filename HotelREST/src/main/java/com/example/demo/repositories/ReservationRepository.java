package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{

}
