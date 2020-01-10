package base;

import base.dao.Booking;
import base.dao.BookingDates;
import base.dao.HotelBookingPostResponse;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TestDataBuilder extends Base {
	
	private static Booking booking;
	private static BookingDates bookingDates;
	public static HotelBookingPostResponse hotelBookingPostResponse;
	
	public String getPathAsString(String field) {
		String prefix="";
		if(getMethod().equalsIgnoreCase("post"))
			prefix="booking.";
		if(field!=null) {
			if(field.equalsIgnoreCase("firstname"))
				return prefix+"firstname";
			else if(field.equalsIgnoreCase("lastname"))
				return prefix+"lastname";
			else if(field.equalsIgnoreCase("totalprice"))
				return prefix+"totalprice";
			else if(field.equalsIgnoreCase("depositpaid"))
				return prefix+"depositpaid";
			else if(field.equalsIgnoreCase("checkin"))
				return prefix+"bookingdates.checkin";
			else if(field.equalsIgnoreCase("checkout"))
				return prefix+"bookingdates.checkout";
			else if(field.equalsIgnoreCase("additionalneeds"))
				return prefix+"additionalneeds";
		}
		return "";
	}
	
	public void createNewHotelBookingResponseToJavaObject(Response response) {
		String json = response.getBody().asString();
		System.out.println("json="+json);
		JsonPath jsonPath = new JsonPath(json);

		bookingDates = new BookingDates();
		bookingDates.setCheckin(jsonPath.getString("booking.bookingdates.checkin"));
		bookingDates.setCheckout(jsonPath.getString("booking.bookingdates.checkout"));

		booking = new Booking();
		booking.setFirstName(jsonPath.getString("booking.firstname"));
		booking.setLastName(jsonPath.getString("booking.lastname"));
		booking.setTotalPrice(Integer.parseInt(jsonPath.getString("booking.totalprice")));
		booking.setDepositPaid(Boolean.parseBoolean(jsonPath.getString("booking.depositpaid")));
		booking.setBookingDates(bookingDates);
		booking.setAdditionalNeeds(jsonPath.getString("booking.additionalneeds"));

		hotelBookingPostResponse = new HotelBookingPostResponse();
		hotelBookingPostResponse.setBookingId(jsonPath.getString("bookingid"));
		hotelBookingPostResponse.setBooking(booking);
	}
}
