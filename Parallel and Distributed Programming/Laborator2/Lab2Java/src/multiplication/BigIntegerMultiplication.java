package multiplication;

import addition.*;
import difference.SequentialD;
import generator.BigIntegerGenerator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static addition.Elems.*;
import static multiplication.DigitMultiplication.multiplyVector;

/**
 * Created on: 25-Oct-19, 13:56
 *
 * @author: Katy Bucșa
 */

public class BigIntegerMultiplication {
    /**
     * @param file the file name from where the numbers are read
     * @return the string with the number digits
     */
    private static List<Integer> readNumber(String file) {
        String number = "";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                number += line;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Arrays.asList(number.split(""))
                .stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private static List<Integer> karatsuba(List<Integer> num1, List<Integer> num2) {
        if (num1.size() == 1) {
            return multiplyVector(num2, num1.get(0));
        }
        if (num2.size() == 1) {
            return multiplyVector(num1, num2.get(0));
        }
        while (num1.size() < num2.size()) {
            num1.add(0, 0);
        }
        while (num2.size() < num1.size()) {
            num2.add(0, 0);
        }
        int m = num1.size();
        int mij; //= m/2;
        if (m % 2 == 0) {
            mij = m / 2;
        } else {
            mij = m / 2 + 1;
        }

        List<Integer> high1, low1, high2, low2, z0, z1, z2;

        high1 = new ArrayList<>(num1.subList(0, mij));
        low1 = new ArrayList<>(num1.subList(mij, num1.size()));
        high2 = new ArrayList<>(num2.subList(0, mij));
        low2 = new ArrayList<>(num2.subList(mij, num2.size()));
        while (high1.get(0).equals(0) && high1.size() > 1) {
            high1.remove(0);
        }
        while (high2.get(0).equals(0) && high2.size() > 1) {
            high2.remove(0);
        }

        List<Integer> low1high1 = SequentialA.sequentialAdd(high1, low1);
        List<Integer> low2high2 = SequentialA.sequentialAdd(high2, low2);


        z0 = karatsuba(low1, low2);
        z1 = karatsuba(low1high1, low2high2);
        z2 = karatsuba(high1, high2);

        //((z1 - z2 - z0) * 10 ^ m2)
        List<Integer> z1_z2_z0 = SequentialD.sequentialDiff(SequentialD.sequentialDiff(z1, z2), z0);
        if (m % 2 != 0) {
            z1_z2_z0.addAll(Collections.nCopies(mij - 1, 0));
        } else {
            z1_z2_z0.addAll(Collections.nCopies(mij, 0));
        }
        //(z2 * 10 ^ (m2 * 2))
        if (z2.get(0) != 0) {
            if (m % 2 != 0) {
                z2.addAll(Collections.nCopies(2 * (mij - 1), 0));
            } else {
                z2.addAll(Collections.nCopies(m, 0));
            }
        }

        return SequentialA.sequentialAdd(SequentialA.sequentialAdd(z2, z1_z2_z0), z0);
        //return (z2 * 10 ^ (m2 * 2)) + ((z1 - z2 - z0) * 10 ^ m2) + z0
    }

    private static Runnable createThread(String type, int begin, int offset) {
        if ("C".equals(type))
            return new MyThreadC(begin, offset);
        if ("Carry".equals(type))
            return new MyThreadCarry(begin, offset);
        if ("M".equals(type))
            return new MyThreadMult(begin, offset);
        return new MyThreadFinal(begin, offset);
    }

    /**
     * method to create and start the threads
     *
     * @param threads    - the array which will contain the started threads
     * @param threadType - what type of threads to create
     * @param nrThreads  - how many threads to create
     * @param nrC        - how many operations a thread must do
     * @param r          - how many threads must do nrC + 1 operations
     */
    private static void startThreads(Thread[] threads, String threadType, int nrThreads, int nrC, int r) {
        int count = 0;

        //create and start threads
        for (int i = 0; i < nrThreads; i++) {
            Runnable myThread;
            if (r > 0) {
                myThread = createThread(threadType, count, nrC + 1);
                count += nrC + 1;
            } else {
                myThread = createThread(threadType, count, nrC);
                count += nrC;
            }
            threads[i] = new Thread(myThread);
            threads[i].start();
            r--;
        }
    }

    /**
     * method to join the started threads
     *
     * @param threads - the array of threads to join
     */
    private static void joinThreads(Thread[] threads) {
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void initialize() {
        if (Elems.N1.size() < Elems.N2.size()) {
            List<Integer> aux = Elems.N1;
            Elems.N1 = Elems.N2;
            Elems.N2 = aux;
        }
        Elems.RESULT = new ArrayList<>(Collections.nCopies(Elems.N1.size(), 0));
        CARRIES = new ArrayList<>(Collections.nCopies(Elems.N1.size(), 0));
        C = new ArrayList<>(Collections.nCopies(Elems.N1.size(), '0'));
    }

    private static void parallelAddition(int nrThreads) {
        initialize();
        if (Elems.N1.size() < nrThreads) {
            nrThreads = Elems.N1.size();
        }

        int nrC = Elems.N1.size() / nrThreads;
        int r = Elems.N1.size() % nrThreads;
        Thread[] threads = new Thread[nrThreads];

        startThreads(threads, "C", nrThreads, nrC, r);
        joinThreads(threads);

        threads = new Thread[nrThreads];

        startThreads(threads, "Carry", nrThreads, nrC, r);
        joinThreads(threads);

        threads = new Thread[nrThreads];

        startThreads(threads, "Final", nrThreads, nrC, r);
        joinThreads(threads);
    }

    //inmulteste primul numar pe rand cu fiecare cifra din cel de-al doilea numar
    private static void bigIntegerParallelMultiplication(int nrThreads) {
        if (Elems.N1.size() < Elems.N2.size()) {
            List<Integer> aux = Elems.N1;
            Elems.N1 = Elems.N2;
            Elems.N2 = aux;
        }
        Elems.elems = new ArrayList<>(Collections.nCopies(Elems.N2.size(), null));
        if (Elems.N2.size() < nrThreads) {
            nrThreads = Elems.N2.size();
        }
        int nrC = Elems.N2.size() / nrThreads;
        int r = Elems.N2.size() % nrThreads;
        Thread[] threads = new Thread[nrThreads];

        startThreads(threads, "M", nrThreads, nrC, r);
        joinThreads(threads);

        Elems.N1 = Elems.elems.get(0);
        for (int i = 1; i < Elems.elems.size(); i++) {
            Elems.N2 = Elems.elems.get(i);
            parallelAddition(nrThreads);
            Elems.N1 = Elems.RESULT;
        }
    }

    public static void main(String[] args) {
        String file1 = "a.txt", file2 = "b.txt";
        BigIntegerGenerator.generateBigInt(10000, file1);
        BigIntegerGenerator.generateBigInt(10000, file2);
        List<Integer> number1 = readNumber("a.txt");
        List<Integer> number2 = readNumber("b.txt");
        Elems.N1 = number1;
        Elems.N2 = number2;
        long begin = System.currentTimeMillis();
        //List<Integer> result = karatsuba(number1, number2);
        bigIntegerParallelMultiplication(8);
        System.out.println(begin);
        long end = System.currentTimeMillis();
        System.out.println(end);
        System.out.println((end - begin) / 1000.0);
//        System.out.println();
        //Collections.reverse(RESULT);
//        for (Integer i : result) {
//            System.out.print(i);
//        }
//        System.out.println();
    }
}
