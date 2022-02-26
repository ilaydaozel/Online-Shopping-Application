
@SuppressWarnings("serial")
public class ProductNotFoundException extends Exception {
	public ProductNotFoundException() {
		super("Product is not found!");
	}
	public ProductNotFoundException(String message) {
		super(message);
	}
}

