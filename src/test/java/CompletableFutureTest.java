import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;

public class CompletableFutureTest {
    static CountDownLatch countDownLatch = new CountDownLatch(10);

    //private static ThreadPoolExecutor threadPoolExcutors = new ThreadPoolExecutor(10,160,10L,TimeUnit.SECONDS,new LinkedBlockingQueue(100));
    //先熟悉Api

    /**
     * 依赖关系
     * thenApply()：把前面任务的执行结果，交给后面的Function
     * thenCompose()：用来连接两个有依赖关系的任务，结果由第二个任务返回
     * and集合关系
     * thenCombine()：合并任务，有返回值
     * thenAccepetBoth()：两个任务执行完成后，将结果交给thenAccepetBoth处理，无返回值
     * runAfterBoth()：两个任务都执行完成后，执行下一步操作(Runnable类型任务)
     * or聚合关系
     * applyToEither()：两个任务哪个执行的快，就使用哪一个结果，有返回值
     * acceptEither()：两个任务哪个执行的快，就消费哪一个结果，无返回值
     * runAfterEither()：任意一个任务执行完成，进行下一步操作(Runnable类型任务)
     * 并行执行
     * allOf()：当所有给定的 CompletableFuture 完成时，返回一个新的 CompletableFuture
     * anyOf()：当任何一个给定的CompletablFuture完成时，返回一个新的CompletableFuture
     */
    @Test
    public void futureTest(){

    }

    /*public static void main(String[] args)  {
        // 生产者，可以指定返回结果
        CompletableFuture<String> firstTask = CompletableFuture.supplyAsync(() -> {
            System.out.println("异步任务开始执行");
            System.out.println("异步任务执行结束");
            return "返回结果";
        });

        String result1 = firstTask.join();
        String result2 = null;
        try {
            result2 = firstTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println(result1 + "," + result2);
    }*/

    /*public static void main(String[] args) throws IOException {
        ExecutorService executor = Executors.newFixedThreadPool(10);

    CompletableFuture<String> taskA = CompletableFuture.supplyAsync(() -> {
        String id = UUID.randomUUID().toString();
        System.out.println("执行任务A：" + id);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return id;
    });
    CompletableFuture<String> taskB = taskA.thenApply(result -> {
        System.out.println("任务B获取到任务A结果：" + result);
        result = result.replace("-", "");
        return result;
    });

    System.out.println("main线程拿到结果：" + taskB.join());

       *//* CompletableFuture<String> taskB = CompletableFuture.supplyAsync(() -> {
            String id = UUID.randomUUID().toString();
            System.out.println("执行任务A：" + id + "," + Thread.currentThread().getName());
            return id;
        }).thenApplyAsync(result -> {
            System.out.println("任务B获取到任务A结果：" + result + "," + Thread.currentThread().getName());
            result = result.replace("-", "");
            return result;
        },executor);

        System.out.println("main线程拿到结果：" + taskB.join());*//*
    }*/


    /*public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        //ExecutorService executor = Executors.newFixedThreadPool(10);
        ThreadPoolExecutor threadPoolExcutors = new ThreadPoolExecutor(2,5,10L,TimeUnit.SECONDS,new LinkedBlockingQueue(100));

        List<Integer> lsit = new ArrayList<>();
        lsit.add(1);
        lsit.add(2);
        lsit.add(3);
        lsit.add(4);
        lsit.add(5);
        lsit.add(6);
        lsit.add(7);
        lsit.add(8);
        lsit.add(9);
        lsit.add(10);

        long time = new Date().getTime();
        System.out.println(time);
        List<CompletableFuture<Integer>>  integerCompletableFutureList = new ArrayList<>();
        for (Integer i : lsit){
            CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return i+1;
            }, threadPoolExcutors);
            integerCompletableFutureList.add(integerCompletableFuture);

        }
        CompletableFuture[] completableFutures = integerCompletableFutureList.stream().toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(completableFutures).thenRun(() -> {
            for(CompletableFuture<Integer> aa:integerCompletableFutureList){
                try {
                            System.out.println(aa.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }

        }).join();

        threadPoolExcutors.shutdown();

        *//*for (Integer i : lsit){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i+1);
        }*//*

        long time2 = new Date().getTime();
        System.out.println(time2);
        System.out.println(time2-time);
    }*/

    /*public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        *//*ExecutorService executor = Executors.newFixedThreadPool(10);*//*
        //ThreadPoolExecutor threadPoolExcutors = new ThreadPoolExecutor(10,160,10L,TimeUnit.SECONDS,new LinkedBlockingQueue(100));

        List<Integer> lsit = new ArrayList<>();
        lsit.add(1);
        lsit.add(2);
        lsit.add(3);
        lsit.add(4);
        lsit.add(5);
        lsit.add(6);
        lsit.add(7);
        lsit.add(8);
        lsit.add(9);
        lsit.add(10);

        long time = new Date().getTime();
        System.out.println(time);
        List<CompletableFuture<Integer>>  integerCompletableFutureList = new ArrayList<>();
        for (Integer i : lsit){
            CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return i+1;
            });
            integerCompletableFutureList.add(integerCompletableFuture);

        }
        CompletableFuture[] completableFutures = integerCompletableFutureList.stream().toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(completableFutures).whenComplete((v, th) -> {
            for(CompletableFuture<Integer> aa:integerCompletableFutureList){
                try {
                    System.out.println(aa.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }

        }).join();


//        countDownLatch.await(10,TimeUnit.SECONDS);

        *//*for (Integer i : lsit){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i+1);
        }*//*

        long time2 = new Date().getTime();
        System.out.println(time2);
        System.out.println(time2-time);
    }*/
}
