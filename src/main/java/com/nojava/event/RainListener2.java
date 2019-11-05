package com.nojava.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class RainListener2 implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if (applicationEvent instanceof RainEvent) {
            System.out.println("老师  ： " + applicationEvent.getSource() + "  现在各位休息一下");
        }
    }
}
