package Service;

import Domain.*;
import Repo.InMemoryRepository;

import java.lang.reflect.Member;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CinemaService {
    InMemoryRepository<Customer> customerRepo = new InMemoryRepository<Customer>();
    InMemoryRepository<Staff> staffRepo = new InMemoryRepository<Staff>();
    InMemoryRepository<Movie> movieRepo = new InMemoryRepository<Movie>();
    InMemoryRepository<Showtime> showtimeRepo = new InMemoryRepository<Showtime>();
    InMemoryRepository<Screen> screenRepo = new InMemoryRepository<Screen>();
    InMemoryRepository<Seat> seatRepo = new InMemoryRepository<Seat>();
    InMemoryRepository<Booking> bookingRepo = new InMemoryRepository<Booking>();
    InMemoryRepository<Ticket> ticketRepo = new InMemoryRepository<Ticket>();
    InMemoryRepository<BasicMembership> basicMembershipRepo = new InMemoryRepository<BasicMembership>();
    InMemoryRepository<PremiumMembership> premiumMembershipRepo = new InMemoryRepository<PremiumMembership>();

    public CinemaService() {}

    public void addCustomer(String firstName, String lastName, String email, boolean underage) {
        Customer customer = new Customer(firstName, lastName, email, underage);
        customerRepo.add(customer);
    }

    public Customer getCustomer(int id) {
        return customerRepo.read(id);
    }

    public void updateCustomer(int id, String firstName, String lastName,String email, boolean underage) {
        Customer customer = new Customer(firstName, lastName, email, underage);
        customerRepo.update(id, customer);
    }

    public void addStaff(String firstName, String lastName, String email) {
        Staff staff = new Staff(firstName, lastName, email);
        staffRepo.add(staff);
    }

    public void updateStaff(int id, String firstName, String lastName, String email) {
        Staff staff = new Staff(firstName, lastName, email);
        staffRepo.update(id, staff);
    }

    public void addMovie(String title, boolean pg, String genre, LocalDate releaseDate) {
        Movie movie = new Movie(title, pg, genre, releaseDate);
        movieRepo.add(movie);
    }

    public Movie getMovie(int id) {
        return movieRepo.read(id);
    }

    public void updateMovie(int id, String title, boolean pg, String genre, LocalDate releaseDate) {
        Movie movie = new Movie(title, pg, genre, releaseDate);
        movieRepo.update(id, movie);
    }

    public void deleteMovie(int id) {
       movieRepo.delete(id);
    }

    public Integer findMovieIdByTitle(String title) {
        for (Map.Entry<Integer, Movie> entry : movieRepo.getAll().entrySet()) {
            if (entry.getValue().getTitle().equalsIgnoreCase(title)) {
                return entry.getKey();
            }
        }

        return null;
    }

    public void addShowtime(int screenId, int movieId, LocalDate date, LocalTime startTime, int duration) {
        Screen screen = getScreen(screenId);
        Showtime showtime = new Showtime(screenId, movieId, date,startTime, duration, screen.getSeats());
        showtimeRepo.add(showtime);
    }

    public Showtime getShowtime(int id) {
        return showtimeRepo.read(id);
    }

    public void updateShowtime(int id, int screenId, int movieId, LocalDate date, LocalTime startTime, int duration, List<Seat> seats) {
        Showtime showtime = new Showtime(screenId, movieId, date, startTime, duration, seats);
        showtimeRepo.update(id, showtime);
    }

    public void deleteShowtime(int id) {
        showtimeRepo.delete(id);
    }

    public void addScreen(int nrStandardSeats, int nrVipSeats, int nrPremiumSeats) {
        Screen screen = new Screen(nrStandardSeats, nrVipSeats, nrPremiumSeats);
        screenRepo.add(screen);

        for (int i = 1; i <= nrStandardSeats; i++) {
            seatRepo.add(new Seat(i, SeatType.standard));
        }
        for (int i = 1; i <= nrVipSeats; i++) {
            seatRepo.add(new Seat(i, SeatType.vip));
        }
        for( int i = 1; i <=nrPremiumSeats; i++) {
            seatRepo.add(new Seat(i, SeatType.premium));
        }
    }

    public Screen getScreen(int id) {
        return screenRepo.read(id);
    }

    public void updateScreen(int id, int nrStandardSeats, int nrVipSeats, int nrPremiumSeats) {
        Screen screen = new Screen(nrStandardSeats, nrVipSeats, nrPremiumSeats);
        screenRepo.update(id, screen);
    }

    public void deleteScreen(int id){
        screenRepo.delete(id);
    }

    public void addSeat(int seatNr,  SeatType type) {
        Seat seat = new Seat(seatNr,type);
        seatRepo.add(seat);
    }

    public Seat getSeat(int id) {
        return seatRepo.read(id);
    }

    public void updateSeat(int id, int seatNr,  SeatType type) {
        Seat seat = new Seat(seatNr, type);
        seatRepo.update(id, seat);
    }

    public Seat findSeatBySeatNr(int screenId, int seatNr) {
        Screen screen = screenRepo.read(screenId);

        for(Seat seat : screen.getSeats()) {
            if(seat.getSeatNr() == seatNr) {
                return seat;
            }
        }

        return null;
    }

    public int addBooking(int customerId, int showtimeId, LocalDate date, int nrOfCustomers) {
        Booking booking = new Booking(customerId, showtimeId, date, nrOfCustomers);
        return bookingRepo.add(booking);
    }

    public Booking getBooking(int id) {
        return bookingRepo.read(id);
    }

    public void updateBooking(int id, int customerId, int showtimeId, LocalDate date, int nrOfCustomers) {
        Booking booking = new Booking(customerId, showtimeId, date, nrOfCustomers);
        bookingRepo.update(id, booking);
    }

    public int addTicket(int bookingId, int screenId, int seatId, double price) {
        Ticket ticket = new Ticket(bookingId, screenId, seatId, price);
        return ticketRepo.add(ticket);
    }

    public Ticket getTicket(int id) {
        return ticketRepo.read(id);
    }

    public void updateTicket(int id, int bookingId, int screenId, int seatId, double price) {
        Ticket ticket = new Ticket(bookingId, screenId, seatId, price);
        ticketRepo.update(id, ticket);
    }

    public BasicMembership addBasicMembership(int customerId, LocalDate startDate, LocalDate endDate, List<Booking> bookings) {
        BasicMembership basicMembership = new BasicMembership(customerId, startDate,endDate, bookings);
        Customer customer = getCustomer(customerId);
        customer.setMembershipId(basicMembershipRepo.add(basicMembership));
        return basicMembership;
    }

    public BasicMembership getBasicMembership(int id) {
        return basicMembershipRepo.read(id);
    }

    public void updateBasicMembership(int id, int customerId, LocalDate startDate, LocalDate endDate, List<Booking> bookings) {
        BasicMembership basicMembership = new BasicMembership(customerId, startDate,endDate, bookings);
        basicMembershipRepo.update(id, basicMembership);
    }

    public PremiumMembership addPremiumMembership(int customerId, LocalDate startDate, LocalDate endDate, List<Booking> bookings) {
        PremiumMembership premiumMembership = new PremiumMembership(customerId, startDate,endDate, bookings);
        Customer customer = getCustomer(customerId);
        customer.setMembershipId(premiumMembershipRepo.add(premiumMembership));
        return premiumMembership;
    }

    public PremiumMembership getPremiumMembership(int id) {
        return premiumMembershipRepo.read(id);
    }

    public void updatePremiumMembership(int id, int customerId, LocalDate startDate, LocalDate endDate, List<Booking> bookings) {
        PremiumMembership premiumMembership = new PremiumMembership(customerId, startDate,endDate, bookings);
        premiumMembershipRepo.update(id, premiumMembership);
    }

    public int getMembershipType(int customerId) {
        Map<Integer, BasicMembership> basicMemberships = basicMembershipRepo.getAll();
        Map<Integer, PremiumMembership> premiumMemberships = premiumMembershipRepo.getAll();

        for(BasicMembership basicMembership : basicMemberships.values()) {
            if(basicMembership.getCustomerId() == customerId) {
                return 1;
            }
        }

        for(PremiumMembership premiumMembership : premiumMemberships.values()) {
            if(premiumMembership.getCustomerId() == customerId) {
                return 2;
            }
        }

        return 0;
    }

    public Map<Integer, Showtime> displayShowtimes(Customer customer) {
        Map<Integer, Showtime> unfilteredShowtimes = showtimeRepo.getAll();

        if(customer.getUnderaged()) {
            Map<Integer, Showtime> filteredShowtimes = new HashMap<>();
            for(Map.Entry<Integer, Showtime> entry : unfilteredShowtimes.entrySet()) {
                if(!movieRepo.read(entry.getValue().getMovieId()).getPg())
                    filteredShowtimes.put(entry.getKey(), entry.getValue());
            }
            return filteredShowtimes;
        }

        return unfilteredShowtimes;
    }

    public Customer findCustomerByEmail(String email){
        Map<Integer, Customer> customers = customerRepo.getAll();

        for(Map.Entry<Integer, Customer> entry : customers.entrySet()){
            if(entry.getValue().getEmail().equals(email))
                return entry.getValue();
        }

        return null;
    }

    public int getIdOfCustomer(Customer customer){
        Map<Integer, Customer> customers = customerRepo.getAll();

        for(Map.Entry<Integer, Customer> entry : customers.entrySet()){
            if(entry.getValue().equals(customer))
                return entry.getKey();
        }

        return 0;
    }

    public int getIdOfBooking(Booking booking) {
        Map<Integer, Booking> bookings = bookingRepo.getAll();

        for(Map.Entry<Integer, Booking> entry : bookings.entrySet()){
            if(entry.getValue().equals(bookings))
                return entry.getKey();
        }

        return 0;
    }

    public List<Ticket> bookingTickets(int bookingId) {
        Map<Integer, Ticket> tickets = ticketRepo.getAll();
        List<Ticket> bookingTickets = new ArrayList<>();

        for(Map.Entry<Integer, Ticket> entry : tickets.entrySet()){
            if(entry.getValue().getBookingId() == bookingId)
                bookingTickets.add(entry.getValue());
        }

        return bookingTickets;
    }

    public Staff findStaffByEmail(String email){
        Map<Integer, Staff> staffs = staffRepo.getAll();

        for(Map.Entry<Integer, Staff> entry : staffs.entrySet()){
            if(entry.getValue().getEmail().equals(email))
                return entry.getValue();
        }

        return null;
    }

    public double calculateDiscountedPrice(double price, Membership membership) {
        return membership.offerDiscount(price);
    }

}
