package entities;

import java.util.*;

/**
 * The basic combat actor of this system. An <u>Entity</u> can either be controlled by the 
 * player or controlled by the computer, but it is what interacts directly with the combat 
 * system.
 * 
 * <p> This abstract class contains getter and setter methods for each of its protected
 * variables, as well as <code>.checkForDeath()</code>. It also contains the two abstract
 * (undefined) methods <code>.die()</code> and <code>.attack()</code>.
 * 
 * <p> The protected variables housed in this class include every stat necessary
 * for the combat system (<b><code>HPmax</code></b>, <b><code>HP</code></b>, 
 * <b><code>strength</code></b>, <b><code>defense</code></b>, and <b><code>speed</code></b>)
 * , an array of the stat names (<b><code>statNames</code></b>), a simple boolean death 
 * value (<b><code>dead</code></b>), and a <b><code>name</code></b> String. 
 * 
 * @author Dr. Cheese
 */
public abstract class Entity {
	protected int HPmax = 0;
	protected int HP = 0;
	protected int strength = 0;
	protected int defense = 0;
	protected int speed = 0;
	public boolean dying = false;
	public boolean dead = false;
	
	protected static final String[] statNames = {"HPMax", "HP", "Strength", "Defense", 
		"Speed", "XP value"};
	//protected int[] stats = {0, 0, 0, 0, 0, 0};
	protected String name;
	
	/**
	 * Looks for and returns the name string of the entity.
	 * @return the name of the entity.
	 */
	public final String getName() {
		return name;
	}
	
	/**
	 * Changes the name string of the entity to something new.
	 * @param newName The new name for the entity.
	 */
	public final void setName(String newName) {
		name = newName;
	}
	
	/**
	 * Looks for and returns the max HP stat of the entity.
	 * @return the max HP stat of the entity.
	 */
	public final int maxHP() {
		return HPmax;
	}
	
	/**
	 * Sets the max HP stat of the entity to a new value.
	 * @param newMax the new Max HP stat for the entity.
	 */
	public final void setMaxHP(int newMax) {
		HPmax = newMax;
	}
	
	/**
	 * Looks for and returns the current HP stat of the entity.
	 * @return the current HP stat of the entity.
	 */
	public final int currentHP() {
		return HP;
	}
	
	/**
	 * Sets the current HP stat of the entity to a new value.
	 * @param newTotal the new current HP stat for the entity.
	 */
	public final void setHP(int newTotal) {
		HP = newTotal;
	}
	
	/**
	 * Looks for and returns the strength stat of the entity.
	 * @return the strength stat of the entity.
	 */
	public final int strength() {
		return strength;
	} 
	
	/**
	 * Sets the strength stat of the entity to a new value.
	 * @param newStr the new strength stat for the entity.
	 */
	public final void setStrength(int newStr) {
		strength = newStr;
	}
	
	/**
	 * Looks for and returns the defense stat of the entity.
	 * @return the max defense of the entity.
	 */
	public final int defense() {
		return defense;
	}
	
	/**
	 * Sets the defense stat of the entity to a new value.
	 * @param newDef the new defense stat for the entity.
	 */
	public final void setDefense(int newDef) {
		defense = newDef;
	}
	
	/**
	 * Looks for and returns the speed stat of the entity.
	 * @return the max speed of the entity.
	 */
	public final int speed() {
		return speed;
	}
	
	/**
	 * Sets the max speed stat of the entity to a new value.
	 * @param newSpd the new speed stat for the entity.
	 */
	public final void setSpeed(int newSpd) {
		speed = newSpd;
	}
	
	/**
	 * This method checks if the entity has a current HP value that is less than or equal to
	 * 0, and if it does, it runs the <code>.die()</code> method. This is so that any 
	 * entities that have "died" are removed from combat.
	 * 
	 * @see #die(ArrayList)
	 */
	public final void checkForDeath() {
		if (dead || dying) return;
		if (HP <= 0) {
			HP = 0;
			dying = true;
		}
	}
	
	/**
	 * This method removes the entity from combat in a way that differs depending on if it 
	 * is a character or monster/NPC. If a monster dies, they are removed from combat with 
	 * a simple statement and the monster's XP value is added to the party. If a character 
	 * dies, <b> (currently) </b> the game will close.
	 * @param actors The encounter ArrayList
	 * 
	 * @see #checkForDeath()
	 */
	public abstract void die(ArrayList<Entity> actors);
	
	/**
	 * The <code>.act()</code> method is how an <u>Entity</u> interacts with the combat 
	 * system. Essentially, it encompasses what the <u>Entity</u> does on its turn.
	 * @param encounter the encounter the <u>Entity</u> is currently in.
	 * @see #attack(Entity)
	 */
	public abstract void act(ArrayList<Entity> encounter);
	
	/**
	 * This method makes the entity attack another <u>Entity</u>, which is the default 
	 * option for monsters/NPCs.
	 * @param other the entity that is the target of the attack.
	 * 
	 * @see #act(ArrayList)
	 */
	public abstract void attack(Entity other);
}
