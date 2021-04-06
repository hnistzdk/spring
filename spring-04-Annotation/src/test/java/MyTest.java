import com.zdk.pojo.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zdk
 * @date 2021/4/6 19:12
 */
public class MyTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        User user = context.getBean("user", User.class);
        System.out.println(user.name);
    }
}
