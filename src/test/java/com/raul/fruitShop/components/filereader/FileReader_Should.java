package com.raul.fruitShop.components.filereader;

import com.raul.fruitShop.exceptions.FruitShopException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

@SpringBootTest
public class FileReader_Should {

    @Autowired
    FileReader fileReader;

    @Test
    public void read_all_the_file() throws FruitShopException {
        String expected = "PRODUCT,PRICE\nPear, 0.75\nApple,0.9\nOrange,1";

        Stream<String> fileStream = fileReader.readFileAsStream("FruitShopPrices.txt");
        String result = fileStream.collect(Collectors.joining("\n"));

        assertEquals(expected, result);
    }

    @Test
    public void return_exception_with_null_file(){
        assertThrowsExactly(FruitShopException.class, () -> fileReader.readFileAsStream(null));
    }

}
