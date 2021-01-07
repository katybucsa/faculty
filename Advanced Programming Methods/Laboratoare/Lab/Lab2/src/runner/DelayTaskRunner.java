package runner;

public class DelayTaskRunner extends AbstractTaskRunner {

    public DelayTaskRunner(TaskRunner tr){
        super(tr);
    }

    public void executeAll(){
        try{
            while(hasTask()) {
                Thread.sleep(3000);
                executeOneTask();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
