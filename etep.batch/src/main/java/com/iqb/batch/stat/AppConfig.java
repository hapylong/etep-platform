package com.iqb.batch.stat;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * 
 * @Copyright 北京爱钱帮财富科技有限公司
 * @author jack
 * @Date 2015年12月21日-下午4:16:49
 */
@Configuration
//@EnableWebMvc
@ComponentScan(value="com.iqb")
 
@ImportResource({"classpath:/config/iqb/batch/task/task-config.xml",
                    "classpath:/config/iqb/batch/spring-init.xml"
	             }) 
//@Import({DataSourceConfig.class})  
public class AppConfig {	
}