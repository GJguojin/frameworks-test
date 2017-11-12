package com.gj.test;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;

import com.gj.test.WebApplication;


/**
 * 测试配置
 */
@SpringBootApplication
@ComponentScan(excludeFilters={@Filter(type=FilterType.ASSIGNABLE_TYPE,value={WebApplication.class})})
public class WebTestConfiguration {

}