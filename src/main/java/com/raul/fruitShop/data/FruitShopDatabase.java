package com.raul.fruitShop.data;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
public class FruitShopDatabase {

    private List<ShopPricesData> prices;
    private List<ShopPurchaseData> purchase;

}
