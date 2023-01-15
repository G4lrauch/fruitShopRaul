package com.raul.fruitShop.data;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ShopTicketData {

    private double totalPrice;
    private List<String> productsPurchased;
    private List<String> offersApplied;

}
