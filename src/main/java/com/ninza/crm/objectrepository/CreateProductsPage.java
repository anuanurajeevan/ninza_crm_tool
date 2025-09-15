package com.ninza.crm.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateProductsPage {
	
	WebDriver driver;
	public CreateProductsPage(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);	
	}
	
	@FindBy (name = "productName")
	private WebElement productName;
	
	@FindBy (name = "productCategory")
	private WebElement productCategory;
	
	@FindBy (name = "quantity")
	private WebElement quantity;
	
	@FindBy (name = "price")
	private WebElement price;
	
	@FindBy (name = "vendorId")
	private WebElement vendorId;
	
	@FindBy (xpath = "//button[@type='submit']")
	private WebElement addButton;
	
	@FindBy(xpath = "//div[@class='Toastify__toast-body']")
	private WebElement toastMsg;
	
	public WebElement getToastMsg() {
		return toastMsg;
	}


	public WebElement getProductName() {
		return productName;
	}

	public WebElement getProductCategory() {
		return productCategory;
	}

	public WebElement getQuantity() {
		return quantity;
	}

	public WebElement getPrice() {
		return price;
	}

	public WebElement getVendorId() {
		return vendorId;
	}

	public WebElement getAddButton() {
		return addButton;
	}

}
