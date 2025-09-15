package com.ninza.crm.producttest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.ninza.crm.baseClass.BaseClass;
import com.ninza.crm.generic.fileutility.ExcelFileUtility;
import com.ninza.crm.generic.fileutility.PropertyFileUtility;
import com.ninza.crm.generic.webdriverUtility.JavaUtility;
import com.ninza.crm.generic.webdriverUtility.WebdriverUtility;
import com.ninza.crm.objectrepository.CreateProductsPage;
import com.ninza.crm.objectrepository.HomePage;
import com.ninza.crm.objectrepository.LoginPage;
import com.ninza.crm.objectrepository.ProductsPage;


public class CreateProductTest extends BaseClass{
@Test (groups = {"Smoke","Regression"})
	public void createProductTest() throws IOException, InterruptedException {
		
		

        //Read data from excel sheet
        //Create random number
        int randNum = jUtil.getRandomNumber();
        String productName = eUtil.toReadTheDataFromExcel("CampProducts", 1, 1)+randNum;
        String quantity = eUtil.toReadTheDataFromExcel("CampProducts", 1, 2);
		String price = eUtil.toReadTheDataFromExcel("CampProducts", 1, 3); 
		


        
        //click on product link
		HomePage hp = new HomePage(driver);
		hp.getProductsLink().click();
		
        //click on add product button
        ProductsPage pp = new ProductsPage(driver);
        pp.getCreateProduct().click();
        
		//Create product
        CreateProductsPage cp = new CreateProductsPage(driver);
        cp.getProductName().sendKeys(productName);
		
		
		WebElement category = cp.getProductCategory();
		wUtil.select(category, 3);
		
		WebElement quantityEle = cp.getQuantity();
		Thread.sleep(4000);
		quantityEle.clear();
		quantityEle.sendKeys(quantity);
		
		WebElement priceEle = cp.getPrice();
		priceEle.clear();
		priceEle.sendKeys(price);
		
		
		WebElement vendor = cp.getVendorId();
		wUtil.select(vendor, 2);

        
		cp.getAddButton().click();
		
		Thread.sleep(1000);
		
		WebElement successMsg = hp.getToastMsg();
		wUtil.waitForVisibilityOfElement(driver, successMsg);
		String msg = successMsg.getText();
		System.out.println(msg);
		if (msg.contains(productName)) {
			System.out.println("Product is created uccessfully");
			} 
		else {
			System.out.println("Product is not created");
			}
		
		
	}


}
