package com.raul.fruitShop.components.filereader;


import com.raul.fruitShop.Exceptions.FruitShopException;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Component
public class FileReaderManager implements FileReader {

    @Override
    public Stream<String> readFileAsStream(String file) throws FruitShopException {
        try {
            Path path = Paths.get(getClass().getClassLoader().getResource(file).toURI());
            return Files.lines(path, Charset.forName("ISO-8859-1"));
        } catch (URISyntaxException | IOException e) {
            System.out.println("Error reading file: " + e.toString());
            throw new FruitShopException("Error reading file");
        }
    }
}
