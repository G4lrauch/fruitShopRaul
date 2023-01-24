package com.raul.fruitShop.services;


import com.raul.fruitShop.data.ShopTicketData;
import com.raul.fruitShop.exceptions.FruitShopException;

public interface FruitShopService {

    public ShopTicketData calculateTicketOfPurchase() throws FruitShopException;

    public void printTicketOfPurchase();

}
