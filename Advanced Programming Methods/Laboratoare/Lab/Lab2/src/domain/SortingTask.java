package domain;

import java.util.Collections;
import java.util.Vector;

public class SortingTask extends Task{
    private Vector<Integer> numere;
    private Strategy strategy;

    public SortingTask(Vector<Integer> v,Strategy s){
        numere=v;
        strategy=s;
    }

    public class AbstractSorter{
        public void bubbleSort(){
            for(int i=0;i<numere.size()-1;i++)
                for(int j=i+1;j<numere.size();j++)
                    if(numere.elementAt(i)>numere.elementAt(j))
                        Collections.swap(numere,i,j);
        }


        int partition( int low, int high)
        {
            int pivot = numere.elementAt(high);
            int i = low-1;
            for (int j=low; j<high; j++)
                if (numere.elementAt(j) <= pivot)
                {
                    i++;
                    Collections.swap(numere,i,j);
                }
            Collections.swap(numere,i+1,high);
            return i+1;
        }



        void quickSort(int low, int high) {
            if (low < high) {
                int pi = partition(low, high);

                quickSort(low, pi - 1);
                quickSort(pi + 1, high);
            }
        }


        public void sort(){
            if(strategy==Strategy.bubble)
                bubbleSort();
            else if(strategy==strategy.quick)
                quickSort(0,numere.size()-1);
            else
                return;
        }
    }

    public void execute(){
        AbstractSorter as=new AbstractSorter();
        as.sort();
        for(int e:numere)
            System.out.println(e+"\n");
    }
}