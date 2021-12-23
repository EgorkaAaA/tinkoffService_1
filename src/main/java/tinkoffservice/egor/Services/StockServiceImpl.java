package tinkoffservice.egor.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.tinkoff.invest.openapi.MarketContext;
import ru.tinkoff.invest.openapi.OpenApi;
import ru.tinkoff.invest.openapi.model.rest.MarketInstrumentList;
import ru.tinkoff.invest.openapi.model.rest.Orderbook;
import tinkoffservice.egor.Dto.StockPrice;
import tinkoffservice.egor.Dto.StockPricesDto;
import tinkoffservice.egor.Dto.StocksDto;
import tinkoffservice.egor.Enittys.Currency;
import tinkoffservice.egor.Enittys.Stock;
import tinkoffservice.egor.Exception.StockNotFoundException;
import tinkoffservice.egor.Services.Interface.StockService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {
    private final OpenApi openApi;

    @Async
    public CompletableFuture<MarketInstrumentList> getMarketInstrumentTickers(String ticker) {
        MarketContext context = openApi.getMarketContext();
        return context.searchMarketInstrumentsByTicker(ticker);
    }

    @Override
    public Stock getStockByTicker(String ticker) {
        CompletableFuture<MarketInstrumentList> cf = getMarketInstrumentTickers(ticker);
        var list = cf.join().getInstruments();

        if(list.isEmpty()) {
            throw new StockNotFoundException(ticker);
        }
        var item = list.get(0);

        return new Stock(
                item.getTicker(),
                item.getFigi(),
                item.getName(),
                item.getType().getValue(),
                Currency.valueOf(item.getCurrency().getValue()),
                "TINKOFF");
    }

    @Override
    public StocksDto getStocksByTickers(List<String> tickers) {
        List<CompletableFuture<MarketInstrumentList>> marketInstruments = new ArrayList<>();
        tickers.forEach(ticker -> marketInstruments.add(getMarketInstrumentTickers(ticker)));
        List<Stock> stocks = marketInstruments
                .stream()
                .map(CompletableFuture::join)
                .map(mi -> {
                    if(!mi.getInstruments().isEmpty()) {
                        return mi.getInstruments().get(0);
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .map(mi -> new Stock(
                        mi.getTicker(),
                        mi.getFigi(),
                        mi.getName(),
                        mi.getType().getValue(),
                        Currency.valueOf(mi.getCurrency().getValue()),
                        "TINKOFF")
                )
                .collect(Collectors.toList());
        return new StocksDto(stocks);
    }

    @Async
    public CompletableFuture<Optional<Orderbook>> getPrice(String figi) {
        var orderBook = openApi.getMarketContext().getMarketOrderbook(figi,0);
        return orderBook;
    }

    @Override
    public StockPricesDto getPrices(List<String> figis) {
        List<CompletableFuture<Optional<Orderbook>>> orderBooks = new ArrayList<>();
        figis.forEach(figi -> orderBooks.add(getPrice(figi)));
        List<StockPrice> prices =  orderBooks.stream()
                .map(CompletableFuture::join)
                .map(oo -> oo.orElseThrow(() -> new StockNotFoundException(oo.toString())))
                .map(orderBook -> new StockPrice(
                        orderBook.getFigi(),
                        orderBook.getLastPrice().doubleValue())).collect(Collectors.toList());

        return new StockPricesDto(prices);
    }
}
