package com.b2online;

import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class WriteFilesTest {

    @Test
    public void test() throws IOException {
        String fileName = "src/main/resources/saida.txt";
        Charset utf8 = StandardCharsets.UTF_8;
        List<String> list = Arrays.asList("Line 1", "Line 2");
        Path path = Paths.get(fileName);
        try {
            Files.write(Paths.get(fileName), list, utf8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
