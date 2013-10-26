package org.marathon.finalmatch;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

public class Quest1 {
    public static void processQuestion1() {
        final Map<String, Long> counterMap = new HashMap<String, Long>();

        String[] logFileNames = { "eTrade.20131021.log", "eTrade.20131022.log", "eTrade.20131023.log",
                "eTrade.20131024.log", "eTrade.20131025.log" };
        for (String fileName : logFileNames) {
            IOUtil.processLine(new LineProcessor() {
                @Override
                public void process(String line) {
                    ProductLog pl = LogParser.parseNewOrderRequestLog(line);
                    if (pl == null) {
                        return;
                    }
                    Long count = counterMap.get(pl.getCode());
                    if (count == null) {
                        count = 0L;
                    }
                    count += pl.getQuantity();
                    counterMap.put(pl.getCode(), count);
                }
            }, fileName);

            ValueComparator comparator = new ValueComparator(counterMap);
            TreeMap<String, Long> sortMap = new TreeMap<String, Long>(comparator);
            sortMap.putAll(counterMap);

            Iterator<Entry<String, Long>> iterator = sortMap.entrySet().iterator();
            StringBuilder sb = new StringBuilder(LogParser.parseDateFromFilename(fileName));

            for (int i = 0; i < 10; i++) {
                sb.append(",").append(iterator.next().getKey());
            }
            sb.append("\n");

            IOUtil.writeStringToFile("C:\\CodeMarathon\\Result\\Q1.csv", sb.toString());
            counterMap.clear();
        }

    }
}

class ValueComparator implements Comparator<String> {

    private Map<String, Long> counterMap;

    public ValueComparator(Map<String, Long> counterMap) {
        this.counterMap = counterMap;
    }

    @Override
    public int compare(String a, String b) {
        if (counterMap.get(a) >= counterMap.get(b)) {
            return -1;
        } else {
            return 1;
        }
    }

}