package com.nojava.event;

import org.springframework.context.ApplicationEvent;

/**
 * spring
 * 自定义事件和监听器
 * 将实现ApplicationListener接口的实现类交给spring管理 ，通过spring上下文publishEvent将继承ApplicationEvent的类进行发布。这时监听会对事件进行判断是否进行处理
 *
 */
public class RainEvent extends ApplicationEvent {

    public RainEvent(Object source) {
        super(source);
    }
}
