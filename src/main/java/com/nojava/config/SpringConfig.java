package com.nojava.config;

import com.nojava.event.RainListener1;
import com.nojava.event.RainListener2;
import com.nojava.servlet.StaticClassTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Configuration ==    <beans/>
 * @ComponentScan == <context:component-scan base-package=""></context:component-scan>  扫描哪个包下面有Spring中加了注解的组件
 * @Bean    ==<bean/>
 */
@Configuration
//@ComponentScan(basePackages = {"com.nojava.service.impl","com.nojava.dao"})
@Import(SpringMvcConfig.class)
public class SpringConfig {
    @Bean
    public StaticClassTest staticClassTest(){
        return new StaticClassTest();
    }

    @Bean
    public RainListener1 rainListener1(){
        return new RainListener1();
    }

    @Bean
    public RainListener2 rainListener2(){
        return new RainListener2();
    }

}
