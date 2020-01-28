import java.util.ArrayList;

/* Living.java
 * @version 1.0
 * @author Paula Yuan
 * @date Nov. 21, 2019
 * This class is a blueprint for all the living things in Cheerville.
 */

abstract class Living {
  
  // class variables
  
  private int MAX_HP;
  private int MIN_HP;
  private int hp;
  private int[] location = new int[2];  // maybe i should just make this an x, y int 
  
  // methods: getters and setters
  
  /**
   * setMaxHP
   * this method sets the max hp for this living thing based on the integer paramater given
   * @param int max
   * @return no return value
   */
  public void setMaxHP(int max) {
    this.MAX_HP = max;
  }
  
  /**
   * getMaxHP
   * this method returns the max hp for this living thing
   * @return int MAX_HP
   */
  public int getMaxHP() {
    return this.MAX_HP;
  }
  
  /**
   * setMinHP
   * this method sets the min hp for this living thing based on the integer paramater given
   * @param int min
   * @return no return value
   */
  public void setMinHP(int min) {
    this.MIN_HP = min;
  }
  
  /**
   * getMinHP
   * this method returns the min hp for this living thing
   * @return int MIN_HP
   */
  public int getMinHP() {
    return this.MIN_HP;
  }
  
  /**
   * setHealth
   * this method hp for this living thing based on the given int parameter
   * @param int health
   * @return no return value
   */
  public void setHealth(int health) {
    this.hp = health;
  }
  
  /**
   * getHealth
   * this method returns the hp of this living thing
   * @return int hp
   */
  public int getHealth() {
    return this.hp;
  }
  
  /**
   * setLocation
   * this method sets the location for this living thing based on the int x, int y given
   * @param int x, int y
   * @return no return value
   */
  public void setLocation(int x, int y) {
    this.location[0] = x;
    this.location[1] = y;
  }
  
  /**
   * getX
   * this method returns the int x location of this living thing
   * @return int location[0]
   */
  public int getX() {
    return this.location[0];
  }
  
  /**
   * getY
   * this method returns the int y location of this living thing
   * @return int location[1]
   */
  public int getY() {
    return this.location[1];
  }
  
  // other methods:
  
  /**
   * isLiving
   * this method returns whether or not the Living thing is, in fact, living -- boolean true or false
   * @return boolean based on algorithm
   */
  public boolean isLiving() {
    if (this.getHealth() <= 0) {
      return false;
    }
    return true;
  }
  
  /**
   * collide
   * this method returns the Living thing that this thing is colliding with, if any, out of all te Living things in the
   * list of all living things.
   * @return null if no collision, the item being collided with if there is a collision
   */
  public Living collide(ArrayList<Living> beings) {
    for (int i = 0; i < beings.size(); i++) {
      if ((this.getX() == beings.get(i).getX()) && (this.getY() == beings.get(i).getY())) { 
        return beings.get(i);
      }
    }
    return null;
  }
  
  // constructors
  
  /**
   * Living
   * this constructor initializes the values for an object of this class if nothing else is given
   * this constructor isn't really useful.
   */
  Living() {
    this.setLocation(0, 0);
    this.setHealth(20);
    this.setMinHP(10);
    this.setMaxHP(30);
  }
  
}