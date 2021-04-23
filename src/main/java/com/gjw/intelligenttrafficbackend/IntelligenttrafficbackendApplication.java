package com.gjw.intelligenttrafficbackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.gjw.intelligenttrafficbackend.dao")
public class IntelligenttrafficbackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntelligenttrafficbackendApplication.class, args);
    }

}
