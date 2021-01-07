public abstract class StackContainer implements Container{
    private int length;
    private Task[] taskC;
    public StackContainer(){
        length=0;
        taskC=new Task[15];
    }

    public Task remove(){
        if(length>0){
            length--;
            Task t=taskC[length];
            taskC[length]=null;
            return t;
        }
        return null;
    }
}
