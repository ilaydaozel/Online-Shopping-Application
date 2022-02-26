
import java.util.Set;

public class ProductInventory<T extends Product> extends Inventory<T> {
	public ProductInventory() {
		super();
	}
	/**
	 * @param product the String which is going to used by to find Product with that name.
	 * @return Product if it is in the inventory, null if it is not.
	 */
	public Product findProduct(String product) {
		Product returnProduct = null;
		try {
			Set<T> inventoryKeys = super.getInventory().keySet();
			for (T element : inventoryKeys) {
				if (element.getName().equals(product)) {
					returnProduct = element;
				}
			}
			if (returnProduct == null) {
				throw new ProductNotFoundException();
			}
		}
		catch (ProductNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return returnProduct;
	}

}
