package com.java.pe.edu.upc.center.reservation.domain.model.commands;

public class CancelReservationCommand {
    private Long reservationId;

    public CancelReservationCommand(Long reservationId) {
        this.reservationId = reservationId;
    }

    public Long getReservationId() {
        return reservationId;
    }
}