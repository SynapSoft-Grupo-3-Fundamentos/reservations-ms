package com.java.pe.edu.upc.center.reservation.application.internal.commandservices;

import com.java.pe.edu.upc.center.reservation.application.internal.outboundservices.acl.ExternalCaregiverService;
import com.java.pe.edu.upc.center.reservation.domain.model.aggregates.Reservation;
import com.java.pe.edu.upc.center.reservation.domain.model.commands.CreateReservationCommand;
import com.java.pe.edu.upc.center.reservation.domain.model.commands.UpdateReservationStatusCommand;
import com.java.pe.edu.upc.center.reservation.domain.model.valueobjects.ReservationStatus;
import com.java.pe.edu.upc.center.reservation.domain.services.ReservationCommandService;
import com.java.pe.edu.upc.center.reservation.infrastructure.persistence.jpa.repositories.ReservationRepository;
import com.java.pe.edu.upc.center.shared.domain.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ReservationCommandServiceImpl implements ReservationCommandService {
    private final ReservationRepository reservationRepository;
    private final ExternalCaregiverService externalCaregiverService;

    public ReservationCommandServiceImpl(ReservationRepository reservationRepository, ExternalCaregiverService externalCaregiverService) {
        this.reservationRepository = reservationRepository;
        this.externalCaregiverService = externalCaregiverService;
    }

    public Reservation handle(CreateReservationCommand command) {
//        var isCaregiverExists = externalCaregiverService.checkCaregiverExists(command.caregiverId());
//        if (!isCaregiverExists) {
//            throw new UserNotFoundException(command.caregiverId());
//        }
        Reservation reservation = new Reservation(command);
        return reservationRepository.save(reservation);
    }

    public Reservation handle(UpdateReservationStatusCommand command) {
        Reservation reservation = reservationRepository.findById(command.reservationId())
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));

        reservation.setStatus(ReservationStatus.valueOf(command.status()));
        return reservationRepository.save(reservation);
    }

    public Reservation findById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }
}

