package com.java.pe.edu.upc.center.reservation.infrastructure.external.googlecalendar;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.*;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class GoogleCalendarService {

    private static final String APPLICATION_NAME = "CaregiverSchedule";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String CALENDAR_ID = "soft.help.2024@gmail.com";

    public String addEventToCalendar(LocalDateTime startTime, LocalDateTime endTime) throws Exception {
        InputStream in = GoogleCalendarService.class.getResourceAsStream("/service-account.json");
        if (in == null) {
            throw new RuntimeException("No se encontró el archivo service_account.json");
        }

        GoogleCredentials credentials = GoogleCredentials.fromStream(in)
                .createScoped(List.of("https://www.googleapis.com/auth/calendar"));

        HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(credentials);

        Calendar service = new Calendar.Builder(GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, requestInitializer)
                .setApplicationName(APPLICATION_NAME)
                .build();

        Event event = new Event()
                .setSummary("Agende de solicutud de reserva")
                .setLocation("Lima");

        EventDateTime start = new EventDateTime()
                .setDateTime(new com.google.api.client.util.DateTime(startTime.toString()))
                .setTimeZone("America/Lima");
        event.setStart(start);

        EventDateTime end = new EventDateTime()
                .setDateTime(new com.google.api.client.util.DateTime(endTime.toString()))
                .setTimeZone("America/Lima");
        event.setEnd(end);

        event.setReminders(new Event.Reminders()
                .setUseDefault(false)
                .setOverrides(List.of(
                        new EventReminder().setMethod("email").setMinutes(24 * 60),
                        new EventReminder().setMethod("popup").setMinutes(10))));

        Event createdEvent = service.events().insert(CALENDAR_ID, event).execute();

        return "Evento creado: " + createdEvent.getHtmlLink();
    }
}

