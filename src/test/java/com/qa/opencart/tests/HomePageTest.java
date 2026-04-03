package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class HomePageTest extends BaseTest {
   
	
	@Test(groups = {"smoke"})
	public void homePageTitle() {
		String actualTitle = homePage.getHomePageTitle();
		 Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE);
	}
	
	
	@Test(groups = {"smoke"})
	public void homePageUrl() {
		String actualUrl = homePage.getHomePageUrl();
		Assert.assertEquals(actualUrl, prop.getProperty("url"));
	}
	
	@Test(groups= {"regression"} ,dataProvider = "getProductData")
	public void homeSearch(String prodcutName) {
	String actualHeader =	homePage.doSearch(prodcutName);
	Assert.assertEquals(actualHeader, "Search - "+prodcutName);
		
	}
	
	
	@DataProvider
	public Object[][] getProductData() {
		return new Object [][] {
			{"Macbook"},
			{"iMac"},
			{"Samsung"}
		};
	}
	
	
	
	
	
	
	
	
}
