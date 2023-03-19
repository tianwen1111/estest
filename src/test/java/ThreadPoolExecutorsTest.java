import org.junit.Test;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class ThreadPoolExecutorsTest {

    private static ThreadPoolExecutor  threadPoolExcutors = new ThreadPoolExecutor(10,160,10L,TimeUnit.SECONDS,new LinkedBlockingQueue(100));


   // private static ThreadPoolTaskExecutor threadPoolExcutors = new ThreadPoolTaskExecutor();

    @Test
    public void testPoolExcutor(){

    }

   /* public static void main(String[] args){

        List<String> sss = new ArrayList<String>();
        List<Integer> list = new ArrayList<>();

        sss.add("1");
        sss.add("2");

        for (String ss:sss) {
            threadPoolExcutors.execute(()->{
                System.out.println("laile"+ ss);
                list.add(Integer.getInteger(ss));
            });
        }
        System.out.println("nag");
        for (Integer i:list){
            System.out.println(i);
        }


    }*/

    /*public static void main(String[] args) throws IOException {

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
        for (Integer i : lsit){
            threadPoolExcutors.execute(()->{
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i+1);
            });
        }

        long time2 = new Date().getTime();
        System.out.println(time2);
        System.out.println(time2-time);
    }*/


    /*public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("t1:" + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.setDaemon(true);
        t1.start();
    }*/
}
