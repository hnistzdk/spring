import com.zdk.pojo.Hello;
import com.zdk.pojo.TestClass;
import com.zdk.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zdk
 * @date 2021/4/5 15:02
 */
public class MyTest {
    public static void main(String[] args) {
        //获取spring的上下文对象
        ApplicationContext context=new ClassPathXmlApplicationContext("beans.xml");
        Hello hello = (Hello) context.getBean("hello");
        System.out.println(hello.toString());

        System.out.println("===============");

        TestClass testClass =(TestClass) context.getBean("testClass");
        System.out.println(testClass.toString());
    }
    @Test
    public void construct(){
        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
        //分别对应三种不同的new对象时没有无参构造时给对象属性赋值的方法
        User user1 = (User) context.getBean("user1");
        User user2 = (User) context.getBean("user2");
        User user3 = (User) context.getBean("user3");
        //别名的使用  使用别名后原名依旧生效
        User user4 = (User) context.getBean("user11111");
        System.out.println(user1.getName());
        System.out.println(user2.getName());
        System.out.println(user3.getName());
        System.out.println(user4.getName());
    }
}
