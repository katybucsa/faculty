package factory;

import domain.Task;
import factory.Container;

import java.util.ArrayList;

public abstract class SuperclassStackQueue implements Container {
    protected ArrayList<Task> tasks;

    protected SuperclassStackQueue(){
        tasks=new ArrayList<Task>();
    }

    abstract public Task remove();

    public void add(Task t){
        tasks.add(t);
    }

    public int size(){
        return tasks.size();
    }

    public boolean isEmpty(){
        return tasks.size()==0;
    }

}
