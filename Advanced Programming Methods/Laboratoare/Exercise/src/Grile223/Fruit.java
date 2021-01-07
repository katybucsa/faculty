package Grile223;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.lang.reflect.Array;

/*abstract class Fruit {
    @Override
    public String toString() {
        return "Fruit";
    }
}

class Apple extends Fruit {
    @Override
    public String toString() {
        return "Apple";
    }
}

class Pear extends Fruit {
    @Override
    public String toString() {
        return "Pear";
    }
}

class Main5{
    public static void main(String[] args) {
        List basket = new ArrayList<>();
        basket.add(new Apple());
        basket.add(new Pear());
        basket.add(new Apple());
        List<? extends Fruit> fridge = new ArrayList<>(basket);
        fridge.forEach(fruit -> System.out.print(fruit + " "));
        List<? extends Fruit> store = new ArrayList<>(10);
        Collections.copy(store, basket);
        store.forEach(fruit -> System.out.print(fruit + " "));
    }

}*/


public abstract class Fruit {
    @Override
    public String toString() {
        return "Fruit";
    }
}

class Tomato extends Fruit {
    @Override
    public String toString() {
        return "Tomato";
    }
}

 class Apple extends Fruit {
    @Override
    public String toString() {
        return "Apple";
    }
}

class Pear extends Fruit {
    @Override
    public String toString() {
        return "Pear";
    }
}

class Fresh<F extends Fruit> {
    private F fruit;

    public Fresh(F fruit) {
        this.fruit = fruit;
    }

    public F getFruit() {
        return fruit;
    }
}
/*
class Main6 {

    public static void main(String[] args) {
        List<Fresh<Fruit>> listFruit = new ArrayList<>();
        listFruit.add(new Fresh<Apple>(new Apple()));
        listFruit.add(new Fresh<Tomato>(new Tomato()));
        listFruit.add(new Fresh<Pear>(new Pear()));
        for (Fresh<Fruit> item : listFruit) {
            System.out.println(item.getFruit());
        }
    }
}*/
