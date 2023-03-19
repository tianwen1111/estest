import java.util.concurrent.locks.ReentrantLock;

public class ThreadTest2 {


    private static int count=0;

    private static ReentrantLock lock = new ReentrantLock();

    public static void increment()  {
        lock.lock();
        try {
            count++;
            System.out.println(count);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            lock.unlock();
        }


    }

    /*public static void increment()  {

            count++;

    }*/


    /*public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                increment();

            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                increment();
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        //System.out.println(count);
    }*/
}
