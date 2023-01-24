package com.raul.fruitShop.data;

import lombok.*;


@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@ToString
public class ShopItemPriceData {

    private String product;
    private Double price;

    public enum ShopPricesLabels {
        PRODUCT,
        PRICE
    }

}
