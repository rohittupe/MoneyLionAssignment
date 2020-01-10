package base;

public enum APIResources {
	CreateNewHotelBookingAPI("/booking"),
	GetHotelBookingAPI("/booking/");
private String resource;
	
	APIResources(String resource)
	{
		this.resource=resource;
	}
	
	public String getResource()
	{
		return resource;
	}

}
