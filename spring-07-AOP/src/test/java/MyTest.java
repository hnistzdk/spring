import com.zdk.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zdk
 * @date 2021/4/7 20:58
 */
public class MyTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        //动态代理代理的是接口getBean的第二个参数应该是接口.class,生产的对象也应该是接口类型。不应该是实现类
        UserService userService = context.getBean("userService", UserService.class);

        userService.delete();
    }
}
