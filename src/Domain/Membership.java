package Domain;

import java.time.LocalDate;
import java.util.List;

public abstract class Membership {
    int customerId;
    LocalDate startDate;
    LocalDate endDate;
    List<Booking> bookings;
    double discount;

    public Membership(int customerId, LocalDate startDate, LocalDate endDate) {
        this.customerId = customerId;
        this.startDate = LocalDate.now();
        this.endDate = endDate;
    }

    public int getCustomerId() {
        return customerId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) { this.bookings = bookings; }

    public abstract double offerDiscount(double price);
}
