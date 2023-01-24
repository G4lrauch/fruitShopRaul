package com.raul.fruitShop.data;

import com.raul.fruitShop.exceptions.FruitShopException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;


@Getter
@Setter
@ToString
@Component
public class FruitShopDatabase {

    private List<ShopItemPriceData> itemsPrice;
    private List<ShopItemCartData> itemsCart;
    private ShopTicketData ticket;

    public FruitShopDatabase() {
        this.itemsPrice = new ArrayList<>();
        this.itemsCart = new ArrayList<>();
    }

    private BiPredicate<ShopItemPriceData, String> itemExists = (item, name) -> item.getProduct().equalsIgnoreCase(name);

    private BiPredicate<List<ShopItemPriceData>, String> itemExistsInList =
            (list, name) -> list.stream()
                                .anyMatch(item -> itemExists.test(item, name));

    public Double findItemPriceByItemName(String itemName) throws FruitShopException {
        if(StringUtils.isBlank(itemName)) {
            throw new FruitShopException("No item name provided");
        }
        if(!itemExistsInList.test(itemsPrice, itemName)) {
            throw new FruitShopException("Item '" + itemName + "' not found in stock");
        }

        Optional<Double> price = itemsPrice.stream()
                .filter(item -> itemExists.test(item, itemName))
                .map(ShopItemPriceData::getPrice)
                .findFirst();

        if(price.isPresent()) {
            return  price.get();
        } else {
            throw new FruitShopException("Not throwable exception");
        }
    }

}
