package com.java.pe.edu.upc.center.reservation.domain.model.commands;


import java.time.LocalDateTime;

public record CreateReservationCommand(Long caregiverId, Long tutorId, LocalDateTime date, String startTime, String endTime, Double totalAmount){}


