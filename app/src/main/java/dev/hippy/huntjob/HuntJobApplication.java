package dev.hippy.huntjob;

import java.util.Locale;
import net.datafaker.Faker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HuntJobApplication {

    public static void main(String[] args) {
        SpringApplication.run(HuntJobApplication.class, args);
    }

    @Bean
    public Faker getFaker() {
        return new Faker(Locale.US);
    }
}
