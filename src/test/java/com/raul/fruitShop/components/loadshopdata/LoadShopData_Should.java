package com.raul.fruitShop.components.loadshopdata;

import com.raul.fruitShop.data.ShopPricesData;
import com.raul.fruitShop.data.ShopPurchaseData;
import com.raul.fruitShop.exceptions.FruitShopException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class LoadShopData_Should {

    @Autowired
    LoadShopData loadData;

    Comparator<ShopPricesData> shopPricesDataComparator =
            Comparator.comparing(ShopPricesData::getProduct)
                      .thenComparing(ShopPricesData::getPrice);

    Comparator<ShopPurchaseData> shopPurchaseDataComparator =
            Comparator.comparing(ShopPurchaseData::getProduct)
                    .thenComparing(ShopPurchaseData::getQuantity);

    private List<ShopPricesData> expectedListShopPricesData() {
        List<ShopPricesData> result = new ArrayList<>();
        result.add(new ShopPricesData("Pear", 0.75));
        result.add(new ShopPricesData("Apple", 0.9));
        result.add(new ShopPricesData("Orange", 1.0));
        return result;
    }

    private List<ShopPurchaseData> expectedListShopPurchaseData() {
        List<ShopPurchaseData> result = new ArrayList<>();
        result.add(new ShopPurchaseData("Pear", 3.0));
        result.add(new ShopPurchaseData("Orange", 25.0));
        result.add(new ShopPurchaseData("Apple", 12.0));
        return result;
    }

    private Stream<String> streamOfShopPrices() {
        return Arrays.asList("PRODUCT,PRICE","Pear, 0.75","Apple,0.9","Orange,1").stream();
    }

    private Stream<String> streamOfShopPurchase() {
        return Arrays.asList("PRODUCT, QUANTITY","Pear,3","Orange, 25","Apple, 12").stream();
    }


    @Test
    public void in_getShopPricesData_return_a_list_of_ShopPricesData_with_valid_stream() throws FruitShopException {
        List<ShopPricesData> expected = expectedListShopPricesData();
        List<ShopPricesData> result = loadData.getShopPricesData(streamOfShopPrices());

        expected.sort(shopPricesDataComparator);
        result.sort(shopPricesDataComparator);

        assertTrue(expected.containsAll(result));
    }

    @Test
    public void in_getShopPurchaseData_return_a_list_of_ShopPurchaseData_with_valid_stream() throws FruitShopException {
        List<ShopPurchaseData> expected = expectedListShopPurchaseData();
        List<ShopPurchaseData> result = loadData.getShopPurchaseData(streamOfShopPurchase());

        expected.sort(shopPurchaseDataComparator);
        result.sort(shopPurchaseDataComparator);

        assertTrue(expected.containsAll(result));
    }

    @Test
    public void in_getShopPricesData_return_exception_with_null_stream() {
        assertThrowsExactly(FruitShopException.class, () -> loadData.getShopPricesData(null));
    }

    @Test
    public void in_getShopPurchaseData_return_exception_with_null_stream() {
        assertThrowsExactly(FruitShopException.class, () -> loadData.getShopPurchaseData(null));
    }

}
