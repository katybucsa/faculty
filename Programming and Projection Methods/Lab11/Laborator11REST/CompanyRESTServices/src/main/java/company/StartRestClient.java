package company;

import company.client.RidesClient;
import company.client.ServiceException;
import company.model.Ride;
import org.springframework.web.client.RestClientException;

public class StartRestClient {
    private final static RidesClient ridesClient = new RidesClient();
    public static final String URL = "http://localhost:8080/company/rides";

    public static void main(String[] args) {
        Ride ride = new Ride(4, "Suceava", "2019-05-28", "12:48");
        try {
//            //create
//            show(() -> System.out.println("Ride added " + ridesClient.create(ride)));

//            //update
//            show(() -> System.out.println("Updating  ride ..." + ride));
//            ride.setDestination("Brasov");
//            show(() -> System.out.println("Ride update result: " + ridesClient.update(ride)));
//
//            //getById
//            show(()-> System.out.println("Ride with id 2: "+ ridesClient.getById(2)));

//            //getAll
//            show(() -> {
//                Ride[] res = ridesClient.getAll();
//                for (Ride r : res) {
//                    System.out.println(r.toString());
//                }
//            });
//
//            //delete
//            System.out.println("Deleting ride with id 4");
//            show(()-> System.out.println("Ride deleted"+ ridesClient.delete(4)));
        } catch (RestClientException e) {
            System.out.println("Exception ... " + e);
        }

    }


    private static void show(Runnable task) {
        try {
            task.run();
        } catch (ServiceException e) {
            System.out.println("Service exception" + e);
        }
    }
}
