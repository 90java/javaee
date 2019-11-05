package com.nojava.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.nojava.controller")
public class SpringMvcConfig {
}
