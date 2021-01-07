package domain;

public abstract class Task{
    private String taskId;
    private String descriere;

    public Task(String id, String descr){
        this.taskId=id;
        this.descriere=descr;
    }

    public Task(){}

    public void setId(String new_id){
        this.taskId=new_id;
    }

    public void SetDescr(String new_descr){
        this.descriere=new_descr;
    }

    public String getId(){
        return this.taskId;
    }

    public String getDescr(){
        return this.descriere;
    }

    public abstract void execute();

    @Override
    public String toString(){
        return "id="+this.taskId+"| descriere="+this.descriere+"| ";
    }
}

