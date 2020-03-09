package com.zzx.sharding1;

import com.zzx.sharding1.api.ExampleService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.sql.SQLException;

@SpringBootApplication(scanBasePackages = "com.zzx.sharding1")
@MapperScan(value = "com.zzx.sharding1.mapper")
public class ZzxSharding1Application {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(ZzxSharding1Application.class, args);

        //try (ConfigurableApplicationContext applicationContext = SpringApplication.run(ZzxSharding1Application.class, args)) {
        //    ExampleExecuteTemplate.run(applicationContext.getBean(ExampleService.class));
        //}
    }



}
