Feature: MoneyLion Test
This Feature test the moneylion website

@WebTest
  Scenario: Able to access MoneyLion Plus Membership page successfully
    Given I am a new customer and access to the MoneyLion website
     When I hover on "Company" and click on "About" at the top of the webpage
     Then I should redirected to the MoneyLion's about page
     And I should be able to see "Offices located in New York City, San Francisco, Salt Lake City, and Kuala Lumpur" text displayed under COME JOIN US
  
   @WebTest
  Scenario Outline: Able to verify total deposited amount over a period of time
    Given I am a new customer and access to the MoneyLion website
     When I hover on "Products" and click on "Investing" at the top of the webpage
      And I scroll to view the projection slider
      And I select <initialAmount> as my deposit amount
      And I change the year to <year>
     Then I should able to see the <projectedValue> and <depositedAmount> are displayed
    Examples: 
      | initialAmount | year | projectedValue | depositedAmount | 
      | $125          | 21   | 89,820         | 31,500          | 
      | $350          | 20   | 227,863        | 84,000          | 
      | $450          | 1    | 5,676          | 5,400           | 
  
  
  @WebTest
  Scenario: Able to see ways to earn rewards
    Given I am a new customer and access to the MoneyLion website
     When I click on the "Rewards" at the bottom of the page
     When I scroll to view the "Earn rewards by" section
     Then I should able to see all four ways to earn rewards

 @APITest
  Scenario Outline: Create new Hotel booking from April 27th 2019 to 30th April 2019 with total price of 2389 without paying deposit
    Given Create New Hotel Booking Payload with <firstName> <lastName> <totalPrice> <depositPaid> <checkInDate> <checkOutDate> <additionalNeeds>
     When user calls "CreateNewHotelBookingAPI" with "POST" http request
     Then the API call got success with status code "200"
     And "firstname" in response body is <firstName>
     And "lastname" in response body is <lastName>
     And "totalprice" in response body is <totalPrice>
     And "depositpaid" in response body is <depositPaid>
     And "checkin" in response body is <checkInDate>
     And "checkout" in response body is <checkOutDate>
     And "additionalneeds" in response body is <additionalNeeds>
      And verify bookingid created or not
  
    Examples: 
      | firstName | lastName | totalPrice | depositPaid | checkInDate | checkOutDate | additionalNeeds | 
      | mohit     | tu       | 2389       | false   | 2019-04-27  | 2019-04-30   | nothing         | 
  
  @APITest
  Scenario Outline: Retreive booking created
    Given Creation of New Hotel Booking is successfull
     When user calls "GetHotelBookingAPI" with "GET" http request
     Then the API call got success with status code "200"
     And "firstname" in response body is <firstName>
     And "lastname" in response body is <lastName>
     And "totalprice" in response body is <totalPrice>
     And "depositpaid" in response body is <depositPaid>
     And "checkin" in response body is <checkInDate>
     And "checkout" in response body is <checkOutDate>
     And "additionalneeds" in response body is <additionalNeeds>
   
     Examples: 
      | firstName | lastName | totalPrice | depositPaid | checkInDate | checkOutDate | additionalNeeds | 
      | mohit     | tu       | 2389       | false   | 2019-04-27  | 2019-04-30   | nothing         | 