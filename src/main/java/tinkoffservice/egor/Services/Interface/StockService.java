package tinkoffservice.egor.Services.Interface;

import tinkoffservice.egor.Dto.StockPrice;
import tinkoffservice.egor.Dto.StockPricesDto;
import tinkoffservice.egor.Dto.StocksDto;
import tinkoffservice.egor.Enittys.Stock;

import java.util.List;

public interface StockService {
    Stock getStockByTicker(String ticker);

    StocksDto getStocksByTickers(List<String> tickers);

    StockPricesDto getPrices(List<String> figi);
}
