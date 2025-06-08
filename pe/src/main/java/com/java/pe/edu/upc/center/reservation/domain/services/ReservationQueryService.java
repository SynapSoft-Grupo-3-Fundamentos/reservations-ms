package com.java.pe.edu.upc.center.reservation.domain.services;




import com.java.pe.edu.upc.center.reservation.domain.model.aggregates.Reservation;
import com.java.pe.edu.upc.center.reservation.domain.model.queries.GetReservationByCaregiverQuery;
import com.java.pe.edu.upc.center.reservation.domain.model.queries.GetReservationById;
import com.java.pe.edu.upc.center.reservation.domain.model.queries.GetReservationByTutorQuery;

import java.util.List;
import java.util.Optional;

public interface ReservationQueryService {
    List<Reservation> handle(GetReservationByCaregiverQuery query);
    List<Reservation> handle(GetReservationByTutorQuery query);
    Optional<Reservation> handle(GetReservationById query);
}