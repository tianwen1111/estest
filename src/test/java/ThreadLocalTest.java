import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class ThreadLocalTest {

    ThreadLocal threadLocal = new ThreadLocal();

    @Before
    public void a(){


        threadLocal.set(1);

        System.out.println(threadLocal.get());

        threadLocal.set(2);

        System.out.println(threadLocal.get());
    }


    @Test
    public void a1(){

        System.out.println(threadLocal.get());
    }
}
