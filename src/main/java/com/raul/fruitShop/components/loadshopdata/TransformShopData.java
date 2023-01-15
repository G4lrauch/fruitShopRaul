package com.raul.fruitShop.components.loadshopdata;

import com.raul.fruitShop.data.ShopPricesData;
import com.raul.fruitShop.data.ShopPurchaseData;

import java.util.List;
import java.util.stream.Stream;

public interface LoadShopData {

    public List<ShopPricesData> loadStorePricesData(Stream<String> streamPrices);
    public List<ShopPurchaseData> loadStorePurchaseData(Stream<String> streamPurchase);

}
