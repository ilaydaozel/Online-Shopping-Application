
import java.util.ArrayList;

public class ContactInfo {
	private String telephone;
	private String eMail;
	private ArrayList<Address> addresses;
	
	public ContactInfo() {
		this(" "," "," "," "," "," "," "," ");
		addresses = new ArrayList<Address>();
	}
	public ContactInfo(String telephone, String eMail,String addressTitle,String country, String city, String district, String street, String doorNumber) {
		addresses = new ArrayList<Address>();
		this.telephone=telephone;
		this.eMail=eMail;
		Address address=new Address(addressTitle,country, city, district, street, doorNumber);
		addresses.add(address);
	}

	public void addAddress(Address address) {
		 addresses.add(address);
	 }
	
	public String getTelephone() {
		return telephone;
	}
	public String geteMail() {
		return eMail;
	}
	public ArrayList<Address> getAddresses() {
		return addresses;
	}
    public String toString() {
    	String stringAddresses="";
    	for (Address address: addresses) {
    		stringAddresses += address.toString();
    		}
   
    	return("Telephone: "+ telephone+ ", Email: "+ eMail+ ", Address: "+ stringAddresses );
    }
}

