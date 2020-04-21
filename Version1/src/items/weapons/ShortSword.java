package items.weapons;

import entities.Entity;

/**
 * This class of <u>Weapon</u> represents the damage a character would do when equipped with
 * some base gear.
 * 
 * <p> This is a <u>Weapon</u>-type <u>Item</u> with a base damage (<b><code>dmg</code></b>) 
 * of 5. The <b><code>name</code></b> of this weapon is "shortsword".
 * @author Dr. Cheese
 *
 */
public class ShortSword extends AbstractWeapon {
	
	/**
	 * The base constructor of the <i>Shortsword</i> class sets its damage 
	 * (<b><code>dmg</code></b>) to 5 and its <b><code>name</code></b> to "shortsword".
	 */
	public ShortSword() {
		dmg = 5;
		name = "shortsword";
	}
	
	@Override
	public String attackString(int dmg, Entity user, Entity target) {
		String s = user.getName() + " ";
		if (dmg == 0) {
			s += "swings the shortsword around in the air to intimidate the " 
					+ target.getName()+ ", dealing 0 damage.";
		} else {
			s += "slices the " + target.getName() + " up a little bit, dealing " + dmg
					+ " damage.";
		}
		return s;
	}

}
