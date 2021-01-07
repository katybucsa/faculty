import java.time.LocalDateTime;

public class MessageTask extends Task{
    private String from,to,mesaj;
    private LocalDateTime date;
    public MessageTask(String m, String f, String t, LocalDateTime d, String id, String des){
        super(id,des);
        mesaj=m; from=f; to=t; date=d;
    }

    @Override
    public void execute(){
        System.out.print(mesaj+" "+date);
    }

    @Override
    public String toString(){
        return super.toString()+" "+mesaj+" "+from+" "+to;
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof MessageTask){
            MessageTask m =(MessageTask) o;
            return super.getId().equals(m.getId());
        }
        return false;
    }
}
