package com.raul.fruitShop.data;

import lombok.*;

import java.util.Map;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StorePricesData {

    private Map<String, Double> storePrices;

}
