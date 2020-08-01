package cn.weidea.wesports;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

@MapperScan("cn.weidea.wesports.mapper")
@SpringBootApplication
@EnableDubbo
public class orderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(orderServiceApplication.class, args);
    }
}
