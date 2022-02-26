
public class AdminUser extends User {
	public AdminUser() {
		super();
	}
	public AdminUser(String userName, String password, String money) {
		super(userName, password, money);
	}
	/**
	 * @param supplier the Supplier which this class will give approval to.
	 * @return true if supplier is in the correct form, false if its not.
	 */
	public boolean giveApproval(Supplier supplier) {
		boolean result=false;
		if((!supplier.getUserName().equals(" "))&& (!supplier.getPassword().equals(" "))) {
			result=true;
			supplier.setApproved(true);
			System.out.println(supplier.getUserName()+" is approved.");
		}
		else {
			System.out.println("This is not a valid supplier! Approval is rejected!");
		}
		return result;
	}
	public boolean depositBalance(double money) {
		return super.getActiveBalance().depositBalance(money);
	}
	public String toString() {
		return super.toString();
	}
}
