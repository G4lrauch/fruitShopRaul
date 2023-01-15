package com.raul.fruitShop.components.filereader;

import com.raul.fruitShop.Exceptions.FruitShopException;

import java.nio.file.Path;
import java.util.stream.Stream;


public interface FileReader {

    public Stream<String> readFileAsStream(String file) throws FruitShopException;

}
