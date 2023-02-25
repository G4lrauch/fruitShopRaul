package com.raul.fruitShop.data;

import com.raul.fruitShop.exceptions.FruitShopException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;


@SpringBootTest(classes = FruitShopDatabase.class)
class FruitShopDatabase_Should {

    @Autowired
    FruitShopDatabase database;

    @BeforeEach
    void initialData() {
        List<ShopItemPriceData> pricesList = new ArrayList<>();
        pricesList.add(new ShopItemPriceData("Pear", 0.25));
        pricesList.add(new ShopItemPriceData("Orange", 0.75));
        database.setItemsPrice(pricesList);
    }

    @Test
    void return_exception_if_null_name_provided_in_findItemPriceByItemName() {
        assertThrowsExactly(FruitShopException.class, () -> database.findItemPriceByItemName(null));
    }

    @Test
    void return_exception_if_no_name_provided_in_findItemPriceByItemName() {
        assertThrowsExactly(FruitShopException.class, () -> database.findItemPriceByItemName(""));
    }

    @Test
    void return_exception_if_no_item_found_in_findItemPriceByItemName() {
        assertThrowsExactly(FruitShopException.class, () -> database.findItemPriceByItemName("Banana"));
    }

    @Test
    void return_price_of_item_found_in_findItemPriceByItemName() throws FruitShopException {
        assertEquals(0.25, database.findItemPriceByItemName("Pear"));
    }

    @Test
    void return_price_of_item_found_ignoring_case_in_findItemPriceByItemName() throws FruitShopException {
        assertEquals(0.25, database.findItemPriceByItemName("pear"));
    }

}
