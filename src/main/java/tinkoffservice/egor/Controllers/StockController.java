package tinkoffservice.egor.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;
import tinkoffservice.egor.Dto.StockPricesDto;
import tinkoffservice.egor.Dto.StocksDto;
import tinkoffservice.egor.Enittys.Stock;
import tinkoffservice.egor.Services.Interface.StockService;
import tinkoffservice.egor.Services.StockServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StockController {
    private final StockService stockServiceImpl;

    @Autowired
    public StockController(StockServiceImpl stockServiceImpl) {
        this.stockServiceImpl = stockServiceImpl;
    }

    @GetMapping("/stocks/{ticker}")
    public Stock getStock(@PathVariable String ticker){
        return stockServiceImpl.getStockByTicker(ticker);
    }

    @PostMapping("/stocks/stocksByTikers")
    public StocksDto getStocksByTickers(@RequestBody List<String> tickers) {
        return stockServiceImpl.getStocksByTickers(tickers);
    }

    @PostMapping("/prices")
    public StockPricesDto getPrices(@RequestBody List<String> figi) {
        return stockServiceImpl.getPrices(figi);
    }
}
