package demoqa.utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;
    private static final int maxRetryCount = 2; //static final значение не меняется

    @Override
    public boolean retry(ITestResult result){
        if(retryCount < maxRetryCount){
            retryCount++;
            System.out.println("Retrying test ["+result.getName() + "] within ["+ retryCount +"] times");
            return true;
        }
        return false;
    }
}
