package com.raul.fruitShop.data;

import lombok.*;


@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@ToString
public class ShopPurchaseData {

    private String product;
    private Double quantity;

}
