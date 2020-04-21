package items.consumables;

import entities.characters.*;

/**
 * This class of <u>Consumable</u> is a Health Potion, an item common to many RPGs. It gives 
 * a target character a certain amount of health instantaneously. 
 * 
 * <p> This is a <u>Consumable</u>-type <u>Item</u> with a modifier stat 
 * (<b><code>mod</code></b>) of 15 that modifies (<b><code>buffedStat</code></b>) "HP". 
 * The <b><code>duration</code></b> of this consumable is 0, and the 
 * <b><code>name</code></b> of this consumable is "health potion".
 * 
 * <p> Note: this was the first consumable created for this system.
 * 
 * @author Dr. Cheese
 *
 */
public class HealthPotion extends AbstractConsumable {
	
	/**
	 * The base constructor of the <i>HealthPotion</i> class sets the 
	 * <b><code>buffedStat</code></b> variable to "HP", sets the <b><code>mod</code></b> 
	 * stat to 15, sets the <b><code>duration</code></b> stat to 0, and sets the
	 * <b><code>name</code></b> of the item to "health potion".
	 */
	public HealthPotion() {
		buffedStat = "HP";
		mod = 15;
		duration = 0;
		name = "health potion";
	}
	
	/**
	 * This method checks what the difference between the target's current HP and max HP is,
	 * and depending on the size of the difference does not apply (current HP is max), heals
	 * the target to full (difference between current and max HP is less than or equal to 
	 * the <b><code>mod</code></b> stat), or heals the target for <b><code>mod</code></b> HP.
	 */
	public void apply(AbstractChar subject) {
		if (subject.currentHP() == subject.maxHP()) {
			System.out.println("You don't want to do that.");
			return;
		}
		if (subject.maxHP() - subject.currentHP() <= mod) {
			System.out.println(subject.getName() + " was healed for " + (subject.maxHP() - 
					subject.currentHP()) + " HP.");
			subject.setHP(subject.maxHP());
		} else {
			subject.setHP(subject.currentHP() + mod);
			System.out.println(subject.getName() + " was healed for " + mod + " HP.");
		}
	}
}
