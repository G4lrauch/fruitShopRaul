package com.raul.fruitShop.data;

import lombok.*;


@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@ToString
public class ShopItemCartData {

    private String product;
    private Double quantity;

    public enum ShopPurchaseLabels {
        PRODUCT,
        QUANTITY
    }

}
