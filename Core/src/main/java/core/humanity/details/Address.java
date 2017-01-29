package core.humanity.details;

/***
 * Class represents address details.
 * 
 * @author Mateusz Mucha
 *
 */
public class Address {
	private String city, postalCode, streetFullAddress;

	/***
	 * Default constructor sets basic fields.
	 */
	public Address() {
		this.city = null;
		this.postalCode = null;
		this.streetFullAddress = null;
	}

	/***
	 * Method gets city.
	 * 
	 * @return city
	 */
	public String getCity() {
		return city;
	}

	/***
	 * Method sets city.
	 * 
	 * @param city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/***
	 * Method gets postal code of city.
	 * 
	 * @return postal code of city
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/***
	 * Method sets postal code of city.
	 * 
	 * @param postalCode
	 *            - postal code of city
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/***
	 * Method gets full street address.
	 * 
	 * @return full street address
	 */
	public String getStreetFullAddress() {
		return streetFullAddress;
	}

	/***
	 * Method sets full street address.
	 * 
	 * @param streetFullAddress
	 *            - full address as street with home number
	 */
	public void setStreetFullAddress(String streetFullAddress) {
		this.streetFullAddress = streetFullAddress;
	}
}