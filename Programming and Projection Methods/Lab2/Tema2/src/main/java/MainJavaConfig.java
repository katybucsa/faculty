import domain.Ride;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import utils.FirmaDeTransportConfig;
import repository.RideRepository;

public class MainJavaConfig {
    public static void main(String[] args) {
        Ride ride = new Ride(3,"Madrid", "25-06-2019", "14:00");
        ApplicationContext springJavaConfig=new AnnotationConfigApplicationContext(FirmaDeTransportConfig.class);
        RideRepository repo=springJavaConfig.getBean(RideRepository.class);
        repo.save(ride);
        //repo.delete(3);
    }
}
