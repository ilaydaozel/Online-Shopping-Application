
public interface IInventory<T> {
	/**This method adds the given element with given quantity to the inventory.
	 * @param element the T type which will be added to the inventory
	 * @param quantity the integer which will determine how many of this element will be added to the inventory.
	 */
	public void addElementToInventory(T element,int quantity);
	/**This method checks the given element exist with the given quantity, if it does, it removes it.
	 * @param element the T type which will be removed from the inventory
	 * @param quantity the integer which will determine how many of this element will be removed from the inventory.
	 * @return true if the element is removed, false if its not.
	 */
	public boolean removeElementFromInventory(T element,int quantity);
	/**This method shows the inventory elements.
	 * @param string the string which is used to print inventory names in the implementation classes.
	 */
	public void showInventory(String string);
}
