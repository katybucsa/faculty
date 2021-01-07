public class NumarComplexError extends Exception{
    private String msg;

    public NumarComplexError(String m){
        super(m);
    }

    public String getMsg(){
        return msg;
    }
}
