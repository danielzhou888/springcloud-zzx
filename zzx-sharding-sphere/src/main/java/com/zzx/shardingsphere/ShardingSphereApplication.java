package com.zzx.shardingsphere;

import com.zzx.shardingsphere.service.ExampleService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.sql.SQLException;

//@MapperScan(basePackages = "com.zzx.shardingsphere.repository")
@SpringBootApplication(scanBasePackages = "com.zzx.shardingsphere")
public class ShardingSphereApplication {

    public static void main(String[] args) throws SQLException {
        try (ConfigurableApplicationContext applicationContext = SpringApplication.run(ShardingSphereApplication.class, args)) {
            ExampleExecuteTemplate.run(applicationContext.getBean(ExampleService.class));
        }
        //SpringApplication.run(ShardingSphereApplication.class, args);
    }

}
