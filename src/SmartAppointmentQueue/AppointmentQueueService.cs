namespace SmartAppointmentQueue;

public sealed record Appointment(Guid Id, string CustomerName, DateTime StartTime);

public sealed class AppointmentQueueService
{
    private readonly List<Appointment> _appointments = new();
    private int _nextTicket = 1;

    public Appointment BookAppointment(string customerName, DateTime startTime)
    {
        if (string.IsNullOrWhiteSpace(customerName))
            throw new ArgumentException("Customer name is required.", nameof(customerName));

        if (_appointments.Any(a => a.StartTime == startTime))
            throw new InvalidOperationException("This time slot is already booked.");

        var appointment = new Appointment(Guid.NewGuid(), customerName.Trim(), startTime);
        _appointments.Add(appointment);
        return appointment;
    }

    public bool CancelAppointment(Guid appointmentId)
    {
        var existing = _appointments.FirstOrDefault(a => a.Id == appointmentId);
        if (existing is null) return false;
        _appointments.Remove(existing);
        return true;
    }

    public int TakeQueueTicket(string customerName)
    {
        if (string.IsNullOrWhiteSpace(customerName))
            throw new ArgumentException("Customer name is required.", nameof(customerName));
        return _nextTicket++;
    }
}
