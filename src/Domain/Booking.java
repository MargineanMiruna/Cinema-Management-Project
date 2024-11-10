package Domain;

import java.time.LocalDate;
import java.util.List;

public class Booking {
    private int customerId;
    private int showtimeId;
    private LocalDate date;
    private int nrOfCustomers;
    private List<Integer> tickets;

    public Booking(int customerId, int showtimeId, LocalDate date, int nrOfCustomers) {
        this.customerId = customerId;
        this.showtimeId = showtimeId;
        this.date = LocalDate.now();
        this.nrOfCustomers = nrOfCustomers;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setNrOfCustomers(int nrOfCustomers) {
        this.nrOfCustomers = nrOfCustomers;
    }

    public void setTickets(List<Integer> tickets) {
        this.tickets = tickets;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(int showtimeId) {
        this.showtimeId = showtimeId;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getNrOfCustomers() {
        return nrOfCustomers;
    }

    public List<Integer> getTickets() {
        return tickets;
    }
}
