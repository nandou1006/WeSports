package cn.weidea.wesports.service;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

import java.io.IOException;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@EnableDubbo
@Slf4j
public class FaceServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FaceServiceApplication.class, args);
        try {
            System.in.read();
        } catch (IOException e) {
            log.error("{}", e);
        }
    }

}
