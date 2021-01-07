package company;


import company.persistance.DBConnection;
import company.persistance.RideRepository;
import company.persistance.RideValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@Configuration
public class AppConfig {
    @Bean
    @Primary
    public Properties jdbcProps() {
        Properties serverProps = new Properties();
        try {
            serverProps.load(new FileReader("C:\\Users\\Katy\\Documents\\A2S2\\MPP\\Lab\\Lab12\\Laborator12\\CompanyRESTServices\\src\\main\\resources\\db.config"));
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

    @Bean(name = "dbConnection")
    public DBConnection dbConnection() {
        return new DBConnection(jdbcProps());
    }

    @Bean(name = "riderepo")
    public RideRepository rideRepository() {
        return new RideRepository();
    }
}
