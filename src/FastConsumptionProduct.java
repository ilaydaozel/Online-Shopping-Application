
public class FastConsumptionProduct extends Product {
	public FastConsumptionProduct() {
		super();
	}
	public FastConsumptionProduct(String category, String name, double price, double weight) {
		super(category,name,price,weight);
	}
	
	public double calculateCargoPrice() {
		return super.calculateCargoPrice()*3.5;
	}
	public String toString() {
		return(super.toString());
	}

}
