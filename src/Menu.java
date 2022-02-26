import java.util.Scanner;

public class Menu {
	public static void menu() {
		Scanner scan = new Scanner(System.in);
		User user = null;
		boolean isSignedUp = false;
		boolean isSignedIn = false;
		while (true) {
			System.out.println("Which operation do you want to perform?");
			System.out.println("1) SIGN UP");
			System.out.println("2) SIGN IN");
			System.out.println("3) QUIT");
			String input = scan.nextLine();
			if (input.equals("1")) {
				System.out.println("Sign up is successfully done for all the accounts.");
				System.out.println();
				isSignedUp = true;
			}
			else if (input.equals("2")) {
				if (isSignedUp==false) {
					System.out.println("You have to sign up first!");
					System.out.println();
				}
				else {
					isSignedIn = true;
					System.out.println("User name: ");
					String userName = scan.nextLine();
					System.out.println("Password: ");
					String password = scan.nextLine();
					user = DataBaseOperations.isUserNameAndPasswordValid(userName, password);
					if (user == null) {
						System.out.println("Wrong user name or wrong password!");
						System.out.println();
					}
					else {
						System.out.println("Sign in is succesful " + user.getUserName());
						if (user instanceof AdminUser) {
							System.out.println("Admin User's Active Balance: " + user.getActiveBalance());
							System.out.println();
							while(!(user ==null)) {
								System.out.println("What operation do you want to perform?");
								System.out.println("1) Give Approval to all suppliers ");
								System.out.println("2) Show Balance ");
								System.out.println("3) SIGN OUT ");
								System.out.println();
								String adminUserInput = scan.nextLine();
								if (adminUserInput.equals("1")) {
									DataBaseOperations.giveAllApprovals((AdminUser)user);
								}
								else if (adminUserInput.equals("2")) {
									System.out.println("Admin User's Active Balance: " + user.getActiveBalance());
									System.out.println();
								}
								else if (adminUserInput.equals("3")) {
									System.out.println("SIGN OUT is succesful.");
									System.out.println();
									user = null;
								}
								else {
									System.out.println("Invalid operation!");
									System.out.println();
								}
							}	
						}
						else if (user instanceof Supplier) {
							System.out.println(user.getUserName()+"'s Active Balance: "+user.getActiveBalance());
							System.out.println();
							while(!(user==null)) {
								System.out.println("What operations do you want to perform?");
								System.out.println("1) Calculate endorsement");
								System.out.println("2) Show Balance");
								System.out.println("3) Add address");
								System.out.println("4) Show old products");
								System.out.println("5) Show shop products(Register to see)");
								System.out.println("6) SIGN OUT");
								System.out.println();
								String supplierInput=scan.nextLine();
								if(supplierInput.equals("1")) {
									System.out.println(user.getUserName()+"'s total endorsement is "+((Supplier)user).calculateEndorsement());
									System.out.println();
								}
								else if(supplierInput.equals("2")) {
									System.out.println(user.getUserName()+ "'s Active Balance: " + user.getActiveBalance());
									System.out.println();
								}
								else if (supplierInput.equals("3")) {
									System.out.println("Write your address:");
									System.out.println("Address Title: ");
									String addressTitle=scan.nextLine();
									System.out.println("Country: ");
									String country=scan.nextLine();
									System.out.println("City: ");
									String city=scan.nextLine();
									System.out.println("District: ");
									String district=scan.nextLine();
									System.out.println("Street: ");
									String street=scan.nextLine();
									System.out.println("Door number: ");
									String doorNumber =scan.nextLine();
									Address newAddress = new Address(addressTitle, country, city, district, street, doorNumber);
									if(CSVReader.writeAddressToFile(newAddress, user)) {
										((Supplier)user).addAddress(newAddress);
										 System.out.println("New address is added.");
										 System.out.println();
									}
									else {
										System.out.println("Unsuccessful adress addition operation.");
										System.out.println();
									}
								}
								else if(supplierInput.equals("4")) {
									((Supplier)user).showOldProductsInventory();
									System.out.println();
								}
								else if(supplierInput.equals("5")) {
									boolean isValidShopNameAndTaxNumber=false;
									while(!isValidShopNameAndTaxNumber) {
										System.out.println("Register your shop to see your shop inventory.");
										System.out.println("Shop title: ");
										String shopName = scan.nextLine();
										System.out.println("Tax number: ");
										String taxNumber = scan.nextLine();
										isValidShopNameAndTaxNumber= DataBaseOperations.isShopNameAndTaxNumberValid((Supplier)user,shopName,taxNumber);
										if(isValidShopNameAndTaxNumber) {
											((Supplier)user).showShopInventory();
										}
										else {
											System.out.println("Wrong shop name or wrong tax number!");
											System.out.println("1) Try again");
											System.out.println("2) Quit your shop register");
											System.out.println();
											String registerInput=scan.nextLine();
											if(registerInput.equals("2")) {
												System.out.println("Shop register is unsuccessful, you can't see your shop inventory!");
												System.out.println();
												break;
											}
										}
									}
								}
								else if (supplierInput.equals("6")) {
									System.out.println("SIGN OUT is succesful.");
									System.out.println();
									user = null;
								}
								else {
									System.out.println("Invalid operation!");
									System.out.println();
								}
							}
						}
						else if(user instanceof Customer) {
							System.out.println(user.getUserName()+"'s Active Balance: "+user.getActiveBalance());
							System.out.println();
							while(!(user==null)) {
								System.out.println("What operations do you want to perform?");
								System.out.println("1) See products and put into basket");
								System.out.println("2) Buy the products which are in the basket");
								System.out.println("3) Return a product");
								System.out.println("4) Show balance");
								System.out.println("5) Show basket products");
								System.out.println("6) Show bought products");
								System.out.println("7) Deposit");
								System.out.println("8) Add address");
								System.out.println("9) Quit");
								System.out.println();
								String customerInput=scan.nextLine();
								if(customerInput.equals("1")) {
									BasketOperations.putIntoTheBasket((Customer)user);
								}
								else if(customerInput.equals("2")) {
									Address address=null;
									while(address==null) {
										System.out.println("To which address do you want us to send your products?");
										((Customer)user).showAddresses();
										String stringAddress = scan.nextLine();
										address=ShopOperations.checkAddress((Customer)user, stringAddress);
									}
									ShopOperations.buy((Customer)user,address);
								}
								else if(customerInput.equals("3")) {
									System.out.println("Which product do you want to return?");
									System.out.println("(Call by only it's name, input is not capital sensitive.)");
									String tempProductName = scan.nextLine();
									String productName=tempProductName.toLowerCase();
									System.out.println("How many of that product do you want to return?");
									String quantity = scan.nextLine();
									ReturnOperations.returnProduct((Customer)user, productName,quantity);
								}
								else if (customerInput.equals("4")) {
									System.out.println(user.getUserName()+ "'s Active Balance: " + user.getActiveBalance());
									System.out.println();
								}
								else if(customerInput.equals("5")) {
									((Customer)user).showBasketInventory();
									System.out.println();
								}
								else if(customerInput.equals("6")) {
									((Customer)user).showBoughtProductsInventory();
									System.out.println();
								}
								else if (customerInput.equals("7")) {
									System.out.println("How much money will be deposited?");
									String moneyInput= scan.nextLine();
									double money= Double.parseDouble(moneyInput);
									((Customer)user).depositBalance(money);
								}
								else if(customerInput.equals("8")) {
									System.out.println("Write your address:");
									System.out.println("Address Title: ");
									String addressTitle=scan.nextLine();
									System.out.println("Country: ");
									String country=scan.nextLine();
									System.out.println("City: ");
									String city=scan.nextLine();
									System.out.println("District: ");
									String district=scan.nextLine();
									System.out.println("Street: ");
									String street=scan.nextLine();
									System.out.println("Door number: ");
									String doorNumber =scan.nextLine();
									Address newAddress = new Address(addressTitle, country, city, district, street, doorNumber);
									if(CSVReader.writeAddressToFile(newAddress, user)) {
										((Customer)user).addAddress(newAddress);
										 System.out.println("New address is added.");
										 System.out.println();
									}
									else {
										System.out.println("Unsuccessful adress addition operation.");
										System.out.println();
									}
								}
								else if(customerInput.equals("9")) {
									System.out.println("SIGN OUT is succesful.");
									System.out.println();
									user = null;
								}
								else {
									System.out.println("Invalid operation!");
									System.out.println();
								}
							}
						}
						
					}
					
				}
			}
				
			else if (input.equals("3")) {
				if (isSignedIn) {
					System.out.println("Quiting...");
					System.out.println();
					break;
				}
				else {
					System.out.println("You have to sign in first.");
					System.out.println();
				}
			}
			else {
				System.out.println("Invalid input!");
				System.out.println();
			}
		}
		
	}
}
