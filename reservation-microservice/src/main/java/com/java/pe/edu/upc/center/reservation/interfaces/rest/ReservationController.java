package com.java.pe.edu.upc.center.reservation.interfaces.rest;

import com.java.pe.edu.upc.center.reservation.domain.model.aggregates.Reservation;
import com.java.pe.edu.upc.center.reservation.domain.model.commands.UpdateReservationStatusCommand;
import com.java.pe.edu.upc.center.reservation.domain.model.queries.GetReservationByCaregiverQuery;
import com.java.pe.edu.upc.center.reservation.domain.model.queries.GetReservationByTutorQuery;
import com.java.pe.edu.upc.center.reservation.domain.services.ReservationCommandService;
import com.java.pe.edu.upc.center.reservation.domain.services.ReservationQueryService;
import com.java.pe.edu.upc.center.reservation.infrastructure.external.googlecalendar.GoogleCalendarService;
import com.java.pe.edu.upc.center.reservation.interfaces.rest.resources.CreateReservationResource;
import com.java.pe.edu.upc.center.reservation.interfaces.rest.resources.ReservationResource;
import com.java.pe.edu.upc.center.reservation.interfaces.rest.transform.CreateReservationCommandFromResourceAssembler;
import com.java.pe.edu.upc.center.reservation.interfaces.rest.transform.ReservationResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/reservations")
@Tag(name="Reservations", description = "Reservation Management Endpoints")
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH})
public class ReservationController {
    private final ReservationCommandService reservationCommandService;
    private final ReservationQueryService reservationQueryService;
    private final GoogleCalendarService calendarService;

    public ReservationController(ReservationCommandService reservationCommandService, ReservationQueryService reservationQueryService, GoogleCalendarService calendarService) {
        this.reservationCommandService = reservationCommandService;
        this.reservationQueryService = reservationQueryService;
        this.calendarService = calendarService;
    }

    @PostMapping
    public ResponseEntity<ReservationResource> createReservation(@RequestBody CreateReservationResource resource) throws Exception {
        var createReservationCommand = CreateReservationCommandFromResourceAssembler.toCommandFromResource(resource);

        Reservation reservation = reservationCommandService.handle(createReservationCommand);
        calendarService.addEventToCalendar(resource.startTime(), resource.endTime());

        return new ResponseEntity<>(ReservationResourceFromEntityAssembler.toResourceFromEntity(reservation), HttpStatus.CREATED);
    }

    @GetMapping("/caregiver/{caregiverId}")
    public ResponseEntity<List<ReservationResource>> getReservationByCaregiverId(@PathVariable Long caregiverId) {
        GetReservationByCaregiverQuery getReservationByCaregiverQuery = new GetReservationByCaregiverQuery(caregiverId);

        List<Reservation> reservations = reservationQueryService.handle(getReservationByCaregiverQuery);

        return new ResponseEntity<>(reservations.stream().map(ReservationResourceFromEntityAssembler::toResourceFromEntity).toList(), HttpStatus.OK);
    }

    @GetMapping("/tutor/{tutorId}")
    public ResponseEntity<List<ReservationResource>> getReservationByTutorId(@PathVariable Long tutorId) {
        GetReservationByTutorQuery getReservationByTutorQuery = new GetReservationByTutorQuery(tutorId);

        List<Reservation> reservations = reservationQueryService.handle(getReservationByTutorQuery);

        return new ResponseEntity<>(reservations.stream().map(ReservationResourceFromEntityAssembler::toResourceFromEntity).toList(), HttpStatus.OK);
    }

    @PatchMapping("/{reservationId}/status/{status}")
    public ResponseEntity<ReservationResource> updateReservationStatus(@PathVariable Long reservationId, @PathVariable String status) {
        UpdateReservationStatusCommand updateReservationStatusCommand = new UpdateReservationStatusCommand(reservationId, status);
        Reservation reservation = reservationCommandService.handle(updateReservationStatusCommand);
        return new ResponseEntity<>(ReservationResourceFromEntityAssembler.toResourceFromEntity(reservation), HttpStatus.OK);
    }
}
