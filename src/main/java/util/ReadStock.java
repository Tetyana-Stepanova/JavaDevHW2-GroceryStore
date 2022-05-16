package util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Product;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ReadStock {

    public List<Product> readAndMapJsonToListProducts() throws IOException {
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/stock.json"));
        List<Product> products = gson.fromJson(reader, new TypeToken<List<Product>>() {}.getType());
        reader.close();
        return products;
    }
}