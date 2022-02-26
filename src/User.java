
public abstract class User {
	private String userName;
	private String password;
	private ActiveBalance activeBalance;
	
	public User() {
		this(" "," ","0");
	}
	public User(String userName, String password, String money) {
		this.userName=userName;
		this.password=password;
		double balance = Double.parseDouble(money);
		this.activeBalance = new ActiveBalance(balance);
		
	}
	public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return password;
	}
	public ActiveBalance getActiveBalance() {
		return activeBalance;
	}
	public String toString() {
		return ("User name: "+ userName+", Password: "+ password+ ", "+activeBalance.toString() );
	}
}

