package com.raul.fruitShop.components.filereader;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LoadFileData_Should {

    @Autowired
    LoadFileData loadFileData;

    @Test
    public void read_all_the_file() {
        String expected = "PRODUCT,PRICE\nPear, 0.75\nApple,0.9\nOrange,1";

        Path path = Paths.get(getClass().getClassLoader().getResource("test_file.txt").toURI());
        Stream<String> fileStream = fileReader.readFileAsStream(path);
        String content = fileStream.collect(Collectors.joining("\n"));

        assertEquals(expected, content);
    }

}
