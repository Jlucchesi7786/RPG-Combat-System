package items.weapons;

import items.Item;

/**
 * The class that handles all of the getters and setters for the <u>Weapon</u> type, 
 * as well as the <code>.equals()</code> and <code>.toString()</code> methods.
 * 
 * <p>This abstract class implements the <code>.getDmg()</code> and <code>.setDmg()</code> 
 * statements from the <u>Weapon</u> interface and the <code>.getName()</code>, 
 * <code>.setName()</code>, <code>.equals()</code> and <code>.toString()</code> statements 
 * from the <u>Item</u> interface. It contains the protected variables
 * <b><code>dmg</code></b> and <b><code>name</code></b> for use in the constructors of 
 * individual <u>Weapon</u>-type objects.
 * @author Dr. Cheese
 *
 */
public abstract class AbstractWeapon implements Weapon {
	protected int dmg;
	protected String name;
	
	@Override
	public final int getDmg() {
		return dmg;
	}

	@Override
	public final void setDmg(int newdmg) {
		dmg = newdmg;
	}
	
	@Override
	public final String getName() {
		return name;
	}
	
	@Override
	public final void setName(String newName) {
		name = newName;
	}
	
	/**
	 * Checks if the other item is an instance of a weapon, and then compares their names
	 * and damage stats.
	 */
	@Override
	public final boolean equals(Item other) {
		if (!(other instanceof Weapon) || !(other.getName().equals(name)) || 
				((Weapon) other).getDmg() != dmg) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		return "This is a weapon named " + name + " that does " + dmg + 
				" damage on its own.";
	}
}
