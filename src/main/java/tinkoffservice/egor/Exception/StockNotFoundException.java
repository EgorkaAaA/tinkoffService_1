package tinkoffservice.egor.Exception;

public class StockNotFoundException extends RuntimeException {
    public StockNotFoundException(String ticker) {
        super(String.format("Stock with ticker: %s not found", ticker));
    }
}