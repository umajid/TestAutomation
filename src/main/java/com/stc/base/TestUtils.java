package com.stc.base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;


public class TestUtils extends TestBase{

	public static WebDriverWait wait = new WebDriverWait(driver, 30);

	// Launch application
	public  static void openApplication(){
		try{

			// Page timeout
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			// open application
			driver.get(Env.getProperty("url"));
			// maximize window
			driver.manage().window().maximize();

		}
		catch(Exception e){
			Assert.assertTrue(false, e.getMessage());
		}
	}

	// Log in with correct username and password
	public  static void doLogin(){
		try{

			// Click on Login Button
			driver.findElement(By.xpath(OR.getProperty("Login_Button"))).click();

			// Enter Email Id 
			driver.findElement(By.xpath(OR.getProperty("Username_Textbox"))).sendKeys(Data.getProperty("Email"));

			// Enter Password 
			driver.findElement(By.xpath(OR.getProperty("Password_Textbox"))).sendKeys(Data.getProperty("Password"));

			// Click on login button
			driver.findElement(By.xpath(OR.getProperty("loginSubmitButton"))).click();

			// Wait for Home page to display
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("LoggedIn_User"))));


		}
		catch(Exception e){
			Assert.assertTrue(false, e.getMessage());
		}
	}

	// Select another city other than default

	public  static void changeCity(String cityName){
		try{

			// Click on City Option to select a new city
			driver.findElement(By.xpath(OR.getProperty("City_Button"))).click();

			Thread.sleep(5000);
			// Wait till city page is displaying
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.getProperty("Sabya_City"))));

			// Select City
			WebElement element = driver.findElement(By.xpath(cityName));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", element);

			// Wait till the Page loads
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("City_Button"))));
			Thread.sleep(3000);

		}
		catch(Exception e){
			Assert.assertTrue(false, e.getMessage());
		}
	}

	// Search and select a product
	public  static void searchProduct(String productName){
		try{

			// Search for a product 
			driver.findElement(By.id(OR.getProperty("Search_Textbox"))).sendKeys(productName);

			Thread.sleep(5000);
			// Wait for Product to display
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(OR.getProperty("SearchedProduct_Link"))));

			// Click on the Product
			driver.findElement(By.linkText(OR.getProperty("SearchedProduct_Link"))).click();

			// wait for the product to appear
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("ProductDetails"))));

		}
		catch(Exception e){
			Assert.assertTrue(false, e.getMessage());
		}
	}

	// Verify Email and Mobile Field is shown with values 
	public static void verifyNotifyEmailAndMobileRegUser(){

		try{

			// Click on Notify me  
			driver.findElement(By.xpath(OR.getProperty("NotifyMe_Button"))).click();

			// Verify Registerd Email is displaying up successfully 
			String actualText = driver.findElement(By.xpath(OR.getProperty("ProductDetails_Email_Textbox"))).getAttribute("value");

			//Verify Email Id displaying
			// Assert State of Mobile Number
			Assert.assertEquals(actualText, Data.getProperty("Email"), "Email Id appearing on Notify me option" +actualText );
			Thread.sleep(8000);


		}
		catch(Exception e){
			Assert.assertTrue(false, e.getMessage());
		}
	}


	// Verify Email and Mobile Field is shown with values 
	public static void verifyAddtoCart(){

		try{

			// Add to Cart Status  
			boolean cartStatus = driver.findElement(By.xpath(OR.getProperty("AddToCart_Button"))).isEnabled();

			if (cartStatus)
				Assert.assertTrue(false, "Add to Cart is Enabled even for Out of Stock Products");
			else
				Assert.assertTrue(true, "Add to Cart is disabled  for Out of Stock Products");
		}
		catch(Exception e){
			Assert.assertTrue(false, e.getMessage());
		}
	}



	// Verify Email and Mobile Field is shown with values 
	public static void verifyNotifyEmailAndMobileGuestUser(){

		try{

			// Click on Notify me  
			driver.findElement(By.xpath(OR.getProperty("NotifyMe_Button"))).click();

			// Verify Email Text box is displaying up for Guest User
			boolean EmailTextbox = driver.findElement(By.xpath(OR.getProperty("ProductDetails_Email_Textbox"))).isDisplayed();
			Assert.assertEquals(EmailTextbox, true,"Email Textbox displaying for Guest user for out of stock product");

			// Getting Email Text Error Message
			String actualText = driver.findElement(By.xpath(OR.getProperty("EmailErrorNotifyMe"))).getText();

			// Assert Email Error Message
			Assert.assertEquals(actualText, Data.getProperty("EmailError_NotifyMe"), "For Guest User Email is appearing as blank with Error Message" +actualText );


		}
		catch(Exception e){
			Assert.assertTrue(false, e.getMessage());
		}
	}


	// Add to Cart and checkout
	public static void navToHomePage(){
		try{
			// Select Product and click on add to cart
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("HomePage"))));
			driver.findElement(By.xpath(OR.getProperty("HomePage"))).click();
		}
		catch(Exception e){
			Assert.assertTrue(false,  e.getMessage());
		}		
	}
	// Navigate to Profile Page
	public  static void navToProfilePage(){
		try{

			Thread.sleep(3000);
			// Click on Logged In User
			driver.findElement(By.xpath(OR.getProperty("LoggedIn_User"))).click();

			// Click on Profile Button 
			driver.findElement(By.xpath(OR.getProperty("Profile_Button"))).click();

		}
		catch(Exception e){
			Assert.assertTrue(false, e.getMessage());
		}
	}


	// Log in with correct username and password
	public  static void modifyEmail(String email){
		try{

			// Navigate to Profile Page
			navToProfilePage();

			// Click on Edit Profile button
			driver.findElement(By.xpath(OR.getProperty("EditProfile_Button"))).click();

			// Click on Modify Email button
			driver.findElement(By.xpath(OR.getProperty("ModifyEmail_Button"))).click();

			//Wait till New Email Id Texbox is displaying
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.getProperty("Email_Textbox"))));

			Thread.sleep(3000);
			// Enter New Email Id
			driver.findElement(By.id(OR.getProperty("Email_Textbox"))).sendKeys(email);

			// Click on Submit Button
			driver.findElement(By.xpath(OR.getProperty("Submit_Button"))).click();

			//Wait till save button is displaying
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Save_Button"))));

			// Click on Save Button
			driver.findElement(By.xpath(OR.getProperty("Save_Button"))).click();

			//Wait till save is finished loading
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("EditProfile_Button"))));

			// Verify Modified Email is displaying up successfully 
			String actualText = driver.findElement(By.xpath(OR.getProperty("ProfileTab_EmailTextbox"))).getText();

			// Assert if the First name is appearing correctly on Home Page
			Assert.assertEquals(actualText, email, "Modified email is displaying corretly");
			Thread.sleep(7000);

		}
		catch(Exception e){
			Assert.assertTrue(false, e.getMessage());
		}
	}

	// Verify Email and Mobile state(Verified or Not Verified) on Profile Page
	public static void VerifyMobileEmailState(String mobileState,String emailState){
		try{
			// Verify  Email state 
			String actualText = driver.findElement(By.xpath(OR.getProperty("EmailStatus"))).getText();

			// Assert Status of Email 
			Assert.assertEquals(actualText, mobileState, "Email Status is displaying corretly as" +actualText );

			// Verify Mobile Number state 
			actualText = driver.findElement(By.xpath(OR.getProperty("MobileNumStatus"))).getText();

			// Assert State of Mobile Number
			Assert.assertEquals(actualText, mobileState, "Mobile Number Status is displaying corretly as" +actualText );

		}
		catch(Exception e){
			Assert.assertTrue(false,  e.getMessage());
		}		
	}

	// Select Sawa Vouchers  from headers
	public static void selectSawaVouchers(){
		try{
			// Click on Telecom Vouchers link
			driver.findElement(By.linkText(OR.getProperty("TelecomVouchers_Link"))).click();

			// Click on Sawa Link
			driver.findElement(By.linkText(OR.getProperty("SAWA_Link"))).click();

			// Wait till products load
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("ProductName"))));
			driver.findElement(By.xpath(OR.getProperty("CloseEmailVerifyAlert"))).click();

		}
		catch(Exception e){
			Assert.assertTrue(false,  e.getMessage());
		}		
	}

	// Add to Cart and checkout
	public static void addAndNavToCheckout(String addToCart){
		try{
			// Select Product and click on add to cart
			driver.findElement(By.xpath(addToCart)).click();

			// Click on Checkout button
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("CheckoutButton"))));
			WebElement element = driver.findElement(By.xpath(OR.getProperty("CheckoutButton")));
			Actions actions = new Actions(driver);
			actions.moveToElement(element).click().build().perform();

			// Wait till products load
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("ContactDetails"))));

		}
		catch(Exception e){
			Assert.assertTrue(false,  e.getMessage());
		}		
	}

	// Delete Cart

	public static void deleteCart(){
		try{
			// Select on cart Icon
			driver.findElement(By.xpath(OR.getProperty("Cart_Icon"))).click();

			// Click on Checkout button
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("Delete_Item"))));
			driver.findElement(By.xpath(OR.getProperty("Delete_Item"))).click();
			Thread.sleep(5000);

		}
		catch(Exception e){
			Assert.assertTrue(false,  e.getMessage());
		}		
	}


	// Verify User is navigate to contact details page after clicking on checkout

	public static void verifyContactDetailsPage(){
		try{
			// Verify user is on contacts page
			String actualText = driver.findElement(By.xpath(OR.getProperty("ContactDetails"))).getText().trim();

			System.out.println("---------"+actualText+"--------");
			// Assert State of Mobile Number
			Assert.assertEquals(actualText, Data.getProperty("ConatctsPage"), "User is navigated to my contacts page" );


		}
		catch(Exception e){
			Assert.assertTrue(false,  e.getMessage());
		}		
	}







	// Create New Account Method and Verify if new Account is created.
	public static void fillNewAccountDetails(){
		try{
			// Enter basic details
			fillBasicDetails();
			// Generate random email id using First and Last Name
			String emailAddress = Data.getProperty("FirstNameValue")+  Data.getProperty("LastNameValue") + generateRandomNumber(3) + "@yahoo.in";

			// Enter Email Id 
			driver.findElement(By.id(OR.getProperty("Email_Textbox"))).sendKeys(emailAddress);

			// Enter Mobile Number
			Thread.sleep(3000);	
			driver.findElement(By.id(OR.getProperty("Mobile_Textbox"))).sendKeys(Data.getProperty("MobileNumber"));

			// Enter Password 
			Thread.sleep(3000);			
			driver.findElement(By.xpath(OR.getProperty("Password_Textbox"))).sendKeys(Data.getProperty("FirstNameValue")+"@123");

		}
		catch(Exception e){
			Assert.assertTrue(false,  e.getMessage());
		}		
	}

	// Click on Submit and verify the corresponding result
	public static void submitAndVerifyAccountMessage(String options,String mobile){
		try{
			// Clear Mobile Number and Renter again
			Thread.sleep(1000);
			driver.findElement(By.id(OR.getProperty("Mobile_Textbox"))).clear();
			Thread.sleep(2000);
			driver.findElement(By.id(OR.getProperty("Mobile_Textbox"))).sendKeys(mobile);


			// Select  Subscribe to Offers & Alerts and Terms & Conditions is Selected
			if(options.equalsIgnoreCase("Both Select")){
				//Select the terms and Condition check box 				
				boolean termsAndConditionsFlag =driver.findElement(By.id(OR.getProperty("TermsAndConditions"))).isSelected();
				if(!termsAndConditionsFlag)
					driver.findElement(By.xpath(OR.getProperty("TermsAndConditionsCheckbox"))).click();

				//Select  Subscribe to Offers & Alerts check box 				
				boolean subscribe =driver.findElement(By.id(OR.getProperty("Subscription"))).isSelected();
				if(!subscribe)
					driver.findElement(By.xpath(OR.getProperty("SubscriptionCheckbox"))).click();

				// Click on Create Account button
				driver.findElement(By.xpath(OR.getProperty("CreatAccountButton"))).click();

				// Wait for Home page to display
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("LoggedIn_User"))));

				// Verify user is sucessfully logged in and home page is displaing
				String actualText = driver.findElement(By.xpath(OR.getProperty("LoggedIn_User"))).getText();

				// Assert if the First name is appearing correctly on Home Page
				Assert.assertEquals(actualText, Data.getProperty("FirstNameValue"), "User is sucessfully logged in to the application and is currently on home page");

				// Sign out the Logged In User
				signoutUser();


			}
			// Select  Subscribe to Offers & Alerts only
			else if(options.equalsIgnoreCase("Offers")){
				//Make sure  the terms and Condition check box is unselect				
				boolean termsAndConditionsFlag =driver.findElement(By.id(OR.getProperty("TermsAndConditions"))).isSelected();
				if(termsAndConditionsFlag)
					driver.findElement(By.xpath(OR.getProperty("TermsAndConditionsCheckbox"))).click();

				//Select  Subscribe to Offers & Alerts check box 				
				boolean subscribe =driver.findElement(By.id(OR.getProperty("Subscription"))).isSelected();
				if(!subscribe)
					driver.findElement(By.xpath(OR.getProperty("SubscriptionCheckbox"))).click();

				// Click on Create Account button
				driver.findElement(By.xpath(OR.getProperty("CreatAccountButton"))).click();

				// Get the Error Text
				String actualText = driver.findElement(By.xpath(OR.getProperty("ErrorText"))).getText();

				// Assert if the First name is appearing correctly on Home Page
				Assert.assertEquals(actualText, Data.getProperty("TermsAndCondtionErrorMsg"), "Error messge is coming up correctly when Terms and condition checkbox is unselected during sign up.");

			}
			// Select Terms & Conditions is Selected
			else if(options.equalsIgnoreCase("Terms")){

				// Clear Mobile Number and Renter again
				driver.findElement(By.id(OR.getProperty("Mobile_Textbox"))).clear();
				Thread.sleep(2000);
				driver.findElement(By.id(OR.getProperty("Mobile_Textbox"))).sendKeys(mobile);

				//Select the terms and Condition check box 				
				boolean termsAndConditionsFlag =driver.findElement(By.id(OR.getProperty("TermsAndConditions"))).isSelected();
				if(!termsAndConditionsFlag)
					driver.findElement(By.xpath(OR.getProperty("TermsAndConditionsCheckbox"))).click();

				//Select  Subscribe to Offers & Alerts check box 				
				boolean subscribe =driver.findElement(By.id(OR.getProperty("Subscription"))).isSelected();
				if(subscribe)
					driver.findElement(By.xpath(OR.getProperty("SubscriptionCheckbox"))).click();

				// Click on Create Account button
				driver.findElement(By.xpath(OR.getProperty("CreatAccountButton"))).click();

				// Wait for Home page to display
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("LoggedIn_User"))));

				// Verify user is sucessfully logged in and home page is displaing
				String actualText = driver.findElement(By.xpath(OR.getProperty("LoggedIn_User"))).getText();

				// Assert if the First name is appearing correctly on Home Page
				Assert.assertEquals(actualText, Data.getProperty("FirstNameValue"), "User is sucessfully logged in to the application and is currently on home page");

				// Sign out the Logged In User
				signoutUser();


			}

		}
		catch(Exception e){
			Assert.assertTrue(false,  e.getMessage());
		}		
	}

	// Verify Duplicate Mobile Number Error Message
	public static void verifyDuplicateMobileErrorMessage(){
		try{
			//Select the terms and Condition check box 				
			boolean termsAndConditionsFlag =driver.findElement(By.id(OR.getProperty("TermsAndConditions"))).isSelected();
			if(!termsAndConditionsFlag)
				driver.findElement(By.xpath(OR.getProperty("TermsAndConditionsCheckbox"))).click();

			// Click on Create Account button
			driver.findElement(By.xpath(OR.getProperty("CreatAccountButton"))).click();


			// Fetch the Error Message 
			String actualText = driver.findElement(By.xpath(OR.getProperty("ErrorText"))).getText();

			// Verify the Duplicate Mobile Message is reflecting correctly.
			Assert.assertEquals(actualText, Data.getProperty("DuplicateMobileNumErrorMsg"), "Error Messge appears correctly when the user tries to register with Mobile Number that is already used ");

		}
		catch(Exception e){
			Assert.assertTrue(false,  e.getMessage());
		}
	}

	// Verify Duplicate Email  Error Message
	public static void verifyDuplicateEmailErrorMessage(){
		try{

			// Enter basic details
			fillBasicDetails();

			// Enter Email Id 
			driver.findElement(By.id(OR.getProperty("Email_Textbox"))).sendKeys(Data.getProperty("RegisteredEmail"));


			// Generate random vaid mobile number
			String mobileNum = "5" + generateRandomNumber(8);

			// Enter Random valid Mobile Number
			Thread.sleep(3000);
			driver.findElement(By.id(OR.getProperty("Mobile_Textbox"))).sendKeys(mobileNum);

			// Enter Password 
			Thread.sleep(3000);
			driver.findElement(By.xpath(OR.getProperty("Password_Textbox"))).sendKeys(Data.getProperty("FirstNameValue")+"@123");


			//Select the terms and Condition check box 				
			boolean termsAndConditionsFlag =driver.findElement(By.id(OR.getProperty("TermsAndConditions"))).isSelected();
			if(!termsAndConditionsFlag)
				driver.findElement(By.xpath(OR.getProperty("TermsAndConditionsCheckbox"))).click();

			// Click on Create Account button
			driver.findElement(By.xpath(OR.getProperty("CreatAccountButton"))).click();


			// Fetch the Error Message 
			String actualText = driver.findElement(By.xpath(OR.getProperty("ErrorText"))).getText();

			// Verify the Duplicate Email Message is reflecting correctly.
			Assert.assertEquals(actualText, Data.getProperty("DuplicateEmailErrorMsg"), "Error Messge appears correctly when the user tries to register with Email that is already used ");

		}
		catch(Exception e){
			Assert.assertTrue(false,  e.getMessage());
		}
	}



	// Verify Invalid Mobile Num Error Message
	public static void verifyInvalidMobileNumErrorMsg(){
		try{

			// Enter basic details
			fillBasicDetails();

			// Generate random email id using First and Last Name
			String emailAddress = Data.getProperty("FirstNameValue")+  Data.getProperty("LastNameValue") + generateRandomNumber(3) + "@yahoo.in";

			// Enter Email Id 
			driver.findElement(By.id(OR.getProperty("Email_Textbox"))).sendKeys(emailAddress);


			// Generate random Invalid mobile number
			String mobileNum = "5" + generateRandomNumber(7);

			// Enter Random Invalid Mobile Number
			Thread.sleep(3000);
			driver.findElement(By.id(OR.getProperty("Mobile_Textbox"))).sendKeys(mobileNum);

			// Enter Password 
			Thread.sleep(3000);
			driver.findElement(By.xpath(OR.getProperty("Password_Textbox"))).sendKeys(Data.getProperty("FirstNameValue")+"@123");


			//Select the terms and Condition check box 				
			boolean termsAndConditionsFlag =driver.findElement(By.id(OR.getProperty("TermsAndConditions"))).isSelected();
			if(!termsAndConditionsFlag)
				driver.findElement(By.xpath(OR.getProperty("TermsAndConditionsCheckbox"))).click();

			// Click on Create Account button
			driver.findElement(By.xpath(OR.getProperty("CreatAccountButton"))).click();


			// Fetch the Error Message 
			String actualText = driver.findElement(By.xpath(OR.getProperty("ErrorText"))).getText();

			// Verify Invalid Mobile Number Message is reflecting correctly.
			Assert.assertEquals(actualText, Data.getProperty("InvalidMobileNumErrorMsg"), "Error Messge appears correctly when the user tries to register with Invalid Mobile Number");

		}
		catch(Exception e){
			Assert.assertTrue(false,  e.getMessage());
		}
	}


	// Verify Invalid Email  Error Message
	public static void verifyInvalidEmailErrorMsg(){
		try{

			// Enter basic details
			fillBasicDetails();

			// Generate random Invalid email id using First and Last Name
			String emailAddress = Data.getProperty("FirstNameValue")+  Data.getProperty("LastNameValue") + generateRandomNumber(3) + "test.in";

			// Enter Email Id 
			driver.findElement(By.id(OR.getProperty("Email_Textbox"))).sendKeys(emailAddress);


			// Generate random valid mobile number
			String mobileNum = "5" + generateRandomNumber(8);

			// Enter Random valid Mobile Number
			Thread.sleep(3000);
			driver.findElement(By.id(OR.getProperty("Mobile_Textbox"))).sendKeys(mobileNum);

			// Enter Password 
			Thread.sleep(3000);
			driver.findElement(By.xpath(OR.getProperty("Password_Textbox"))).sendKeys(Data.getProperty("FirstNameValue")+"@123");


			//Select the terms and Condition check box 				
			boolean termsAndConditionsFlag =driver.findElement(By.id(OR.getProperty("TermsAndConditions"))).isSelected();
			if(!termsAndConditionsFlag)
				driver.findElement(By.xpath(OR.getProperty("TermsAndConditionsCheckbox"))).click();

			// Click on Create Account button
			driver.findElement(By.xpath(OR.getProperty("CreatAccountButton"))).click();


			// Fetch the Error Message 
			String actualText = driver.findElement(By.xpath(OR.getProperty("ErrorText"))).getText();

			// Verify Invalid Email Message is reflecting correctly.
			Assert.assertEquals(actualText, Data.getProperty("InvalidEmailMsg"), "Error Messge appears correctly when the user tries to register with Invalid Email");

		}
		catch(Exception e){
			Assert.assertTrue(false,  e.getMessage());
		}
	}


	// Verify Error Message is displaying if small password is chosen while creating new account.
	public static void verifySmallPasswordErrorMsg(){
		try{

			// Enter basic details
			fillBasicDetails();

			// Generate random Invalid email id using First and Last Name
			String emailAddress = Data.getProperty("FirstNameValue")+  Data.getProperty("LastNameValue") + generateRandomNumber(3) + "@test.in";

			// Enter Email Id 
			driver.findElement(By.id(OR.getProperty("Email_Textbox"))).sendKeys(emailAddress);


			// Generate random valid mobile number
			String mobileNum = "5" + generateRandomNumber(8);

			// Enter Random valid Mobile Number
			Thread.sleep(3000);
			driver.findElement(By.id(OR.getProperty("Mobile_Textbox"))).sendKeys(mobileNum);

			// Enter Password 
			Thread.sleep(3000);
			driver.findElement(By.xpath(OR.getProperty("Password_Textbox"))).sendKeys(Data.getProperty("FirstNameValue"));


			//Select the terms and Condition check box 				
			boolean termsAndConditionsFlag =driver.findElement(By.id(OR.getProperty("TermsAndConditions"))).isSelected();
			if(!termsAndConditionsFlag)
				driver.findElement(By.xpath(OR.getProperty("TermsAndConditionsCheckbox"))).click();

			// Click on Create Account button
			driver.findElement(By.xpath(OR.getProperty("CreatAccountButton"))).click();


			// Fetch the Error Message 
			String actualText = driver.findElement(By.xpath(OR.getProperty("ErrorText"))).getText();

			// Verify Password Criteria Error Message is reflecting correctly.
			Assert.assertEquals(actualText, Data.getProperty("PasswordCriteriaMessage"), "Error Messge appears correctly when the user tries to chose a password that does not meet the required criteria");

		}
		catch(Exception e){
			Assert.assertTrue(false,  e.getMessage());
		}
	}

	// Enter basic details
	public static void fillBasicDetails(){
		try{	

			// Click on login buton 
			driver.findElement(By.xpath(OR.getProperty("Login_Button"))).click();

			//Click on Create Account Link
			driver.findElement(By.linkText(OR.getProperty("CreateAccount_Link"))).click();

			// Enter First Name
			driver.findElement(By.id(OR.getProperty("FirstName_Textbox"))).sendKeys(Data.getProperty("FirstNameValue"));

			// Enter Last Name
			driver.findElement(By.id(OR.getProperty("LastName_Textbox"))).sendKeys(Data.getProperty("LastNameValue"));
		}
		catch(Exception e){
			Assert.assertTrue(false,  e.getMessage());
		}
	}
	// Sign out from application
	public static void signoutUser(){
		try{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("LoggedIn_User"))));
			// Click on the logged in user button
			driver.findElement(By.xpath(OR.getProperty("LoggedIn_User"))).click();
			// Click on sign out button to log out from the application
			driver.findElement(By.xpath(OR.getProperty("SignOut_button"))).click();
			Thread.sleep(5000);

		}
		catch(Exception e){
			Assert.assertTrue(false,  e.getMessage());
		}
	}

	// Verify Terms and conditions default state
	public static void verifyTermsAndConditionsState(){
		try{
			// Click on Login button
			driver.findElement(By.xpath(OR.getProperty("Login_Button"))).click();

			//Click on Create Account Link
			driver.findElement(By.linkText(OR.getProperty("CreateAccount_Link"))).click();

			boolean termsAndConditionsFlag =driver.findElement(By.id(OR.getProperty("TermsAndConditions"))).isSelected();

			if(!termsAndConditionsFlag)
				Assert.assertTrue(true, "Terms and Conditions Checkbox by default is unchecked");
			else
				Assert.assertTrue(false, "Terms and Conditions Checkbox by default is checked");

		}
		catch(Exception e){
			Assert.assertTrue(false,  e.getMessage());
		}		
	} 

	// Verify Offers and Subscribe  default state
	public static void verifyOffersAndAlertsState(){
		try{
			// Click on Login button
			driver.findElement(By.xpath(OR.getProperty("Login_Button"))).click();

			//Click on Create Account Link
			driver.findElement(By.linkText(OR.getProperty("CreateAccount_Link"))).click();

			boolean subscribeFlag =driver.findElement(By.id(OR.getProperty("Subscription"))).isSelected();
			if(subscribeFlag)
				Assert.assertTrue(true, "Subscribe to Offers & Alerts by default is checked");
			else
				Assert.assertTrue(false, "Subscribe to Offers & Alerts by default is unchecked");
		}
		catch(Exception e){
			Assert.assertTrue(false,  e.getMessage());
		}		
	}

	// DeSelect Offers and Alert field
	public static void deselectOffersAndAlerts(){
		try{
			// Click on Login button
			driver.findElement(By.xpath(OR.getProperty("Login_Button"))).click();

			//Click on Create Account Link
			driver.findElement(By.linkText(OR.getProperty("CreateAccount_Link"))).click();

			// Unselect Offers and Alert field
			driver.findElement(By.xpath(OR.getProperty("SubscriptionCheckbox"))).click();

			// Verify that the Offers and Alerts Field is deselected
			boolean subscribeFlag =driver.findElement(By.id(OR.getProperty("Subscription"))).isSelected();

			Assert.assertEquals(subscribeFlag,false,"Subscribe to Offers & Alerts Field is deselected");
		}
		catch(Exception e){
			Assert.assertTrue(false,  e.getMessage());
		}		
	}

	// Generate Random Number
	public static String generateRandomNumber(int s){

		try{
			double number = Math.random();
			double pow = Math.pow(10, s);
			int random = (int) (number*pow);
			return Integer.toString(random);
		}
		catch(Exception e){
			Assert.assertTrue(false,  e.getMessage());
			return null;
		}

	}
	
	
	
	// Umendra
	/**
	 * This method will use to login 
	 * @param UserNmae- Pass String as UserName
	 * @param Password- Pass String as Pasword
	 */
	public void LoginToApplication(String UserNmae,String Password) {
		try {
			//click on login button
			driver.findElement(By.xpath(OR.getProperty("LoginButton"))).click();
			driver.findElement(By.xpath(OR.getProperty("UserNameInputBox"))).sendKeys(UserNmae);
			driver.findElement(By.xpath(OR.getProperty("PasswordInputBox"))).sendKeys(Password);
			driver.findElement(By.xpath(OR.getProperty("LoginClickButton"))).click();
			wait(4);
		}catch (Exception e) {
			Assert.assertTrue(false, "Unable to login on application" + e.getMessage());
		}
	}
	
	
	/**
	 * This method will use to wait
	 * @param num- Pass integer value for wait time in second 
	 * @throws InterruptedException
	 */
	public void wait(int num) throws InterruptedException {
		int waitTime= num*1000;
		Thread.sleep(waitTime);
	}
	
	/**
	 * this method will use to delete the cart value if product is available in cart
	 */
	public void deleteCartProductIfAvailable() {
		try {
			String cartValue = driver.findElement(By.xpath(OR.getProperty("CartCount"))).getText();
			int cartCount = Integer.parseInt(cartValue);
			if (cartCount != 0) {
				driver.findElement(By.xpath(OR.getProperty("CartCount"))).click();
				wait(4);
				driver.findElement(By.xpath(OR.getProperty("DeleteItem"))).click();
				wait(6);
				System.out.println("User has successfully remove the product from cart");
			}

		} catch (Exception e) {
			Assert.assertTrue(false, "Unable to login on application" + e.getMessage());
		}
	}
	
	/**
	 * this method will use to scroll the page hori
	 */
	public void pageScrollDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		js.executeScript("window.scrollBy(0,200)");		
	}
	


}
