import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class ABC{

    public void afis(){
        System.out.println("MPP");
    }
}


public class Main {
    public static void main(String[] args) {
        Logger logger= LogManager.getLogger();
        logger.debug("MPP");
        ABC abc=new ABC();
        abc.afis();
    }
}
