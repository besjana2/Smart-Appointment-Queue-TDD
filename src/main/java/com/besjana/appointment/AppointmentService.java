package com.besjana.appointment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.HashMap;
import java.util.Map;

/** In-memory appointment booking and queue management for the TDD coursework. */
public class AppointmentService {
    private static final DateTimeFormatter SLOT_FORMAT =
            DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm").withResolverStyle(ResolverStyle.STRICT);
    private static final int MAX_CUSTOMER_LENGTH = 50;
    private final Map<String, String> appointments = new HashMap<>();
    private final int maxQueueNumber;
    private int nextQueueNumber = 1;

    public AppointmentService() { this(100); }

    /** A configurable cap permits testing the final ticket without issuing 100 tickets. */
    public AppointmentService(int maxQueueNumber) {
        if (maxQueueNumber < 1) {
            throw new IllegalArgumentException("Queue limit must be at least 1");
        }
        this.maxQueueNumber = maxQueueNumber;
    }

    /** Book on a half-hour boundary during 08:00–17:30 (inclusive). */
    public boolean bookAppointment(String timeSlot, String customerName) {
        validateSlot(timeSlot);
        if (customerName == null || customerName.isBlank()
                || customerName.trim().length() < 2
                || customerName.trim().length() > MAX_CUSTOMER_LENGTH) {
            throw new IllegalArgumentException("Customer name must have 2 to 50 characters");
        }
        if (appointments.containsKey(timeSlot)) {
            return false;
        }
        appointments.put(timeSlot, customerName.trim());
        return true;
    }

    /** Cancel a known booking; returns false when the time slot is free. */
    public boolean cancelAppointment(String timeSlot) {
        validateSlot(timeSlot);
        return appointments.remove(timeSlot) != null;
    }

    /** Issue a unique ticket; refuse tickets after the configured queue limit. */
    public int issueQueueNumber() {
        if (nextQueueNumber > maxQueueNumber) {
            throw new IllegalStateException("Queue is full");
        }
        return nextQueueNumber++;
    }

    public boolean isSlotBooked(String timeSlot) {
        validateSlot(timeSlot);
        return appointments.containsKey(timeSlot);
    }

    public int getBookingCount() { return appointments.size(); }

    private static void validateSlot(String timeSlot) {
        if (timeSlot == null || timeSlot.isBlank()) {
            throw new IllegalArgumentException("Time slot is required");
        }
        final LocalDateTime value;
        try {
            value = LocalDateTime.parse(timeSlot, SLOT_FORMAT);
        } catch (DateTimeParseException ex) {
            throw new IllegalArgumentException("Time slot format must be yyyy-MM-dd HH:mm", ex);
        }
        int minutes = value.getHour() * 60 + value.getMinute();
        if (minutes < 8 * 60 || minutes > 17 * 60 + 30 || value.getMinute() % 30 != 0) {
            throw new IllegalArgumentException("Appointments must be at half-hour slots from 08:00 to 17:30");
        }
    }
}
