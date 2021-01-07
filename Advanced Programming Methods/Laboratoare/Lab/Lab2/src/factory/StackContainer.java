package factory;

import domain.Task;

public  class StackContainer extends SuperclassStackQueue {

    public StackContainer(){
        super();
    }

    public Task remove(){
        Task t=tasks.get(tasks.size()-1);
        tasks.remove(tasks.size()-1);
        return t;
    }


}
