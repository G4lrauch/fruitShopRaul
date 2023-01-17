package com.raul.fruitShop.components.filereader;

import com.raul.fruitShop.exceptions.FruitShopException;

import java.util.stream.Stream;


public interface FileReader {

    public Stream<String> readFileAsStream(String fileName) throws FruitShopException;

}
