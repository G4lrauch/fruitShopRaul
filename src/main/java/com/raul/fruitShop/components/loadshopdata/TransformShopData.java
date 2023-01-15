package com.raul.fruitShop.components.loadshopdata;

import com.raul.fruitShop.data.ShopPricesData;
import com.raul.fruitShop.data.ShopPurchaseData;

import java.util.List;
import java.util.stream.Stream;

public interface TransformShopData {

    public List<ShopPricesData> getPricesData(Stream<String> streamPrices);
    public List<ShopPurchaseData> getPurchaseData(Stream<String> streamPurchase);

}
