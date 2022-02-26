
public class Supplier extends RegularUser {
	private boolean isApproved;
	private Inventory<Product> oldProductsInventory;
	public Shop shop;
	
	public Supplier() {
		
	}
	public Supplier(Inventory<Product> oldProductsInventory,Shop shop, String userName, String password,String money,String telephone, String eMail, String addressTitle,String country, String city, String district, String street, String doorNumber) {
		super(userName, password, money, telephone, eMail, addressTitle,country, city, district, street, doorNumber);
		this.oldProductsInventory=oldProductsInventory;
		this.shop = shop;
		isApproved = false;
    }
	public void showOldProductsInventory() {
		oldProductsInventory.showInventory("Old Products");
	}
	public void showShopInventory() {
		shop.showShopInventory();
	}
	public void addAddress(Address address) {
		super.getContactInfo().addAddress(address);
	}
	public boolean getIsApproved() {
		return isApproved;
	}
	public boolean reduceBalance(double money) {
		return super.getActiveBalance().reduceBalance(money);
	}
	public boolean depositBalance(double money) {
		return super.getActiveBalance().depositBalance(money);
	}
	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}
	public Inventory<Product> getOldProductsInventory() {
		return oldProductsInventory;
	}
	public Shop getShop() {
		return shop;
	}
	public void addToOldProductsInventory(Product product, int quantity) {
		oldProductsInventory.addElementToInventory(product, quantity);
	}
	public boolean removeFromOldProductsInventory(Product product, int quantity) {
		return oldProductsInventory.removeElementFromInventory(product, quantity);
	}
	public double calculateEndorsement() {
		return oldProductsInventory.calculateTotalPrice();
	}
	public String toString() {
		return(super.toString()+ ", Approval: "+ isApproved);
	}
}

