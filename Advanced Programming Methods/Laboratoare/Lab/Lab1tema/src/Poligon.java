public abstract class Poligon {
    private float pReala;
    private String pImaginara;

    public Poligon(float r, String i){
        pReala=r;
        pImaginara=i;
    }

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

    public String toString(){
        return pReala+pImaginara;
    }
}
