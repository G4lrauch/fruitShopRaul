package com.raul.fruitShop.components.filereader;


import com.raul.fruitShop.exceptions.FruitShopException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Component
public class FileReaderManager implements FileReader {

    @Override
    public Stream<String> readFileAsStream(String fileName) throws FruitShopException {
        try {
            if(StringUtils.isBlank(fileName)) {
                throw new FruitShopException("File name not provided");
            }
            Path path = Paths.get(getClass().getClassLoader().getResource(fileName).toURI());
            return Files.lines(path, Charset.forName("ISO-8859-1"));
        } catch (FruitShopException | URISyntaxException | IOException e) {
            System.out.println("Error reading file: " + e.toString());
            throw new FruitShopException("Error reading file");
        }
    }
}
