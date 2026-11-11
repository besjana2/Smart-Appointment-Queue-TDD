package com.besjana.appointment;

import java.util.HashMap;
import java.util.Map;

/** Minimal in-memory service for the first TDD assignment. */
public class AppointmentService {
    private final Map<String, String> appointments = new HashMap<>();
    private int nextQueueNumber = 1;

    /** Books an available time slot for a nonblank customer name. */
    public boolean bookAppointment(String timeSlot, String customerName) {
        if (timeSlot == null || timeSlot.isBlank() || customerName == null || customerName.isBlank()) {
            throw new IllegalArgumentException("Time slot and customer name are required");
        }
        if (appointments.containsKey(timeSlot)) return false;
        appointments.put(timeSlot, customerName);
        return true;
    }

    /** Removes a booked appointment; returns false when it does not exist. */
    public boolean cancelAppointment(String timeSlot) {
        if (timeSlot == null || timeSlot.isBlank()) {
            throw new IllegalArgumentException("Time slot is required");
        }
        return appointments.remove(timeSlot) != null;
    }

    /** Returns successive, unique queue numbers starting from 1. */
    public int issueQueueNumber() {
        return nextQueueNumber++;
    }
}
