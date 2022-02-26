public class Product {
	private String category;
	private String name;
	private double price;
	private double weight;
	public Product() {
		this(" "," ",0.0,0.0);
	}
	public Product(String category, String name, double price, double weight) {
		this.category=category;
		this.name =name;
		this.price=price;
		this.weight=weight;
	}
	public double calculateCargoPrice() {
		return weight*2.0;
	}
	public String toString() {
		return (" Category: "+ category+ ", Name: "+ name+", Price: "+ price+ ", Weight: "+ weight);
	}
	public boolean equals(Object other) {
		if(other==null) {
			return false;
		}
		else if(getClass()!=other.getClass()) {
			return false;
		}
		Product o=(Product)other;
		return (category.equals(o.category)&& name.equals(o.name)&& price==o.price && weight==o.weight);
	}
	public String getCategory() {
		return category;
	}
	public String getName() {
		return name;
	}
	public double getPrice() {
		return price;
	}
	public double getWeight() {
		return weight;
	}

}
