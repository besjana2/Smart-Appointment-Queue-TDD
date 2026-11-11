package com.besjana.appointment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppointmentServiceTest {
    private AppointmentService service;

    @BeforeEach
    void setUp() { service = new AppointmentService(); }

    // Functionality 1: booking
    @Test
    void bookAppointment_acceptsFreeSlot() {
        assertTrue(service.bookAppointment("2026-11-12 10:00", "Besjana"));
    }

    @Test
    void bookAppointment_rejectsAlreadyBookedSlot() {
        assertTrue(service.bookAppointment("2026-11-12 10:00", "Besjana"));
        assertFalse(service.bookAppointment("2026-11-12 10:00", "Arta"));
    }

    @Test
    void bookAppointment_rejectsBlankCustomer() {
        assertThrows(IllegalArgumentException.class, () -> service.bookAppointment("2026-11-12 11:00", " "));
    }

    // Functionality 2: cancellation
    @Test
    void cancelAppointment_removesExistingBooking() {
        service.bookAppointment("2026-11-12 10:00", "Besjana");
        assertTrue(service.cancelAppointment("2026-11-12 10:00"));
        assertTrue(service.bookAppointment("2026-11-12 10:00", "Arta"));
    }

    @Test
    void cancelAppointment_returnsFalseForUnknownSlot() {
        assertFalse(service.cancelAppointment("2026-11-12 15:00"));
    }

    // Functionality 3: queue numbers
    @Test
    void issueQueueNumber_startsAtOne() {
        assertEquals(1, service.issueQueueNumber());
    }

    @Test
    void issueQueueNumber_increments() {
        assertEquals(1, service.issueQueueNumber());
        assertEquals(2, service.issueQueueNumber());
        assertEquals(3, service.issueQueueNumber());
    }
}
