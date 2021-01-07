package runner;

import domain.Task;

public abstract class AbstractTaskRunner implements TaskRunner{
    private TaskRunner tRunner;

    public AbstractTaskRunner(TaskRunner tr){
        tRunner=tr;
    }

    public void executeOneTask(){

        tRunner.executeOneTask();
    }

    public void executeAll(){
        while(tRunner.hasTask())
            executeOneTask();
    }

    public void addTask(Task t){

        tRunner.addTask(t);
    }

    public boolean hasTask(){

        return tRunner.hasTask();
    }
}
