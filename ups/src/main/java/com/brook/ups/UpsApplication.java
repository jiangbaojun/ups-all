package com.brook.ups;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass=true)
@EnableSwagger2
public class UpsApplication {

    public static void main(String[] args) {
        SpringApplication.run(UpsApplication.class, args);
    }

}
