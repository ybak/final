package org.marathon.finalmatch.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

public class IOUtil {

    public static void processLine(LineProcessor callback, String directory, String fileName) {
        File file = null;
        try {
            file = new File(directory + fileName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        processFileLine(callback, file);
    }

    public static void processResourceLine(LineProcessor callback, String fileName) {
        File file = null;
        try {
            URL stationURL = ClassLoader.getSystemResource(fileName);
            file = new File(stationURL.toURI());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        processFileLine(callback, file);
    }

    public static void writeStringToFile(String outputFileName, String content) {
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

    private static void processFileLine(LineProcessor callback, File file) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
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

}