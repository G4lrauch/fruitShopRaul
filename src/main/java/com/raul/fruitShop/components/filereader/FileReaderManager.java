package com.raul.fruitShop.components.filereader;


import com.raul.fruitShop.exceptions.FruitShopException;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Log4j2
@Component
public class FileReaderManager implements FileReader {

    @Override
    public Stream<String> readFileAsStream(String fileName) throws FruitShopException {
        try {
            if(StringUtils.isBlank(fileName)) {
                throw new FruitShopException("File name not provided");
            }
            Path path = Paths.get(getClass().getClassLoader().getResource(fileName).toURI());
            return Files.lines(path, Charset.forName(StandardCharsets.ISO_8859_1.name()));
        } catch (FruitShopException e) {
            log.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error reading file: " + e.getMessage());
            throw new FruitShopException("Error reading file");
        }
    }

    @Override
    public List<String> readFileAsList(String fileName) throws FruitShopException {
        return readFileAsStream(fileName)
                .collect(toList());
    }
}
