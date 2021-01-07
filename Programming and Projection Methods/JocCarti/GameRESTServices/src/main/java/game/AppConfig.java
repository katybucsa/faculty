package game;

import game.persistance.DBConnection;
import game.persistance.GamePlayerRepository;
import game.persistance.GameRepository;
import game.persistance.GameValidator;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@Configuration
public class AppConfig {

    private final Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty(
                "hibernate.hbm2ddl.auto", "validate");
        return hibernateProperties;
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.sqlite.JDBC");
        dataSource.setUrl("jdbc:sqlite:C:/Users/Katy/Documents/A2S2/MPP/Lab/Database/Carti.db");

        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("game.model");
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

    @Bean
    @Primary
    public Properties jdbcProps() {
        Properties serverProps = new Properties();
        try {
            serverProps.load(new FileReader("C:\\Users\\Katy\\Documents\\A2S2\\MPP\\Lab\\JocCarti\\GameRESTServices\\src\\main\\resources\\db.config"));
            serverProps.list(System.out);
        } catch (IOException e) {
            System.out.println("Cannot find db.config " + e);
        }
        return serverProps;
    }

    @Bean(name = "gameval")
    public GameValidator rideValidator() {
        return new GameValidator();
    }

    @Bean(name = "dbConnection")
    public DBConnection dbConnection() {
        return new DBConnection(jdbcProps());
    }

    @Bean(name = "gamerepo")
    public GameRepository gameRepository() {
        return new GameRepository();
    }

    @Bean(name = "gameplayerrepo")
    public GamePlayerRepository gamePlayerRepository() {
        return new GamePlayerRepository();
    }

}
