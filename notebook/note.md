##常用依赖
```xml
<dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.11</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>5.3.5</version>
        </dependency>
        <dependency>
          <groupId>org.springframework</groupId>
          <artifactId>spring-jdbc</artifactId>
          <version>5.3.5</version>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.18</version>
        </dependency>
    </dependencies>
```

##常用bean的xml文件配置
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd
        http://www.springframework.org/schema/aop
        https://www.springframework.org/schema/aop/spring-aop.xsd">

    <!--    配置注解的支持-->
    <context:annotation-config/>
</beans>
```

##注解说明
- @Autowired:自动装配,通过类型，名字,
    如果Autowired不能唯一自动装配上属性，则需要通过@Qualifier(value="")
- @Nullable:字段标记了这个注解，说明该字段可以为null  
- @Resource:自动装配,也是先通过类型再名字
  
- @Component:注解(义为组成部分、组件)等价于,
  <bean id="user" class="com.zdk.pojo.User />
  
- @Value注解的使用(放到属性上和set方法上是一样的)
  ![img_1.png](img_1.png)
  
- @Repository注解用于dao层时的注入，和@Component类似 只是习惯于在dao用此注解
- @Service注解用于service层时的注入
- @Controller注解用于controller层的注入，同上
- 以上注解使用时必须被<context:component-scan base-package="com.zdk.pojo"/>
  扫描到才会生效,这三个和@Component功能一样，等价。都代表将某个类注入到
  spring容器中，进行装配bean
  

- 使用纯Java形式完成bean.xml配置文件的操作.
  大致的步骤是，创建一个配置类，使用@Configuration进行注解，在其内部的方法中，
  使用@Bean进行注解，@Configuration就类似一个<beans>标签，@Bean相当于一个<bean>子标签，
  @ComponentScan("com.zdk")是扫描包用的，@Import(MyConfig2.class)可以引入其他的配置类  
![img_2.png](img_2.png)![img_3.png](img_3.png)![img_4.png](img_4.png)
  
##代理模式
###角色分析
  - 抽象角色：一般使用接口或者抽象类来解决
  - 真实角色：被代理的角色
  - 代理角色：代理真实角色，代理真实角色后，可以实现代理角色的功能，且可以做一些
    其他的附属(公共)操作
    

###代码步骤
1. 写接口

```java
public interface Rent {
public void rent();
}
```

2. 真实角色

```java
public class Host implements Rent{
    @Override
    public void rent() {
        System.out.println("房东要出租房子");
    }
}
```

3. 代理角色
   
```java
//代理类(代理角色)

public class Proxy {
    private Host host;
    public Proxy() {
    }
    public Proxy(Host host) {
        this.host = host;
    }
    public void rent(){
        host.rent();
    }
    public void seeHouse(){
        System.out.println("中介带你看房");
    }
    public void heTong(){
        System.out.println("中介和你签租赁合同");
    }
    public void money(){
        System.out.println("中介收中介费");
    }
}
```

4. 客户端访问代理角色

```java
public class Client {
    public static void main(String[] args) {
        Host host=new Host();
        //代理,中介帮房东出租房子,但是中介(代理角色)能执行一些其他附属操作
        Proxy proxy = new Proxy(host);
        //通过代理实现,不用找房东,直接找中介租房
        proxy.rent();
    }
}
```
###1.静态代理
####好处：
  - 可以使真实角色的操作更加纯粹，不用去关注一些公共的业务
  - 公共业务就交给代理角色，实现了业务的分工
  - 公共业务发生扩展时，可以方便进行集中管理
####缺点:
  - 一个真实角色就会产生一个代理角色；代码量翻倍，开发效率变低,可用动态代理解决

###2.动态代理
####好处：
  - 可以使真实角色的操作更加纯粹，不用去关注一些公共的业务
  - 公共业务就交给代理角色，实现了业务的分工
  - 公共业务发生扩展时，可以方便进行集中管理
  - 一个动态代理类代理的是一个接口，一般对应一类业务
  - 一个动态代理类可以代理多个类，只要这些类都实现了同一个接口
####特点  
  - 动态代理和静态代理角色一样
  - 动态代理的代理类是动态生成的，不是直接写好的
  - 动态代理分为两大类
1. 基于接口的动态代理
  - JDK动态代理
2. 基于类的动态代理
  - cglib
  - Java字节码实现：Javassist

####需要了解两个类
  - Proxy(代理)
  

  - invocationHandler(调用处理程序)

##SpringAop
###AOP作用
    提供声明式事务;允许用户自定义切面
###AOP名词

- 横切关注点:跨越应用程序多个模块的方法或功能。即是，与我们业务逻辑无关的，但是我们需要关注的部分，就是横切关注点。如日志，安全，缓存，事务等等....
- 切面(ASPECT)︰横切关注点被模块化的特殊对象。即，它是一个类。
- 通知(Advice):切面必须要完成的工作。即，它是类中的一个方法。
- 目标(Target)︰被通知对象。
- 代理(Proxy)︰向目标对象应用通知之后创建的对象。
- 切入点(PointCut):切面通知执行的“地点"的定义。
- 连接点(JointPoint) :与切入点匹配的执行点。


###spring实现AOP 方式一：使用原生springAPI接口
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd
        http://www.springframework.org/schema/aop
        https://www.springframework.org/schema/aop/spring-aop.xsd">
    <!--    配置注解的支持-->
    <context:annotation-config/>
<!--    注册bean-->
    <bean id="userService" class="com.zdk.service.UserServiceImpl"/>
    <bean id="log" class="com.zdk.log.Log"/>
    <bean id="afterLog" class="com.zdk.log.AfterLog"/>
<!--    方式一：使用原生spring API接口-->
<!--    配置aop:需要导入aop的约束-->
    <aop:config>
<!--        需要一个切入点expression：需要在哪个地方去执行execution(要执行的位置:)-->
        <aop:pointcut id="pointCut" expression="execution(* com.zdk.service.UserServiceImpl.*(..))"/>
<!--        执行环绕增加-->
        <aop:advisor advice-ref="log" pointcut-ref="pointCut"/>
        <aop:advisor advice-ref="afterLog" pointcut-ref="pointCut"/>
    </aop:config>
</beans>
```

###实现方式二：使用自定义类来实现AOP
```java
//使用自定义类实现AOP

public class DiyPointCut {
    public void before(){
        System.out.println("========方法执行前========");
    }
    public void after(){
        System.out.println("========方法执行后========");
    }
}
```

```xml
<!--    方式二，使用自定义的类实现AOP-->
    <bean id="diy" class="com.zdk.diy.DiyPointCut"/>
    <aop:config>
<!--        自定义切面,ref为切面要引用的类-->
        <aop:aspect ref="diy">
<!--            切入点-->
            <aop:pointcut id="point" expression="execution(* com.zdk.service.UserServiceImpl.*(..))"/>
<!--        通知-->
            <aop:before method="before" pointcut-ref="point"/>
            <aop:after method="after"  pointcut-ref="point"/>
        </aop:aspect>
    </aop:config>
```
###实现方式三：使用注解来实现AOP
```java
//使用注解方式实现AOP

//使用注解标注这个类是一个切面

@Aspect
public class AnnotationPointCut {
    @Before("execution(* com.zdk.service.UserServiceImpl.*(..))")
    public void before(){
        System.out.println("========方法执行前========");
    }
    @After("execution(* com.zdk.service.UserServiceImpl.*(..))")
    public void after(){
        System.out.println("========方法执行后========");
    }

    //在环绕增强中，可以给定一个参数，代表我们要获取处理切入的点

    @Around("execution(* com.zdk.service.UserServiceImpl.*(..))")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("环绕前");
        System.out.println("方法签名(名称)Signature:"+joinPoint.getSignature());
        //执行around方法
        Object proceed = joinPoint.proceed();
        System.out.println("环绕后");
    }
}
```

```xml
<!--    方式三-->
    <bean id="annotationPointCut" class="com.zdk.diy.AnnotationPointCut"/>
<!--    要开启注解支持-->
    <aop:aspectj-autoproxy/>
```


#spring整合mybatis

##回忆mybatis,步骤:
1. 编写实体类
2. 编写核心配置文件
3. 编写接口
4. 编写Mapper.xml
5. 测试

###注意！！！！资源过滤器
使用此种方法时 必须配置资源过滤，否则会报错
![img_5.png](img_5.png)
```xml
<!--    配置过滤 防止资源导出失败-->
    <build>
        <resources>
            <resource>
                <directory>src/main/resources</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>true</filtering>
            </resource>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>true</filtering>
            </resource>
        </resources>
    </build>
```
###步骤
####1.导入所需依赖,并以防万一加上上述资源过滤代码
```xml
    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.11</version>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.47</version>
        </dependency>
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.5.6</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>5.3.5</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>5.3.5</version>
        </dependency>
        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>1.9.6</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.mybatis/mybatis-spring -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
            <version>2.0.6</version>
        </dependency>
    </dependencies>
```
####2.编写配置文件
```xml
<?xml version="1.0" encoding="UTF8" ?>
<!--究极之恶心的  如果xml文件的第一行的 encoding=UTF-8就会报错   改成UTF8才不会报错-->
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <settings>
                <setting name="logImpl" value="STDOUT_LOGGING"/>
    </settings>
    <typeAliases>
        <package name="com.zdk.pojo"/>
    </typeAliases>

    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://localhost:3306/mybatis?useSSL=true&amp;useUnicode=true&amp;characterEncoding=UTF-8&amp;serverTimezone=UTC"/>
                <property name="username" value="root"/>
                <property name="password" value="root"/>
            </dataSource>
        </environment>
    </environments>
    <mappers>
        <mapper class="com.zdk.mapper.UserMapper"/>
    </mappers>
</configuration>
```
####3.编写接口
```java
public interface UserMapper {
    List<User> getUserList();
}
```
####4.编写Mapper.xml文件
```xml
<?xml version="1.0" encoding="UTF8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--namespace绑定一个对应的Dao/Mapper接口-->
<mapper namespace="com.zdk.mapper.UserMapper">
    <select id="getUserList" resultType="user">
        select * from user ;
    </select>
</mapper>
```
####测试前可以写一个工具类，用于获取sqlSession
```java
public class MybatisUtil {
    private static SqlSessionFactory sqlSessionFactory;
    public static SqlSession getSqlSession() throws IOException {
        String resource="mybatis-config.xml";
        InputStream in =Resources.getResourceAsStream(resource);
        sqlSessionFactory=new SqlSessionFactoryBuilder().build(in);
        //设置为自动提交事务
        return sqlSessionFactory.openSession(true);
    }
}
```

####5.测试
```java
public class MyTest {
    @Test
    public void getUserListTest() throws IOException {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = mapper.getUserList();
        for (User user : userList) {
            System.out.println(user);
        }
    }
}
```
##spring整合mybatis
###注意，xml文件出现字节的 UTF-8 序列的字节 1无效错误时的解决方案
- 在pom.xml文件中的build标签下添加以下属性
```xml
<plugins>
    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-resources-plugin</artifactId>
        <configuration>
            <encoding>UTF-8</encoding>
        </configuration>
    </plugin>
</plugins>
```
- 在pom.xml文件中的build标签下的resources标签下添加以下resource
```xml
<resource>
    <directory>src/main/resources</directory>
        <includes>
            <include>**/*.properties</include>
            <include>**/*.xml</include>
        </includes>
    <filtering>true</filtering>
</resource>
```
- 添加完以上两个东西 运行才不会报错

###方式一:
1. 编写数据源配置
   
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:jdbc="http://www.springframework.org/schema/jdbc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd
        
        
        http://www.springframework.org/schema/jdbc
        http://www.springframework.org/schema/jdbc/spring-jdbc.xsd">

    <!--    DataSource:使用spring提供的数据替换mybatis的配置
            这里使用spring提供的jdbc：org.springframework.jdbc.datasource.DriverManagerDataSource
    -->

    <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
        <property name="url" value="jdbc:mysql://localhost:3306/mybatis?useSSL=true&amp;useUnicode=true&amp;characterEncoding=UTF-8&amp;serverTimezone=UTC"/>
        <property name="username" value="root"/>
        <property name="password" value="root"/>
    </bean>
<!--    spring-dao.xml专注于数据库的设置  不再改变-->
</beans>
```

2. sqlSessionFactory

```xml
<!--    SqlSessionFactory-->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        <!--        绑定mybatis配置文件-->
        <property name="configLocation" value="mybatis-config.xml"/>
        <property name="mapperLocations" value="com/zdk/mapper/*.xml"/>
    </bean>
```
3. sqlSessionTemple
   
```xml
<bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
        <!--        只能使用构造器注入sqlSession，因为上面这个类没有set方法-->
        <constructor-arg index="0" ref="sqlSessionFactory"/>
    </bean>
```

4. 需要给接口增加实现类
   
```java
public class UserMapperImpl implements UserMapper{
    //纯mybatis时的所有操作，都使用sqlSession执行，现在使用SqlSessionTemplate;

    private SqlSessionTemplate sqlSession;
    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<User> getUserList() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.getUserList();
    }
}
```
5. 将自己写的实现类注入到spring中
   
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:jdbc="http://www.springframework.org/schema/jdbc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        
        
        http://www.springframework.org/schema/aop
        https://www.springframework.org/schema/aop/spring-aop.xsd
        http://www.springframework.org/schema/jdbc
        http://www.springframework.org/schema/jdbc/spring-jdbc.xsd">
<!--    此applicationContext.xml配置文件专注于配置bean就可
        spring-dao.xml专注于数据库的设置，不再改变
-->

    <import resource="spring-dao.xml"/>

    <bean id="userMapper" class="com.zdk.mapper.UserMapperImpl">
        <property name="sqlSession" ref="sqlSession"/>
    </bean>
</beans>
```
6. 测试使用

```java
public class MyTest {
    //方式一
    @Test
    public void getUserListTest() throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper userMapper = context.getBean("userMapper", UserMapperImpl.class);
        List<User> userList = userMapper.getUserList();
        for (User user : userList) {
            System.out.println(user);
        }
    }
}
```
###方式2:
- 基本步骤同上,但简化了获取sqlSession的操作
```xml
<bean id="userMapper2" class="com.zdk.mapper.UserMapperImpl2">
        <property name="sqlSessionFactory" ref="sqlSessionFactory"/>
</bean>
```
- 将接口的实现类继承SqlSessionDaoSupport类，通过getSqlSession()方法获取sqlSession
```java
public class UserMapperImpl2 extends SqlSessionDaoSupport implements UserMapper {
    @Override
    public List<User> getUserList() {
        SqlSession sqlSession = getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.getUserList();
    }
}
```
- 测试
```java
public class MyTest {
    //方式二
    @Test
    public void getUserListTest2() throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper userMapper = context.getBean("userMapper2", UserMapperImpl2.class);
        List<User> userList = userMapper.getUserList();
        for (User user : userList) {
            System.out.println(user);
        }
    }   
}
```
##回顾事务
- 一组操作当成一个事务，要么都成功，要么都失败
- 确保数据的完整性和一致性

###事务的ACID原则
- 原子性
- 一致性
- 隔离性:多个业务操作同一个资源时要保证隔离性,防止数据损坏
- 持久性:事务一旦提交,无论系统发生什么问题,结果都不会再被影响,被持久化的写入到存储器中

##spring中的事务管理
- 声明式事务：AOP
- 编程式事务：需要在代码中进行管理

###步骤
1. 导入需要的命令,aop,tx,jdbc等
```text
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:jdbc="http://www.springframework.org/schema/jdbc"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       https://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       https://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/aop
       https://www.springframework.org/schema/aop/spring-aop.xsd
       http://www.springframework.org/schema/tx
       https://www.springframework.org/schema/tx/spring-tx.xsd
       http://www.springframework.org/schema/jdbc
       http://www.springframework.org/schema/jdbc/spring-jdbc.xsd">
```
2. 在spring-dao.xml文件中配置
```xml
<!--    配置声明式事务-->
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"/>
    </bean>

<!--    结合AOP实现事务的织入-->

    <!--    配置事务通知-->
    <tx:advice id="txAdvice" transaction-manager="transactionManager">
<!--        给哪些方法配置事务-->
<!--        配置事务的传播特性:propagation,默认是REQUIRED-->
        <tx:attributes>
            <tx:method name="add" propagation="REQUIRED"/>
            <tx:method name="delete" propagation="REQUIRED"/>
            <tx:method name="update" propagation="REQUIRED"/>
            <tx:method name="select" read-only="true"/>
            <tx:method name="*" propagation="REQUIRED"/>
        </tx:attributes>
    </tx:advice>

<!--    配置事务切入-->
    <aop:config>
<!--        切入点：表达式表示mapper包下的所有类的所有方法,..是参数-->
        <aop:pointcut id="txPointCut" expression="execution(* com.zdk.mapper.*.*(..))"/>
        <aop:advisor advice-ref="txAdvice" pointcut-ref="txPointCut"/>
    </aop:config>
```
3. 测试
```java
@Override
    public class MyTest{
    @Override
    public List<User> getUserList() {
        UserMapper mapper = getSqlSession().getMapper(UserMapper.class);
        User user=new User(6, "张振明","11111111");
        mapper.addUser(user);
        //让删除时sql语句错误，看配置声明式事务后add操作是否会被执行
        mapper.deleteUser(6);
        return mapper.getUserList();
    }
}
```