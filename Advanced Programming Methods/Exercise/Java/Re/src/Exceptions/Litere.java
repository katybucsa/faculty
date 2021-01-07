package Exceptions;

class Litere {
    String str = "a";
    void A(){
        int c;
        try{
            str += "b";
            B();
        }
        catch (Exception e){
            str += "c";
        }finally {
            str+='2';
        }
    }
    void B() throws Exception{
        try{
            str += "d";
            C();
        }
        catch (Exception e){
            throw new Error();

        }
        finally {
            str += "e";
            throw new Exception();
        }

        //str += "f";
    }
    void C() throws Exception{
        str += "g";
        throw new Exception();
    }
    void afisare(){
        System.out.println(str);
    }
    public static void main(String[] args){
        Litere lit = new Litere();
        lit.A();
        lit.afisare();
    }
}




abstract class AA {
    protected Integer[] arr = new Integer[10];

    public AA(){
        arr[0] = 0;
        arr[1] = 1;
    }
    public abstract int getItemAt(String index) throws
            ArrayIndexOutOfBoundsException;
}


class BB extends AA {

    @Override
    public int getItemAt(String index) throws ArrayIndexOutOfBoundsException {
        int position=-1;
        try {
            position = Integer.valueOf(index);
        }catch (NumberFormatException e){
            //throw new Exception("Nu se poate converti");
        }
        return arr[position];
    }
}

class Main1 {
    public static void main(String[] args) {
        AA a = new BB();

        System.out.println(a.getItemAt("1a"));
    }
}


