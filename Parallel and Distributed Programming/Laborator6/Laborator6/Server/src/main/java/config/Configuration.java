package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Created on: 19-Dec-19, 22:56
 *
 * @author: Katy Bucșa
 */

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    @Primary
    public Properties jdbcProps() {
        Properties serverProps = new Properties();
        try {
            serverProps.load(new FileReader("C:\\Users\\Katy\\Documents\\A3S1\\PPD\\Laborator\\Laborator6\\Server\\src\\main\\resources\\db.config"));
            serverProps.list(System.out);
        } catch (IOException e) {
            System.out.println("Cannot find db.config " + e);
        }
        return serverProps;
    }
}