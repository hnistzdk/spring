import com.zdk.pojo.People;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zdk
 * @date 2021/4/5 19:42
 */
public class MyTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        People people = context.getBean("people",People.class);
        people.getDog().shout();
        people.getCat().shout();
    }
}
