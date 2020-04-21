package items;

/**
 * This interface ties both the <u>Weapon</u> and <u>Consumable</u> item trees together, and
 * it contains the statements <code>.equals()</code>, <code>.getName()</code>,  
 * <code>.setName()</code>, and <code>.toString()</code>.
 * @author Dr. Cheese
 *
 */
public interface Item {
	/**
	 * Checks if this item is equal to another item by comparing the names and every stat.
	 * @param other The other item to check.
	 * @return True if the name and all stats are equal, or false if otherwise.
	 */
	public boolean equals(Item other);
	
	/**
	 * This gets the name of the item and returns it.
	 * @return the name of the item.
	 */
	public String getName();
	
	/**
	 * This sets the name of the item to something new.
	 * @param newName The new name for the item.
	 */
	public void setName(String newName);
	
	/**
	 * Lets the reader know what the item is, and what it does.
	 * @return the name, type, and function of the item.
	 */
	public String toString();
}
