package base.dao;

import base.dao.Booking;

public class HotelBookingPostResponse {
	
	String bookingId;
	Booking booking;

	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

}
