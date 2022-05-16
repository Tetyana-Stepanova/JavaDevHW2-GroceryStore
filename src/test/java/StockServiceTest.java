import model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.StockService;

import java.util.*;

public class StockServiceTest {

    //the StockService has ReadStock class as internal dependency.
    //you have to use Mockito to mock calls to the class
    //here is simple example https://pastebin.com/1zSjHTC2
    StockService stockService = new StockService();

    @Test
    public void printBasketCost(){
        stockService.printBasketCost("ABCD");
    }

    @Test
    public void calculateTotalCost(){
        Map<String, Double> checkValue = new HashMap<>();
        checkValue.put("ABCD", 7.25);
        checkValue.put("AAABCCCCCCD", 13.0);
        checkValue.put("AAAA", 4.25);
        checkValue.put("CCCCCCC", 6.0);
        checkValue.put("  AAAAA B CCCCCCCC D", 17.5);

        checkValue.forEach((key, value) ->
                Assertions.assertEquals(Double.valueOf(stockService.calculateTotalCost(key)), checkValue.get(key)));
    }

    @Test
    public void calculateTotalCost_CheckExceptions(){
       Map<String, String> checkExceptions = new HashMap<>();
       checkExceptions.put("", "You basket is empty");
       checkExceptions.put(null, "You basket is empty");
       checkExceptions.put("ANMCDTY", "There is unknown product in your basket");

       checkExceptions.forEach((key, value) ->
               Assertions.assertEquals(checkExceptions.get(key), stockService.calculateTotalCost(key)));
    }
    @Test
    public void calculate(){
        Product productA = new Product("A", 1.25, 3, 3.0);
        double resultA = stockService.calculate(productA, 1);
        Assertions.assertEquals(resultA, 1.25);

        double resultAAA = stockService.calculate(productA, 3);
        Assertions.assertEquals(resultAAA, 3.0);

        double resultAAAA = stockService.calculate(productA, 4);
        Assertions.assertEquals(resultAAAA, 4.25);
    }
}
