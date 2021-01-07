public class NumarComplex {
    private float pReala;
    private String pImaginara;

    public NumarComplex(float r, String i){
        pReala=r;
        pImaginara=i;
    }

    public NumarComplex(){}

    public void setRe(float r){
        pReala=r;
    }

    public void setIm(String i){
        pImaginara=i;
    }

    public float getRe(){
        return pReala;
    }

    public String getIm(){
        return pImaginara;
    }

    @Override public String toString(){
        return pReala+pImaginara;
    }
}

