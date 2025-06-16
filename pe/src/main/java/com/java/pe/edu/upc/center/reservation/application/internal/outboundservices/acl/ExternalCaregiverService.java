package com.java.pe.edu.upc.center.reservation.application.internal.outboundservices.acl;



public interface ExternalCaregiverService {
    boolean checkCaregiverExists(Long caregiverId);
}