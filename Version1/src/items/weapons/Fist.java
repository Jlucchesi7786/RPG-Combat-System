package items.weapons;

import entities.Entity;

/**
 * This class of <u>Weapon</u> represents the base damage a character would do, effectively 
 * by "punching" their target.
 * 
 * <p> This is a <u>Weapon</u>-type <u>Item</u> with a base damage (<b><code>dmg</code></b>) 
 * of 0. The <b><code>name</code></b> of this weapon is "fist".
 * 
 * <p> Note: this was the first weapon created for this system.
 * @author Dr. Cheese
 *
 */
public class Fist extends AbstractWeapon {
	
	/**
	 * The base constructor of the <i>Fist</i> class sets its damage 
	 * (<b><code>dmg</code></b>) to 0 and its <b><code>name</code></b> to "fist".
	 */
	public Fist() {
		dmg = 0;
		name = "fist";
	}
	
	public String attackString(int dmg, Entity user, Entity target) {
		String s = user.getName() + " ";
		if (dmg == 0) {
			s += "punches the " + target.getName() + "'s surprisingly tough skin, dealing 0"
					+ " damage and hurting their hand in the process.";
		} else {
			s += "socks the " + target.getName() + " right where it hurts, dealing " + dmg 
					+ " damage in the process.";
		}
		return s;
	}
}
