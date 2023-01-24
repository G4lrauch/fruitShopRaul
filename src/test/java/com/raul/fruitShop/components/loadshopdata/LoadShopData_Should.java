package com.raul.fruitShop.components.loadshopdata;

import com.raul.fruitShop.config.AppConfig;
import com.raul.fruitShop.data.ShopItemCartData;
import com.raul.fruitShop.data.ShopItemPriceData;
import com.raul.fruitShop.exceptions.FruitShopException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(classes = {LoadShopDataManager.class, AppConfig.class})
class LoadShopData_Should {

    @Autowired
    LoadShopData loadData;

    Comparator<ShopItemPriceData> shopPricesDataComparator =
            Comparator.comparing(ShopItemPriceData::getProduct)
                      .thenComparing(ShopItemPriceData::getPrice);

    Comparator<ShopItemCartData> shopPurchaseDataComparator =
            Comparator.comparing(ShopItemCartData::getProduct)
                    .thenComparing(ShopItemCartData::getQuantity);

    List<ShopItemPriceData> expectedListShopPricesData() {
        List<ShopItemPriceData> result = new ArrayList<>();
        result.add(new ShopItemPriceData("Pear", 0.75));
        result.add(new ShopItemPriceData("Apple", 0.9));
        result.add(new ShopItemPriceData("Orange", 1.0));
        return result;
    }

    List<ShopItemCartData> expectedListShopPurchaseData() {
        List<ShopItemCartData> result = new ArrayList<>();
        result.add(new ShopItemCartData("Pear", 3.0));
        result.add(new ShopItemCartData("Orange", 25.0));
        result.add(new ShopItemCartData("Apple", 12.0));
        return result;
    }

    List<String> listOfShopPrices() {
        return Arrays.asList("PRODUCT,PRICE","Pear, 0.75","Apple,0.9","Orange,1");
    }

    List<String> listOfShopPurchase() {
        return Arrays.asList("PRODUCT, QUANTITY","Pear,3","Orange, 25","Apple, 12");
    }


    @Test
    void return_a_list_of_ShopPricesData_with_valid_list_in_getShopPricesData() throws FruitShopException {
        List<ShopItemPriceData> expected = expectedListShopPricesData();
        List<ShopItemPriceData> result = loadData.getShopPricesData(listOfShopPrices());

        expected.sort(shopPricesDataComparator);
        result.sort(shopPricesDataComparator);

        assertTrue(expected.containsAll(result));
    }

    @Test
    void return_a_list_of_ShopPurchaseData_with_valid_list_in_getShopPurchaseCartData() throws FruitShopException {
        List<ShopItemCartData> expected = expectedListShopPurchaseData();
        List<ShopItemCartData> result = loadData.getShopPurchaseCartData(listOfShopPurchase());

        expected.sort(shopPurchaseDataComparator);
        result.sort(shopPurchaseDataComparator);

        assertTrue(expected.containsAll(result));
    }

    @Test
    void return_exception_with_null_list_in_getShopPricesData() {
        assertThrowsExactly(FruitShopException.class, () -> loadData.getShopPricesData(null));
    }

    @Test
    void return_exception_with_null_list_in_getShopPurchaseCartData() {
        assertThrowsExactly(FruitShopException.class, () -> loadData.getShopPurchaseCartData(null));
    }

    @Test
    void return_exception_with_invalid_list_in_getShopPricesData() {
        assertThrowsExactly(FruitShopException.class, () -> loadData.getShopPricesData(listOfShopPurchase()));
    }

    @Test
    void return_exception_with_invalid_list_in_getShopPurchaseCartData() {
        assertThrowsExactly(FruitShopException.class, () -> loadData.getShopPurchaseCartData(listOfShopPrices()));
    }

}
