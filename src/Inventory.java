import java.util.HashMap;
import java.util.Map;

public abstract class Inventory<T> implements IInventory<T> {
	private Map<T,Integer> inventory;
	
	public Inventory() {
		inventory=new HashMap<T,Integer>();
	}
	public Map<T, Integer> getInventory() {
		return inventory;
	}
	public double calculateTotalPrice() {
		double totalPrice=0;
		for(Map.Entry<T,Integer> entry: inventory.entrySet()) {
			totalPrice+=((Product)entry.getKey()).getPrice()*entry.getValue() ;
		}
		return totalPrice;
	}
	public double calculateTotalCargoPrice() {
		double totalPrice = 0;
		for(Map.Entry<T,Integer> entry: inventory.entrySet()) {
			totalPrice+=((Product)entry.getKey()).calculateCargoPrice()*entry.getValue();
		}
		return totalPrice;
		
	}
	public void addElementToInventory(T element, int quantity) {
		if ((quantity>=0) && (element != null)) {
			if (inventory.containsKey(element)) {
				int currentQuantity = inventory.get(element);
				int newQuantity = currentQuantity + quantity;
				inventory.put(element, newQuantity);
			}
			else {
				inventory.put(element, quantity);
			}
		}
		else {
			System.out.println("Elements aren't suitable for addition to the inventory.");
		}
	}
	public boolean isThereEnoughProduct(T element,int quantity) {
		boolean result=false;
		if((quantity>=0) && (element != null)) {
			if(inventory.containsKey(element)) {
				if(inventory.get(element)>=quantity) {
					result=true;
				}
				else {
					System.out.println("There are not enough elements.");
				}
			}
			else {
				System.out.println("There is no such element in the inventory.");
			}
		}
		else {
			System.out.println("Elements aren't suitable for removing from the inventory");
		}
		return result;
	}
	public boolean removeElementFromInventory(T element, int quantity) {
		boolean result=false;
		int currentQuantity = inventory.get(element);
		if(currentQuantity>quantity) {
			inventory.remove(element);
			inventory.put(element, currentQuantity-quantity);
			result=true;
		}
		else if(currentQuantity==quantity) {
			inventory.remove(element);
			result=true;
		}
		return result;
	}
	public int numberOfElements() {
		return inventory.size();
	}
	public void showInventory(String string) {
		if (inventory.isEmpty()) {
			System.out.println(string + " Inventory is empty!");
			return;
		}
		for(Map.Entry<T, Integer> entry: inventory.entrySet()) {
			System.out.println(entry.getKey() + " Quantity: " + entry.getValue());
		}
	}
}

