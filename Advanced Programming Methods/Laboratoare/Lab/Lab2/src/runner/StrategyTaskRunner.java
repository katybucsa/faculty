package runner;

import domain.Strategy;
import domain.Task;
import factory.Container;
import factory.TaskContainerFactory;

public class StrategyTaskRunner implements  TaskRunner {
    private Container container;

    public StrategyTaskRunner(Strategy s){
        container= TaskContainerFactory.getInstance().createContainer(s);
    }

    public void executeOneTask(){
        if(!container.isEmpty()){
            Task t=container.remove();
            t.execute();
        }
    }

    public void executeAll(){
        while (!container.isEmpty()){
            executeOneTask();
        }
    }

    public void addTask(Task t){
        container.add(t);
    }

    public boolean hasTask(){
        return !container.isEmpty();
    }
}
