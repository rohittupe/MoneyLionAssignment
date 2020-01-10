package base.dao;

import base.dao.BookingDates;

public class Booking {
	
	private String firstName;
	private String lastName;
	private int totalPrice;
	private Boolean depositPaid;
	private BookingDates bookingDates;
	private String additionalNeeds;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Boolean getDepositPaid() {
		return depositPaid;
	}
	public void setDepositPaid(Boolean depositPaid) {
		this.depositPaid = depositPaid;
	}
	public String getAdditionalNeeds() {
		return additionalNeeds;
	}
	public void setAdditionalNeeds(String additionalNeeds) {
		this.additionalNeeds = additionalNeeds;
	}
	public BookingDates getBookingDates() {
		return bookingDates;
	}
	public void setBookingDates(BookingDates bookingDates) {
		this.bookingDates = bookingDates;
	}

}
