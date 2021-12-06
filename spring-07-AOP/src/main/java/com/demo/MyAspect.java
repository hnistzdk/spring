package com.demo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author zdk
 * @date 2021/12/5 17:40
 */
@Aspect
@Component
public class MyAspect {

    /**
     * @Before 前置通知注解
     * 属性：value 是切入点表达式，表示切面的功能执行的位置
     * 位置：在方法的上面
     * 特点：
     * 1.在目标方法之前先执行
     * 2.不会改变目标方法的执行结果
     * 3.不会影响目标方法的执行
     */
    /**
     * 切面表达式的多种编写方式
     * execution(public void com.demo.SomeServiceImpl.doSome(String,Integer))
     * execution(void com.demo.SomeServiceImpl.doSome(String,Integer))
     * execution(void *..SomeServiceImpl.doSome(String,Integer))
     * execution(* *..SomeServiceImpl.doSome(..))
     * execution(* *..SomeServiceImpl.do*(..))
     */
    /*@Before(value = "execution(* *..SomeServiceImpl.do*(..))")
    public void myBefore(){
        //以下是要执行的功能代码
        System.out.println("前置通知：在目标方法执行前输出执行时间:"+new Date());
    }*/

    /**
     * 一个方法可以加入多个前置通知
     */
/*    @Before(value = "execution(* *..SomeServiceImpl.do*(..))")
    public void myBefore2(){
        //以下是要执行的功能代码
        System.out.println("前置通知2：在目标方法执行前输出执行时间:"+new Date());
    }*/

    /**
     * 指定通知方法中的参数：JoinPoint
     * JoinPoint：就代表了业务方法，即要加入切面功能的业务方法doSome(String,Integer)
     * 作用是：可以在方法中获取方法执行时的信息，例如方法名称，方法的实参
     * 这个JoinPoint参数的值是由框架赋予的，必须是第一个位置的参数
     */
    @Before(value = "execution(* *..SomeServiceImpl.do*(..))")
    public void myBefore(JoinPoint joinPoint){
        System.out.println("方法的签名(即定义)："+joinPoint.getSignature());
        System.out.println("方法的名称："+joinPoint.getSignature().getName());
        //获取方法的实参
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            System.out.println("参数->"+arg);
        }
        //以下是要执行的功能代码
        System.out.println("前置通知：在目标方法执行前输出执行时间:"+new Date());
    }

    /**
     * 后置通知
     * 方法的定义要求
     * 1.是公共方法 public
     * 2.方法没有返回值
     * 3.方法名称自定义
     * 4.方法有参数的，推荐类型为Object，参数名自定义
     */
    /**
     * @AfterReturning:后置通知
     * 属性：1.value 切入点表达式
     *      2.returning 自定义的变量，表示目标方法的返回值,此自定义变量名必须和通知方法的形参名一样
     * 特点：1.在目标方法之后执行
     *      2.能获取到目标方法的返回值，可以根据这个返回值做不同的处理操作
     *      3.可以修改这个返回值
     * @param res
     */

    @AfterReturning(value = "execution(* *..SomeServiceImpl.doOther(..))",returning = "res")
    public void myAfterReturning(Object res){
        //Object res：是目标方法执行后的返回值，根据返回值做切面的功能处理
        System.out.println("后置通知：在目标方法执行后执行，获取的返回值是："+res);
        Person objRes =(Person) res;
        objRes.setName("你被修改了");
    }


    /**
     * @Around：环绕通知
     *      属性：value 切入点表达式
     *      特点：
     *      1.是功能最强的通知
     *      2.在目标方法执行前、后都能进行功能的增强
     *      3.可以控制目标方法是否被调用执行
     *      4.可以修改原来的目标方法的执行结果，影响最终的调用结果
     * 环绕通知等同于jdk动态代理使用InvocationHandler
     *
     * 参数：ProceedingJoinPoint
     *      就等同于动态代理invoke方法中的Method参数
     *      作用：用于执行目标方法
     * 返回值：环绕方法的返回值就是要执行的目标方法的返回值，可以被修改
     *
     *
     * @param proceedingJoinPoint
     * @return
     */
    @Around(value = "execution(* *..SomeServiceImpl.doFirst(..))")
    public Object myAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //实现环绕通知
        Object result = null;
        //1.环绕：目标方法执行前增强
        System.out.println("环绕通知：目标方法执行前输出时间："+new Date());
        //2.调用执行目标方法
        /*
         * 相当于 method.invoke();result = doFirst();
         * 可以根据参数条件等决定目标方法是否执行
         */
        result = proceedingJoinPoint.proceed();
        //3.环绕：目标方法执行后增强
        System.out.println("环绕通知：目标方法执行后,提交事务");
        //4.返回方法返回结果
        return result;
    }
}
