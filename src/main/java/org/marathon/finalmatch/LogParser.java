package org.marathon.finalmatch;

public class LogParser {
    public static ProductLog parseNewOrderRequestLog(String line) {
        if (line.contains("New Order Request")) {
            String[] split = line.split(",");
            return new ProductLog(split[1].split("\\:")[1], Long.valueOf(split[2].split("\\:")[1]));
        }
        return null;
    }

    public static String parseDateFromFilename(String fileName) {
        return fileName.substring(7, 11) + "-" + fileName.substring(11, 13) + "-" + fileName.substring(13, 15);
    }
}
