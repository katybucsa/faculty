import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Main {

    public static void main(String[] args) {
        MessageTask[] tasks=new MessageTask[5];
        for(int i=0; i<5;i++){
            LocalDateTime data=LocalDateTime.parse("02/03/2010 12:23:34",DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
            tasks[i]=new MessageTask("mesaje","from","to",data,"123","vdv");
        }
        for(MessageTask task:tasks){
            System.out.println(" "+task);
        }
    }
}
