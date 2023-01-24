package com.raul.fruitShop.data;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(classes = ShopTicketData.class)
class ShopTicketData_Should {

    ShopTicketData initialData() {
        List<ShopPurchasedItemData> products = new ArrayList<>();
        products.add(new ShopPurchasedItemData("Apple", 3.0, 3.0));
        products.add(new ShopPurchasedItemData("Orange", 1.5, 1.5));
        products.add(new ShopPurchasedItemData("Pear", 1.5, 1.5));
        return new ShopTicketData(products);
    }

    @Test
    void sum_correct_all_purchased_items_prices(){
        ShopTicketData ticketData = initialData();
        assertEquals(6.0, ticketData.getTotalPrice());
    }

    @Test
    void sum_correct_with_not_purchased_items(){
        ShopTicketData ticketData = new ShopTicketData();
        ticketData.calculateTotalPrice();
        assertEquals(0.0, ticketData.getTotalPrice());
    }

}
