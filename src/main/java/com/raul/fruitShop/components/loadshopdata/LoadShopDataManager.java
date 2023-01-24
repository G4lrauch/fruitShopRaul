package com.raul.fruitShop.components.loadshopdata;

import com.raul.fruitShop.config.AppConfig;
import com.raul.fruitShop.data.ShopItemCartData;
import com.raul.fruitShop.data.ShopItemPriceData;
import com.raul.fruitShop.exceptions.FruitShopException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;


@Log4j2
@Component
public class LoadShopDataManager implements LoadShopData {

    @Autowired
    private AppConfig config;

    private final Function<String, List<String>> splitStringLineToList = line ->
            Arrays.stream(line.split(config.getFileItemSeparator()))
                    .map(String::trim)
                    .collect(toList());

    private final Function<String, ShopItemPriceData> transformStringLineToItemPriceData = line ->
            new ShopItemPriceData(splitStringLineToList.apply(line).get(0),
                    Double.parseDouble(splitStringLineToList.apply(line).get(1)));

    private final Function<String, ShopItemCartData> transformStringLineToItemCartData = line ->
            new ShopItemCartData(splitStringLineToList.apply(line).get(0),
                Double.parseDouble(splitStringLineToList.apply(line).get(1)));

    private final Predicate<List<String>> inputListCanBeTransformToItemCartData = list -> {
        List<String> header = splitStringLineToList.apply(list.get(0));
        return header.get(0).equals(ShopItemCartData.ShopPurchaseLabels.PRODUCT.toString()) &&
                header.get(1).equals(ShopItemCartData.ShopPurchaseLabels.QUANTITY.toString());
    };

    private final Predicate<List<String>> inputListCanBeTransformToItemPriceData = list -> {
        List<String> header = splitStringLineToList.apply(list.get(0));
        return header.get(0).equals(ShopItemPriceData.ShopPricesLabels.PRODUCT.toString()) &&
                header.get(1).equals(ShopItemPriceData.ShopPricesLabels.PRICE.toString());
    };


    @Override
    public List<ShopItemPriceData> getShopPricesData(List<String> listPrices) throws FruitShopException {
        try {
            if(listPrices == null) {
                throw new FruitShopException("List of purchased items provided is null");
            }
            if(!inputListCanBeTransformToItemPriceData.test(listPrices)) {
                throw new FruitShopException("List provided cannot be transformed in item prices");
            }
            return listPrices
                    .stream()
                    .skip(1)
                    .map(transformStringLineToItemPriceData::apply)
                    .collect(toList());
        } catch (FruitShopException e) {
            log.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error transforming shop data: " + e.getMessage());
            throw new FruitShopException("Error transforming shop data");
        }
    }

    @Override
    public List<ShopItemCartData> getShopPurchaseCartData(List<String> listPurchaseCart) throws FruitShopException {
        try {
            if(listPurchaseCart == null) {
                throw new FruitShopException("List of purchased items provided is null");
            }
            if(!inputListCanBeTransformToItemCartData.test(listPurchaseCart)) {
                throw new FruitShopException("List provided cannot be transformed in item cart");
            }
            return listPurchaseCart
                    .stream()
                    .skip(1)
                    .map(transformStringLineToItemCartData::apply)
                    .collect(toList());
        } catch (FruitShopException e) {
            log.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error transforming shop data: " + e.getMessage());
            throw new FruitShopException("Error transforming shop data");
        }
    }

}
