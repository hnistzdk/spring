import com.zdk.pojo.Student;
import com.zdk.pojo.User;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zdk
 * @date 2021/4/5 16:40
 */
public class MyTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Student student = (Student) context.getBean("student");
        System.out.println(student.toString());
    }

    @Test
    public void pTest(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("userbeans.xml");
        User user = (User) context.getBean("user");
        User user1 = (User) context.getBean("user1");
        User user2 = (User) context.getBean("user1");
        //设置为scope为singleton时,为单例模式(spring默认)，从容器中get同一个id的对象时,拿到的是同一个对象,以下语句结果为true
        System.out.println(user1==user2);

        //设置为scope为prototype时,为原型模式,每次从容器中get时都产生一个新的对象,上面语句结果为false

//        System.out.println(user.toString());
//        System.out.println(user1.toString());
    }
}
