package model.human.details;

public class Address {
	private String city, postalCode, streetFullAddress;
	
	
	public Address() {
		this.city = null;
		this.postalCode = null;
		this.streetFullAddress = null;
	}
	
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getStreetFullAddress() {
		return streetFullAddress;
	}
	public void setStreetFullAddress(String streetFullAddress) {
		this.streetFullAddress = streetFullAddress;
	}
}