
public class Shop {
	private Inventory<Product> shopInventory;
	private String shopCategory;
	private String shopTitle;
	private String taxNumber; 
	
	public Shop() {

	}
	public Shop(Inventory<Product> shopInventory, String shopCategory, String shopTitle,String taxNumber) {
		this.shopInventory=shopInventory;
		this.shopCategory=shopCategory;
		this.shopTitle=shopTitle;
		this.taxNumber=taxNumber;
    }
	public void showShopInventory() {
		shopInventory.showInventory("Shop");
	}
	public void addToShop(Product product, int quantity) {
		shopInventory.addElementToInventory(product, quantity);
	}
	public boolean removeFromShop(Product product, int quantity) {
		return shopInventory.removeElementFromInventory(product, quantity);
	}
	public Inventory<Product> getShopInventory() {
		return shopInventory;
	}
	public String getShopTitle() {
		return shopTitle;
	}
	public String getTaxNumber() {
		return taxNumber;
	}
	public String getShopCategory() {
		return shopCategory;
	}
	
}

