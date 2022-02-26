
public class ActiveBalance {
	private double balance;
	public ActiveBalance() {
		this(0.0);
	}
	public ActiveBalance(double balance) {
		if (balance>=0.0) {
			this.balance=balance;
		}
		else {
			System.out.println("Active Balance should be above 0.");
		}
	}
	public boolean hasEnoughMoney(double money) {
		return balance>=money;
	}
	/**
	 * @param balance the double which will be added to the balance
	 * @return true if given balance is greater or equal to zero, false if its not.
	 */
	public boolean depositBalance(double balance) {
		boolean result = false;
		if (balance>=0) {
			this.balance+=balance;
			result = true;	
		}
		else {
			System.out.println("Added Balance should be above 0.");
			System.out.println();
		}
		return result;
		
	}
	/**
	 * @param balance the double which will be removed from the balance
	 * @return true if given balance is greater or equal to the this.balance, false if its not.
	 */
	public boolean reduceBalance(double balance) {
		boolean result=false;
		if(this.balance>=balance) {
			this.balance-=balance;
			result=true;
		}
		else {
			System.out.println("There is no enough balance.");
		}
		return result;
	}
	public double getBalance() {
		return balance;
	}
	public String toString() {
		return ("Balance: "+ balance);
	}

}
