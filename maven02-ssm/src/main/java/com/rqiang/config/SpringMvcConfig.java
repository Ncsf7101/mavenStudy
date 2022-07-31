package com.rqiang.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan({"com.rqiang.controller", "com.rqiang.config"})
@EnableWebMvc
public class SpringMvcConfig {

}
