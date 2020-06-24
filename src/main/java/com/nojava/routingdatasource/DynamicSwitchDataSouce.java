package com.nojava.routingdatasource;

import java.lang.annotation.*;

//@Target 指定该注解能修饰的目标（只能是方法）
@Target({ElementType.METHOD,ElementType.TYPE})
//@Retention 该注解可以保留多久
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DynamicSwitchDataSouce {

    String dataSource() default "";

}
