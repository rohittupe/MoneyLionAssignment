package stepDefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.junit.Cucumber;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pageObjects.AboutPage;
import pageObjects.HomePage;
import pageObjects.ProductsInvestingPage;
import pageObjects.ProductsRewardsPage;

import org.junit.runner.RunWith;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;

import base.APIResources;
import base.Base;
import base.TestDataBuilder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(Cucumber.class)
public class StepDefinitions extends Base {

	HomePage homePage;
	AboutPage aboutPage;
	ProductsInvestingPage productsInvestingPage;
	ProductsRewardsPage productsRewardsPage;
	
    @Given("^I am a new customer and access to the MoneyLion website$")
    public void i_am_a_new_customer_and_access_to_the_moneylion_website() throws Throwable {
    	driver = Base.getDriver();
    	driver.get(Base.getMoneyLionURL());
    }

    @When("^I hover on \"([^\"]*)\" and click on \"([^\"]*)\" at the top of the webpage$")
    public void i_hover_on_something_and_click_on_something_at_the_top_of_the_webpage(String menu, String subMenu) throws Throwable {
    	homePage = new HomePage(driver);
    	homePage.hoverOverAndClickOnMenuAndSubMenu(menu, subMenu);
    }

    @When("^I click on the \"([^\"]*)\" at the bottom of the page$")
    public void i_click_on_the_something_at_the_bottom_of_the_page(String strArg1) throws Throwable {
    	homePage = new HomePage(driver);
    	homePage.clickOnRewardsLinkPresentAtBottomOfPage();
    }

    @Then("^I should redirected to the MoneyLion's about page$")
    public void i_should_redirected_to_the_moneylions_about_page() throws Throwable {
    	aboutPage = new AboutPage(driver);
    	assertThat(aboutPage.isAboutPageDisplayed()).isEqualTo(true);
    }

    @Then("^I should able to see the (.+) and (.+) are displayed$")
    public void i_should_able_to_see_the_and_are_displayed(String projectedvalue, String depositedamount) throws Throwable {
    	productsInvestingPage = new ProductsInvestingPage(driver);
    	assertThat(productsInvestingPage.getProjectedValue()).containsIgnoringCase(projectedvalue);
		assertThat(productsInvestingPage.getDepositedValue()).containsIgnoringCase(depositedamount);
    }

    @Then("^I should able to see all four ways to earn rewards$")
    public void i_should_able_to_see_all_four_ways_to_earn_rewards() throws Throwable {
    	productsRewardsPage = new ProductsRewardsPage(driver);
    	List<String> earnRewardsByList = Arrays.asList("signing up for free credit monitoring", "paying a bill on time", "tracking your savings", "saving more with MoneyLion Plus");
    	assertThat(productsRewardsPage.waitAndVerifyRewardMessage(earnRewardsByList)).isEqualTo(true);
    }

    @And("^I should be able to see \"([^\"]*)\" text displayed under COME JOIN US$")
    public void i_should_be_able_to_see_something_text_displayed_under_come_join_us(List<String> list1) throws Throwable {
    	aboutPage = new AboutPage(driver);
    	System.out.println(list1);
    	assertThat(aboutPage.getOfficeLocationsText()).containsIgnoringCase(list1.get(0));
    }

    @And("^I scroll to view the projection slider$")
    public void i_scroll_to_view_the_projection_slider() throws Throwable {
    	productsInvestingPage = new ProductsInvestingPage(driver);
    	productsInvestingPage.scrollToProjectionSlider();
    }

    @And("^I select (.+) as my deposit amount$")
    public void i_select_as_my_deposit_amount(String initialamount) throws Throwable {
    	productsInvestingPage = new ProductsInvestingPage(driver);
    	productsInvestingPage.setDepositAmount(initialamount);
    }

    @And("^I change the year to (.+)$")
    public void i_change_the_year_to(String year) throws Throwable {
    	productsInvestingPage = new ProductsInvestingPage(driver);
    	productsInvestingPage.setYearOnChart(Integer.parseInt(year));
    }

    @And("^I scroll to view the \"([^\"]*)\" section$")
    public void i_scroll_to_view_the_something_section(String text) throws Throwable {
    	productsRewardsPage = new ProductsRewardsPage(driver);
    	productsRewardsPage.scrollToEarnRewardsBy(text);
    }

    @After("@WebTest")
	public void tearDown() {
		driver.close();
	}
    
    RequestSpecification reqSpec;
	ResponseSpecification resSpec;
	Response response;
	TestDataBuilder data =new TestDataBuilder();
	static String bookingId;
	
    @Given("^Create New Hotel Booking Payload with (.+) (.+) (.+) (.+) (.+) (.+) (.+)$")
    public void create_new_hotel_booking_payload_with_details(String firstName, String lastName, String totalPrice, String depositPaid, String checkInDate, String checkOutDate, String additionalNeeds) throws Throwable {
    	Map<String, String> bookingdatesRequestBodyAsMap = new HashMap<String, String>();
		bookingdatesRequestBodyAsMap.put("checkin", checkInDate);
		bookingdatesRequestBodyAsMap.put("checkout", checkOutDate);

		Map<String, Object> requestBodyAsMap = new HashMap<String, Object>();
		requestBodyAsMap.put("firstname", firstName);
		requestBodyAsMap.put("lastname", lastName);
		requestBodyAsMap.put("totalprice", new Integer(totalPrice));
		requestBodyAsMap.put("depositpaid", new Boolean(depositPaid));
		requestBodyAsMap.put("bookingdates", bookingdatesRequestBodyAsMap);
		requestBodyAsMap.put("additionalneeds", additionalNeeds);
		reqSpec = given().spec(requestSpecsification()).body(requestBodyAsMap);
    }

    @Given("^Creation of New Hotel Booking is successfull$")
    public void creation_of_new_hotel_booking_is_successfull() throws Throwable {
    	reqSpec = given().spec(requestSpecsification());
    }

    @When("^user calls \"([^\"]*)\" with \"([^\"]*)\" http request$")
    public void user_calls_with_http_request(String resource, String method) throws Throwable {
    	APIResources resourceAPI = APIResources.valueOf(resource);
    	resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
    	if(method.equalsIgnoreCase("POST")) {
    		response = reqSpec.when().post(getBaseURL()+resourceAPI.getResource());
    		data.createNewHotelBookingResponseToJavaObject(response);
    	}
    	else if(method.equalsIgnoreCase("GET")) {
    		if(resource.contains("GetHotelBookingAPI")) {
    	    	String bookingId = TestDataBuilder.hotelBookingPostResponse.getBookingId();
    	    	System.out.println("Booking Id is="+bookingId);
    	    	response = reqSpec.when().get(getBaseURL()+resourceAPI.getResource()+bookingId);
    		}
    		else
    			response = reqSpec.when().get(getBaseURL()+resourceAPI.getResource());
    	}
    	setMethod(method);
    }

    @Then("^the API call got success with status code \"([^\"]*)\"$")
    public void the_api_call_got_success_with_status_code(int statusCode) throws Throwable {
    	response.then().statusCode(statusCode);
    }

    @And("^\"(.+)\" in response body is (.*?)$")
    public void something_in_response_body_is_something(String keyValue, String expectedvalue) throws Throwable {
    	JsonPath jsonPath = new JsonPath(response.getBody().asString());
    		assertEquals(expectedvalue, jsonPath.getString(data.getPathAsString(keyValue)));
    }

    @And("^verify bookingid created or not$")
    public void verify_bookingid_created_or_not() throws Throwable {
    	response.then().body("bookingid", notNullValue());
    }
    
    @Then("^the API call got success with status code (\\d+)$")
    public void the_API_call_got_success_with_status_code(int arg1) throws Throwable {
        System.out.println("called");
    }
}