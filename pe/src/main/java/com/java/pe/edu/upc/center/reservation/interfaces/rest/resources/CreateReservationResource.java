package com.java.pe.edu.upc.center.reservation.interfaces.rest.resources;


import java.time.LocalDateTime;

public record CreateReservationResource(
        Long caregiverId,
        Long tutorId,
        LocalDateTime startTime,
        LocalDateTime endTime,
        double totalAmount
) {}