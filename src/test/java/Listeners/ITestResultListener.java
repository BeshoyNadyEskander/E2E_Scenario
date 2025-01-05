package Listeners;

import Utilities.LogsUtils;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class ITestResultListener implements ITestListener {

    public void onTestStart(ITestResult result) {

        LogsUtils.info("Test case" + result.getName() + " started");
    }

    public void onTestSuccess(ITestResult result) {

        LogsUtils.info("Test case" + result.getName() + " success");
    }

    public void onTestSkipped(ITestResult result) {
        LogsUtils.info("Test case" + result.getName() + " skipped");
    }
}
