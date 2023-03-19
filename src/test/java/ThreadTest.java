public class ThreadTest {

    /*public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            sync();
        },"t1");

        Thread t2 = new Thread(() -> {
            sync();
        },"t2");
        t1.start();
        t2.start();
        Thread.sleep(12000);
        synchronized (ThreadTest.class) {
            ThreadTest.class.notifyAll();
        }
    }

    public static synchronized void sync()  {
        try {
            for (int i = 0; i < 10; i++) {
                if(i == 5) {
                    ThreadTest.class.wait();
                }
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/
}
