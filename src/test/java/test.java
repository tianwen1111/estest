import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class test {


    @Test
    public void a(){

        Set set = new HashSet();
        String[] s =  {"饭店附近阿克苏房间卡322#@（）","复旦复华萨达%##{}","复旦复华萨达%##{}","饭店附近阿克苏房间卡322#@（）"};
        StringBuffer sb = new StringBuffer();
        for (String b:s){
            if (set.add(b)){
                sb.append(b).append("......");
            }
        }

        System.out.println(sb.toString());
    }
}
