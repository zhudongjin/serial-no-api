package com.jiji.serial;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.jiji", "com.jiji.common"})
@MapperScan("com.jiji.serial.**.mapper")
@EnableFeignClients(basePackages = "com.jiji")
@EnableDiscoveryClient
@RefreshScope
@ServletComponentScan(basePackages = {"com.jiji", "com.jiji.common"})
public class SerialNoApplication {

    public static void main(final String[] args) {
        SpringApplication.run(SerialNoApplication.class, args);
    }

}
