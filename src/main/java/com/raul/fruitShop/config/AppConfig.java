package com.raul.fruitShop.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
@Getter
public class AppConfig {

    @Value( "${store.prices.file.name}" )
    public String storePricesFile;

    @Value( "${store.purchase.file.name}" )
    public String storePurchaseFile;

    @Value("${store.file.item.separator}")
    public String fileItemSeparator;

}
