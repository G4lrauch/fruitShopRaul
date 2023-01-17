package com.raul.fruitShop.managers;

import com.raul.fruitShop.components.filereader.FileReaderManager;
import com.raul.fruitShop.components.loadshopdata.LoadShopData;
import com.raul.fruitShop.config.AppConfig;
import com.raul.fruitShop.data.FruitShopDatabase;
import com.raul.fruitShop.data.ShopTicketData;
import com.raul.fruitShop.exceptions.FruitShopException;
import com.raul.fruitShop.services.FruitShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Component
public class FruitShopManager implements FruitShopService {

    @Autowired
    private FileReaderManager loadFile;

    @Autowired
    private LoadShopData transformData;

    @Autowired
    private AppConfig config;

    @Autowired
    private FruitShopDatabase database;

    @Autowired
    private ShopTicketData ticket;

    private void populateData() throws FruitShopException {
        Stream<String> pricesFile = loadFile.readFileAsStream(config.getStorePricesFile());
        database.setPrices(transformData.getShopPricesData(pricesFile));
        Stream<String> purchaseFile = loadFile.readFileAsStream(config.getStorePurchaseFile());
        database.setPurchase(transformData.getShopPurchaseData(purchaseFile));
    }

    private void doCalculateTicket() throws FruitShopException {

    }

    @Override
    public void printTicketOfPurchase() throws FruitShopException {
        populateData();
        doCalculateTicket();

        //mock data
        ticket.setTotalPrice(20.5);
        List<String> productsPurchased = database.getPurchase()
                .stream()
                .map(item -> item.getProduct())
                .collect(toList());
        ticket.setProductsPurchased(productsPurchased);

        System.out.println("Total price: " + ticket.getTotalPrice());
        System.out.println("Products purchased: ");
        ticket.getProductsPurchased().forEach(System.out::println);
    }

}
