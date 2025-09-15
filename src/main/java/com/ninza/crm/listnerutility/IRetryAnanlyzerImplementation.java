package com.ninza.crm.listnerutility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class IRetryAnanlyzerImplementation implements IRetryAnalyzer {
	
	int count =0;
	int limitCount = 5;
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		
		if(count<limitCount) {
			count++;
			return true;
		}
		return false;
	}
	
	

}
