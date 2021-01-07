package factory;

import domain.Strategy;

public class TaskContainerFactory implements Factory{

    private static final TaskContainerFactory ourInstance=new TaskContainerFactory();

    private TaskContainerFactory() {
    }

    public static TaskContainerFactory getInstance() {
        return ourInstance;
    }

    public Container createContainer(Strategy s){
        if(s==Strategy.LIFO)
            return new StackContainer();
        else if(s==Strategy.FIFO)
            return new QueueContainer();
        return  null;
    }
}
