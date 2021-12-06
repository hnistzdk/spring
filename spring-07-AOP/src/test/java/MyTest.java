import com.demo.Person;
import com.demo.SomeService;
import com.demo.SomeServiceImpl;
import com.zdk.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;

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


    @Test
    public void test1(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        //注意这里需要使用someServiceImpl才能获取到bean,使用someService获取不到
        SomeService someService = applicationContext.getBean("someServiceImpl", SomeService.class);
        //类是 com.sun.proxy.$Proxy17  说明是被生成的代理类
        System.out.println(someService.getClass().getName());
        someService.doSome("张迪凯", 20);
    }

    @Test
    public void test2(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        SomeService someService = applicationContext.getBean("someServiceImpl", SomeService.class);
        Person res = someService.doOther("张迪凯");
        System.out.println(res);
    }

    @Test
    public void test3(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        SomeService someService = applicationContext.getBean("someServiceImpl", SomeService.class);
        Person res = someService.doFirst("张迪凯",20);
        System.out.println("方法返回值："+res);
    }
}
