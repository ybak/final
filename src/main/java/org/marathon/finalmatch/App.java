package org.marathon.finalmatch;


public class App {
    private static String directoryName = "C:\\CodeMarathon\\Data\\Test\\";
    private static String[] logFileNames = { "eTrade.20131021.log", "eTrade.20131022.log", "eTrade.20131023.log",
            "eTrade.20131024.log", "eTrade.20131025.log" };
    
    
    public static void main(String[] args) {
        Quest1.processQuestion1(directoryName, logFileNames);
        Quest2.processQuestion2(directoryName, logFileNames);
    }
}
