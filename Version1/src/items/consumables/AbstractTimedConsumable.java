package items.consumables;

import entities.characters.*;

/**
 * This class differentiates itself from the regular <u>AbstractConsumable</u> by also 
 * containing a <code>.remove()</code> method, which removes the bonus from the target
 * <i>Character</i>. This is necessary because a timed consumable needs to have a way to 
 * remove its bonus once its duration has elapsed.
 * @author Dr. Cheese
 *
 */
public abstract class AbstractTimedConsumable extends AbstractConsumable {
	
	/**
	 * This method, in addition to modifying the stats of a character, also adds the 
	 * item to the list of durations in the <i>Character</i>'s class.
	 */
	public abstract void apply(AbstractChar subject);
	
	/**
	 * This method removes the bonus of the item from a particular <i>Character</i>, 
	 * unmodifying stats and removing things from duration lists in the process.
	 * @param subject the <i>Character</i> to remove the bonus from.
	 */
	public abstract void remove(AbstractChar subject);
}
