
public class Basket {
private Inventory<Product> basketInventory;
	
	public Basket() {
	}
	public Basket(Inventory<Product> basketInventory) {
		this.basketInventory=basketInventory;
    }
	public Inventory<Product> getBasketInventory() {
		return basketInventory;
	}
	public void showBasketInventory() {
		basketInventory.showInventory("Basket");
		System.out.println();
		System.out.println("Total cost of basket products: "+ basketInventory.calculateTotalPrice());
		System.out.println("Total cargo cost: "+ basketInventory.calculateTotalCargoPrice());
		System.out.println();
	}
	public void addToBasket(Product product, int quantity) {
		basketInventory.addElementToInventory(product, quantity);
	}
	public boolean removeFromBasket(Product product, int quantity) {
		return basketInventory.removeElementFromInventory(product, quantity);
	}

}
