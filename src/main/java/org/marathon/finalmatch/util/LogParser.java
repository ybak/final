package org.marathon.finalmatch.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.marathon.finalmatch.domain.ProductLog;
import org.marathon.finalmatch.domain.RequestLog;
import org.marathon.finalmatch.domain.ResponseLog;

public class LogParser {

    private static final int LOG_CONTENT_BEGIN_INDEX = 26;

    private static final String TIME_PATTERN = "HH:mm:ss.S";

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat(TIME_PATTERN);

    public static ProductLog parseNewOrderRequestLog(String line) {
        if (isNewRequest(line)) {
            String[] split = line.split(",");
            return new ProductLog(split[1].split("\\:")[1], Long.valueOf(split[2].split("\\:")[1]));
        }
        return null;
    }

    public static String parseDateFromFilename(String fileName) {
        return fileName.substring(7, 11) + "-" + fileName.substring(11, 13) + "-" + fileName.substring(13, 15);
    }

    public static RequestLog parseRequestLog(String line) {
        if (isRequest(line)) {
            Long requestTime;
            try {
                requestTime = parseLogTime(line);
                String requestId = parseRequestID(line);
                return new RequestLog(requestId, requestTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static ResponseLog parseResponseLog(String line) {
        if (isResponse(line)) {
            Long responseTime;
            try {
                responseTime = parseLogTime(line);
                String requestId = parseRequestID(line);
                return new ResponseLog(requestId, responseTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static String parseRequestID(String line) {
        int reqeustCommandIndex = line.indexOf("[REQ", LOG_CONTENT_BEGIN_INDEX);
        String requestId = line.substring(reqeustCommandIndex + 1, line.indexOf("]", reqeustCommandIndex));
        return requestId;
    }

    private static boolean isNewRequest(String line) {
        return line.contains("New Order Request");
    }

    private static boolean isRequest(String line) {
        return isNewRequest(line) || line.contains("to cancel Order");
    }

    private static boolean isResponse(String line) {
        return line.contains("New Order [") || line.contains("is cancelled by request") || line.contains("is rejected");
    }

    private static Long parseLogTime(String line) throws ParseException {
        String timeStr = line.substring(line.indexOf(" "), line.indexOf("]"));
        Long requestTime = FORMAT.parse(timeStr).getTime();
        return requestTime;
    }

}
