import model.Product;
import org.junit.jupiter.api.Test;
import util.ReadStock;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ReadStockTest {
    ReadStock readStock = new ReadStock();

    @Test
    public void readAndMapJsonToListProducts() throws IOException {
        List<Product> productList = readStock.readAndMapJsonToListProducts();
        assertFalse(productList.isEmpty());

        List<Product> productListTest = List.of(new Product("A", 1.25, 3, 3.00),
                                                new Product("B", 4.25, 0, 0.00),
                                                new Product("C", 1.00, 6, 5.00),
                                                new Product("D", 0.75, 0, 0.00));
        for(int i = 0; i < productListTest.size(); i++){
            assertEquals(productList.get(i), productListTest.get(i));
        }
    }
}
