package com.raul.fruitShop.components.loadshopdata;

import com.raul.fruitShop.data.ShopItemCartData;
import com.raul.fruitShop.data.ShopItemPriceData;
import com.raul.fruitShop.exceptions.FruitShopException;

import java.util.List;

public interface LoadShopData {

    public List<ShopItemPriceData> getShopPricesData(List<String> listPrices) throws FruitShopException;
    public List<ShopItemCartData> getShopPurchaseCartData(List<String> listPurchaseCart) throws FruitShopException;

}
