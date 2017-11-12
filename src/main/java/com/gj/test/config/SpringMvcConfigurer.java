package com.gj.test.config;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers.DateDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * SpringMvc配置文件
 */
@Configuration
public class SpringMvcConfigurer extends WebMvcConfigurerAdapter {
	
	/*
	 * 使用jetty服务器
	 */
	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		JettyEmbeddedServletContainerFactory factory = new JettyEmbeddedServletContainerFactory();
		return factory;
	}
    
	/*
	 * json序列化
	 */
    @Bean
	@Primary
	public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
		ObjectMapper mapper =  builder.createXmlMapper(false).build();
		
		//忽略值为null的属性
		mapper.setSerializationInclusion(Include.NON_NULL);
		
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	    mapper.disable(JsonGenerator.Feature.AUTO_CLOSE_TARGET);
	    mapper.enable(JsonGenerator.Feature.ESCAPE_NON_ASCII);
		
		SimpleModule module = new SimpleModule();
		module.addSerializer(Long.class, new ToStringSerializer());  
		module.addSerializer(Date.class, new DateSerializer(false, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")));
		module.addDeserializer(Date.class, new DateDeserializer(new DateDeserializer(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss" ));
		mapper.registerModule(module);
		return mapper;
	}
    


	@Override
	public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
		//全局异常处理
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedMethods("GET","POST","DELETE","OPTIONS","PUT");
		registry.addMapping("/**/**").allowedMethods("GET","POST","DELETE","OPTIONS","PUT");
	}
    
}
