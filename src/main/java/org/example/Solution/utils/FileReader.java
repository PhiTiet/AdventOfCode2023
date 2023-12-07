package org.example.Solution.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

import static org.example.Solution.utils.RegexUtils.*;

public class FileReader {

    public List<String> getLines(String path) {
        return getLines(path, WINDOWS_NEWLINE);
    }

    public List<String> getLines(String path, String delimiter){
        InputStream is = FileReader.class.getResourceAsStream(path);

        String str;
        try {
            str = new String(Objects.requireNonNull(is).readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return List.of(str.split(delimiter));
    }

}
