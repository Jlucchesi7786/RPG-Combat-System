package entities.characters;

import items.*;
import items.consumables.*;
import items.weapons.*;

import java.util.*;

import entities.Entity;

/**
 * The <i>AbstractChar</i> class does most of the behind-the-scenes work for the Character
 * portion of the combat system, including inventory management and combat actions. 
 * 
 * @author Dr. Cheese
 *
 */
public abstract class AbstractChar extends Entity {
	/**
	 * Keeps track of the total amount of XP the <i>AbstractChar</i> has recieved.
	 */
	protected int XPtotal = 0;
	
	/**
	 * Keeps track of what the <i>AbstractChar</i> has on hand and can use.
	 */
	protected ArrayList<Item> inventory = new ArrayList<Item>();
	/**
	 * Keeps track of what the current <u>Weapon</u> of the <i>AbstractChar</i> is, which
	 * modifies damage.
	 */
	protected Weapon currentWeapon;

	/**
	 * Keeps track of all of the <u>AbstractTimedConsumable</u>s that require a timer.
	 */
	protected ArrayList<Consumable> bonuses = new ArrayList<Consumable>();
	/**
	 * Keeps track of the timing for each of the <u>AbstractTimedConsumable</u>s in the 
	 * <code><b>bonuses</b></code> ArrayList.
	 */
	protected ArrayList<Integer> durations = new ArrayList<Integer>();
	
	/**
	 * This method adds an item to the character's inventory at the end.
	 * @param a The item to be added.
	 */
	public final void get(Item a) {
		inventory.add(a);
	}
	
	/**
	 * This method lets the character equip a weapon in their inventory. It checks a 
	 * couple things, based on the specified item. First, it checks if the item is in 
	 * the character's inventory. Secondly, it checks if the item is a <u>Weapon</u>. If 
	 * both of those conditions return true, it will run the 
	 * <code>.equip(Weapon newWeapon)</code> method. If one or both of those conditions 
	 * returns false, it will print out a message stating what was wrong.
	 * @param a The item to check on/be equipped
	 * @see #equip(Weapon)
	 * @see #equip(int)
	 */
	public final void equip(Item a) {
		boolean inInventory = false;
		boolean isWeapon = false;
		
		for (Item e: inventory) {
			if (a.equals(e)) {
				inInventory = true;
				if (a instanceof Weapon) {
					isWeapon = true;
				}
				break;
			}
		}
		
		if (inInventory && isWeapon) {
			equip((Weapon) a);
			return;
		} else if (!isWeapon && inInventory) {
			System.out.println("Sorry, the thing you're trying to equip is not a weapon.");
		} else if (!inInventory) {
			System.out.println("The thing you are trying to equip is not in "
					+ "your inventory.");
		}
	}
	
	/**
	 * This method checks if the item in the specified slot in the inventory is a weapon,
	 * then runs the <code>equip(Weapon newWeapon)</code> method if it is.
	 * @param i the slot to check/equip
	 * 
	 * @see #equip(Weapon)
	 * @see #equip(Item)
	 */
	public final void equip(int i) {
		if (inventory.get(i) instanceof Weapon) {
			equip((Weapon) inventory.get(i));
		}
	}
	
	/**
	 * The fully abstracted equip method. Simply sets the current weapon to a 
	 * new one specified as the parameter.
	 * @param newWeapon The weapon to be equipped.
	 */
	public final void equip(Weapon newWeapon) {
		currentWeapon = newWeapon;
	}
	
	/**
	 * This method lets a character use a consumable they have in their inventory. It 
	 * checks the character's inventory for a consumable that matches the one specified, 
	 * then if it finds it it has the <u>Consumable</u> run its <code>.apply()</code> method
	 * on this <i>AbstractChar</i>.
	 * @param a the consumable to check for/use.
	 * 
	 * @see #addBonus(AbstractTimedConsumable)
	 * @see #act(ArrayList)
	 */
	public final void use(Consumable a) {
		int place = 0;
		for (Item e: inventory) {
			if (e.equals(a)) {
				a.apply((Char) this);
				break;
			} else {
				place++;
			}
		}
		inventory.remove(place);
	}
	
	/**
	 * This method adds a timed consumable to the character's ArrayList of 
	 * <code><b>bonuses</b></code>, then runs the <code>.addDuration()</code> method on the 
	 * same consumable.
	 * @param a The <i>AbstractTimedConsumable</i> to add to the bonuses ArrayList.
	 * 
	 * @see #addDuration(AbstractTimedConsumable)
	 */
	public final void addBonus(AbstractTimedConsumable a) {
		bonuses.add(a);
		addDuration(a);
	}
	
	/**
	 * This method adds the duration stat of the inputted <i>AbstractTimedConsumable</i> to 
	 * the <code><b>durations</b></code> ArrayList.
	 * @param a the <i>AbstractTimedConsumable</i> to find a duration from, then add.
	 */
	private final void addDuration(AbstractTimedConsumable a) {
		durations.add(a.getDuration());
	}
	
	/**
	 * "Attacks" the target <u>Entity</u> by calculating a damage value and then making
	 * the target's current HP equal to their previous current HP minus that damage value.
	 * It then prints out a statement letting the console know that this Character did X 
	 * amount of damage to "its target entity".
	 * 
	 * @see #planAttack(ArrayList)
	 */
	@Override
	public final void attack(Entity other) {
		int dmg = strength + currentWeapon.getDmg() - other.defense();
		if (dmg < 0) dmg = 0;
		other.setHP(other.currentHP() - dmg);
		System.out.println(currentWeapon.attackString(dmg, this, other));
	}
	
	/**
	 * This method chooses a random <u>Entity</u> from the encounter ArrayList that is not
	 * an instance of <i>AbstractChar</i> to attack, then runs the <code>.attack()</code>
	 * method on the random <u>Entity</u>.
	 * @param actors the encounter ArrayList.
	 * 
	 * @see #attack(Entity)
	 * @see #act(ArrayList)
	 */
	private final void planAttack(ArrayList<Entity> actors) {
		int notchars = 0;
		ArrayList<Integer> places = new ArrayList<Integer>();
		for (int i = 0; i < actors.size(); i++) {
			if (!(actors.get(i) instanceof AbstractChar)) {
				notchars++;
				places.add(i);
			}
		}
		int rand = (int) Math.random() * notchars;
		attack(actors.get(rand));
	}
	
	/**
	 * This method constructs and then returns a string containing the entirety of the 
	 * character's inventory in list form. 
	 * @return A string containing the contents of the character's inventory, with a label.
	 * 
	 * @see #get(Item)
	 * @see #equip(Weapon)
	 */
	public final String showInventory() {
		String s = name + "'s inventory:";
		for (Item a: inventory) {
			s += "\n - " + a.getName();
		}
		return s;
	}
	
	/**
	 * The <i>AbstractChar</i> <code>.act()</code> method runs through each duration in the
	 * <code><b>durations</b></code> ArrayList and lowers the value by 1, removes any effects
	 * if necessary, prints out a status line, then asks the player for input. Depending on
	 * what the player inputs, the character will do a variety of different things.
	 * 
	 * @see #planAttack(ArrayList)
	 * @see #status()
	 * @see #use(Consumable)
	 */
	@Override
	public final void act(ArrayList<Entity> encounter) {
		Scanner reader = new Scanner(System.in);

		for (int i = 0; i < durations.size(); i++) {
			durations.set(i, (durations.get(i) - 1));
			if (durations.get(i) == 0) {
				((AbstractTimedConsumable) bonuses.get(i)).remove(this);
			}
		}
		
		System.out.println("\n" + name + ", " + HP + "/" + HPmax + " HP");
		System.out.println("What would you like " + name + " to do?");
		String response = reader.nextLine();
		
		if (response.equals("attack")) {
			planAttack(encounter);
		} else if (response.equals("use item")) {
			System.out.println(showInventory());
			System.out.println("What item do you want to use?");
			response = reader.nextLine();
			for (Item a: inventory) {
				if (a.getName().equals(response) && a instanceof Consumable) {
					use((Consumable) a);
					break;
				} else if (a.getName().equals(response) && !(a instanceof Consumable)) {
					System.out.println("That's not an item!");
					break;
				}
			}
		} else if (response.equals("status")) {
			status();
		}
		System.out.println();
		reader.close();
	}
	
	/**
	 * This method clears the <i>AbstractChar</i>'s <code><b>bonuses</b></code> ArrayList 
	 * and the <code><b>durations</b></code> ArrayList. It is meant to be used at the end of
	 * a combat encounter.
	 */
	public void clearEffects() {
		while (durations.size() > 0) {
			durations.remove(0);
			bonuses.remove(0);
		}
	}
	
	/**
	 * This method prints out the status string to the console.
	 * 
	 * @see #statusString()
	 */
	public void status() {
		System.out.println(statusString());
	}
	
	/**
	 * This method constructs a string containing all of the character's stats and their 
	 * values, then returns it.
	 * @return A String containing every stat and its value.
	 * 
	 * @see #status()
	 */
	private String statusString() {
		String s = "\n" + name + ": " + HP + "/" + HPmax + " HP, \n  Strength: " + strength 
				+ "\n  Defense:  " + defense + "\n  Speed:    " + speed;
		return s;
	}
	
	/**
	 * This method checks if the <i>AbstractChar</i> is dead already, and if it is not it 
	 * removes the <i>AbstractChar</i> from the encounter ArrayList.
	 */
	@Override
	public void die(ArrayList<Entity> actors) {
		if (dead) return;
		System.out.println(name + " died");
		dying = false;
		dead = true;
		for (Entity a: actors) {
			if (a.equals(this)) {
				actors.remove(a);
				break;
			}
		}
		dead = true;
	}
	
	/**
	 * Finds and returns the XP total of the <i>AbstractChar</i>.
	 * @return the <code><b>XPtotal</b></code> of the <i>AbstractChar</i>.
	 */
	public int XPtotal() {
		return XPtotal;
	}
	
	/**
	 * Sets the XP total of the <i>AbstractChar</i> to a new value.
	 * @param newTotal the new value for <code><b>XPtotal</b></code> to be set to.
	 */
	public void setXPtotal(int newTotal) {
		XPtotal = newTotal;
	}
	
	/**
	 * Adds an amount of XP equal to the amount inputted to the <i>AbstractChar</i>'s
	 * <code><b>XPtotal</b></code>.
	 * @param XPval The amount of XP to add.
	 * 
	 * @see #setXPtotal(int)
	 */
	public void addXP(int XPval) {
		XPtotal += XPval;
	}
	
	/**
	 * Checks if the other <u>Entity</u> is equal to this Entity by checking if it is an 
	 * instance of <i>AbstractChar</i>, then checking the value of each stat across both
	 * <i>AbstractChar</i>s.
	 * @param other The other entity to check for equivalency.
	 * @return True if everything is equal, and false if otherwise.
	 */
	public boolean equals(Entity other) {
		if (!(other instanceof AbstractChar) || (other.maxHP() != HPmax) 
				|| (other.currentHP() != HP) || (other.strength() != strength)
				|| (other.defense() != defense) || (other.speed() != speed) 
				|| (((AbstractChar) other).XPtotal() != XPtotal)
				|| !(((AbstractChar) other).showInventory().equals(showInventory()))) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Tells what the character's name and stats are.
	 * 
	 * @see #status()
	 * @see #statusString()
	 */
	@Override
	public final String toString() {
		String s = "This is a character named " + name + " with " + HP + 
				"/" + HPmax + " HP, " + strength + " strength, and " 
				+ defense + " defense.";
		return s;
	}
}
