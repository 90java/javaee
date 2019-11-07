package com.nojava;

import com.nojava.bean.Student;
import com.nojava.bean.Teacher;
import com.nojava.config.SpringConfig;
import com.nojava.event.RainEvent;
import com.nojava.test.aop.AopTest;
import com.nojava.test.aop.IAopTest;
import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

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

    }

}
