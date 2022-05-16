import service.StockService;

public class Main {
    private static StockService stockService = new StockService();

    public static void main(String[] args) {

        stockService.printBasketCost("");
        stockService.printBasketCost("ABCD");
        stockService.printBasketCost("AAABCCCCCCD");
        stockService.printBasketCost("ABCDFGH");
        stockService.printBasketCost(null);

    }
}
