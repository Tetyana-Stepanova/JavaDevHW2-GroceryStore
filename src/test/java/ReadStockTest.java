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
        //printing shouldn't be a part of the test
        //we have to use asserts to compare with expected values
        productList.forEach(System.out::println);
    }
}
