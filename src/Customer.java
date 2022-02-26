import java.util.ArrayList;

public class Customer extends RegularUser {
	private boolean freeCargoCharge;
	private Inventory<Product> boughtProductsInventory;
	private Basket basket;
	
	public Customer() {
		
	}
	public Customer(Inventory<Product> boughtProductsInventory,Basket basket, String userName, String password,String money,String telephone, String eMail,String addressTitle, String country, String city, String district, String street, String doorNumber) {
		super(userName, password, money, telephone, eMail, addressTitle,country, city, district, street, doorNumber);
		this.boughtProductsInventory=boughtProductsInventory;
		this.basket = basket;
		freeCargoCharge = false;
    }
	public void showAddresses() {
		ArrayList<Address> addresses = getContactInfo().getAddresses();
		int index =1;
		for (Address address: addresses) {
			System.out.println(index+") "+ address.getAddressTitle());
			index++;
		}
	}
	public boolean hasEnoughBalance(double money) {
		return super.getActiveBalance().hasEnoughMoney(money);
	}
	public boolean reduceBalance(double money) {
		return super.getActiveBalance().reduceBalance(money);
	}
	public boolean depositBalance(double money) {
		System.out.println(money +" tl is added to the "+ super.getUserName()+"'s balance." );
		System.out.println();
		return super.getActiveBalance().depositBalance(money);
	}
	public void addAddress(Address address) {
		super.getContactInfo().addAddress(address);
	}
	public void showBoughtProductsInventory() {
		boughtProductsInventory.showInventory("Bought Products");
	}
	public void showBasketInventory() {
		basket.showBasketInventory();
	}
	public String toString() {
		return(super.toString()+", Free cargo: "+ freeCargoCharge);
	}
	public Basket getBasket() {
		return basket;
	}
	public Inventory<Product> getBoughtProductsInventory() {
		return boughtProductsInventory;
	}
	public boolean hasFreeCargoCharge() {
		return freeCargoCharge;
	}
	public void setFreeCargoCharge(boolean freeCargoCharge) {
		this.freeCargoCharge = freeCargoCharge;
	}
	public void addToBoughtProductsInventory(Product product, int quantity) {
		boughtProductsInventory.addElementToInventory(product, quantity);
	}
	public boolean removeFromBoughtProductsInventory(Product product, int quantity) {
		return boughtProductsInventory.removeElementFromInventory(product, quantity);
	}

}
