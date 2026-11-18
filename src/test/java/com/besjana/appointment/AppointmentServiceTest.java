package com.besjana.appointment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class AppointmentServiceTest {
    private AppointmentService service;
    private static final String SLOT = "2026-11-19 10:00";

    @BeforeEach
    void setUp() { service = new AppointmentService(); }

    @Test
    void booking_rejectsSingleLetterNameOutsideTheBasicMultilingualPlane() {
        String name = new String(Character.toChars(0x10400));

        assertThrows(IllegalArgumentException.class, () -> service.bookAppointment(SLOT, name));
        assertFalse(service.isSlotBooked(SLOT));
    }

    @ParameterizedTest
    @ValueSource(strings = {"+10000-11-19 10:00", "-0001-11-19 10:00"})
    void booking_rejectsYearsOutsideTheFourDigitSlotFormat(String slot) {
        assertThrows(IllegalArgumentException.class, () -> service.bookAppointment(slot, "Besjana"));
        assertEquals(0, service.getBookingCount());
    }

    @Test
    void booking_rejectsSingleLetterNameSurroundedByUnicodeSpaces() {
        String name = "\u2003A\u2003";

        assertThrows(IllegalArgumentException.class, () -> service.bookAppointment(SLOT, name));
        assertFalse(service.isSlotBooked(SLOT));
    }

    @Test
    void booking_usesTheSameSlotWithOrWithoutSurroundingSpaces() {
        String paddedSlot = " " + SLOT + " ";

        assertTrue(service.bookAppointment(paddedSlot, "Besjana"));
        assertTrue(service.isSlotBooked(SLOT));
        assertTrue(service.isSlotBooked(paddedSlot));
        assertFalse(service.bookAppointment(SLOT, "BesjanaK"));
        assertTrue(service.cancelAppointment(paddedSlot));
        assertFalse(service.isSlotBooked(SLOT));
    }

    // Positive testing: valid booking and the earliest/latest allowed slots.
    @Test
    void booking_succeedsForAvailableSlot() {
        assertTrue(service.bookAppointment(SLOT, "Besjana"));
        assertTrue(service.isSlotBooked(SLOT));
        assertEquals(1, service.getBookingCount());
    }

    @ParameterizedTest
    @ValueSource(strings = {"2026-11-19 08:00", "2026-11-19 17:30"})
    void booking_acceptsOpeningAndClosingBoundary(String slot) {
        assertTrue(service.bookAppointment(slot, "Besjana"));
    }

    @Test
    void booking_rejectsDoubleBookingAndPreservesExistingBooking() {
        assertTrue(service.bookAppointment(SLOT, "Besjana"));
        assertFalse(service.bookAppointment(SLOT, "BesjanaK"));
        assertEquals(1, service.getBookingCount());
    }

    // Negative, boundary, and parameterized tests.
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "A", "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXY"})
    void booking_rejectsInvalidCustomerNames(String name) {
        assertThrows(IllegalArgumentException.class, () -> service.bookAppointment(SLOT, name));
    }

    @Test
    void booking_rejectsNullCustomer() {
        assertThrows(IllegalArgumentException.class, () -> service.bookAppointment(SLOT, null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"2026-11-19 07:30", "2026-11-19 18:00", "2026-11-19 08:15", "invalid", "2026-02-30 10:00"})
    void booking_rejectsInvalidSlots(String slot) {
        assertThrows(IllegalArgumentException.class, () -> service.bookAppointment(slot, "Besjana"));
    }
    @Test
    void booking_rejectsNullAndBlankSlots() {
        assertThrows(IllegalArgumentException.class, () -> service.bookAppointment(null, "Besjana"));
        assertThrows(IllegalArgumentException.class, () -> service.bookAppointment(" ", "Besjana"));
    }

    @ParameterizedTest
    @CsvSource({"AB, true", "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWX, true"})
    void booking_acceptsMinimumAndMaximumNameLength(String name, boolean expected) {
        assertEquals(expected, service.bookAppointment(SLOT, name));
    }

    @Test
    void cancellation_removesExistingBookingAndFreesSlot() {
        service.bookAppointment(SLOT, "Besjana");
        assertTrue(service.cancelAppointment(SLOT));
        assertFalse(service.isSlotBooked(SLOT));
        assertEquals(0, service.getBookingCount());
        assertTrue(service.bookAppointment(SLOT, "BesjanaK"));
    }

    @Test
    void cancellation_returnsFalseWhenBookingDoesNotExist() {
        assertFalse(service.cancelAppointment(SLOT));
    }

    @Test
    void cancellation_rejectsInvalidSlot() {
        assertThrows(IllegalArgumentException.class, () -> service.cancelAppointment("bad input"));
    }

    @Test
    void query_rejectsInvalidSlot() {
        assertThrows(IllegalArgumentException.class, () -> service.isSlotBooked(null));
    }

    @Test
    void queue_startsAtOneAndIncrements() {
        assertEquals(1, service.issueQueueNumber());
        assertEquals(2, service.issueQueueNumber());
        assertEquals(3, service.issueQueueNumber());
    }

    @Test
    void queue_acceptsFinalTicketAndRejectsBeyondMaximum() {
        AppointmentService smallQueue = new AppointmentService(2);
        assertEquals(1, smallQueue.issueQueueNumber());
        assertEquals(2, smallQueue.issueQueueNumber());
        assertThrows(IllegalStateException.class, smallQueue::issueQueueNumber);
    }

    @Test
    void queue_rejectsZeroAndNegativeCapacity() {
        assertThrows(IllegalArgumentException.class, () -> new AppointmentService(0));
        assertThrows(IllegalArgumentException.class, () -> new AppointmentService(-1));
    }
}
