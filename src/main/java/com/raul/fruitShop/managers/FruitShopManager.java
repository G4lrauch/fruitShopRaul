package com.raul.fruitShop.managers;

import com.raul.fruitShop.components.filereader.FileReaderManager;
import com.raul.fruitShop.components.loadshopdata.LoadShopData;
import com.raul.fruitShop.config.AppConfig;
import com.raul.fruitShop.data.FruitShopDatabase;
import com.raul.fruitShop.data.ShopPurchasedItemData;
import com.raul.fruitShop.data.ShopTicketData;
import com.raul.fruitShop.exceptions.FruitShopException;
import com.raul.fruitShop.mappers.FruitShopMapper;
import com.raul.fruitShop.services.FruitShopService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Log4j2
@Service
public class FruitShopManager implements FruitShopService {

    @Autowired
    private FileReaderManager loadFile;

    @Autowired
    private LoadShopData transformData;

    @Autowired
    private FruitShopMapper mapper;

    @Autowired
    private AppConfig config;

    @Autowired
    private FruitShopDatabase database;


    private void populateDataBase() throws FruitShopException {
        List<String> pricesFile = loadFile.readFileAsList(config.getStorePricesFile());
        database.setItemsPrice(transformData.getShopPricesData(pricesFile));
        List<String> purchaseFile = loadFile.readFileAsList(config.getStorePurchaseFile());
        database.setItemsCart(transformData.getShopPurchaseCartData(purchaseFile));
    }

    private ShopPurchasedItemData calculatePriceOfPurchasedItem(ShopPurchasedItemData item) {
        try {
            Double itemUnitPrice = database.findItemPriceByItemName(item.getProduct());
            ShopPurchasedItemData newItemData = mapper.map(item);
            newItemData.setPrice(item.getQuantity() * itemUnitPrice);
            return newItemData;
        } catch (FruitShopException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public ShopTicketData calculateTicketOfPurchase() throws FruitShopException {
        List<ShopPurchasedItemData> purchasedItems = mapper.map(database.getItemsCart());
        List<ShopPurchasedItemData> purchasedItemsWithPrice = purchasedItems
                .stream()
                .map(this::calculatePriceOfPurchasedItem)
                .collect(toList());
        return new ShopTicketData(purchasedItemsWithPrice);
    }

    @Override
    public void printTicketOfPurchase() {
        try {
            populateDataBase();
            ShopTicketData ticket = calculateTicketOfPurchase();
            database.setTicket(ticket);

            System.out.println("Products purchased: ");
            ticket.getProductsPurchased()
                    .forEach(product ->
                            System.out.printf("\t%s\t%.2f Kg\t%.2f E %n",
                                    product.getProduct(), product.getQuantity(), product.getPrice()));
            System.out.println("Total price: " + ticket.getTotalPrice());
        } catch (Exception e) {
            System.out.println("Errors encountered when generating the ticket, check log");
        }
    }

}
