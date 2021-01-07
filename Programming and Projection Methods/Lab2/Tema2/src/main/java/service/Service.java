package service;

import domain.Booking;
import domain.Client;
import domain.Ride;
import domain.User;
import repository.*;
import utils.*;

import java.util.ArrayList;
import java.util.List;

public class Service implements Observable<AppEvent> {
    private UserRepositoryInterface user_repo;
    private RideRepositoryInterface ride_repo;
    private ClientRepositoryInterface client_repo;
    private BookingRepositoryInterface booking_repo;
    private List<Observer<AppEvent>> observers = new ArrayList<>();

    public Service(UserRepository user_repo, RideRepository ride_repo, ClientRepository client_repo, BookingRepository booking_repo) {
        this.user_repo = user_repo;
        this.ride_repo = ride_repo;
        this.client_repo = client_repo;
        this.booking_repo = booking_repo;
    }

    public User findUser(String username, String password) {
        return user_repo.findOne(username, password);
    }

    public Iterable<RBooking> findAllRBookings(String dest, String date, String hour) {
        return booking_repo.findBookingsByDestDateHour(dest, date, hour);
    }

    public String bookPlaces(Ride ride, Client c, int nrplaces) {
        Ride old = ride;
        client_repo.save(c);
        String places = "";
        int x = nrplaces;
        String p = ride.getPlaces();
        for (int i = 1; i <= 18; i++) {
            if (ride.getPlaces().charAt(i) == '0') {
                if (x == 1)
                    places += i;
                else
                    places += i + ",";
                p = p.substring(0, i) + '1' + p.substring(i + 1);
                x--;
                if (x == 0)
                    break;
            }
        }
        ride.setPlaces(p);
        ride_repo.update(ride);
        Client cl = client_repo.findLastAdded();
        Booking booking = new Booking(cl, ride, nrplaces, places);
        booking_repo.save(booking);
        notifyObservers(new AppEvent(Action.UPDATE, ride, old));
        //notifyObservers(new AppEvent(Action.ADD,booking));
        return places;
    }

    public Iterable<Ride> findAllRides() {
        return ride_repo.findAll();
    }

    public void addObserver(Observer<AppEvent> o) {
        observers.add(o);
    }

    public void removeObserver(Observer<AppEvent> o) {
        observers.remove(o);
    }

    public void notifyObservers(AppEvent e) {
        observers.stream().forEach(x -> x.update(e));
    }
}
