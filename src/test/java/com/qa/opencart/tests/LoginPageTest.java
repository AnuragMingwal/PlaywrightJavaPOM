package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class LoginPageTest extends BaseTest {
	
	
	@BeforeMethod(alwaysRun = true)
	public void loginPageNavigation() {
		loginPage = homePage.navigateToLoginPage();
	}
	
		@Test(groups = {"smoke"})
		public void loginPageTitle() {
			
		String actualTitle = loginPage.getLoginTitle();
		Assert.assertEquals(actualTitle, AppConstants.ACCOUNT_PAGE_TITLE);
		}
	 @Test(groups = {"regression"})
	//@Test(priority = 2)
	public void forgotPasswordLink() {
	
		boolean actualTitle  = loginPage.forgottenLink();
		Assert.assertTrue(actualTitle);
	}
	@Test(groups = {"smoke","regression"})
	//@Test(priority = 3)
	public void loginApplication() {
		loginPage = homePage.navigateToLoginPage();
		boolean actualLogin = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("userpassword").trim());
       Assert.assertTrue(actualLogin);
	}

}
