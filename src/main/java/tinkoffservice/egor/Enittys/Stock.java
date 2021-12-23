package tinkoffservice.egor.Enittys;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import org.springframework.web.bind.annotation.GetMapping;

@Value
@AllArgsConstructor
public class Stock {
    private String ticker;

    private String figi;

    private String name;

    private String type;

    private Currency currency;

    private String source;
}
