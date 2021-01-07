package company.server;

import company.persistance.*;
import company.services.IServer;
import company.model.*;
import company.services.*;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements IServer.Iface {
    private int clientPort = 9091;
    private IUserRepository user_repo;
    private IRideRepository ride_repo;
    private IClientRepository client_repo;
    private IBookingRepository booking_repo;
    private Map<String, Integer> loggedUsers;

    public Server(IUserRepository user_repo, IRideRepository ride_repo, IClientRepository client_repo, IBookingRepository booking_repo) {
        this.user_repo = user_repo;
        this.ride_repo = ride_repo;
        this.client_repo = client_repo;
        this.booking_repo = booking_repo;
        loggedUsers = new ConcurrentHashMap<>();
    }

    @Override
    public int login(User u) throws AppException {
        User user = user_repo.findOne(u);
        if (user == null)
            throw new AppException("Date invalide!");
        loggedUsers.put(u.getID(), clientPort);
        int cp = clientPort;
        clientPort += 1;
        return cp;
    }

    @Override
    public void logout(User u) {
        loggedUsers.remove(u.getID());
    }

    @Override
    public synchronized List<Ride> findAllRides() throws AppException {
        try {
            List<Ride> rides = new ArrayList<>();
            ride_repo.findAll().forEach(x -> rides.add(x));
            return rides;
        } catch (RepositoryException e) {
            throw new AppException(e.getMessage());
        }
    }

    @Override
    public synchronized String bookPlaces(Ride ride, Clientj c, int nrplaces) throws AppException {
        try {
            ride = ride_repo.findOneby_Destination_Date_Hour(ride.getDestination(), ride.getDate(), ride.getHour());
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
            Clientj cl = client_repo.findLastAdded();
            Booking booking = new Booking(cl, ride, nrplaces, places);
            booking_repo.save(booking);
            notifyObservers();
            return places;
        } catch (RepositoryException e) {
            throw new AppException(e.getMessage());
        } catch (ValidationException e) {
            throw new AppException(e.getMessage());
        }
    }

    @Override
    public synchronized List<RBooking> findAllRBookings(String dest, String date, String hour) {
        List<RBooking> rb = new ArrayList<>();
        booking_repo.findBookingsByDestDateHour(dest, date, hour).forEach(x -> rb.add(x));
        return rb;
    }

    private synchronized void notifyObservers() {
        ExecutorService executor = Executors.newFixedThreadPool(loggedUsers.size());
        loggedUsers.entrySet().forEach(x -> executor.execute(() -> {
            System.out.println("Notifying observer");
            try {
                TTransport transport = new TSocket("localhost", x.getValue());
                transport.open();

                TProtocol protocol = new TBinaryProtocol(transport);

                IObserver.Client client = new IObserver.Client(protocol);
                client.update();

            } catch (TException e) {
                e.printStackTrace();
            }
        }));
        executor.shutdown();
        System.out.println();
    }
}
