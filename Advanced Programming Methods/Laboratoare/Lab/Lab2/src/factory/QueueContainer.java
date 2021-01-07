package factory;

import domain.Task;

public  class QueueContainer extends SuperclassStackQueue {

    public QueueContainer(){
        super();
    }
    public Task remove(){
        Task t=tasks.get(0);
        tasks.remove(0);
        return t;
    }

}
