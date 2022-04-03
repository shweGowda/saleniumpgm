package sfdc.listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;

import sfdc.test.BaseTest;
import sfdc.utilities.ReusableUtil;

public class TestListeners implements ITestListener {

	//ExtentTest test =null;
	ReusableUtil ru = new ReusableUtil();
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		//System.out.println("the method is started"+ result.getName());
		BaseTest.test=BaseTest.extent.createTest(result.getName());
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("the test case passed");
		BaseTest.test.pass(result.getName());
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("the script failed");
		try {
			BaseTest.test.addScreenCaptureFromPath(ru.takeScreenShot());
		} catch (IOException e) {
			e.printStackTrace();
		}
		BaseTest.test.fail(result.getName());
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
