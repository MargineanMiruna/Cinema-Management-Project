package Domain;

import java.time.LocalDate;
import java.util.List;

public class BasicMembership extends Membership{
    int customerId;
    LocalDate startDate;
    LocalDate endDate;
    List<Booking> bookings;
    double price = 50;
    double discount = 10;

    public BasicMembership(int customerId, LocalDate startDate, LocalDate endDate, List<Booking> bookings) {
        super(customerId, startDate, endDate);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public double offerDiscount(double price) {
        return price - price * (discount / 100);
    }
}
