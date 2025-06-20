package com.java.pe.edu.upc.center.reservation.infrastructure.persistence.jpa.repositories;

import com.java.pe.edu.upc.center.reservation.domain.model.aggregates.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByCaregiverId(Long caregiverId);
    List<Reservation> findByTutorId(Long tutorId);
}