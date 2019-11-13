package com.nojava.test.aop.annotation;

import com.nojava.test.aop.AopTest;
import com.nojava.test.aop.IAopTest;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(value = {"com.nojava.test.aop"})
@EnableAspectJAutoProxy   //<aop:aspectj-autoproxy/>
public class AopConfig {

    @Bean
    public IAopTest aopTest(){
        return new AopTest();

    }

}
