package runner;

import java.time.LocalDate;

public class PrinterTaskRunner extends AbstractTaskRunner {

    public PrinterTaskRunner(TaskRunner tr){
        super(tr);
    }

    public void executeOneTask(){
        super.executeOneTask();
        System.out.println(LocalDate.now());
    }
}
