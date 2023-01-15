package com.raul.fruitShop.data;

import lombok.*;

import java.util.Map;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StorePurchaseData {

    private Map<String, Double> storePrices;

}
