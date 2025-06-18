package com.java.pe.edu.upc.center.reservation.interfaces.rest.resources;

import java.time.LocalDateTime;

public record ReservationResource(
        Long id,
        Long caregiverId,
        Long tutorId,
        LocalDateTime startTime,
        LocalDateTime endTime,
        String status,
        double totalAmount
) {}