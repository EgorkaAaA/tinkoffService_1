package tinkoffservice.egor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableConfigurationProperties
@EnableAsync(proxyTargetClass = true)
public class TinkoffServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TinkoffServiceApplication.class, args);
    }

}
