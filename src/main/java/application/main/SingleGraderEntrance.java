package application.main;

import application.config.Parameters;
import application.grade.Grader;
import application.util.file.CSVWriter;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static application.config.DefaultConf.getDefaultParameters;
import static application.util.file.Misc.readStringFrom;
import static java.lang.System.exit;

public class SingleGraderEntrance {
    /**
     * PAAA Grader Entrance
     * @param args input source. (stream or file location)
     */


    public static void main(String[] args) {
        Map<String, Double> result = new HashMap<>();
        //Process parameters
        Parameters parameters = readParameters(args);
        //Initialize a grader
        Grader grader = new Grader(parameters);
        try {
            result = grader.assess();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
            exit(1);
        }
        //Output the result to CSV file
        CSVWriter writer;
        try {
            writer = new CSVWriter(parameters.getParameterOfSource("output"));
            for (String key : result.keySet()) {
                //System.out.println(key + " : " + result.get(key));
                writer.append(key, result.get(key).toString());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("PAAA terminated normally. No errors. :)");
    }

    private static Parameters readParameters(String[] args) {
        Parameters parameters = getDefaultParameters();
        if (args.length == 0) {
            //Do nothing here
        }else if (args[0].equals("stream")) {
            System.out.println("PAAA - Please input parameters here.");
            parameters = new Parameters(new ByteArrayInputStream(readStringFromConsole().getBytes()));
        }else{
            try {
                parameters = new Parameters(new ByteArrayInputStream(readStringFrom(args[0]).getBytes()));
            } catch (IOException e) {
                System.out.println("IO Error from Configuration file");
                exit(1);
            }
        }
        return parameters;
    }

    private static String readStringFromConsole() {
        StringBuilder stringBuilder = new StringBuilder();
        Scanner scanner = new Scanner(System.in);
        String nextLine = scanner.nextLine();
        while (nextLine != null && !nextLine.equals("")) {
            stringBuilder.append(nextLine);
            nextLine = scanner.nextLine();
        }
        return stringBuilder.toString();
    }
}
