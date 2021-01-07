package company.services;


import company.model.Ride;
import company.persistance.RepositoryException;
import company.persistance.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/company/rides")
public class RideRESTController {
    @Autowired
    private RideRepository ride_repo;


    @RequestMapping(method = RequestMethod.POST)
    public Ride createRide(@RequestBody Ride ride) {
        ride_repo.save(ride);
        return ride;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Ride updateRide(@RequestBody Ride ride,@PathVariable int id) {
        ride.setID(id);
        ride_repo.update(ride);
        return ride;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteRide(@PathVariable int id) {
        try{
            ride_repo.delete(id);
            return new ResponseEntity<Ride>(HttpStatus.OK);
        }catch (RepositoryException re){
            return new ResponseEntity<>(re.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getRideById(@PathVariable int id) {
        try {
            Ride ride = ride_repo.findOne(id);
            return new ResponseEntity<>(ride, HttpStatus.OK);
        } catch (RepositoryException re) {
            return new ResponseEntity<>(re.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Ride> getAllRides() {
        List<Ride> rides = new ArrayList<>();
        ride_repo.findAll().forEach(x -> rides.add(x));
        return rides;
    }

    @ExceptionHandler(RepositoryException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String userError(RepositoryException e) {
        return e.getMessage();
    }
}
