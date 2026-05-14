// src/main/java/utils/AppiumTestListener.java
package utils;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.ISuite;
import org.testng.ISuiteListener;

import java.util.LinkedHashMap;
import java.util.Map;

public class AppiumTestListener implements ITestListener, ISuiteListener {

    // Stores testName → PASS/FAIL for every test in the run
    private static final Map<String, String> testResults = new LinkedHashMap<>();

    @Override
    public void onTestStart(ITestResult result) {
        String name = result.getName();
        TestLogger.setCurrentTest(name);
        TestLogger.action("▶ Test STARTED: " + name);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String name = result.getName();
        TestLogger.pass("✅ Test PASSED: " + name);
        testResults.put(name, "PASS");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String name = result.getName();
        TestLogger.fail("❌ Test FAILED: " + name);
        TestLogger.fail("   Reason: " + result.getThrowable().getMessage());
        testResults.put(name, "FAIL");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String name = result.getName();
        TestLogger.warning("⚠️  Test SKIPPED: " + name);
        testResults.put(name, "SKIP");
    }

    @Override
    public void onFinish(ISuite suite) {
        // Generate the PDF once the entire suite finishes
        try {
            String path = "test-reports/" + suite.getName() + "_Report.pdf";
            PdfReportGenerator.generateReport(path, suite.getName(), testResults);
        } catch (Exception e) {
            System.err.println("Failed to generate PDF report: " + e.getMessage());
        }
    }
}