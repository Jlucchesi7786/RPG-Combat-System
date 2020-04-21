package entities.monsters;

import entities.Entity;

/**
 * This class of <u>Monster</u> is an orc, another common enemy in many RPGs. They are a 
 * fair bit stronger and beefier than goblins, but they are slower and give more XP.
 * 
 * <p> This is a <u>Monster</u> <b><code>name</code></b>d "orc". it has an 
 * <b><code>HPmax</code></b> of 15, and starts with <b><code>HP</code></b> equal to its max.
 * It has 10 <b><code>strength</code></b>, 6 <b><code>defense</code></b>, and 4 
 * <b><code>speed</code></b>. It gives 20 XP (<b><code>XPval</code></b>) on death.
 * 
 * @author Dr. Cheese
 *
 */
public class Orc extends Monster {
	public static final String defaultName = "orc";
	public static final int[] stats = {15, 15, 10, 6, 4, 20};
	
	/**
	 * The base constructor of the <i>Orc</i> sets the <b><code>name</code></b> to 
	 * "orc", <b><code>HPmax</code></b> to 15, <b><code>HP</code></b> to 15, 
	 * <b><code>strength</code></b> to 10, <b><code>defense</code></b> to 6, 
	 * <b><code>speed</code></b> to 4, and <b><code>XPval</code></b> to 20.
	 */
	public Orc() {
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
		String s = "The orc ";
		if (dmg > 0) {
			s += "swings its axe at " + other.getName() + ", dealing " + dmg + " damage.";
		} else {
			s += "stares " + other.getName() + " down, dealing no damage.";
		}
		return s;
	}

}
