package com.raul.fruitShop.data;

import lombok.*;


@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ShopPurchasedItemData {

    private String product;
    private Double quantity;
    private Double price;

}
