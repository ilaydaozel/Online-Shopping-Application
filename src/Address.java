
public class Address {
	private String addressTitle;
	private String country;
	private String city;
	private String district;
	private String street;
	private String doorNumber;
	
	public Address() {
		this(" "," "," "," "," "," ");        
	}
	public Address(String addressTitle, String country, String city, String district, String street, String doorNumber) {
		this.addressTitle = addressTitle;
		this.country=country;
		this.city=city;
		this.district=district;
		this.street=street;
		this.doorNumber=doorNumber;
	}
	
	public String getCountry() {
		return country;
	}
	public String getAddressTitle() {
		return addressTitle;
	}
	public String getCity() {
		return city;
	}
	public String getDistrict() {
		return district;
	}
	public String getStreet() {
		return street;
	}
	public String getDoorNumber() {
		return doorNumber;
	}
	public String toString() {
		return ("Adress Title: "+addressTitle + ", Country: " +  country + ", City: " + city + ", District:  " + district + ", Street:  " + street + ", DoorNumber: " + doorNumber+ " ");
	}
}
