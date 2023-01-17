package com.raul.fruitShop.components.loadshopdata;

import com.raul.fruitShop.data.ShopPricesData;
import com.raul.fruitShop.data.ShopPurchaseData;
import com.raul.fruitShop.exceptions.FruitShopException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Component
public class LoadShopDataManager implements LoadShopData {

    @Override
    public List<ShopPricesData> getShopPricesData(Stream<String> streamPrices) throws FruitShopException {
        try {
            if(streamPrices == null) {
                throw new FruitShopException("Stream provided is null");
            }
            return streamPrices
                    .skip(1)
                    .map(line -> {
                        String[] splitted = line.split(",");
                        return new ShopPricesData(splitted[0].trim(), Double.parseDouble(splitted[1].trim()));
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("Error transforming shop data: " + e.toString());
            throw new FruitShopException("Error transforming shop data");
        }
    }

    @Override
    public List<ShopPurchaseData> getShopPurchaseData(Stream<String> streamPurchase) throws FruitShopException {
        try {
            if(streamPurchase == null) {
                throw new FruitShopException("Stream provided is null");
            }
            return streamPurchase
                    .skip(1)
                    .map(line -> {
                        String[] splitted = line.split(",");
                        return new ShopPurchaseData(splitted[0].trim(), Double.parseDouble(splitted[1].trim()));
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("Error transforming shop data: " + e.toString());
            throw new FruitShopException("Error transforming shop data");
        }
    }

}
