package com.raul.fruitShop.managers;

import com.raul.fruitShop.Exceptions.FruitShopException;
import com.raul.fruitShop.components.filereader.FileReaderManager;
import com.raul.fruitShop.components.loadshopdata.TransformShopData;
import com.raul.fruitShop.config.AppConfig;
import com.raul.fruitShop.data.FruitShopDatabase;
import com.raul.fruitShop.data.ShopTicketData;
import com.raul.fruitShop.services.FruitShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class FruitShopManager implements FruitShopService {

    @Autowired
    private FileReaderManager loadFile;

    @Autowired
    private TransformShopData transformData;

    @Autowired
    private AppConfig config;

    @Autowired
    private FruitShopDatabase database;

    private ShopTicketData ticket;

    private void populateData() throws FruitShopException {
        Stream<String> pricesFile = loadFile.readFileAsStream(config.getStorePricesFile());
        database.setPrices(transformData.getPricesData(pricesFile));
        Stream<String> purchaseFile = loadFile.readFileAsStream(config.getStorePurchaseFile());
        database.setPurchase(transformData.getPurchaseData(purchaseFile));
    }

    private void doCalculateTicket() throws FruitShopException {

    }

    public void printTicketOfPurchase() throws FruitShopException {
        populateData();
        doCalculateTicket();

        //mock data
        ticket.setTotalPrice(20.5);

        System.out.println("TotalPrice: " + ticket.getTotalPrice());
        System.out.println("ProductsPurchased: ");
        ticket.getProductsPurchased().forEach(System.out::println);
    }

}
