package application.util.file;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class CSVWriterTest {
    @Test
    void CSVWriterSimpleTest() throws IOException {
        CSVWriter csv = new CSVWriter("/home/amazingrise/Coding/123.csv");
        csv.append("hello","you","me");
        csv.close();
    }
}
