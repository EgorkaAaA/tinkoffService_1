package tinkoffservice.egor.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import tinkoffservice.egor.Enittys.Stock;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class StocksDto {
    public List<Stock> stocks;
}
