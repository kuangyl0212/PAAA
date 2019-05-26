package application.util.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

//Misc Utilities
public class Misc {
    public static File[] getFileList(String sourceFolder) {
        File folder = new File(sourceFolder);
        File[] listOfFiles = new File[0];
        for (File listOfFile : Objects.requireNonNull(folder.listFiles())) {
            if (listOfFile.isFile()) {
                System.out.println("File " + listOfFile.getName());
                if (listOfFile.getName().endsWith(".py")) {
                    listOfFiles = insertElement(listOfFiles, listOfFile);
                }
            } else if (listOfFile.isDirectory()) {
                System.out.println("Directory " + listOfFile.getName());
            }
        }
        return listOfFiles;
    }

    public static String readStringFrom(String filePath) throws FileNotFoundException {
        return new Scanner(new File(filePath)).useDelimiter("\\Z").next();
    }

    public static String readStringFrom(File file) throws FileNotFoundException {
        return new Scanner(file).useDelimiter("\\Z").next();
    }

    private static File[] insertElement(File[] original,
                                        File element, int index) {
        int length = original.length;
        File[] destination = new File[length + 1];
        System.arraycopy(original, 0, destination, 0, index);
        destination[index] = element;
        System.arraycopy(original, index, destination, index
                + 1, length - index);
        return destination;
    }
    private static File[] insertElement(File[] original,
                                        File element) {
        int index = original.length;
        return insertElement(original, element, index);
    }
}
