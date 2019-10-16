package com.nojava.config;

import com.nojava.servlet.StaticClassTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class SpringConfig {
    @Bean
    public StaticClassTest staticClassTest(){
        return new StaticClassTest();
    }
}
