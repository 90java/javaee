package com.nojava.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class RainListener1 implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if (applicationEvent instanceof RainEvent) {
            System.out.println("不听课的学生发现  ： " + applicationEvent.getSource() + "  要准备睡觉了...");
        }
    }
}
