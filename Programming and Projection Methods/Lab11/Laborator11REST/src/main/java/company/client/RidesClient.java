package company.client;

import company.model.Ride;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Callable;

public class RidesClient {
    public static final String URL = "http://localhost:8080/company/rides";

    private RestTemplate restTemplate = new RestTemplate();

    private <T> T execute(Callable<T> callable) {
        try {
            return callable.call();
        } catch (ResourceAccessException | HttpClientErrorException e) { // server down, resource exception
            throw new ServiceException(e);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public Ride create(Ride ride) {

        return execute(() -> restTemplate.postForObject(URL, ride, Ride.class));
    }

    public Ride update(Ride ride) {
        return execute(() -> {
            restTemplate.put(String.format("%s/%s", URL, ride.getID()), ride);
            return null;
        });
    }

    public Ride delete(int id) {
        return execute(() -> {
            restTemplate.delete(String.format("%s/%s", URL, id));
            return null;
        });
    }

    public Ride getById(int id) {
        return execute(() -> restTemplate.getForObject(String.format("%s/%s", URL, id), Ride.class));
    }

    public Ride[] getAll() {
        return execute(() -> restTemplate.getForObject(URL, Ride[].class));
    }
}
