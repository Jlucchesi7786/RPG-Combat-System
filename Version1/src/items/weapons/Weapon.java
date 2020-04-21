package items.weapons;

import items.Item;
import entities.Entity;


/**
 * This interface starts the <u>Weapon</u> branch of classes, and contains the statements 
 * <code>.getDmg()</code> and <code>.setDmg()</code>. It implements the <u>Item</u> 
 * interface.
 * @author Dr. Cheese
 *
 */
public interface Weapon extends Item {
	/**
	 * This method finds and returns the standalone damage value of the particular weapon.
	 * @return The damage value of the weapon.
	 */
	public int getDmg();
	
	/**
	 * This method sets the standalone damage value of the weapon to a new value.
	 * @param newDmg the new damage value for the weapon.
	 */
	public void setDmg(int newDmg);
	
	/**
	 * This method returns a String containing stylized information on how much the weapon
	 * is doing, representing the attack using the weapon.
	 * @param dmg How much damage the user is doing with the weapon.
	 * @param user The <u>Entity</u> that is using the weapon.
	 * @param target The target <u>Entity</u> the weapon is being used on.
	 * @return A String containing stylized information on what the weapon is doing.
	 */
	public String attackString(int dmg, Entity user, Entity target);
}
