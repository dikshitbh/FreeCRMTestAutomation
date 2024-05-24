package org.crm.ExtentReportListener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;

public class ExtentReportListenerNG implements ITestListener {

    private static final String OUTPUT_FOLDER = System.getProperty("user.dir")+"/reports/";
    private static final String FILE_NAME = "TestExecutionReport.html";



    private static ExtentReports extent = init();
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    private static ExtentReports init() {
        clearOldReports();
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(OUTPUT_FOLDER + FILE_NAME);
        htmlReporter.config().setDocumentTitle("Test Results");
        htmlReporter.config().setReportName("Test Results");
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Automation Tester", "Your Name");
        extent.setSystemInfo("Organization", "Your Company");
        extent.setSystemInfo("Build no", "W2A-1234");
        return extent;
    }

    private static void clearOldReports() {
        try {
            File reportDir = new File(OUTPUT_FOLDER);
            if (reportDir.exists()) {
                for (File file : reportDir.listFiles()) {
                    if (!file.isDirectory()) {
                        file.delete();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void onStart(ITestContext context) {
        System.out.println("Test Suite started!");
    }

    public synchronized void onFinish(ITestContext context) {
        System.out.println("Test Suite is ending!");
        extent.flush();
        test.remove();
    }

    public synchronized void onTestStart(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " started!"));
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
        test.set(extentTest);
    }

    public synchronized void onTestSuccess(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " passed!"));
        test.get().log(Status.PASS, "Test passed");
    }

    public synchronized void onTestFailure(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " failed!"));
        test.get().fail(result.getThrowable());
    }

    public synchronized void onTestSkipped(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " skipped!"));
        test.get().log(Status.SKIP, "Test Skipped");
    }

    public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
    }
}
