package tinkoffservice.egor.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Value;

import java.util.List;


@AllArgsConstructor
@Value
public class StockPricesDto {
    List<StockPrice> prices;
}
