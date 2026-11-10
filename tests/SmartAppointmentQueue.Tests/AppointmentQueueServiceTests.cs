using SmartAppointmentQueue;
using Xunit;

namespace SmartAppointmentQueue.Tests;

public class AppointmentQueueServiceTests
{
    private static readonly DateTime TomorrowAtNine = new(2026, 11, 11, 9, 0, 0);

    [Fact]
    public void BookAppointment_FreeSlot_ReturnsAppointment()
    {
        var service = new AppointmentQueueService();
        var appointment = service.BookAppointment("Besjana", TomorrowAtNine);
        Assert.Equal("Besjana", appointment.CustomerName);
        Assert.Equal(TomorrowAtNine, appointment.StartTime);
    }

    [Fact]
    public void BookAppointment_DuplicateSlot_ThrowsInvalidOperationException()
    {
        var service = new AppointmentQueueService();
        service.BookAppointment("Besjana", TomorrowAtNine);
        Assert.Throws<InvalidOperationException>(() =>
            service.BookAppointment("Klienti B", TomorrowAtNine));
    }

    [Fact]
    public void CancelAppointment_ExistingAppointment_ReturnsTrue()
    {
        var service = new AppointmentQueueService();
        var appointment = service.BookAppointment("Besjana", TomorrowAtNine);
        Assert.True(service.CancelAppointment(appointment.Id));
    }

    [Fact]
    public void CancelAppointment_UnknownId_ReturnsFalse()
    {
        var service = new AppointmentQueueService();
        Assert.False(service.CancelAppointment(Guid.NewGuid()));
    }

    [Fact]
    public void TakeQueueTicket_TwoCustomers_ReturnsConsecutiveNumbers()
    {
        var service = new AppointmentQueueService();
        Assert.Equal(1, service.TakeQueueTicket("Besjana"));
        Assert.Equal(2, service.TakeQueueTicket("Klienti B"));
    }

    [Fact]
    public void TakeQueueTicket_BlankName_ThrowsArgumentException()
    {
        var service = new AppointmentQueueService();
        Assert.Throws<ArgumentException>(() => service.TakeQueueTicket("  "));
    }
}
