package items.consumables;

import items.Item;

/**
 * This class handles the getters and setters for the <u>Consumable</u> family of classes, 
 * as well as the <code>.equals()</code> and <code>.toString()</code> methods.
 * 
 * <p>This abstract class implements the getter and setter statements from the 
 * <u>Consumable</u> interface and the <code>.getName()</code>, <code>.setName()</code>, 
 * <code>.equals()</code> and <code>.toString()</code> statements from the <u>Item</u> 
 * interface. It houses the protected variables <b><code>buffedStat</code></b>, 
 * <b><code>mod</code></b>, <b><code>duration</code></b>, and <b><code>name</code></b> 
 * for use in the constructors of individual <u>Consumable</u>-type objects. 
 * @author Dr. Cheese
 *
 */
public abstract class AbstractConsumable implements Consumable {
	protected String buffedStat;
	protected int mod;
	protected int duration;
	protected String name;
	
	public final String getBuffedStat() {
		return buffedStat;
	}
	
	public final int getMod() {
		return mod;
	}
	
	public final void setMod(int newMod) {
		mod = newMod;
	}
	
	public final String getName() {
		return name;
	}
	
	public final void setName(String newName) {
		name = newName;
	}

	public final int getDuration() {
		return duration;
	}

	public final void setDuration(int newDuration) {
		duration = newDuration;
	}
	
	/**
	 * Checks if the other item is an instance of a consumable, then checks the names,
	 * buffed stats, modifier values, and durations.
	 */
	public boolean equals(Item other) {
		if (!(other instanceof Consumable) 
				|| !(((Consumable) other).getBuffedStat().equals(buffedStat))
				|| ((Consumable) other).getMod() != mod 
				|| ((Consumable) other).getDuration() != duration
				|| !(other.getName().equals(name))) {
			return false;
		}
		return true;
	}
	
	@Override
	public final String toString() {
		String s =  "this is a " + name + " that adds " + mod + " to " + buffedStat + ". It ";
		if (duration < 1) {
			s += "happens immediately.";
		} else {
			s += "lasts for " + duration;
			if (duration == 1) {
				s += " turn";
			} else {
				s += " turns";
			}
			s+= " after use.";
		}
		return s;
	}
}
