package entities.monsters;

import entities.Entity;
import entities.characters.*;

import java.util.*;

/**
 * A monster is a type of <u>Entity</u> that is not controlled by the player. Any NPCs that
 * are not technically monsters will still need to be made using this abstract class.
 * 
 * <p> This class contains the definitions for the <code>.attack()</code>, 
 * <code>.act()</code>, and <code>.die()</code> methods, as well as the method statement
 * <code>.attackString()</code>. The only variable housed in this class is the 
 * <code><b>XPval</b></code> integer, as it is only necessary for <u>Monster</u>s.
 * @author Dr. Cheese
 *
 */
public abstract class Monster extends Entity {
	protected int XPval = 0;
	
	@Override
	/**
	 * Because monsters cannot equip weapons, they calculate damage by simply taking their
	 * strength and subtracting the defense of the target.
	 * 
	 * @see #act(ArrayList<Entity>)
	 */
	public final void attack(Entity target) {
		int dmg = strength - target.defense();
		if (dmg < 0) dmg = 0;
		target.setHP(target.currentHP() - dmg);
		System.out.println(attackString(dmg, target));
	}
	
	/**
	 * For <u>Monster</u>s, the act method simply finds the instance of <i>Char</i> in the
	 * encounter ArrayList and attacks it.
	 * 
	 * @see #attack(Entity)
	 */
	@Override
	public final void act(ArrayList<Entity> actors) {
		if (!dead) {
			for (Entity a: actors) {
				if (a instanceof Char) {
					this.attack(a);
					break;
				}
			}
		}
	}
	
	@Override
	public final void die(ArrayList<Entity> actors) {
		if (dead) return;
		dying = false;
		dead = true;
		System.out.println("The " + name + " died.");
		for (Entity a: actors) {
			if (a.equals(this)) {
				actors.remove(a);
				break;
			}
		}
		dead = true;
	}
	
	/**
	 * Finds and returns the value containing how much XP is gained when the <u>Monster</u>
	 * dies in combat.
	 * @return The value of XP for the <u>Monster</u>.
	 */
	public final int XPval() {
		return XPval;
	}
	
	/**
	 * Changes the value of XP for the monster to a new value.
	 * @param newVal the new value for the <code><b>XPval</b></code> to be set to.
	 */
	public final void setXPval(int newVal) {
		XPval = newVal;
	}
	
	/**
	 * The <code>.attackString()</code> method constructs a String that lets the player
	 * know what happened in a stylized manner that is meant to vary by monster.  
	 * @param dmg How much damage the monster is doing with the attack.
	 * @param target The target <u>Entity</u> of the attack.
	 * @return A string containing the results of the attack.
	 * 
	 * @see #attack(Entity)
	 */
	public abstract String attackString(int dmg, Entity target);
	
	/**
	 * This method checks for equivalency by first checking if the other <u>Entity</u> is a
	 * <u>Monster</u>, and then checking each stat.
	 * @param other The other <u>Entity</u> to check.
	 * @return True if everything is equal, and false if otherwise.
	 */
	public final boolean equals(Entity other) {
		if (!(other instanceof Monster) || (other.maxHP() != HPmax) 
				|| (other.currentHP() != HP) || (other.strength() != strength)
				|| (other.defense() != defense) || (other.speed() != speed) 
				|| (((Monster) other).XPval() != XPval)) {
			return false;
		}
		
		return true;
	}
}
