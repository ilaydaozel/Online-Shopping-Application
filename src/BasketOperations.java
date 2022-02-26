import java.util.ArrayList;
import java.util.Scanner;

public class BasketOperations {
	/** This method helps to get product type from the user, and according to it, call other helper methods.
	 * @param customer the customer which the product will be added to its basket.
	 */
	public static void putIntoTheBasket(Customer customer) {
		Scanner scan = new Scanner(System.in);
		System.out.println("What type of product do you want to buy?");
		System.out.println("1) Houseware");
		System.out.println("2) Electronic");
		System.out.println("3) Cosmetic");
		System.out.println("4) Accessories");
		String type = scan.nextLine();
		if (type.equals("1")) {
			BasketOperations.putOneTypeIntoTheBasket((Customer)customer,ProductTypes.houseware.toString());
		}
		else if (type.equals("2")) {
			BasketOperations.putOneTypeIntoTheBasket((Customer)customer, ProductTypes.electronic.toString());
		}
		else if (type.equals("3")) {
			BasketOperations.putOneTypeIntoTheBasket((Customer)customer, ProductTypes.cosmetic.toString());
		}
		else if(type.equals("4")) {
			BasketOperations.putOneTypeIntoTheBasket((Customer)customer, ProductTypes.accessories.toString());
		}
		else {
			System.out.println("Invalid product type!");
		}
	}
	/**This method gets the quantity from user and puts the product type with that quantity to the basket with other helper method.
	 * @param customer the customer which the product will be added to its basket.
	 * @param type the String which the product type
	 */
	public static void putOneTypeIntoTheBasket(Customer customer, String type) {
		Scanner scan = new Scanner(System.in);
		ArrayList<Supplier> suppliers = DataBaseOperations.getSuppliers();
		for(Supplier supplier: suppliers) {
			if (supplier.getShop().getShopCategory().equals(type)) {
				if (supplier.getIsApproved()) {
					Supplier supplier2 = supplier;
					supplier.showShopInventory();
					System.out.println("---------------------------------");
					System.out.println("Which product do you want to buy?");
					System.out.println("(Call by only it's name, input is not capital sensitive.)");
					System.out.println("---------------------------------");
					String tempStringProduct = scan.nextLine();
					String stringProduct = tempStringProduct.toLowerCase();
					Product product = ((ProductInventory<Product>)supplier.getShop().getShopInventory()).findProduct(stringProduct);
					if (product != null) {
						System.out.println("How many product do you want to buy?");
						String stringQuantity = scan.nextLine();
						int quantity = Integer.parseInt(stringQuantity);
						
						putIntoBasket(customer,supplier2,product,quantity);
					}
				}
				else {
					System.out.println("Supplier is not approved! You can't add products to the basket from this supplier's shop.");
					System.out.println("First, admin should sign in and give approval to all the valid suppliers.");
					System.out.println();
				}
				
			}
		}
	}
	/**
	 * @param customer the customer which the product will be added to its basket.
	 * @param supplier the supplier which the product comes from its shop.
	 * @param product the product which will be added to the customers basket.
	 * @param quantity the integer which will be quantity for the product.
	 */
	public static void putIntoBasket(Customer customer,Supplier supplier,Product product, int quantity) {
		boolean stock = supplier.getShop().getShopInventory().isThereEnoughProduct(product, quantity);
		if(stock) {
			if (customer.getBasket().getBasketInventory().getInventory().containsKey(product)) {
				int currentQuantity = (Integer)customer.getBasket().getBasketInventory().getInventory().get(product);
				boolean stock2 = supplier.getShop().getShopInventory().isThereEnoughProduct(product, quantity+currentQuantity);
				if (stock2) {
					customer.getBasket().addToBasket(product, quantity);
					System.out.println(quantity+ " "+ product.getName()+"s are added into the basket.");
					System.out.println("Cost of "+quantity+" "+ product.getName()+" products: "+product.getPrice()*quantity );
					System.out.println();
				}
			}
			else {
				customer.getBasket().addToBasket(product, quantity);
				System.out.println(quantity+ " "+ product.getName()+"s are added into the basket.");
				System.out.println("Cost of "+quantity+ " "+product.getName()+" products: "+product.getPrice()*quantity );
				System.out.println();
			}
		}
	}
}
