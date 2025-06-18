package com.java.pe.edu.upc.center.reservation.application.internal.queryservices;

import com.java.pe.edu.upc.center.reservation.domain.model.aggregates.Reservation;
import com.java.pe.edu.upc.center.reservation.domain.model.queries.GetReservationByCaregiverQuery;
import com.java.pe.edu.upc.center.reservation.domain.model.queries.GetReservationById;
import com.java.pe.edu.upc.center.reservation.domain.model.queries.GetReservationByTutorQuery;
import com.java.pe.edu.upc.center.reservation.domain.services.ReservationQueryService;
import com.java.pe.edu.upc.center.reservation.infrastructure.persistence.jpa.repositories.ReservationRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ReservationQueryServiceImpl implements ReservationQueryService {
    private final ReservationRepository reservationRepository;

    public ReservationQueryServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<Reservation> handle(GetReservationByCaregiverQuery query) {
        return reservationRepository.findByCaregiverId(query.caregiverId());
    }

    @Override
    public List<Reservation> handle(GetReservationByTutorQuery query) {
        return reservationRepository.findByTutorId(query.tutorId());
    }

    @Override
    public Optional<Reservation> handle(GetReservationById query) {
        return reservationRepository.findById(query.reservationId());
    }
}