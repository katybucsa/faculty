import java.io.*;

import java.util.*;
import java.util.stream.Collectors;


public class BigIntegerAddition {
    /**
     * @param file the file name from where the numbers are read
     * @return the string with the number digits
     */
    private static String readNumber(String file) {
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
        return number;
    }

    /**
     * @param operand the string to parse to obtain long numbers with no more than 18 digits
     * @return the array with long numbers after string parse
     */
    private static long[] parseString(String operand) {
        List<String> list = new ArrayList();
        for (int i = 0; operand.length() > 18; i++) {
            list.add(operand.substring(operand.length() - 18));
            operand = operand.substring(0, operand.length() - 18);
        }
        list.add(operand);
        Collections.reverse(list);
        long[] tokens = new long[list.size()];
        for (int j = 0; j < tokens.length; j++) {
            tokens[j] = Long.parseLong(list.get(j));
        }
        return tokens;
    }


    private static String convertToString(long[] arr) {
        String res = "";
        for (long e : arr) {
            res += e;
        }
        return res;
    }

    /**
     * @param number1 the first number
     * @param number2 the second number
     * @return the string coresponding to
     */
    private static String sequentialAdd(String number1, String number2) {
        long[] tokens_N1 = parseString(number1);
        long[] tokens_N2 = parseString(number2);
        if (tokens_N1.length < tokens_N2.length) {
            long[] aux = tokens_N1;
            tokens_N1 = tokens_N2;
            tokens_N2 = aux;
        }
        int length_N1 = tokens_N1.length;
        int length_N2 = tokens_N2.length;
        long[] result = new long[tokens_N1.length];
        Arrays.fill(result, 0L);

        for (int i = length_N1 - 1, j = length_N2 - 1; i != -1; i--, j--) {
            if (j < 0) {
                result[i] = result[i] + tokens_N1[i];
            } else {
                result[i] = result[i] + tokens_N1[i] + tokens_N2[j];
            }
            if (i != 0 && String.valueOf(result[i]).length() > 18) {
                result[i - 1] = 1L;
                result[i] = result[i] % 1000000000000000000L;
            }
        }
        return convertToString(result);
    }

    private static List<Integer> getNumberByDigits(String number) {
        return Arrays.asList(number.split(""))
                .stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private static void initialize() {
        if (Res.N1.size() < Res.N2.size()) {
            List<Integer> aux = Res.N1;
            Res.N1 = Res.N2;
            Res.N2 = aux;
        }
        Res.result = new ArrayList<>(Collections.nCopies(Res.N1.size(), 0));
        Res.carries = new ArrayList<>(Collections.nCopies(Res.N1.size(), 0));
        Res.c = new ArrayList<>(Collections.nCopies(Res.N1.size(), '0'));
        Res.prefixes = new ArrayList<>(Collections.nCopies(Res.N1.size(), '0'));
    }

    private static Runnable createThread(String type, int begin, int offset) {
        if ("C".equals(type))
            return new MyThreadC(begin, offset);
        if ("Carry".equals(type))
            return new MyThreadCarry(begin, offset);
        if ("Carry2".equals(type))
            return new MyThreadCarry2(begin, offset);
        if ("Prefix".equals(type))
            return new MyThreadPrefix(begin, offset);
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

    private static void sequentialAddCarries() {
        for (int i = 1; i < Res.N1.size(); i++) {
            int s = Res.result.get(i) + Res.carries.get(i - 1);
            if (s == 10) {
                Res.result.set(i, 0);
                Res.carries.set(i, 1);
            } else {
                Res.result.set(i, s);
            }
        }
        if (Res.carries.get(Res.N1.size() - 1) == 1) {
            Res.result.add(Res.N1.size(), 1);
        }
    }

    private static void parallelAddingV1(int nrTh) {

        initialize();

        if (Res.N1.size() < nrTh) {
            nrTh = Res.N1.size();
        }

        int nrC = Res.N1.size() / nrTh;
        int r = Res.N1.size() % nrTh;
        Thread[] threads = new Thread[nrTh];
        String threadType = "Carry";

        startThreads(threads, threadType, nrTh, nrC, r);
        joinThreads(threads);
        sequentialAddCarries();
    }


    private static void parallelAddingV2(int nrThreads) {
        initialize();

        if (Res.N1.size() < nrThreads) {
            nrThreads = Res.N1.size();
        }

        int nrC = Res.N1.size() / nrThreads;
        int r = Res.N1.size() % nrThreads;
        Thread[] threads = new Thread[nrThreads];

        startThreads(threads, "C", nrThreads, nrC, r);
        joinThreads(threads);

        threads = new Thread[nrThreads];

        startThreads(threads, "Prefix", nrThreads, nrC, r);
        joinThreads(threads);

        threads = new Thread[nrThreads];

        startThreads(threads, "Carry2", nrThreads, nrC, r);
        joinThreads(threads);

        threads = new Thread[nrThreads];

        startThreads(threads, "Final", nrThreads, nrC, r);
        joinThreads(threads);
    }

    private static void sequential(String number1, String number2) {
        String n1 = new StringBuilder(number1).reverse().toString();
        String n2 = new StringBuilder(number2).reverse().toString();

        long begin = System.currentTimeMillis();
        System.out.println(begin);
        System.out.println(sequentialAdd(n1, n2));
        long end = System.currentTimeMillis();
        System.out.println(end);
        System.out.println((end - begin) / 1000.0);
    }

    private static void parallelV1() {
        System.out.println("Paralel varianta 1\n");
        long beginV1 = System.currentTimeMillis();
        System.out.println(beginV1);
        parallelAddingV1(100);
        long endV1 = System.currentTimeMillis();
        System.out.println(endV1);
        System.out.println((endV1 - beginV1) / 1000.0);
        Collections.reverse(Res.result);
//        for (Integer i : Res.result) {
//            System.out.print(i);
//        }
    }

    private static void parallelV2() {
        long beginV2 = System.currentTimeMillis();
        System.out.println(beginV2);
        parallelAddingV2(100);
        long endV2 = System.currentTimeMillis();
        System.out.println(endV2);
        System.out.println((endV2 - beginV2) / 1000.0);
        Collections.reverse(Res.result);

        System.out.println();
        for (Integer i : Res.result) {
            System.out.print(i);
        }
    }

    public static void main(String[] args) {
        String file1 = "a.txt", file2 = "b.txt";
        BigIntegerGenerator.genereateBigInt(1000000, file1);
        BigIntegerGenerator.genereateBigInt(1000000, file2);
        String number1 = readNumber("a.txt");
        String number2 = readNumber("b.txt");
        //number1 = "97531";
        //number2 = "32186";
        //number1 = "9998";
        //number2 = "1112";
        number1 = "99999";
        number2 = "10001";
        Res.N1 = getNumberByDigits(number1);
        Res.N2 = getNumberByDigits(number2);

        //sequential
        if (Res.variant.equals("S")) {
            sequential(number1, number2);
        }

        //parallel V1
        if (Res.variant.equals("V1")) {
            parallelV1();
        }

        //parallel V2
        if (Res.variant.equals("V2")) {
            parallelV2();
        }
    }
}