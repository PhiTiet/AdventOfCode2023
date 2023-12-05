package org.example.Solution.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class FileReader {

    public static final String NEW_LINE = "\r\n";

    public List<String> getLines(String path) {
        return getLines(path, NEW_LINE);
    }

    public List<String> getLines(String path, String delimiter){
        InputStream is = FileReader.class.getResourceAsStream(path);

        String str;
        try {
            str = new String(is.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return List.of(str.split(delimiter));
    }

}
