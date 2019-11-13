package com.nojava;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.nojava.bean.Student;
import com.nojava.bean.Teacher;
import com.nojava.config.SpringConfig;
import com.nojava.event.RainEvent;
import com.nojava.test.aop.AopTest;
import com.nojava.test.aop.AopTest2;
import com.nojava.test.aop.IAopTest;
import com.nojava.test.aop.annotation.AopConfig;
import oracle.jdbc.pool.OracleConnectionPoolDataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import javax.sql.PooledConnection;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SpringTest {

    @Test
    public void test01(){

        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        annotationConfigApplicationContext.publishEvent(new RainEvent("下雨了"));


    }

    @Test
    public void test02(){

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        Teacher teacher = (Teacher)context.getBean("teacher");
        System.out.println(teacher);

    }

    /**
     * java 动态代理测试
     */
    @Test
    public void test03(){

        AopTest aopTest = new AopTest();

        InvocationHandler invocationHandler = new InvocationHandler() {
            /**
             *
             * @param o 在其上调用方法的代理实例
             * @param method
             * @param objects
             * @return
             * @throws Throwable
             */
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                System.out.println("添加前。。");
                Object invoke = method.invoke(aopTest, objects);
                System.out.println("添加后。。");
                return invoke;
            }
        };

        IAopTest proxyInstance = (IAopTest)Proxy.newProxyInstance(aopTest.getClass().getClassLoader(), aopTest.getClass().getInterfaces(), invocationHandler);

        proxyInstance.add();
    }

    /**
     * cglib 动态代理
     */
    @Test
    public void test04(){
        MethodInterceptor methodInterceptor = new MethodInterceptor() {
            /**
             * @param o 表示将来生成的代理对象，
             * @param method 为目标类中方法的反射对象，args为方法的动态入参，
             * @param objects
             * @param methodProxy 为代理类(子类)中方法的反射对象。
             * @return
             * @throws Throwable
             */
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("添加前。。");
                Object o1 = methodProxy.invokeSuper(o, objects);
                System.out.println("添加后。。");
                return o1;
            }
        };
        //cglib：获取代理对象的主要对象
        Enhancer enhancer = new Enhancer();
        //设置谁是父类
        enhancer.setSuperclass(AopTest.class);
        enhancer.setCallback(methodInterceptor);
        //通过字节码技术动态创建子类实例
        IAopTest proxyInstance = (IAopTest)enhancer.create();
        proxyInstance.add();

    }

    /**
     * aop测试
     */
    @Test
    public void test05(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        IAopTest proxy1 = (IAopTest)context.getBean("proxy1");
        proxy1.add();
    }

    /**
     * <aop:config></aop:config> 两种方式测试
     */
    @Test
    public void test06(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        IAopTest aopTest = (IAopTest)context.getBean("aopTest");
        aopTest.add();
    }

    /**
     * 注解测试
     */
    @Test
    public void test07(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);
//        IAopTest aopTest = (IAopTest)context.getBean("aopTest");
        //被代理时，委托类使用自己类接收时报错， 因为被代理了只有使用接口去接收
        IAopTest aopTest = (IAopTest)context.getBean("aopTest");
        aopTest.add();

    }

    /**
     * 测试委托类没有接口时  使用哪种代理
     * 没有接口时 直接可以使用自己类接收，因为使用的cglib动态代理，代理类是委托类的子类
     */
    @Test
    public void test08(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);
//        IAopTest aopTest = (IAopTest)context.getBean("aopTest");
        AopTest2 aopTest = (AopTest2)context.getBean("aopTest2");
        aopTest.add();

    }

    @Test
    public void test09() throws SQLException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring_datasource.xml");
        OracleConnectionPoolDataSource dataSource = (OracleConnectionPoolDataSource)context.getBean("datasource1");
        PooledConnection pooledConnection = dataSource.getPooledConnection();


        Connection connection1 = pooledConnection.getConnection();
        Statement statement = connection1.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from student");
        while (resultSet.next()){

            String string = resultSet.getString(3);
            System.out.println(string);
        }


        resultSet.close();
        connection1.close();

    }

    @Test
    public void test10() throws SQLException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring_datasource.xml");
        BasicDataSource dataSource = (BasicDataSource)context.getBean("datasource2");

        System.out.println(dataSource.getNumActive());
        System.out.println(dataSource.getNumIdle());
        System.out.println("-------------------");

        Connection connection1 = dataSource.getConnection();
        System.out.println(dataSource.getNumActive());
        System.out.println(dataSource.getNumIdle());
        System.out.println(connection1);
        System.out.println("-------------------");
        Connection connection2 = dataSource.getConnection();
        System.out.println(dataSource.getNumActive());
        System.out.println(dataSource.getNumIdle());
        System.out.println(connection2);
        System.out.println("-------------------");
        Connection connection3 = dataSource.getConnection();
        System.out.println(dataSource.getNumActive());
        System.out.println(dataSource.getNumIdle());
        System.out.println(connection3);
        System.out.println("-------------------");
        connection1.close();
        System.out.println(dataSource.getNumActive());
        System.out.println(dataSource.getNumIdle());
        System.out.println("-------------------");
        Connection connection4 = dataSource.getConnection();
        System.out.println(connection4);
        System.out.println(dataSource.getNumActive());
        System.out.println(dataSource.getNumIdle());

    }

    @Test
    public void test11() throws SQLException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring_datasource.xml");
        DataSource dataSource = (DataSource)context.getBean("datasource3");
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from student");
        while (resultSet.next()){
            String string = resultSet.getString(3);
            System.out.println(string);
        }
        resultSet.close();
        connection.close();
    }

    @Test
    public void test12() throws SQLException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring_datasource.xml");
        DataSource dataSource = (DataSource)context.getBean("datasource4");
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from student");
        while (resultSet.next()){
            String string = resultSet.getString(3);
            System.out.println(string);
        }
        resultSet.close();
        connection.close();
    }

    @Test
    public void test13() throws SQLException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring_datasource.xml");
        DataSource dataSource = (DataSource)context.getBean("datasource5");
        Connection connection = dataSource.getConnection();
        Connection connection1 = dataSource.getConnection();
        System.out.println(connection);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from student");
        while (resultSet.next()){
            String string = resultSet.getString(3);
            System.out.println(string);
        }
        connection1.close();
        resultSet.close();
        connection.close();
    }

}
