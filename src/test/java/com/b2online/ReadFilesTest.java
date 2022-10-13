package com.b2online;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.support.LocalizedResourceHelper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ReadFilesTest {

    @Test
    public void test() throws IOException {
        String fileName = "src/main/resources/entradas.txt";
        Stream<String> lines = Files.lines(Paths.get(fileName));
        lines.forEachOrdered(System.out::println);
    }

}
