import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public abstract class ReturnOperations {
	public ReturnOperations() {
		
	}
	
	/**
	 * @param customer the customer which will return the product
	 * @param productName the String which is the returned product's name
	 * @param productQuantity the String which is the returned product's quantity
	 * @return true if the product can be returned, false otherwise
	 */
	public static boolean returnProduct(Customer customer,String productName,String productQuantity) {
		ArrayList<Supplier> suppliers = DataBaseOperations.getSuppliers();
		boolean result = false;
		Product product= null;
		int removedQuantity = Integer.parseInt(productQuantity);
		Iterator<Entry<Product, Integer>> iter = customer.getBoughtProductsInventory().getInventory().entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<Product,Integer> entry = (Map.Entry<Product,Integer>)iter.next();
			if(entry.getKey().getName().equals(productName)) {
				if (entry.getValue()>=removedQuantity) {
					Supplier supplier = null;
					product= entry.getKey();
					for(Supplier iteratorSupplier: suppliers) {
						if(entry.getKey().getCategory().equals(iteratorSupplier.getShop().getShopCategory())) {
							supplier = iteratorSupplier;
							iteratorSupplier.getShop().addToShop(entry.getKey(), removedQuantity);
							iteratorSupplier.removeFromOldProductsInventory(entry.getKey(), removedQuantity);
						}
					}
					double returnedProductsPrice= entry.getKey().getPrice()*removedQuantity;
					supplier.reduceBalance(returnedProductsPrice);
					customer.depositBalance(returnedProductsPrice);
					result = true;
					System.out.println("Return operation is succesfully completed!");
					System.out.println(productQuantity+ " "+ productName+ "s are returned back to the "+ supplier.getShop().getShopTitle()+ " shop.");
					System.out.println();
				}
				else {
					System.out.println("Your bought product inventory has "+ entry.getKey().getName() + " but there is not " + productQuantity + " of them, there is "+ entry.getValue()+ " of them. You can't return that much.");
					System.out.println();
					return false;
				}
			}
		}
		
		if(product!=null) {
			customer.removeFromBoughtProductsInventory(product, removedQuantity);
		}
		else {
			System.out.println("There is no such "+ productName+ " in your bought product inventory. You can't return it.");
			System.out.println();
		}
		
		return result;
	}
}
