package toyblog.june;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class JuneApplication {

    public static void main(String[] args) {
        SpringApplication.run(JuneApplication.class, args);
    }

}
