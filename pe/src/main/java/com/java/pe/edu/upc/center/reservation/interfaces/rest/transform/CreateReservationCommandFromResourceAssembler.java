package com.java.pe.edu.upc.center.reservation.interfaces.rest.transform;

import com.java.pe.edu.upc.center.reservation.domain.model.commands.CreateReservationCommand;
import com.java.pe.edu.upc.center.reservation.interfaces.rest.resources.CreateReservationResource;

public class CreateReservationCommandFromResourceAssembler {

    public static CreateReservationCommand toCommandFromResource(CreateReservationResource resource) {
        return new CreateReservationCommand(
                resource.caregiverId(),
                resource.tutorId(),
                resource.date(),
                resource.startTime(),
                resource.endTime(),
                resource.totalAmount()
        );
    }
}