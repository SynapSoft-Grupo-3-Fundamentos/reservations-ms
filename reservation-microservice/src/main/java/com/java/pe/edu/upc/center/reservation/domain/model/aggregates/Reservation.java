package com.java.pe.edu.upc.center.reservation.domain.model.aggregates;

import com.java.pe.edu.upc.center.reservation.domain.model.commands.CreateReservationCommand;
import com.java.pe.edu.upc.center.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.java.pe.edu.upc.center.reservation.domain.model.valueobjects.ReservationStatus;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@Table(name = "reservations")
public class Reservation extends AuditableAbstractAggregateRoot<Reservation> {

    @Column(nullable = false)
    private Long caregiverId;

    @Column(nullable = false)
    private Long tutorId;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @Column(nullable = false)
    private double totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservationStatus status = ReservationStatus.PENDING;

    public Reservation(CreateReservationCommand command) {
        this.caregiverId = command.caregiverId();
        this.tutorId = command.tutorId();
        this.startTime = command.startTime();
        this.endTime = command.endTime();
        this.totalAmount = command.totalAmount();
    }

    public Reservation() {}

}
