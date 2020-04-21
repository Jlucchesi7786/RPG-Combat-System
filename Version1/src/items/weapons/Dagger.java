package items.weapons;

import entities.Entity;

/**
 * This class of <u>Weapon</u> represents the damage a character would do when equipped with
 * only the barest minimum of gear.
 * 
 * <p> This is a <u>Weapon</u>-type <u>Item</u> with a base damage (<b><code>dmg</code></b>) 
 * of 2. The <b><code>name</code></b> of this weapon is "dagger".
 * @author Dr. Cheese
 *
 */
public class Dagger extends AbstractWeapon {
	
	/**
	 * The base constructor of the <i>Dagger</i> class sets its damage 
	 * (<b><code>dmg</code></b>) to 2 and its <b><code>name</code></b> to "dagger".
	 */
	public Dagger() {
		dmg = 2;
		name = "dagger";
	}
	
	public String attackString(int dmg, Entity user, Entity target) {
		String s = user.getName() + " ";
		if (dmg == 0) {
			s += "breaks a little bit of the tip of the dagger off on the " 
					+ target.getName() + ", dealing 0 damage.";
		} else {
			s += "jams the dagger into a chink in the " + target.getName() + "'s armor, "
					+ "dealing " + dmg + " damage.";
		}
		return s;
	}

}
