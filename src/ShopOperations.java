import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public abstract class ShopOperations {
	
	public ShopOperations() {
		
	}
	
	/**
	 * @param customer the customer which does the buy operation
	 * @param address the address which the products will be sent.
	 */
	public static void buy(Customer customer,Address address) {
		ArrayList<Supplier> suppliers = DataBaseOperations.getSuppliers();
		AdminUser adminUser = DataBaseOperations.getAdminUser();
		double totalPrice = customer.getBasket().getBasketInventory().calculateTotalPrice();
		double productPrice = totalPrice;
		if (customer.hasFreeCargoCharge()) {
			if (customer.hasEnoughBalance(totalPrice)) {
				System.out.println("You won't pay cargo cost for this shop because you have a free cargo coupon. ");
				System.out.println("Total cost of basket products: "+ totalPrice);
				System.out.println();
				customer.setFreeCargoCharge(false);
				gainFreeCargoCoupon(customer,totalPrice);
			}
			else {
				System.out.println(customer.getUserName()+"'s Active Balance: "+ customer.getActiveBalance().getBalance());
				System.out.println("Total cost of basket products: "+ totalPrice);
				System.out.println("You don't have enough balance to buy thoose products.");
				System.out.println("Your cargo coupon is not used due to unsuccessful shopping attempt.");
				System.out.println();
				return;
			}
		}
		else {
			double totalCargoPrice = customer.getBasket().getBasketInventory().calculateTotalCargoPrice();
			totalPrice = totalPrice + totalCargoPrice;
			System.out.println("Total cost of basket products and cargo cost: "+ totalPrice);
			System.out.println();
		}
		if (customer.hasEnoughBalance(totalPrice)) {
			System.out.println();
			System.out.println("Shopping is successful.");
			System.out.println("The products will be sent to " + address.getAddressTitle());
			System.out.println(address.toString());
			System.out.println();
			if (!customer.hasFreeCargoCharge()) {
				gainFreeCargoCoupon(customer,productPrice);
			}
			customer.reduceBalance(totalPrice);
			System.out.println(customer.getUserName()+"'s Active Balance: "+ customer.getActiveBalance().getBalance());
			System.out.println();
			Iterator iter = customer.getBasket().getBasketInventory().getInventory().entrySet().iterator();
			int numberOfBasketElements = customer.getBasket().getBasketInventory().numberOfElements();
			for (int i = 0; i<numberOfBasketElements; i++) {
				Map.Entry<Product,Integer> basketEntry = (Map.Entry<Product, Integer>)iter.next();
				Product productEntry=basketEntry.getKey();
				int quantityEntry=basketEntry.getValue();
				double productsCost=productEntry.getPrice()*quantityEntry;
				double productsCargoCost=productEntry.calculateCargoPrice()*quantityEntry;
				double totalProductsCost = productsCost + productsCargoCost;
				String productCategory= productEntry.getCategory();
				Supplier supplier= null;
				for (Supplier supplierIterationEntry: suppliers) {
					if(supplierIterationEntry.getShop().getShopCategory().equals(productCategory)) {
						supplier=supplierIterationEntry;
					}
				}
				takeCommissionAndTransferTheRestOfTheMoneyToSupplier(adminUser,supplier,totalProductsCost);
				supplier.getShop().removeFromShop(productEntry,quantityEntry);
				customer.getBoughtProductsInventory().addElementToInventory(productEntry, quantityEntry);
				supplier.getOldProductsInventory().addElementToInventory(productEntry, quantityEntry);
			}
			customer.getBasket().getBasketInventory().getInventory().clear();
		}
		else {
			System.out.println(customer.getUserName()+"'s Active Balance: "+ customer.getActiveBalance().getBalance());
			System.out.println("Total cost of basket products: "+ totalPrice);
			System.out.println("You don't have enough balance to buy thoose products.");
			System.out.println();
			return;
		}
	}
	public static Address checkAddress(Customer customer,String stringInput) {
		Address returnAddress = null;
		ArrayList<Address> addresses = customer.getContactInfo().getAddresses();
		int input = Integer.parseInt(stringInput);
		if (input>0 && input<=addresses.size()) {
			returnAddress= addresses.get(input-1);
		}
		else {
			System.out.println("These address number is not valid!");
			System.out.println("Choose again.");
			System.out.println();
		}
		return returnAddress;
	}
	private static void gainFreeCargoCoupon(Customer customer, double price) {
		if (price>=2000) {
			System.out.println("You gained one free carco coupon.");
			customer.setFreeCargoCharge(true);
		}
	}
	/**This method adds the money's %2 to the admin's balance, adds the money's %98 to the supplier's ballance.
	 * @param admin the admin user which takes the total price's %2
	 * @param supplier the supplier which takes the total price's %98
	 * @param money the double which will be sent.
	 */
	private static void takeCommissionAndTransferTheRestOfTheMoneyToSupplier(AdminUser admin,Supplier supplier, double money) {
		double ninetyEightPercentOfMoney=money*(0.98);
		double twoPercentOfMoney=money-ninetyEightPercentOfMoney;
		admin.depositBalance(twoPercentOfMoney);
		supplier.depositBalance(ninetyEightPercentOfMoney);
	}
}
