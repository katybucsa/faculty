package Threads;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class Threadd {
    public static void main(String[] args) {
        /*ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("Asynchronous task");
            }
        });

        System.out.println(Thread.currentThread().getName());
        ExecutorService executor=Executors.newFixedThreadPool(5);
        executor.execute(()->{String threadName=Thread.currentThread().getName();
            System.out.println("Hello"+threadName);});


/*executor.execute(()->{String threadName=Thread.currentThread().getName();
            System.out.println("Hello"+threadName);});
        System.out.println(Thread.currentThread().getName());
        //executorService.shutdown();
        executor.shutdown();*/

        ExecutorService ex=Executors.newFixedThreadPool(5);
        List<Callable<String>> callables= Arrays.asList(()->"task1",
                ()->"task2",()->"task3",()->"task4",()->"task5",()->"task6");
        try {
            List<Future<String>> futures=ex.invokeAll(callables);
            futures.stream()
                    .map(future-> {
                        try {
                            return future.get();
                        } catch (Exception e) {
                            throw new IllegalStateException();
                        }
                    })
                    .forEach(System.out::println);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /*Callable<String> callable(String result, long sleepSeconds){
            return ()->{
                TimeUnit.SECONDS.sleep(sleepSeconds);
                return result;
            };
        }*/

    }

}
