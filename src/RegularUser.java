
public abstract class RegularUser extends User{
	private ContactInfo contactInfo;
	
	public RegularUser() {
		super();
		this.contactInfo = new ContactInfo();
	}
	public RegularUser(String userName, String password,String money, String telephone, String eMail,String addressTitle,String country, String city, String district, String street, String doorNumber) {
		super(userName,password,money);
		this.contactInfo= new ContactInfo(telephone, eMail,addressTitle,country, city, district, street, doorNumber);
	}
	public ContactInfo getContactInfo() {
		return contactInfo;
	}
	public String toString() {
		return(super.toString()+", "+contactInfo.toString());
	}
	
}

