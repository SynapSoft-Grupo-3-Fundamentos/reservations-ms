package com.java.pe.edu.upc.center.reservation.domain.model.commands;

import com.java.pe.edu.upc.center.reservation.domain.model.valueobjects.ReservationStatus;

public record UpdateReservationStatusCommand (Long reservationId, String status) {}

