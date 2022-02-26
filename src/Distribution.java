import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Distribution {
	/**This method distributes the products into the supplier's shops.
	 * @return ArrayList of Users
	 */
	public static ArrayList<User> distribute() {
		CSVReader csvReader = new CSVReader();
		ArrayList<User> users = csvReader.readUserCSV();
		HashMap<Product,Integer> products = csvReader.readProductCSV();
		for (Map.Entry<Product,Integer> entry: products.entrySet()) {
			for(User user: users) {
				if (user instanceof Supplier) {
					if(((Supplier)user).getShop().getShopCategory().equals(ProductTypes.houseware.toString())) {
						if(entry.getKey().getCategory().equals(ProductTypes.houseware.toString())) {
							((Supplier)user).getShop().addToShop(entry.getKey(), entry.getValue());
						}
					}
					else if(((Supplier)user).getShop().getShopCategory().equals(ProductTypes.electronic.toString())) {
						if(entry.getKey().getCategory().equals(ProductTypes.electronic.toString())) {
							((Supplier)user).getShop().addToShop(entry.getKey(), entry.getValue());
						}
					}
					else if(((Supplier)user).getShop().getShopCategory().equals(ProductTypes.cosmetic.toString())) {
						if(entry.getKey().getCategory().equals(ProductTypes.cosmetic.toString())) {
							((Supplier)user).getShop().addToShop(entry.getKey(), entry.getValue());
						}
					}
					else if(((Supplier)user).getShop().getShopCategory().equals(ProductTypes.accessories.toString())) {
						if(entry.getKey().getCategory().equals(ProductTypes.accessories.toString())) {
							((Supplier)user).getShop().addToShop(entry.getKey(), entry.getValue());
						}
					}
				}
			}
		}
		return users;
	}
}
