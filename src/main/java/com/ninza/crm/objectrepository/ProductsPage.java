package com.ninza.crm.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage {
	
	WebDriver driver;
	
	public ProductsPage(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);	
	}
	
	@FindBy (xpath = "//span[text()='Add Product']")
	private WebElement createProductPage;
	
	@FindBy(xpath = "//div[@role='alert']")
	private WebElement successMsg;

	@FindBy (xpath = "//input[@placeholder='Search by product Id']")
	private WebElement searchField;

	public WebElement getSearchField() {
		return searchField;
	}

	public WebElement getCreateProduct() {
		return createProductPage;
	}
	

	public WebElement getSuccessMsg() {
		return successMsg;
	}
	
	
	
	

}
