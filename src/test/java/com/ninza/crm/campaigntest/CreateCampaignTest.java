package com.ninza.crm.campaigntest;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.ninza.crm.baseClass.BaseClass;
import com.ninza.crm.generic.fileutility.ExcelFileUtility;
import com.ninza.crm.generic.fileutility.PropertyFileUtility;
import com.ninza.crm.generic.webdriverUtility.JavaUtility;
import com.ninza.crm.generic.webdriverUtility.WebdriverUtility;
import com.ninza.crm.objectrepository.CampaignPage;
import com.ninza.crm.objectrepository.CreateCampaignPage;
import com.ninza.crm.objectrepository.HomePage;
import com.ninza.crm.objectrepository.LoginPage;

@Listeners(com.ninza.crm.listnerutility.ListnerImplementation.class)
public class CreateCampaignTest extends BaseClass {
	
@Test (groups = {"Smoke","Regression"})	
public void createCampaignWithExpectedDateTest() throws IOException {
			 
			
			//get values from excel file
			 String campaignName = eUtil.toReadTheDataFromExcel("Campaign",1,1);
			 String targetSize =  eUtil.toReadTheDataFromExcel("Campaign", 1, 2);
			 
				
				//Click on campaign link
				HomePage hp = new HomePage(driver);
				hp.getCampaignLink().click();
				
				//Create campaign with Mandatory fields
				// Click on create campaign
				CampaignPage cp = new CampaignPage(driver);
				cp.getCreateCampaign().click();

				CreateCampaignPage ccp = new CreateCampaignPage(driver);
				ccp.getCampaignName().sendKeys(campaignName);
				ccp.getTargetSize().clear();
				ccp.getTargetSize().sendKeys(targetSize);
				
				// Get the date after 30 days
				
				String expectedDate = jUtil.getRequiredDate(30);
				ccp.getExpectedCloseDate().sendKeys(expectedDate);
				
				ccp.getCreateButton().click();
				
				// Verify the succesfull message
				WebElement toastMsg = hp.getToastMsg();
				wUtil.waitForVisibilityOfElement(driver, toastMsg);
				String msg = toastMsg.getText();
				
				
//				if (msg.contains("Successfully Added")) {
//					System.out.println("Campign created successfully with expected date");
//				} 
//				else {
//					System.out.println("Campign is not created");
//				}
				
				//Assert.assertEquals(msg, "Campaign" +campaignName+ " succesfully added", "Campign is not created");
				Assert.assertTrue(msg.contains("Successfully Added"), "Campaign is not created");

			}
@Test (groups = "Regression")
public void createCampaignWithMandatoryFieldsTest() throws IOException {
	
			//get values from excel file
			 String campaignName = eUtil.toReadTheDataFromExcel("Campaign",1,1);
			 String targetSize =  eUtil.toReadTheDataFromExcel("Campaign", 1, 2);
			 
				
				//Click on campaign link
				HomePage hp = new HomePage(driver);
				hp.getCampaignLink().click();
				
				//Create campaign with Mandatory fields
				// Click on create campaign
				CampaignPage cp = new CampaignPage(driver);
				cp.getCreateCampaign().click();

				CreateCampaignPage ccp = new CreateCampaignPage(driver);
				ccp.getCampaignName().sendKeys(campaignName);
				ccp.getTargetSize().clear();
				ccp.getTargetSize().sendKeys(targetSize);
				
				ccp.getCreateButton().click();
				
				// Verify the succesfull message
				WebElement toastMsg = hp.getToastMsg();
				wUtil.waitForVisibilityOfElement(driver, toastMsg);
				String msg = toastMsg.getText();
				
				
//				if (msg.contains("Successfully Added")) {
//					System.out.println("Campign created successfully with manadatory fields");
//				} 
//				else {
//					System.out.println("Campign is not created");
//				}
				
				Assert.assertTrue(msg.contains("Successfully Added"), "Campaign is not created");

		}

@Test
	public void createCampaignWithStatusTest() throws IOException {
		
		
		//get values from excel file
		String campaignName = eUtil.toReadTheDataFromExcel("Campaign", 1, 1);
		String targetSize = eUtil.toReadTheDataFromExcel("Campaign", 1,2);
		String status = eUtil.toReadTheDataFromExcel("Campaign", 1, 3);
	    
		
		
		//Click on campaign link
		HomePage hp = new HomePage(driver);
		hp.getCampaignLink().click();
			
		//Create campaign with Mandatory fields and status
		CampaignPage cp = new CampaignPage(driver);
		cp.getCreateCampaign().click();

		CreateCampaignPage ccp = new CreateCampaignPage(driver);
		ccp.getCampaignName().sendKeys(campaignName);
		ccp.getStatus().sendKeys("Open");
		ccp.getTargetSize().clear();
		ccp.getTargetSize().sendKeys(targetSize);
		ccp.getCreateButton().click();
		 
		// Verify the succesfull message
		WebElement toastMsg = hp.getToastMsg();
		wUtil.waitForVisibilityOfElement(driver, toastMsg);
		String msg = toastMsg.getText();
		
		
//		if (msg.contains("Successfully Added")) {
//			System.out.println("Campign created successfully");
//		} 
//		else {
//			System.out.println("Campign is not created");
//		}
		//Assert.assertEquals(msg, "Campaign" +campaignName+" succesfully added", "Campign is not created");
		Assert.assertTrue(msg.contains("Successfully Added"), "Campaign is not created");
		 //Verify the status
		 String createdStatus = driver.findElement(By.xpath("//td[text()='Iphone']//../td[3]")).getText();
//		 if (createdStatus.equals(status)) {
//			 System.out.println("Campaign created with Open status");
//		 }
//		 else {
//			System.out.println("Campaign status missing");
//		}
		 
		 Assert.assertTrue(createdStatus.contains("Open"),"Status is missing");
		 System.out.println("Campaign created with Open status");
	}

	

}
