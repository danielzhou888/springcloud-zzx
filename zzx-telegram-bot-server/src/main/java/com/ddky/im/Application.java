package com.ddky.im;

import com.ddky.im.poldaswap.BotExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import java.net.UnknownHostException;


@SpringBootApplication(scanBasePackages = "com.ddky.im", exclude = {DataSourceAutoConfiguration.class})
//@EnableDubbo
@ServletComponentScan
@EnableAspectJAutoProxy(proxyTargetClass = true)
//@ConditionalOnClass(SpringfoxWebMvcConfiguration.class)
@Slf4j
//@EnableEncrypt
public class Application extends SpringBootServletInitializer {


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) throws UnknownHostException {

        com.ddky.im.communityeconomy.BotExecutor.sendMsg2Group();

//        ConfigurableApplicationContext application=SpringApplication.run(Application.class, args);
//        Environment env = application.getEnvironment();
//        log.info("\n----------------------------------------------------------\n\t" +
//                        "Application '{}' is running! Access URLs:\n\t" +
//                        "Local: \t\thttp://localhost:{}\n\t" +
//                        "External: \thttp://{}:{}\n\t"+
//                        "Doc: \thttp://{}:{}/doc.html\n"+
//                        "----------------------------------------------------------",
//                env.getProperty("spring.application.name"),
//                env.getProperty("server.port"),
//                InetAddress.getLocalHost().getHostAddress(),
//                env.getProperty("server.port"),
//                InetAddress.getLocalHost().getHostAddress(),
//                env.getProperty("server.port"));

    }



}
