package tinkoffservice.egor.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.invest.openapi.OpenApi;
import ru.tinkoff.invest.openapi.okhttp.OkHttpOpenApi;

@Configuration
@EnableConfigurationProperties(ApiConfig.class)
public class ApplicationConfig {
    private final ApiConfig apiConfig;

    @Autowired
    public ApplicationConfig(ApiConfig apiConfig) {
        this.apiConfig = apiConfig;
    }

    @Bean
    public OpenApi api() {
        String ssoToken = System.getenv("ssoToken");
        return new OkHttpOpenApi(ssoToken,apiConfig.getIsSendBoxMod());
    }
}
