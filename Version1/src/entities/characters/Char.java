package entities.characters;


import items.consumables.*;
import items.weapons.*;

/**
 * This is the class that instantiates everything from the <i>AbstractChar</i> class. For the
 * moment, it only contains constructors and some default variables.
 * @author Dr. Cheese
 *
 */
public class Char extends AbstractChar {
	public static final String defaultName = "defaultText";
	public static final int[] defaultStats = {20, 20, 10, 5, 7};
	
	/**
	 * The base constructor of the <i>Char</i> uses both default parameters to run the 
	 * constructor requiring both a String and array.
	 * 
	 * @see #Char(String, int[])
	 */
	public Char() {
		this(defaultName, defaultStats);
	}
	
	/**
	 * This constructor takes an inputted name String and uses the 
	 * <code><b>defaultStats</b></code> parameter for the other part to run the constructor
	 * needing both a String an array.
	 * @param name The name you want the character to have.
	 * 
	 * @see #Char(String, int[])
	 */
	public Char(String name) {
		this(name, defaultStats);
	}
	
	/**
	 * This constructor takes an inputted array of stats and uses the 
	 * <code><b>defaultName</b></code> parameter for the other part to run the constructor
	 * needing both a String an array.
	 * @param stats The array of stats for the character.
	 * 
	 * @see #Char(String, int[])
	 */
	public Char(int[] stats) {
		this(defaultName, stats);
	}
	
	/**
	 * This constructor sets the <b><code>name</code></b> to the inputted name, then sets 
	 * each of the <i>Char</i>'s stats to their inputted values using the inputted array. 
	 * It then gives the <i>Char</i> a new <i>Fist</i> and 5 <i>HealthPotion</i>s.
	 * 
	 * @param name The String to set the <i>Char</i>'s name to.
	 * @param stats The array of stats for the character.
	 * 
	 * @see #Char()
	 * @see #Char(int[])
	 * @see #Char(String)
	 */
	public Char(String name, int[] stats) {
		this.name = name;
		HPmax = stats[0];
		HP = stats[1];
		strength = stats[2];
		defense = stats[3];
		speed = stats[4];
		
		get(new ShortSword());
		for (int i = 0; i < 5; i++) {
			get(new HealthPotion());
		}
		get(new DefensePotion());
		
		equip(inventory.get(0));
	}
}
