package org.marathon.finalmatch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

public class IOUtil {

    public static void processLine(LineProcessor callback, String fileName) {
        BufferedReader br = null;
        try {
            URL stationURL = ClassLoader.getSystemResource(fileName);
            br = new BufferedReader(new FileReader(new File(stationURL.toURI())));
            String line = null;
            while ((line = br.readLine()) != null) {
                callback.process(line);
            }
            br.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static void writeStringToFile(String outputFileName, String content) {
        FileWriter fw = null;
        try {
            File file = new File(outputFileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            fw = new FileWriter(file, true);
            fw.write(content);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}

abstract class LineProcessor {
    public abstract void process(String line);
}
