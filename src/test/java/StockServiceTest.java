import model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import service.StockService;
import util.ReadStock;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StockServiceTest {

    private ReadStock readStockMock;
    private StockService stockService = new StockService();


    @BeforeEach
    public void init() throws IOException {
        readStockMock = Mockito.mock(ReadStock.class);
        stockService.setReadStock(readStockMock);

        Mockito.when(readStockMock.readAndMapJsonToListProducts()).
                thenReturn((List<Product>) List.of(new Product("F", 1.25, 3, 3.0),
                                                   new Product("N", 4.25, 0, 0.0)));
    }

    @Test
    public void printBasketCost() {
        stockService.printBasketCost("FN");
    }

    @Test
    public void calculateTotalCost() {
        Map<String, Double> checkValue = new HashMap<>();
        checkValue.put("F", 1.25);
        checkValue.put("FFF", 3.0);
        checkValue.put("FFFF", 4.25);
        checkValue.put("FFFFNN", 12.75);

        checkValue.forEach((key, value) ->
                assertEquals(Double.valueOf(stockService.calculateTotalCost(key)), checkValue.get(key)));
    }

    @Test
    public void calculateTotalCost_CheckExceptions() {
        Map<String, String> checkExceptions = new HashMap<>();
        checkExceptions.put("", "You basket is empty");
        checkExceptions.put(null, "You basket is empty");
        checkExceptions.put("FKLMNVX", "There is unknown product in your basket");

        checkExceptions.forEach((key, value) ->
                assertEquals(checkExceptions.get(key), stockService.calculateTotalCost(key)));
    }

    @Test
    public void calculate() {
        Product productA = new Product("A", 1.25, 3, 3.0);
        double resultA = stockService.calculateCostOfOneTypeProduct(productA, 1);
        assertEquals(resultA, 1.25);

        double resultAAA = stockService.calculateCostOfOneTypeProduct(productA, 3);
        assertEquals(resultAAA, 3.0);

        double resultAAAA = stockService.calculateCostOfOneTypeProduct(productA, 4);
        assertEquals(resultAAAA, 4.25);
    }
}
