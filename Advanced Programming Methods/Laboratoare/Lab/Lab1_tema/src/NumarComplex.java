public  class NumarComplex {
    private float pReala;
    private float pImaginara;

    public NumarComplex(float r, float i){
        //constructor for NumarComplex class
        // parameters r,i float numbers
        pReala=r;
        pImaginara=i;
    }
    public NumarComplex(){}
    //constructor without parameters

    public void setRe(float r){
        //setter method
        pReala=r;
    }

    public void setIm(float i){
        //setter method
        pImaginara=i;
    }

    public float getRe(){
        //getter method
        return pReala;
    }

    public float getIm(){
        //getter method
        return pImaginara;
    }

    public void add(NumarComplex nr){
        pReala+=nr.pReala;
        pImaginara+=nr.pImaginara;
    }

    public void substract(NumarComplex nr){
        pReala-=nr.pReala;
        pImaginara-=nr.pImaginara;
    }

    public void divide(NumarComplex nr){
        float pR=pReala;
        pReala=(pReala*nr.pReala+pImaginara*nr.pImaginara)/(nr.pReala*nr.pReala+nr.pImaginara*nr.pImaginara);
        pImaginara=(nr.pReala*pImaginara-pR*nr.pImaginara)/(nr.pReala*nr.pReala+nr.pImaginara*nr.pImaginara);

    }

    public void multiply(NumarComplex nr){
        float pR=pReala;
        pReala=pReala*nr.pReala-pImaginara*nr.pImaginara;
        pImaginara=pR*nr.pImaginara-pImaginara*nr.pReala;
    }


    @Override
    public String toString(){
        //returns the current ComplexNumber in the form it will be printed on the screen
        if(pImaginara<0)
            return String.valueOf(pReala)+String.valueOf(pImaginara)+"i";
        return pReala+"+"+pImaginara+"i";
    }
}