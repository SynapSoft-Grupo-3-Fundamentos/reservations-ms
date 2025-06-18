package com.java.pe.edu.upc.center.reservation.domain.services;


import com.java.pe.edu.upc.center.reservation.domain.model.aggregates.Reservation;
import com.java.pe.edu.upc.center.reservation.domain.model.commands.CreateReservationCommand;
import com.java.pe.edu.upc.center.reservation.domain.model.commands.UpdateReservationStatusCommand;

public interface ReservationCommandService {
    Reservation handle(CreateReservationCommand command);
    Reservation handle(UpdateReservationStatusCommand command);
}