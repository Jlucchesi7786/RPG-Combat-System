package opcode;

import java.util.*;

import entities.Entity;
import entities.monsters.*;
import entities.characters.*;

/**
 * This class is the class that runs each and every encounter in this combat system. It 
 * makes use of two <u>Entity</u> ArrayLists and some loops to do so.
 * <p>Much of this class is static, as I wanted it to be usable without needing to create an 
 * instance of the class in the file someone would be working in.
 * @author Dr. Cheese
 *
 */
public class EncounterHandler {
	static ArrayList<Entity> defaultencounter = new ArrayList<Entity>();
	static ArrayList<Entity> encounter = new ArrayList<Entity>();
	static ArrayList<Entity> trueencounter = new ArrayList<Entity>();
	static Char mainchar = new Char();
	
	/**
	 * The default constructor of the <i>EncounterHandler</i> class constructs the 
	 * <code><b>defaultencounter</b></code> ArrayList.
	 */
	public EncounterHandler() {
		Orc orc1 = new Orc();
		Char char1 = new Char();
		
		defaultencounter.add(orc1);
		defaultencounter.add(char1);
	}
	
	/**
	 * This method runs an encounter containing a new <i>Char</i> and new <i>Orc</i> object.
	 * 
	 * @see #run(Entity[])
	 * @see #run(ArrayList)
	 */
	public static void defaultRun() {
		run(new Entity[] {new Orc(), new Char()});
	}
	
	/**
	 * This method converts an inputted <u>Entity</u> Array into an ArrayList, then uses the 
	 * <code>.run()</code> method on it.
	 * @param enc the array of <u>Entity</u>s to be run.
	 * 
	 * @see #run(ArrayList)
	 */
	public static void run(Entity[] enc) {
		ArrayList<Entity> encList = new ArrayList<Entity>();
		for (Entity a: enc) {
			encList.add(a);
		}
		run(encList);
	}
	
	/**
	 * This method runs an ArrayList of <u>Entity</u>s by making use of various other private
	 * methods and a while loop. While the <code><b>mainchar</b></code> lives, the while loop
	 * will not close, although it will be force-broken if the <code>totaldeaths</code> 
	 * ArrayList size is one less than the <code><b>encounter</b></code>'s size.
	 * @param enc The new encounter arrayList to be run.
	 * 
	 * @see #startEncounter(ArrayList)
	 * @see #calcXP(ArrayList)
	 * @see #cleanup()
	 */
	public static void run(ArrayList<Entity> enc) {
		startEncounter(enc);
		ArrayList<Entity> totaldeaths = new ArrayList<Entity>();
		do {
			ArrayList<Entity> rounddeaths = new ArrayList<Entity>();
			for (Entity a: encounter) {
				if (!a.dead) {
					a.act(trueencounter);
				}
				for (Entity e: encounter) {
					e.checkForDeath();
					if (e.dying) {
						rounddeaths.add(e);
						totaldeaths.add(e);
					}
				}
				while (rounddeaths.size() > 0) {
					rounddeaths.get(0).die(trueencounter);
					rounddeaths.remove(rounddeaths.get(0));
				}
			}
			if (totaldeaths.size() == encounter.size() - 1) {
				break;
			}
		} while (!mainchar.dead);
		System.out.println("You won!");
		mainchar.addXP(calcXP(totaldeaths));
		System.out.println("You gained " + calcXP(totaldeaths) + " XP.");
		cleanup();
	}
	
	/**
	 * This method prints out to the console that there is a new encounter, adds every 
	 * <u>Entity</u> to both the <code><b>encounter</b></code> and 
	 * <code><b>trueencounter</b></code> ArrayLists, finds the <code><b>mainchar</b></code>
	 * in the <code><b>encounter</b></code> ArrayList, then runs the 
	 * <code>.sortEncounter()</code> and <code>.explainEncounter()</code> methods.
	 * @param enc The new encounter ArrayList to be run.
	 * 
	 * @see #sortEncounter(ArrayList)
	 * @see #explainEncounter(ArrayList)
	 */
	private static void startEncounter(ArrayList<Entity> enc) {
		System.out.println("New encounter!");
		for (Entity a: enc) {
			encounter.add(a);
			trueencounter.add(a);
		}
		for (Entity a: encounter) {
			if (a instanceof Char) {
				mainchar = (Char) a;
				break;
			}
		}
		sortEncounter(encounter);
		explainEncounter(encounter);
	}
	
	/**
	 * This method removes all of the <u>Entity</u>s from the <code><b>encounter</b></code>
	 * and <code><b>trueencounter</b></code> ArrayLists, then runs the <i>mainchar</i>'s 
	 * <code>.clearEffects()</code> method.
	 */
	private static void cleanup() {
		while (encounter.size() > 0) {
			encounter.remove(0);
		}
		while (trueencounter.size() > 0) {
			trueencounter.remove(0);
		}
		mainchar.clearEffects();
	}
	
	/**
	 * This method splits the encounter into groups of monsters and the Character using the 
	 * <code>.splitEncounter()</code>, counts the number of <u>Entity</u>s in each group 
	 * using the <code>.countNumEntitiesInGroup()</code> method, then constructs a String 
	 * containing that information in list form and prints it out.
	 * @param encounter The new encounter ArrayList to be run.
	 * 
	 * @see #splitEncounter(ArrayList)
	 * @see #countNumEntitiesInGroup(String, ArrayList)
	 */
	private static void explainEncounter(ArrayList<Entity> encounter) {
		ArrayList<String> groups = splitEncounter(encounter);
		ArrayList<Integer> numEntitiesInGroups = new ArrayList<Integer>();
		for (String s: groups) {
			numEntitiesInGroups.add(countNumEntitiesInGroup(s, encounter));
		}
		System.out.println("This combat consists of: ");
		for (int i = 0; i < groups.size(); i++) {
			String s = " - " + numEntitiesInGroups.get(i);
			int index = 0;
			for (int z = 0; z < i; z++) {
				index += numEntitiesInGroups.get(z);
			}
			if (encounter.get(index) instanceof Char) {
				s += " character named " + groups.get(i);
			} else {
				s += " " + groups.get(i);
			}
			if (numEntitiesInGroups.get(i) != 1) {
				s += "s";
			}
			System.out.println(s);
		}
	}
	
	/**
	 * This method constructs an ArrayList of names using each <u>Entity</u>'s 
	 * <code>.getName()</code> method, then returns it. If a duplicate name is found within
	 * the <code>entityNames</code> ArrayList, a new copy of the name is not added, making it
	 * so that there are no duplicate names.
	 * @param encounter The new encounter ArrayList to be run.
	 * @return A String ArrayList containing all of the unique <u>Entity</u> names from the 
	 * encounter ArrayList.
	 */
	private static ArrayList<String> splitEncounter(ArrayList<Entity> encounter) {
		ArrayList<String> entityNames = new ArrayList<String>();
		entityNames.add(encounter.get(0).getName());
		for (int i = 1; i < encounter.size(); i++) {
			for (String s: entityNames) {
				if (!(encounter.get(i).getName().equals(s))) {
					entityNames.add(encounter.get(i).getName());
					break;
				}
			}
		}
		return entityNames;
	}
	
	/**
	 * This method searches the encounter ArrayList for <u>Entity</u>s with the inputted name
	 * and every time one is found, a counter goes up by 1. It then returns the counted 
	 * number.
	 * @param name The name to search the ArrayList for.
	 * @param encounter The encounter ArrayList to search.
	 * @return the number of <u>Entity</u>s with the inputted name.
	 */
	private static int countNumEntitiesInGroup(String name, ArrayList<Entity> encounter) {
		int numEntities = 0;
		for (Entity a: encounter) {
			if (a.getName().equals(name)) {
				numEntities++;
			}
		}
		return numEntities;
	}
	
	/**
	 * This method takes the new encounter ArrayList and sorts it into descending order by 
	 * speed, meaning the <u>Entity</u> with the highes speed is first and the <u>Entity</u>
	 * with the lowest speed goes last.
	 * @param encounter The new encounter ArrayList
	 */
	private static void sortEncounter(ArrayList<Entity> encounter) {
		for (int i = 0; i < encounter.size(); i++) {
			for (int z = 0; z < encounter.size() - i - 1; z++) {
				if (encounter.get(z).speed() < encounter.get(z+1).speed()) {
					Entity entityA = encounter.get(z+1);
					encounter.set(z+1, encounter.get(z));
					encounter.set(z, entityA);
				}
			}
		}
		
	}
	
	/**
	 * this method calculates the total amount of XP the <code><b>mainchar</b></code>
	 * should receive.
	 * @param totaldeaths The ArrayList containing all of the deaths for the current 
	 * encounter.
	 * @return The amount of XP the <code><b>mainchar</b></code> should receive for the 
	 * encounter.
	 */
	private static int calcXP(ArrayList<Entity> totaldeaths) {
		int combatXP = 0;
		for (Entity a: totaldeaths) {
			combatXP += ((Monster) a).XPval();
		}
		return combatXP;
	}
}
