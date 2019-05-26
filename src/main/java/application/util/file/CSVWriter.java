package application.util.file;

import java.io.*;
import java.util.Date;

public class CSVWriter {
    private File CSVFile;
    private PrintWriter pw;
    public CSVWriter(String FileLocation) throws IOException {
        CSVFile = new File(FileLocation);
        pw = new PrintWriter(new FileOutputStream(CSVFile,true));
        //If file not exists, we will create a new file.
        //And then write a BOM code into the new file,
        try {
            if (!CSVFile.exists()) {
                if (CSVFile.createNewFile())
                {
                    pw.write(new String(new byte[] { (byte) 0xEF, (byte) 0xBB,(byte) 0xBF }));
                }
            }else{
                pw.append("\n");
            }
        } catch (IOException e) {
            System.out.println(e.toString());
            throw e;
        }
        Date currentDate = new Date();
        pw.append("PAAA - Assessing Result on ").append(currentDate.toString()).append("\n");
        pw.append("Name,Score\n");
    }

    public void append(String... args) {
        pw.append(args[0]);
        for (int i = 1; i < args.length; i++) {
            pw.append(",").append(args[i]);
        }
        pw.append("\n");
    }

    public void close() {
        pw.close();
    }
}
