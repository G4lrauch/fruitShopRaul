package com.raul.fruitShop.components.loadshopdata;

import com.raul.fruitShop.data.ShopPricesData;
import com.raul.fruitShop.data.ShopPurchaseData;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Component
public class LoadShopDataManager {

    public List<ShopPricesData> loadStorePricesData(Stream<String> streamPrices) {
        return streamPrices
                .skip(1)
                .map(line -> {
                    String[] splitted = line.split(",");
                    return new ShopPricesData(splitted[0].trim(), Double.parseDouble([1].trim()));
                })
                .collect(Collectors.toList());
    }

    public List<ShopPurchaseData> loadStorePurchaseData(Stream<String> streamPurchase) {
        return streamPurchase
                .skip(1)
                .map(line -> {
                    String[] splitted = line.split(",");
                    return new ShopPurchaseData(splitted[0].trim(), Double.parseDouble([1].trim()));
                })
                .collect(Collectors.toList());
    }

}
