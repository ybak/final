package org.marathon.finalmatch;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Quest2 {

    private static final int MINUTE = 60 * 1000;

    static long delayTotalTime = 0;

    static long responseCount = 0;

    static long delayAvgTime = 0;

    private static final SimpleDateFormat OUTPUT_FORMAT = new SimpleDateFormat("HH:mm:ss");

    public static void processQuestion2() {
        String[] logFileNames = { "eTrade.20131021.log", "eTrade.20131022.log", "eTrade.20131023.log",
                "eTrade.20131024.log", "eTrade.20131025.log" };
        for (String fileName : logFileNames) {
            final Map<String, RequestLog> requestCacheMap = new HashMap<String, RequestLog>();
            final List<ResponseLog> responseList = new ArrayList<ResponseLog>();
            final List<ExceptionPeriod> exceptionPeriodList = new ArrayList<ExceptionPeriod>();
            int lastPeriodIndex = 0;
            ExceptionPeriod maxAvgDelayPeriod = new ExceptionPeriod(0l, 0l);
            IOUtil.processLine(new LineProcessor() {
                @Override
                public void process(String line) {
                    RequestLog request = LogParser.parseRequestLog(line);
                    if (request != null) {
                        requestCacheMap.put(request.getRequestID(), request);
                    } else {
                        ResponseLog response = LogParser.parseResponseLog(line);
                        if (response == null) {
                            return;
                        }
                        responseList.add(response);
                        response.setRequestTime(requestCacheMap.get(response.getRequestID()).getRequestTime());
                        delayTotalTime += response.getDelayTime();
                        responseCount++;
                        requestCacheMap.remove(response.getRequestID());
                    }
                }
            }, fileName);
            delayAvgTime = delayTotalTime / responseCount;
            for (ResponseLog response : responseList) {
                if (response.getDelayTime() > delayAvgTime) {
                    ExceptionPeriod lastPeriod = null;
                    if (exceptionPeriodList.isEmpty()) {
                        lastPeriod = new ExceptionPeriod(response.getRequestTime(), response.getResponseTime());
                        exceptionPeriodList.add(lastPeriod);
                    } else {
                        lastPeriod = exceptionPeriodList.get(lastPeriodIndex);
                    }
                    if ((response.getResponseTime() - lastPeriod.getEndTime()) > 2 * MINUTE) {
                        lastPeriod = new ExceptionPeriod(response.getRequestTime(), response.getResponseTime());
                        exceptionPeriodList.add(lastPeriod);
                        lastPeriodIndex++;
                    }
                    lastPeriod.addNewResponse(response);
                }
            }

            for (ExceptionPeriod period : exceptionPeriodList) {
                if (period.getPeriodTime() > maxAvgDelayPeriod.getPeriodTime()) {
                    maxAvgDelayPeriod = period;
                }
            }

            StringBuilder sb = new StringBuilder(LogParser.parseDateFromFilename(fileName));
            sb.append(",").append(OUTPUT_FORMAT.format(new Date(maxAvgDelayPeriod.getStartTime()))).append(",")
                    .append(OUTPUT_FORMAT.format(new Date(maxAvgDelayPeriod.getEndTime())));
            sb.append("\n");
            IOUtil.writeStringToFile("C:\\CodeMarathon\\Result\\Q2.csv", sb.toString());
        }

    }
}
