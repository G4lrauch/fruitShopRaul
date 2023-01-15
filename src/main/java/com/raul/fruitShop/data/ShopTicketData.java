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
public class ShopTicketData {

    private double totalPrice;
    private List<String> productsPurchased;
    private List<String> offersApplied;

}
