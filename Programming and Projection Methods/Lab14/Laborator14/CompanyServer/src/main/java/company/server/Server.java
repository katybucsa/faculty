package company.server;

import company.model.*;
import company.persistance.IBookingRepository;
import company.persistance.IClientRepository;
import company.persistance.IRideRepository;
import company.persistance.IUserRepository;
import company.services.AppException;
import company.services.INotificationService;
import company.services.INotificationSubscriber;
import company.services.IServer;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server implements IServer {
    private IUserRepository user_repo;
    private IRideRepository ride_repo;
    private IClientRepository client_repo;
    private IBookingRepository booking_repo;
    private Map<String, INotificationSubscriber> loggedUsers;
    private INotificationService notificationService;

    public Server(IUserRepository user_repo, IRideRepository ride_repo, IClientRepository client_repo, IBookingRepository booking_repo, INotificationService notificationService) {
        this.user_repo = user_repo;
        this.ride_repo = ride_repo;
        this.client_repo = client_repo;
        this.booking_repo = booking_repo;
        this.notificationService = notificationService;
        loggedUsers = new ConcurrentHashMap<>();
    }

    @Override
    public void login(User u, INotificationSubscriber o) {
        User user = user_repo.findOne(u);
        if (user == null)
            throw new AppException("Date invalide!");
        loggedUsers.put(u.getID(), o);
//        notificationService.clientLoggedIn(u);
    }

    @Override
    public void logout(User u, INotificationSubscriber o) {
        loggedUsers.remove(u.getID(), o);
    }


    @Override
    public synchronized Ride[] findAllRides() {
        List<Ride> rides = new ArrayList<>();
        ride_repo.findAll().forEach(x -> rides.add(x));
        return rides.toArray(new Ride[rides.size()]);
    }

    @Override
    public synchronized String bookPlaces(Ride ride, Client c, int nrplaces) {
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
        notificationService.placesBooked();
        return places;
    }

    @Override
    public synchronized RBooking[] findAllRBookings(String dest, String date, String hour) {
        List<RBooking> rb = new ArrayList<>();
        booking_repo.findBookingsByDestDateHour(dest, date, hour).forEach(x -> rb.add(x));
        return rb.toArray(new RBooking[rb.size()]);
    }
}
