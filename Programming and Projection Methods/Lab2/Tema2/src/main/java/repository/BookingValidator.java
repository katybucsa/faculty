package repository;

import domain.Booking;


public class BookingValidator implements Validator<Booking> {
    @Override
    public void validate(Booking entity) {
        if(entity.getNr_places_wanted()<1 || entity.getNr_places_wanted()>18)
            throw new ValidationException("Numar de locuri invalid!\n");
    }
}
