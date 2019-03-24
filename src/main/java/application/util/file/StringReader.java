package application.util.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StringReader {
    public static String readStringFrom(String filePath) throws FileNotFoundException {
        return new Scanner(new File(filePath)).useDelimiter("\\Z").next();
    }
}
