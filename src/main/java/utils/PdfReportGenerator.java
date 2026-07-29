//// src/main/java/utils/PdfReportGenerator.java
//package utils;
//
//import com.itextpdf.kernel.colors.ColorConstants;
//import com.itextpdf.kernel.colors.DeviceRgb;
//import com.itextpdf.kernel.pdf.PdfDocument;
//import com.itextpdf.kernel.pdf.PdfWriter;
//import com.itextpdf.layout.Document;
//import com.itextpdf.layout.element.*;
//import com.itextpdf.layout.property.TextAlignment;
//import com.itextpdf.layout.property.UnitValue;
//
//import java.io.File;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//public class PdfReportGenerator {
//
//    // Colors
//    private static final DeviceRgb COLOR_HEADER   = new DeviceRgb(33, 37, 41);
//    private static final DeviceRgb COLOR_PASS      = new DeviceRgb(40, 167, 69);
//    private static final DeviceRgb COLOR_FAIL      = new DeviceRgb(220, 53, 69);
//    private static final DeviceRgb COLOR_INFO      = new DeviceRgb(23, 162, 184);
//    private static final DeviceRgb COLOR_ACTION    = new DeviceRgb(102, 16, 242);
//    private static final DeviceRgb COLOR_WARNING   = new DeviceRgb(255, 193, 7);
//    private static final DeviceRgb COLOR_ASSERT    = new DeviceRgb(253, 126, 20);
//    private static final DeviceRgb COLOR_ROW_EVEN  = new DeviceRgb(248, 249, 250);
//    private static final DeviceRgb COLOR_ROW_ODD   = new DeviceRgb(255, 255, 255);
//
//    public static void generateReport(String outputPath, String suiteName,
//                                      Map<String, String> testResults) throws Exception {
//        new File(outputPath).getParentFile().mkdirs();
//
//        PdfWriter writer = new PdfWriter(outputPath);
//        PdfDocument pdf = new PdfDocument(writer);
//        Document doc = new Document(pdf);
//        doc.setMargins(36, 36, 36, 36);
//
//        List<TestLogger.LogEntry> allLogs = TestLogger.getLogs();
//
//        // ── Cover / Header ────────────────────────────────────────────
//        addHeader(doc, suiteName, testResults);
//
//        // ── Summary Table ─────────────────────────────────────────────
//        doc.add(new Paragraph("Test Summary")
//            .setBold().setFontSize(14).setMarginTop(20));
//        addSummaryTable(doc, testResults);
//
//        // ── Logs per test ─────────────────────────────────────────────
//        doc.add(new Paragraph("Detailed Logs")
//            .setBold().setFontSize(14).setMarginTop(20));
//
//        Map<String, List<TestLogger.LogEntry>> byTest =
//            allLogs.stream().collect(Collectors.groupingBy(e -> e.testName));
//
//        for (Map.Entry<String, String> result : testResults.entrySet()) {
//            String testName = result.getKey();
//            String status   = result.getValue();
//            List<TestLogger.LogEntry> testLogs = byTest.getOrDefault(testName, List.of());
//
//            addTestSection(doc, testName, status, testLogs);
//        }
//
//        doc.close();
//        System.out.println("✅ PDF Report saved: " + outputPath);
//    }
//
//    
//    
//    
//    // ── Header block ──────────────────────────────────────────────────
//    private static void addHeader(Document doc, String suiteName,
//                                   Map<String, String> results) {
//        long passed = results.values().stream().filter(s -> s.equals("PASS")).count();
//        long failed = results.values().stream().filter(s -> s.equals("FAIL")).count();
//        long total  = results.size();
//
//        Paragraph title = new Paragraph("🤖 Appium Test Report")
//            .setFontSize(22).setBold().setFontColor(COLOR_HEADER)
//            .setTextAlignment(TextAlignment.CENTER);
//        doc.add(title);
//
//        doc.add(new Paragraph("Suite: " + suiteName)
//            .setFontSize(12).setTextAlignment(TextAlignment.CENTER)
//            .setFontColor(ColorConstants.DARK_GRAY));
//
//        doc.add(new Paragraph("Generated: " +
//            LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm:ss")))
//            .setFontSize(10).setTextAlignment(TextAlignment.CENTER)
//            .setFontColor(ColorConstants.GRAY));
//
//        // Pass / Fail / Total badges
//        Table badges = new Table(3).setWidth(UnitValue.createPercentValue(60))
//            .setHorizontalAlignment(com.itextpdf.layout.property.HorizontalAlignment.CENTER)
//            .setMarginTop(10).setMarginBottom(10);
//
//        badges.addCell(badgeCell("TOTAL: " + total,   new DeviceRgb(52, 58, 64)));
//        badges.addCell(badgeCell("PASSED: " + passed, COLOR_PASS));
//        badges.addCell(badgeCell("FAILED: " + failed, COLOR_FAIL));
//        doc.add(badges);
//    }
//
//    private static Cell badgeCell(String text, DeviceRgb bg) {
//        return new Cell()
//            .add(new Paragraph(text).setBold().setFontColor(ColorConstants.WHITE)
//                .setTextAlignment(TextAlignment.CENTER).setFontSize(12))
//            .setBackgroundColor(bg).setPadding(8).setBorder(null);
//    }
//
//    // ── Summary table ─────────────────────────────────────────────────
//    private static void addSummaryTable(Document doc, Map<String, String> results) {
//        Table table = new Table(new float[]{4, 1})
//            .setWidth(UnitValue.createPercentValue(100));
//
//        // Header row
//        table.addHeaderCell(headerCell("Test Name"));
//        table.addHeaderCell(headerCell("Status"));
//
//        int i = 0;
//        for (Map.Entry<String, String> e : results.entrySet()) {
//            DeviceRgb rowBg = (i % 2 == 0) ? COLOR_ROW_EVEN : COLOR_ROW_ODD;
//            boolean passed  = "PASS".equals(e.getValue());
//
//            table.addCell(new Cell().add(new Paragraph(e.getKey()).setFontSize(10))
//                .setBackgroundColor(rowBg).setPadding(5));
//
//            table.addCell(new Cell()
//                .add(new Paragraph(e.getValue()).setBold().setFontSize(10)
//                    .setFontColor(passed ? COLOR_PASS : COLOR_FAIL)
//                    .setTextAlignment(TextAlignment.CENTER))
//                .setBackgroundColor(rowBg).setPadding(5));
//            i++;
//        }
//        doc.add(table);
//    }
//
//    private static Cell headerCell(String text) {
//        return new Cell()
//            .add(new Paragraph(text).setBold().setFontColor(ColorConstants.WHITE).setFontSize(11))
//            .setBackgroundColor(COLOR_HEADER).setPadding(6);
//    }
//
//    // ── Per-test section ──────────────────────────────────────────────
//    private static void addTestSection(Document doc, String testName, String status,
//                                        List<TestLogger.LogEntry> logs) {
//        boolean passed = "PASS".equals(status);
//
//        // Section heading
//        doc.add(new Paragraph("▶ " + testName + "  [" + status + "]")
//            .setFontSize(12).setBold()
//            .setFontColor(passed ? COLOR_PASS : COLOR_FAIL)
//            .setMarginTop(16).setMarginBottom(4));
//
//        if (logs.isEmpty()) {
//            doc.add(new Paragraph("No logs recorded for this test.")
//                .setFontSize(9).setItalic().setFontColor(ColorConstants.GRAY));
//            return;
//        }
//
//        // Logs table: Timestamp | Level | Message
//        Table table = new Table(new float[]{2, 1, 5})
//            .setWidth(UnitValue.createPercentValue(100)).setFontSize(9);
//
//        table.addHeaderCell(headerCell("Timestamp"));
//        table.addHeaderCell(headerCell("Level"));
//        table.addHeaderCell(headerCell("Message"));
//
//        int i = 0;
//        for (TestLogger.LogEntry entry : logs) {
//            DeviceRgb rowBg = (i % 2 == 0) ? COLOR_ROW_EVEN : COLOR_ROW_ODD;
//
//            table.addCell(new Cell().add(new Paragraph(entry.timestamp))
//                .setBackgroundColor(rowBg).setPadding(4));
//
//            DeviceRgb levelColor = getLevelColor(entry.level);
//            table.addCell(new Cell()
//                .add(new Paragraph(entry.level.name()).setBold().setFontColor(levelColor)
//                    .setTextAlignment(TextAlignment.CENTER))
//                .setBackgroundColor(rowBg).setPadding(4));
//
//            table.addCell(new Cell().add(new Paragraph(entry.message))
//                .setBackgroundColor(rowBg).setPadding(4));
//            i++;
//        }
//        doc.add(table);
//    }
//
//    private static DeviceRgb getLevelColor(TestLogger.LogLevel level) {
//        switch (level) {
//            case PASS:    return COLOR_PASS;
//            case FAIL:    return COLOR_FAIL;
//            case ACTION:  return COLOR_ACTION;
//            case WARNING: return COLOR_WARNING;
//            case ASSERT:  return COLOR_ASSERT;
//            default:      return COLOR_INFO;
//        }
//    }
//}