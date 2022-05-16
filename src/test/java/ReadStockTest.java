import model.Product;
import org.junit.jupiter.api.Test;
import util.ReadStock;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class ReadStockTest {
    ReadStock readStock = new ReadStock();

    @Test
    public void readAndMapJsonToListProducts() throws IOException {
        List<Product> productList = readStock.readAndMapJsonToListProducts();
        assertFalse(productList.isEmpty());
        productList.forEach(System.out::println);
    }
}
