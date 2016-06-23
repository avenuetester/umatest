import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Screen;
import org.testng.Reporter;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.But;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;
import java.util.List;
import java.util.Map;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.But;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Tester{
	
	public static WebDriver driver=null;
	int beforecount,aftercount;
	Logger loggger = Logger.getLogger("devpinoyLogger");
	@Before
	public void taggedHookMethod1() throws InterruptedException {
		driver=new FirefoxDriver();
		driver.manage().window().maximize();
		
		
	}
	@After
	public void tearDown() {
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		driver.quit();
		
	}
	@Given("^user navigates to \"([^\"]*)\"$")
	public void test1(String URL){
		System.out.println(URL);
		driver.get("http://qa-test.avenuecode.com/users/sign_in");
	}
	@When("^user logs in using Username as \"([^\"]*)\"$")
	public void test2(String UserName) throws InterruptedException{
		try{
		List<WebElement> username=driver.findElements(By.id("user_email"));
		if (username.size()==0){
			Assert.fail("username is missing");
		}
		username.get(0).sendKeys(UserName);
		Thread.sleep(1000);
		username.get(0).sendKeys(Keys.TAB);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	@When("^password as \"([^\"]*)\"$")
	public void test21(String Password) throws InterruptedException{
		try{
		List<WebElement> password=driver.findElements(By.id("user_password"));
		if (password.size()==0){
			Assert.fail("password field missing");
		}
		password.get(0).sendKeys(Password);
		List<WebElement> SignIn=driver.findElements(By.cssSelector("input.btn.btn-primary"));
		if (SignIn.size()==0){
			Assert.fail("Sign In button missing");
		}
		SignIn.get(0).click();
		}catch(Exception e){
			e.printStackTrace();
			driver.quit();
		}
	}
	@Then("^login should be \"([a-zA-Z]{1,})\"$")
	public void test3(String flagg){
		try{
			if (!waitForElement(10,By.className("container-fluid"))){
				Assert.fail("Unable to Sign in ");
			}else{
				System.out.println("User Sign in successfully");
			}
		List<WebElement> result = driver.findElements(By.className("container-fluid"));
		
		}catch(Exception e){
			System.out.println("test");
			driver.quit();
		}
	}
	@Then("^user should see the message \"([a-zA-Z]{1,})\"'s ToDo List$")
	public void test31(String flagg){
		try{
		waitForElement(10,By.className("container"));
		List<WebElement> toDoContainer = driver.findElements(By.className("container"));
		if (toDoContainer.size()==0){
			Assert.fail("Users To do list text container missing");
		}
		List<WebElement> text=toDoContainer.get(0).findElements(By.tagName("H1"));
		String expectedtext=flagg+"'s ToDo List";
		if (text.get(0).getText().contains(expectedtext)){
			System.out.println("message displayed" + expectedtext);
		}else{
			Assert.fail("Expected text "+expectedtext+ " missing ");
		}
		
		}catch(Exception e){
			System.out.println("test");
			driver.quit();
		}
	}
	@Then("^user should see \"([^\"]*)\"$")
	public void test4(String tasks){
		List<WebElement> result = null;
		try{
			if (tasks.equals("My Tasks")) {
				result = driver.findElements(By.className("container-fluid"));
				if (result.size()!=0){
					Reporter.log("Navigation Bar missing");
				}
				
				if (result.get(0).getText().contains(tasks)){
					System.out.println(tasks +  " is available on the navigation bar");
				}else{
				Assert.fail(tasks + " is missing in navigation bar");
				}
			}else if (tasks.equals("Manage Subtasks")){
				result=driver.findElement(By.className("table")).findElements(By.cssSelector("button.btn.btn-xs.btn-primary.ng-binding"));
				if (result.size()!=0){
					Reporter.log("Tasks list missing");
				}
				if (result.get(0).getText().contains(tasks)){
					System.out.println(tasks +  " is available in the Tasks list");
				}else{
				Assert.fail(tasks + " is missing in Tasks list");
				}
			}
		}catch(Exception e){
			driver.quit();
		}
	}
	@Then("^verify No of subtasks created for that tasks$")
	public void test44(){
		String[] NumberOfTasks;
		String subtasksNumber;
		try{
			List<WebElement> result=driver.findElement(By.className("table")).findElements(By.cssSelector("button.btn.btn-xs.btn-primary.ng-binding"));
			if (result.size()!=0){
				Reporter.log("Tasks list missing");
			}
			NumberOfTasks=result.get(0).getText().split(" ");
			subtasksNumber = NumberOfTasks[0].replace(")", "").replace("(", "");
			System.out.println("Displayed subtasks count at MY TASKS : "+subtasksNumber);
			result.get(0).click();
			List<WebElement> subtasksPopup = driver.findElement(By.cssSelector("div.modal-body.ng-scope")).findElements(By.cssSelector("button.btn.btn-xs.btn-danger"));
			System.out.println("No of SubTasks at Managed Subtaks popup : "+subtasksPopup.size());
			if (Integer.parseInt(subtasksNumber)==subtasksPopup.size()){
				System.out.println("verified Number of subtasks");
			}else{
				Assert.fail("subtask count issue");
			}
		}catch(Exception e){
			driver.quit();
		}
	}
	
	@Then("^verify the task lenght in the tasks list$")
	public void test41(String taskslength){
		try{
			List<WebElement> result = driver.findElements(By.cssSelector("div.panel.panel-default"));
			if (result.size()!=0){
				Reporter.log("created task container missing");
			}
			List<WebElement> tasks = result.get(0).findElements(By.className("task_body"));
			if (tasks.size()==0){
				System.out.println("There are No tasks available in My taks");
			}else{
				System.out.println("Tasks are :");
				for (int i=0;i<tasks.size();i++){
					System.out.println(tasks.get(i).getText());
					if (result.get(0).getText().length()<3){
						Assert.fail("Entered task is < 3 chars");
						return;
					}else if (result.get(0).getText().length()>250){
						Assert.fail("Entered task is >250 chars");
						return;
					}
				}
			}
		
		}catch(Exception e){
			driver.quit();
		}
	}
	
	
	@When("^Userclick the link \"([^\"]*)\"$")
	public void test5(String link){
		try{
			System.out.println("link "+link);
			if (link.equals("My Tasks")){
				List<WebElement> result = driver.findElements(By.className("container-fluid"));
				if (result.size()!=0){
					Reporter.log("Navigation Bar missing");
				}
				List<WebElement> links=result.get(0).findElements(By.linkText(link));
				links.get(0).click();
				waitForElement(10,By.className("bs-example"));
			}
			if (link.equalsIgnoreCase("Manage Subtasks")){
				List<WebElement> result = driver.findElements(By.className("bs-example"));
				if (result.size()!=0){
					Reporter.log("My tasks missing");
				}
				List<WebElement> links=result.get(0).findElements(By.cssSelector("button.btn.btn-xs.btn-primary.ng-binding"));
				System.out.println("sizzzz"+links.size());
				links.get(0).click();
				waitForElement(10,By.className("modal-content"));
			}
		
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@When("^enter the new task \"([^\"]*)\" by hitting \"([^\"]*)\"$")
	public void test51(String textfield, String clickThrough){
		try{
		if (!waitForElement(10,By.className("input-group"))){
			Assert.fail("Type a new task here Text field is missing");
		}
		List<WebElement>  newTaskField = driver.findElements(By.id("new_task"));
		newTaskField.get(0).sendKeys(textfield);
		if (clickThrough.equals("enter")){
			newTaskField.get(0).sendKeys(Keys.ENTER);
		}
		waitForElement(10,By.cssSelector("span.input-group-addon.glyphicon.glyphicon-plus"));
		List<WebElement> plusButton = driver.findElements(By.cssSelector("span.input-group-addon.glyphicon.glyphicon-plus"));
		if (plusButton.size()==0){
			Assert.fail("Add task button missing ");
		}
		plusButton.get(0).click();
		waitForElement(10,By.cssSelector("div.panel.panel-default"));
		
		}catch(Exception e){
			driver.quit();
		}
	}
	@Then("^verify the task ID and the task description should be disabled$")
	public void test65(){
		try{
			waitForElement(10,By.className("modal-content"));
		List<WebElement> popupcontainer = driver.findElements(By.className("modal-content"));
		if (popupcontainer.size()!=0){
			Reporter.log("Managed Subtasks navigated popup container missing");
		}
		List<WebElement> taskIDDesc = popupcontainer.get(0).findElements(By.cssSelector("div.modal-header.ng-scope"));
		if (taskIDDesc.size()==0){
			Assert.fail("Task ID and Description is missing");
		}else{
			System.out.println("Tasks ID and Description : "+taskIDDesc.get(0).getText());
			if (taskIDDesc.get(0).findElements(By.tagName("textarea")).size()==0){
				System.out.println("Task ID and Description is NOT updatable");
			}else{
				Assert.fail("task id and description is updatable");
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Then("^User able to add subtask with two fifty chars and MMDDyyyy format data and see the subtask is appended$")
	public void test67(){
		try{
			waitForElement(10,By.className("modal-content"));
		List<WebElement> popupcontainer = driver.findElements(By.className("modal-content"));
		if (popupcontainer.size()!=0){
			Reporter.log("Managed Subtasks navigated popup container missing");
		}
		String str="1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890";
		System.out.println("lenght of string..... : "+str.length());
		String date="12/30/2016";
		List<WebElement> descriptionDateContainer = popupcontainer.get(0).findElements(By.name("sub_task_form"));
		if (descriptionDateContainer.size()==0){
			Assert.fail("Description field is missing");
		}
		descriptionDateContainer.get(1).findElements(By.tagName("input")).get(0).sendKeys(str);
		
		descriptionDateContainer.get(1).findElements(By.tagName("input")).get(1).sendKeys(date);
		
		List<WebElement> addButton = popupcontainer.get(0).findElements(By.id("add-subtask"));
		if (addButton.size()==0){
			Assert.fail("Add button missing....");
		}
		addButton.get(0).click();
		waitForElement(10,By.className("table"));
		List<WebElement> addedSubTasks=popupcontainer.get(0).findElements(By.className("table")).get(0).findElements(By.className("task_body"));
		if (addedSubTasks.size()==0){
			Assert.fail("Unable to add the subtasks with 250 chars of desc and MMDDYYY format");
		}else{
			for (WebElement e:addedSubTasks){
				System.out.println("Added sub task : "+e.getText());
				if (e.getText().equals(str)){
					System.out.println("Added subtask successfully with 250 chars and MMDDYYYY format");
				}
			}
			
		}
		
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@But("^The Task Description and Due Date are required fields")
	public void test68(){
		try{
			waitForElement(10,By.className("modal-content"));
		List<WebElement> popupcontainer = driver.findElements(By.className("modal-content"));
		if (popupcontainer.size()!=0){
			Reporter.log("Managed Subtasks navigated popup container missing");
		}
		//String str="1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890";
		//System.out.println("lenght of string..... : "+str.length());
		//String date="12/30/2016";
		List<WebElement> descriptionDateContainer = popupcontainer.get(0).findElements(By.name("sub_task_form"));
		if (descriptionDateContainer.size()==0){
			Assert.fail("Description field is missing");
		}
		descriptionDateContainer.get(1).findElements(By.tagName("input")).get(0).sendKeys("");
		
		descriptionDateContainer.get(1).findElements(By.tagName("input")).get(1).sendKeys("");
		
		List<WebElement> addButton = popupcontainer.get(0).findElements(By.id("add-subtask"));
		if (addButton.size()==0){
			Assert.fail("Add button missing....");
		}
		addButton.get(0).click();
		waitForElement(10,By.className("table"));
		List<WebElement> addedSubTasks=popupcontainer.get(0).findElements(By.className("table")).get(0).findElements(By.className("task_body"));
		if (addedSubTasks.size()==0){
			Assert.fail("Unable to add the subtasks with 250 chars of desc and MMDDYYY format");
		}else{
			for (WebElement e:addedSubTasks){
				System.out.println("Added sub task : "+ e.getText());
				if (e.getText().equals("empty")){
					Assert.fail("User should not add task with empty desc and empty date");
				}
			}
			
		}
		
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Then("^user should see the created tasks \"([^\"]*)\" so far$")
	public void test6(String createdtasks){
		try{
		List<WebElement> result = driver.findElements(By.cssSelector("div.panel.panel-default"));
		if (result.size()!=0){
			Reporter.log("created task container missing");
		}
		List<WebElement> tasks = result.get(0).findElements(By.className("task_body"));
		if (tasks.size()==0){
			System.out.println("There are No tasks available in My taks");
		}else{
			System.out.println("Tasks are :");
			for (int i=0;i<tasks.size();i++){
				System.out.println(tasks.get(i).getText());
			}
		}
		if (tasks.contains(createdtasks)){
			System.out.println("User able to see the created tasks :"+createdtasks);
		}
		}catch(Exception e){
			driver.quit();
		}
	}
	
	@And("^User counts the list of created taks \"([^\"]*)\"$")
	public void test61(String noOfTasks){
		try{
		List<WebElement> result = driver.findElements(By.cssSelector("div.panel.panel-default"));
		if (result.size()!=0){
			Reporter.log("created task container missing");
		}
		List<WebElement> tasks = result.get(0).findElements(By.className("task_body"));
		if (noOfTasks.equals("before")){
			beforecount=tasks.size();
			System.out.println("before ...."+beforecount);
		}else{
			aftercount=tasks.size();
			System.out.println("after....."+aftercount);
		}
		
		if (tasks.size()==0){
			System.out.println("There are No tasks available in My taks");
		}else{
			System.out.println("Tasks are :");
			for (WebElement e :tasks){
				System.out.println(e.getText());
			}
		}
		
		}catch(Exception e){
			driver.quit();
		}
	}
	@Then("^verify appended task$")
	public void verify_appended_Tasks(){
		try{
			System.out.println("before ...."+beforecount);
			System.out.println("after....."+aftercount);
		if (beforecount<aftercount){
			System.out.println("Task appended successfully");
		}else{
			Assert.fail("unable to append the ");
		}
		}catch(Exception e){
			driver.quit();
		}
	}
	public boolean waitForElement(int timeout, By by) {
		while ( timeout > 0 ) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			WebDriverWait wait=new WebDriverWait(driver,timeout);
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
			List<WebElement> list = driver.findElements(by);
			if ( list.size() != 0 &&
				 list.get(list.size() - 1).isDisplayed() ) {
				return true;
			}
			
			timeout--;
		}
		
		System.out.println(String.format("[INFO] Waiting timed out for %s ...", by.toString()));
		return false;
	}

}
