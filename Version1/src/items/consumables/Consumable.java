package items.consumables;

import entities.characters.*;
import items.Item;

/**
 * This interface starts the <u>Consumable</u> branch of items and contains the statements 
 * <code>.getBuffedStat()</code>, <code>.getMod()</code>, <code>.setMod()</code>, 
 * <code>.getDuration()</code>, <code>.setDuration()</code>, <code>.apply()</code>, and
 * <code>.remove()</code>. It implements the <u>Item</u> interface.
 * @author Dr. Cheese
 *
 */
public interface Consumable extends Item {
	/**
	 * Finds and returns the name of the buffed stat of the item.
	 * @return the name of the buffed stat of the item.
	 */
	public String getBuffedStat();
	
	/**
	 * Finds and returns the value corresponding to how much the item should add to its
	 * buffed stat.
	 * @return how much the item should add to its buffed stat.
	 */
	public int getMod();
	
	/**
	 * Changes the value corresponding to how much the item should add to its buffed stat.
	 * @param newMod The new value to add.
	 */
	public void setMod(int newMod);
	
	/**
	 * Finds and returns how long the items effect should last, in turns, after its use.
	 * @return The duration of the item.
	 */
	public int getDuration();
	
	/**
	 * Sets the duration stat of the item to a new value.
	 * @param newDuration the new duration value.
	 */
	public void setDuration(int newDuration);
	
	/**
	 * This method applies the bonus of the item to a given target <i>Character</i>, 
	 * modifying any stats as necessary.
	 * @param subject The target <i>Character</i> to use the item on.
	 */
	public void apply(AbstractChar subject);
}
