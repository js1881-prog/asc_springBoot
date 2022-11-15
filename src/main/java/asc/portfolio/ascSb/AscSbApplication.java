package asc.portfolio.ascSb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AscSbApplication {

    public static void main(String[] args) {
        SpringApplication.run(AscSbApplication.class, args);
    }

}
