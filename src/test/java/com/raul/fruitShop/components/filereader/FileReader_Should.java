package com.raul.fruitShop.components.filereader;

import com.raul.fruitShop.exceptions.FruitShopException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

@SpringBootTest(classes = FileReaderManager.class)
class FileReader_Should {

    @Autowired
    FileReader fileReader;

    String fileTestName = "FruitShopPrices.txt";
    String fileTestNameError = "Prices.txt";
    String expected = "PRODUCT,PRICE\nPear, 0.75\nApple,0.9\nOrange,1";

    @Test
    void read_all_the_file_in_readFileAsStream() throws FruitShopException {
        Stream<String> fileStream = fileReader.readFileAsStream(fileTestName);
        String result = fileStream.collect(Collectors.joining("\n"));

        assertEquals(expected, result);
    }

    @Test
    void return_exception_with_null_file_in_readFileAsStream(){
        assertThrowsExactly(FruitShopException.class, () -> fileReader.readFileAsStream(null));
    }

    @Test
    void return_exception_with_not_existing_file_in_readFileAsStream(){
        assertThrowsExactly(FruitShopException.class, () -> fileReader.readFileAsStream(fileTestNameError));
    }

    @Test
    void read_all_the_file_in_readFileAsList() throws FruitShopException {
        List<String> fileList = fileReader.readFileAsList(fileTestName);
        String result = fileList.stream().collect(Collectors.joining("\n"));

        assertEquals(expected, result);
    }

    @Test
    void return_exception_with_not_existing_file_in_readFileAsList(){
        assertThrowsExactly(FruitShopException.class, () -> fileReader.readFileAsList(fileTestNameError));
    }

    @Test
    void return_exception_with_null_file_in_readFileAsList(){
        assertThrowsExactly(FruitShopException.class, () -> fileReader.readFileAsList(null));
    }

}
