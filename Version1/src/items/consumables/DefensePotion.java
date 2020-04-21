package items.consumables;

import entities.characters.*;

/**
 * This class of <u>Consumable</u> is a Defense Potion, an item common to many RPGs. It gives
 * a target character a Defense bonus that lasts for 5 turns after its use. 
 * 
 * <p> This is a <u>Consumable</u>-type <u>Item</u> with a modifier stat 
 * (<b><code>mod</code></b>) of 5 that modifies (<b><code>buffedStat</code></b>) "Defense". 
 * The <b><code>duration</code></b> of this consumable is 5, and the 
 * <b><code>name</code></b> of this consumable is "defense potion".
 * 
 * <p> Note: this was the first timed consumable created for this system.
 * @author Dr. Cheese
 *
 */
public class DefensePotion extends AbstractTimedConsumable {
	
	/**
	 * The base constructor of the <i>DefensePotion</i> class sets the 
	 * <b><code>buffedStat</code></b> variable to "defense", sets the <b><code>mod</code></b>
	 * stat to 5, sets the <b><code>duration</code></b> stat to 5, and sets the 
	 * <b><code>name</code></b> of the item to "defense potion".
	 */
	public DefensePotion() {
		buffedStat = "Defense";
		mod = 5;
		duration = 5;
		name = "defense potion";
	}
	
	/**
	 * The <i>DefensePotion</i>'s <code>.apply()</code> method sets the defense of the
	 * target character to their defense plus the <b><code>mod</code></b> stat of the 
	 * <i>DefensePotion</i>, and runs the targets <code>.addBonus()</code> method to add
	 * the <i>DefensePotion</i> and its duration to their respective lists in the target.
	 */
	public void apply(AbstractChar subject) {
		subject.setDefense(subject.defense() + mod);
		subject.addBonus(this);
	}
	
	/**
	 * The <i>DefensePotion</i>'s <code>.remove()</code> method sets the defense of the
	 * target character to their defense minus the <b><code>mod</code></b> stat of the 
	 * <i>DefensePotion</i>.
	 */
	public void remove(AbstractChar subject) {
		subject.setDefense(subject.defense() - mod);
	}

}
