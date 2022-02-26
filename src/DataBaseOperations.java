import java.util.ArrayList;

public abstract class DataBaseOperations {
	
	private static ArrayList<User> users = Distribution.distribute();
	public DataBaseOperations() {
		
	}
	public static ArrayList<User> getUsers() {
		return users;
	}
	public static AdminUser getAdminUser() {
		AdminUser adminUser = null;
		for (User user: users) {
			if (user instanceof AdminUser) {
				adminUser = (AdminUser)user;
			}
		}
		return adminUser;
	}
	public static ArrayList<Supplier> getSuppliers() {
		ArrayList<Supplier> suppliers = new ArrayList<Supplier>();
		for (User user: users) {
			if (user instanceof Supplier) {
				suppliers.add((Supplier)user);
			}
		}
		return suppliers;
	}
	public static User isUserNameAndPasswordValid(String userName,String password) {
		User user = null;
		for (User userForLoop: users) {
			if (userForLoop.getUserName().equals(userName) && userForLoop.getPassword().equals(password)) {
				user = userForLoop;
			}
		}
		return user;
	}
	public static boolean isShopNameAndTaxNumberValid(Supplier supplier,String shopName, String taxNumber) {
		return (supplier.getShop().getShopTitle().equals(shopName)&& supplier.getShop().getTaxNumber().equals(taxNumber));
	}
	public static void giveAllApprovals(AdminUser user) {
		ArrayList<Supplier> suppliers = getSuppliers();
		for (Supplier supplierForAdmin: suppliers) {
			((AdminUser)user).giveApproval(supplierForAdmin);
		}
		System.out.println();
	}
}
