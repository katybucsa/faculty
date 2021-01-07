import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import repository.RideRepository;


public class MainXML {
    public static void main(String[] args) {
        ApplicationContext factory = new
                ClassPathXmlApplicationContext("classpath:spring_config.xml");
        RideRepository repo= factory.getBean(RideRepository.class);
        repo.delete(3);
    }
}
