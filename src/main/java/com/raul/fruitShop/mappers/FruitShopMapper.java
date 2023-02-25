package com.raul.fruitShop.mappers;

import com.raul.fruitShop.data.ShopItemCartData;
import com.raul.fruitShop.data.ShopPurchasedItemData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel="spring")
public interface FruitShopMapper {

    @Mapping(target = "price", constant = "0.0")
    ShopPurchasedItemData map(ShopItemCartData cartData);

    List<ShopPurchasedItemData> map(List<ShopItemCartData> cartData);

    ShopPurchasedItemData map(ShopPurchasedItemData itemData);

}
