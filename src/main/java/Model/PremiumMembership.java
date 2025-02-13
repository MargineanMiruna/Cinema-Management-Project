package Model;

import java.time.LocalDate;

/**
 * The premium membership class represents a basic membership with a fixed price and discount rate.
 * It provides basic discounts to customers based on membership status.
 */
public class PremiumMembership extends Membership {
    private final double price = 100;
    private final double discount = 20;

    /**
     * Constructs a PremiumMembership with the specified customer ID, start date, and end date.
     *
     * @param id The unique identifier of the membership
     * @param customerId The ID of the customer associated with this membership
     * @param startDate The start date of the membership
     * @param endDate The end date of the membership, usually 30 days after the start date
     */
    public PremiumMembership(int id, int customerId, LocalDate startDate, LocalDate endDate) {
        super(id, customerId, startDate, endDate);
    }

    /**
     * Gets the fixed price of the premium membership.
     *
     * @return the price of the membership as a double.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Applies the discount to a given price, reducing it by a specified percentage.
     *
     * @param price the original price on which the discount is applied
     * @return the discounted price as a double.
     */
    @Override
    public double offerDiscount(double price) {
        return price - price * (discount / 100);
    }

    /**
     * Convert the premium membership to a CSV format string.
     */
    @Override
    public String toCSV() {
        return String.join(",", String.valueOf(id), String.valueOf(customerId), String.valueOf(startDate), String.valueOf(endDate));
    }

    /**
     * Return the header for a CSV file representing premium membership.
     */
    @Override
    public String[] getHeader() {
        return new String[]{"id", "customerId", "startDate", "endDate"};
    }

    /**
     * Create a Premium Membership object from a CSV line.
     */
    public static PremiumMembership fromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        return new PremiumMembership(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), LocalDate.parse(parts[2]), LocalDate.parse(parts[3]));
    }
}
