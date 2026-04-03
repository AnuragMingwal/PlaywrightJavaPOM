package com.qa.opencart.listeners;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.qa.opencart.factory.PlaywrightFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReportListener implements ITestListener {

    private static final String OUTPUT_FOLDER = "./build/";
    private static final String FILE_NAME = "TestExecutionReport.html";

    private static ExtentReports extent = init();
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    private static ExtentReports init() {
        Path path = Paths.get(OUTPUT_FOLDER);

        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ExtentSparkReporter reporter = new ExtentSparkReporter(OUTPUT_FOLDER + FILE_NAME);
        reporter.config().setReportName("Open Cart Automation Test Results");

        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(reporter);

        extentReports.setSystemInfo("System", "MAC");
        extentReports.setSystemInfo("Author", "Anurag AutomationTest");
        extentReports.setSystemInfo("Build#", "1.1");
        extentReports.setSystemInfo("Team", "OMS");
        extentReports.setSystemInfo("Customer Name", "NAL");

        return extentReports;
    }

    @Override
    public synchronized void onStart(ITestContext context) {
        System.out.println("Test Suite started!");
    }

    @Override
    public synchronized void onFinish(ITestContext context) {
        System.out.println("Test Suite is ending!");
        extent.flush();
    }

    @Override
    public synchronized void onTestStart(ITestResult result) {
        String methodName = result.getMethod().getMethodName();

        ExtentTest extentTest = extent.createTest(methodName, result.getMethod().getDescription());

        test.set(extentTest);
        test.get().getModel().setStartTime(getTime(result.getStartMillis()));
    }

    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        test.get().pass("Test passed");
        test.get().getModel().setEndTime(getTime(result.getEndMillis()));
        test.remove(); 
    }

    @Override
    public synchronized void onTestFailure(ITestResult result) {

        String screenshotPath = PlaywrightFactory.takeScreenshot();

        test.get().fail(result.getThrowable(),
                MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());

        test.get().getModel().setEndTime(getTime(result.getEndMillis()));
        test.remove(); 
    }

    @Override
    public synchronized void onTestSkipped(ITestResult result) {

        String screenshotPath = PlaywrightFactory.takeScreenshot();

        test.get().skip(result.getThrowable(),
                MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());

        test.get().getModel().setEndTime(getTime(result.getEndMillis()));
        test.remove(); 
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }
}