package com.java.pe.edu.upc.center.reservation.infrastructure.persistence.jpa.repositories;

import com.java.pe.edu.upc.center.reservation.domain.model.aggregates.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByCaregiverId(Long caregiverId);
    List<Reservation> findByTutorId(Long tutorId);
}