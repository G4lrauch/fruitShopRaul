package com.raul.fruitShop.components.loadshopdata;

import com.raul.fruitShop.data.ShopPricesData;
import com.raul.fruitShop.data.ShopPurchaseData;
import com.raul.fruitShop.exceptions.FruitShopException;

import java.util.List;
import java.util.stream.Stream;

public interface LoadShopData {

    public List<ShopPricesData> getShopPricesData(Stream<String> streamPrices) throws FruitShopException;
    public List<ShopPurchaseData> getShopPurchaseData(Stream<String> streamPurchase) throws FruitShopException;

}
