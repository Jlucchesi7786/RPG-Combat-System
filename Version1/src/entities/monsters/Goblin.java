package entities.monsters;

import entities.Entity;

/**
 * This class of <u>Monster</u> is a goblin, a common enemy in many RPGs. They are weak
 * and easy to kill, although they are a tad fast for beginning characters.
 * 
 * <p> This is a <u>Monster</u> <b><code>name</code></b>d "goblin". it has an 
 * <b><code>HPmax</code></b> of 7, and starts with <b><code>HP</code></b> equal to its max.
 * It has 7 <b><code>strength</code></b>, 3 <b><code>defense</code></b>, and 10 
 * <b><code>speed</code></b>. It gives 10 XP (<b><code>XPval</code></b>) on death.
 * 
 * <p> Note: this was the first <u>Monster</u> created for this system.
 * @author Dr. Cheese
 *
 */
public class Goblin extends Monster {
	public static final String defaultName = "goblin";
	public static final int[] stats = {7, 7, 7, 3, 10, 10};
	
	/**
	 * The base constructor of the <i>Goblin</i> sets the <b><code>name</code></b> to 
	 * "goblin", <b><code>HPmax</code></b> to 7, <b><code>HP</code></b> to 7, 
	 * <b><code>strength</code></b> to 7, <b><code>defense</code></b> to 3, 
	 * <b><code>speed</code></b> to 10, and <b><code>XPval</code></b> to 10.
	 */
	public Goblin() {
		name = defaultName;
		HPmax = stats[0];
		HP = stats[1];
		strength = stats[2];
		defense = stats[3];
		speed = stats[4];
		XPval = stats[5];
	}
	
	@Override
	public String attackString(int dmg, Entity other) {
		String s = "The goblin ";
		if (dmg > 0) {
			s += "pokes " + other.getName() + " with its spear, dealing " + dmg + " damage.";
		} else {
			s += "shakes its spear at " + other.getName() + ", dealing no damage.";
		}
		return s;
	}
}
