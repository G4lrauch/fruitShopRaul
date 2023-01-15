package com.raul.fruitShop.components.filereader;

import com.raul.fruitShop.Exceptions.FruitShopException;

import java.util.stream.Stream;


public interface LoadFileData {

    Stream<String> readFileAsStream(String fileName) throws FruitShopException;

}
