package me.iankit.simplechat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class SimplechatApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimplechatApplication.class, args);
    }

}
