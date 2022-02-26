
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class CSVReader {
	public CSVReader() {
		
	}
	/**
	 * @param newAddress the address which will be added to the file
	 * @param currentUser the user which the address will be added to its line.
	 * @return
	 */
	public static boolean writeAddressToFile(Address newAddress, User currentUser) {
		String fileName = "users.csv";
		String updatedLine;
        boolean result = false;
        try {
        	Scanner allFile = new Scanner(new File(fileName));
            String line; 
            ArrayList<String> allFileArrayList = new ArrayList<String>();
            while (allFile.hasNextLine()){
            	line = allFile.nextLine();
                allFileArrayList.add(line);
            }
            Iterator<String> iter = allFileArrayList.iterator();
            iter.next();
            int lineNumber = 1;
            while (iter.hasNext()){
                String[] lineInformations = iter.next().split(",");
                if (lineInformations[1].equals(currentUser.getUserName()) && lineInformations[2].equals(currentUser.getPassword())){
                    updatedLine = allFileArrayList.get(lineNumber) + "," + newAddress.getAddressTitle() +"," + newAddress.getCountry() +"," + newAddress.getCity() +"," + newAddress.getDistrict() +"," + newAddress.getStreet() +"," + newAddress.getDoorNumber();
                    allFileArrayList.set(lineNumber, updatedLine);
                }else{
                    lineNumber++;
                }
            }
            allFile.close();
            
            PrintWriter updatedFile = new PrintWriter(new FileWriter(fileName));
            for (String updatedFileLine : allFileArrayList){
                updatedFile.write(updatedFileLine+ "\n");
            }
            result = true;
            updatedFile.close();

        }
        catch (FileNotFoundException e) {
        	System.out.println(e.getMessage());
        }
        catch (IOException e){
        	System.out.println(e.getMessage());
        }
        return result;
    }
	public HashMap<Product, Integer> readProductCSV() {
		HashMap<Product, Integer> products = new HashMap<Product, Integer>();
		String fileName = "products.csv";
		try {
			 Scanner inFile = new Scanner(new File(fileName));
			 String line = null;
			 while (inFile.hasNextLine()) {
				 line = inFile.nextLine();
				 String[] informations = line.split(",");
				 String category= informations[0];
				 if(category.equals("houseware") || category.equals("electronic") || category.equals("accessories") || category.equals("cosmetic")) {
					 String name= informations[1];
					 String tempPrice= informations[2];
					 double price= Double.parseDouble(tempPrice);
					 String tempWeight= informations[3];
					 double weight= Double.parseDouble(tempWeight);
					 String tempQuantity=informations[4];
					 int quantity= Integer.parseInt(tempQuantity);
					 if(category.equals("houseware")|| category.equals("electronic")) {
						 Product product= new Product(category, name, price, weight);
						 products.put(product, quantity);
					 }
					 else if(category.equals("accessories")) {
						 FragileProduct fragileProduct= new FragileProduct(category, name, price, weight);
						 products.put(fragileProduct, quantity);
					 }
					 else if(category.equals("cosmetic")) {
						 FastConsumptionProduct fastConsumptionProduct= new FastConsumptionProduct(category, name, price, weight);
						 products.put(fastConsumptionProduct, quantity);
					 }
				}
			 }
		}
		catch (FileNotFoundException m) {
			 System.out.println("File" + fileName + "not found.");
		 }
		 catch (IOException e) {
			 System.out.println("Error reading from file" + fileName);
		 }
		
		return products;
	}
	public ArrayList<User> readUserCSV() {
		ArrayList<User> users = new ArrayList<User>();
		String fileName = "users.csv";
		try {
			 Scanner inFile = new Scanner(new File(fileName));
			 String line = null;
			 while (inFile.hasNextLine()) {
				 line = inFile.nextLine();
				 String[] informations = line.split(",");
				 String name = informations[1];
				 String password = informations[2];
				 String balance = informations[3];
				 if (informations[0].equals("1")) {
					 AdminUser admin = new AdminUser(name,password,balance);
					 users.add(admin);
				 }
				 else if (informations[0].equals("2")) {
					 String telephone = informations[4];
					 String eMail = informations[5];
					 String addressTitle = informations[9];
					 String country = informations[10];
					 String city = informations[11];
					 String district = informations[12];
					 String street = informations[13];
					 String doorNumber = informations[14];
					 ProductInventory<Product> basketInventory = new ProductInventory<Product>();
					 Basket basket = new Basket(basketInventory);
					 ProductInventory<Product> boughtProductsInventory = new ProductInventory<Product>();
					 Customer customer = new Customer(boughtProductsInventory,basket,name,password,balance,telephone,eMail,addressTitle,country,city,district,street,doorNumber);
					 int lastIndex = 14;
					 while(informations.length>(lastIndex+1)) {
						 String addressTitle2 = informations[lastIndex+1];
						 String country2 = informations[lastIndex+2];
						 String city2 = informations[lastIndex+3];
						 String district2 = informations[lastIndex+4];
						 String street2 = informations[lastIndex+5];
						 String doorNumber2 = informations[lastIndex+6];
						 Address address= new Address(addressTitle2, country2, city2, district2, street2, doorNumber2);
						 customer.addAddress(address);
						 lastIndex = lastIndex + 6;
					 }
					 
				    
					 users.add(customer);
				 }
				 else if(informations[0].equals("3")) {
					 String telephone = informations[4];
					 String eMail = informations[5];
					 String shopCategory= informations[6];
					 String shopTitle=informations[7];
					 String taxNumber=informations[8];
					 String addressTitle = informations[9];
					 String country = informations[10];
					 String city = informations[11];
					 String district = informations[12];
					 String street = informations[13];
					 String doorNumber = informations[14];
					 ProductInventory<Product> shopInventory= new ProductInventory<Product>();
					 Shop shop= new Shop(shopInventory, shopCategory, shopTitle, taxNumber);
					 ProductInventory<Product> oldProductsInventory= new ProductInventory<Product>();
					 Supplier supplier= new Supplier(oldProductsInventory,shop,name,password,balance,telephone,eMail,addressTitle,country,city,district,street,doorNumber);
					 int lastIndex = 14;
					 while(informations.length>(lastIndex+1)) {
						 String addressTitle2 = informations[lastIndex+1];
						 String country2 = informations[lastIndex+2];
						 String city2 = informations[lastIndex+3];
						 String district2 = informations[lastIndex+4];
						 String street2 = informations[lastIndex+5];
						 String doorNumber2 = informations[lastIndex+6];
						 Address address= new Address(addressTitle2, country2, city2, district2, street2, doorNumber2);
						 supplier.addAddress(address);
						 lastIndex = lastIndex + 6;
					 }
					 users.add(supplier);
				 }
			 }
	  }
		catch (FileNotFoundException m) {
			 System.out.println("File" + fileName + "not found.");
		 }
		 catch (IOException e) {
			 System.out.println("Error reading from file" + fileName);
		 }
		return users;
	}
}
