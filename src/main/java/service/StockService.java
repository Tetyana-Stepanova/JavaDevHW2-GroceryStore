package service;

import model.Product;
import util.ReadStock;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StockService {
    private ReadStock readStock = new ReadStock();

    public void setReadStock(ReadStock readStock){
        this.readStock = readStock;
    }

    public void printBasketCost(String basket) {
        System.out.println("Basket cost = " + calculateTotalCost(basket));
    }

    public String calculateTotalCost(String basket) {
        if (basket == null || basket.isEmpty()) {
            return "You basket is empty";
        } else {
            String basketOptimized = basket.replaceAll("[^a-zA-Z]", "");
            List<String> productsInBasket = new ArrayList<String>(Arrays.asList(basketOptimized.split("")));
            Map<String, Long> basketMap = (Map<String, Long>) productsInBasket.stream().
                    collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            List<Product> actualStock;
            try {
                actualStock = readStock.readAndMapJsonToListProducts();
            } catch (IOException exception) {
                return "Server can't read the file";
            }

            double totalCost = 0.0;
            for (String key : basketMap.keySet()) {
                if (actualStock.stream().noneMatch(product -> product.getProductName().equals(key))) {
                    return "There is unknown product in your basket";
                } else {
                    Product productByKey = actualStock.stream().filter(product -> product.getProductName().equals(key)).findFirst().get();
                    totalCost += calculateCostOfOneTypeProduct(productByKey, basketMap.get(key).intValue());
                }
            }
            return String.valueOf(totalCost);
        }
    }

    public double calculateCostOfOneTypeProduct(Product productByKey, Integer productQuantity) {
        double cost;
        if (productByKey.hasPromotion()) {
            cost = calculatePromotionCost(productByKey, productQuantity);
        } else {
            cost = productQuantity * productByKey.getPrice();
        }
        return cost;

    }

    private double calculatePromotionCost(Product product, Integer quantity) {
        double costOfPromProduct;
        if (quantity % product.getPromAmount() == 0) {
            costOfPromProduct = ((int) quantity / product.getPromAmount()) * product.getPromPrice();
        } else {
            costOfPromProduct = (Math.floor(quantity / product.getPromAmount())) * product.getPromPrice() +
                    (quantity % product.getPromAmount()) * product.getPrice();
        }
        return costOfPromProduct;
    }
}
