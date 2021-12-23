package tinkoffservice.egor.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@AllArgsConstructor
@Value
public class StockPrice {
    String figi;
    Double price;
}
