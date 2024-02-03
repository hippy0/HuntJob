package dev.hippy.huntjob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HuntJobApplication {

    public static void main(String[] args) {
        SpringApplication.run(HuntJobApplication.class, args);
    }
}
