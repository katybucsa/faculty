package utils;

import controller.Controller;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import repository.*;
import service.Service;
import view.AppView;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@Configuration
public class FirmaDeTransportConfig {
    @Bean
    @Primary
    public Properties jdbcProps() {
        Properties serverProps = new Properties();
        try {
            serverProps.load(new FileReader("D:\\A2S2\\MPP\\Lab\\Lab2\\Tema2\\db.config"));
            //System.out.println("Properties set. ");
            serverProps.list(System.out);
        } catch (IOException e) {
            System.out.println("Cannot find db.config " + e);
        }
        return serverProps;
    }

    @Bean(name = "rideval")
    public RideValidator rideValidator() {
        return new RideValidator();
    }

    @Bean(name = "riderepo")
    public RideRepository rideRepository(Properties jdbcProps, RideValidator rideval) {
        return new RideRepository(jdbcProps, rideval);
    }

    @Bean(name = "clientval")
    public ClientValidator clientValidator() {
        return new ClientValidator();
    }

    @Bean(name = "clientrepo")
    public ClientRepository clientRepository(Properties jdbcProps, ClientValidator clientval) {
        return new ClientRepository(jdbcProps, clientval);
    }

    @Bean(name = "bookingval")
    public BookingValidator bookingValidator() {
        return new BookingValidator();
    }

    @Bean(name = "bookingrepo")
    public BookingRepository bookingRepository(Properties jdbcProps, BookingValidator bookingval, RideRepository riderepo, ClientRepository clientrepo) {
        return new BookingRepository(jdbcProps, bookingval, riderepo, clientrepo);
    }

    @Bean(name = "uservalidator")
    public UserValidator userValidator() {
        return new UserValidator();
    }

    @Bean(name = "userrepo")
    public UserRepository userRepository(Properties jdbcProps, UserValidator userval) {
        return new UserRepository(jdbcProps, userval);
    }

    @Bean(name = "serv")
    public Service service(UserRepository urepo, RideRepository rrepo, ClientRepository crepo, BookingRepository brepo) {
        return new Service(urepo, rrepo, crepo, brepo);
    }

    @Bean(name = "controller")
    public Controller controller(Service s) {
        return new Controller(s);
    }

    /*@Bean(name="appview")
    public AppView appView(Controller c){
        return new AppView(c);
    }*/
}
