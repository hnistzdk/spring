import com.zdk.config.MyConfig;
import com.zdk.pojo.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author zdk
 * @date 2021/4/6 19:12
 */
public class MyTest {
    public static void main(String[] args) {
        //如果完全使用了配置类方式去做，就只能通过ApplicationContext上下文来获取容器，通过配置类的class对象来加载
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        User user = context.getBean("myUser", User.class);
        System.out.println(user.name);
    }
}
