package sms.com.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ComponentScan("sms.com")
@ImportResource(value = {"classpath:aggregators.xml"})
public class Context {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Context.class, args);
        System.out.println(ctx.getBeanDefinitionCount());
    }


}
