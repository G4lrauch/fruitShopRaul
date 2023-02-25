package com.raul.fruitShop.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class ShopTicketData {

    private double totalPrice;
    private List<ShopPurchasedItemData> productsPurchased;


    public ShopTicketData() {
        this.totalPrice = 0.0;
        this.productsPurchased = new ArrayList<>();
    }

    public ShopTicketData(List<ShopPurchasedItemData> productsPurchased) {
        this.productsPurchased = productsPurchased;
        this.calculateTotalPrice();
    }

    public double calculateTotalPrice(){
        if(!productsPurchased.isEmpty()) {
            this.totalPrice = productsPurchased.stream()
                            .map(ShopPurchasedItemData::getPrice)
                            .reduce(0.0, Double::sum);
        }
        return this.totalPrice;
    }

}
