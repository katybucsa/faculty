package sample.utils;


public class AppEvent<E> implements Event {
    private Action action;
    private E data, oldData;

    public AppEvent(Action a,E e){
        action=a;
        data=e;
    }

    public AppEvent(Action a, E e, E old){
        action=a;
        data=e;
        oldData=old;
    }

    public Action getAction(){
        return action;
    }

    public  E getData(){
        return data;
    }

    public E getOldData() {
        return oldData;
    }
}