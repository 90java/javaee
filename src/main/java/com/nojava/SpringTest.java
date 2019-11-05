package com.nojava;

import com.nojava.config.SpringConfig;
import com.nojava.event.RainEvent;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringTest {

    @Test
    public void test01(){

        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        annotationConfigApplicationContext.publishEvent(new RainEvent("下雨了"));


    }


}
