
public class FragileProduct extends Product {
	public FragileProduct() {
		super();
	}
	public FragileProduct(String category, String name, double price, double weight) {
		super(category,name,price,weight);
	}
	
	public double calculateCargoPrice() {
		return super.calculateCargoPrice()*4.5;
	}
	public String toString() {
		return(super.toString());
	}
}
