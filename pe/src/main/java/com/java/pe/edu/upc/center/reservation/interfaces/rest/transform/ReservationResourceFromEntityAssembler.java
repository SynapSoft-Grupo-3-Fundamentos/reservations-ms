package com.java.pe.edu.upc.center.reservation.interfaces.rest.transform;

import com.java.pe.edu.upc.center.reservation.domain.model.aggregates.Reservation;
import com.java.pe.edu.upc.center.reservation.interfaces.rest.resources.ReservationResource;

import java.text.SimpleDateFormat;

public class ReservationResourceFromEntityAssembler {

    public static ReservationResource toResourceFromEntity(Reservation entity) {
        return new ReservationResource(
                entity.getId(),
                entity.getCaregiverId(),
                entity.getTutorId(),
                entity.getDate(),
                entity.getStartTime(),
                entity.getEndTime(),
                entity.getStatus().name(),
                entity.getTotalAmount()
        );
    }
}