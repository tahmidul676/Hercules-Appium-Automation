// src/main/java/utils/TestLogger.java
package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TestLogger {

    public enum LogLevel { INFO, PASS, FAIL, WARNING, ACTION, ASSERT }

    public static class LogEntry {
        public String timestamp;
        public LogLevel level;
        public String message;
        public String testName;

        public LogEntry(String testName, LogLevel level, String message) {
            this.timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            this.testName = testName;
            this.level = level;
            this.message = message;
        }
    }

    private static final List<LogEntry> logs = new ArrayList<>();
    private static String currentTest = "General";

    public static void setCurrentTest(String testName) {
        currentTest = testName;
    }

    public static void log(LogLevel level, String message) {
        LogEntry entry = new LogEntry(currentTest, level, message);
        logs.add(entry);
        // Also print to console
        System.out.println("[" + entry.timestamp + "] [" + level + "] " + message);
    }

    public static void info(String msg)    { log(LogLevel.INFO, msg); }
    public static void pass(String msg)    { log(LogLevel.PASS, msg); }
    public static void fail(String msg)    { log(LogLevel.FAIL, msg); }
    public static void action(String msg)  { log(LogLevel.ACTION, msg); }
    public static void warning(String msg) { log(LogLevel.WARNING, msg); }
    public static void assertLog(String msg) { log(LogLevel.ASSERT, msg); }

    public static List<LogEntry> getLogs() { return logs; }

    public static void clearLogs() { logs.clear(); }
}