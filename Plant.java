import java.util.Random;

/* Plant.java
 * @version 1.0
 * @author Paula Yuan
 * @date Nov. 21, 2019
 * This class is a blueprint for all the plants spawned in Cheerville.
 */

public class Plant extends Living {
  
  Random random = new Random(); // new random number generator
  
  // Variables
  
  private int nutritionValue;
  private int age;
  private int minNutValue;
  
  // Methods: getters and setters
  /**
   * getMinNutValue
   * this method returns the integer minimum nutrition value
   * @return int minNutValue
   */
  public int getMinNutValue() {
    return this.minNutValue;
  }
  
  /**
   * setMinNutValue
   * this method sets the minimum nutrition value given an integer value
   * @param int nutValue
   * @return no return value
   */
  public void setMinNutValue(int nutValue) {
    this.minNutValue = nutValue;
  }
  
  /**
   * getAge
   * this method returns the integer age of the plant
   * @return int age
   */
  public int getAge() {
    return this.age;
  }
  
  /**
   * setAge
   * this method sets the age of the plant given an integer age
   * @param int age
   * @return no return value
   */
  public void setAge(int age) {
    this.age = age;
  }
  
  /**
   * getNutValue
   * this method returns the integer nutrition value of the plant
   * @return int nutritionValue
   */
  public int getNutValue() {
    return this.nutritionValue;
  }
  
  /**
   * setNutValue
   * this method sets the nutrition value of the plant based on an integer value
   * @param int value
   * @return no return value
   */
  public void setNutValue(int value) {
    this.nutritionValue = value;
  }
  
  // other methods:
  /**
   * grow
   * Given a boolean value of whether it's raining or not, this method grows a plant, changing its nutrition and hp
   * values.
   * @return no return value
   */
  public void grow(boolean isRaining) {
    this.setAge(this.getAge() + 1);
    if (this.getAge() <= 12) {
      this.setNutValue(this.getNutValue() + 3);  
    } else {
      if (this.getNutValue() >= 3) {
        this.setNutValue(this.getNutValue() - 3);
      }
      this.setHealth(this.getHealth() - 1);
    }
    if (isRaining && this.getHealth() <= this.getMaxHP()) {
      this.setHealth(this.getHealth() + 1);
    }
  }
  
  // Constructors
  
  /**
   * Plant()
   * this constructor initializes values for the plant given no other parameters
   */
  Plant() {
    this.setMaxHP(25);
    this.setMinHP(5);
    this.setHealth(random.nextInt(26) + this.getMinHP());
    this.setLocation(0,0);
    this.setMinNutValue(0);
    this.setNutValue(random.nextInt(21) + this.getMinNutValue());
  }
  
  /**
   * Plant(int x, int y)
   * this constructor intializes the plant given a spawning location x and y, an int maximum HP value, int minimum HP
   * value, and int mininmum nutritional value
   */
  Plant(int x, int y, int maxHP, int minHP, int minNutValue) {
    this.setMaxHP(maxHP);
    this.setMinHP(minHP);
    this.setHealth(random.nextInt(26) + this.getMinHP());
    this.setLocation(x, y);
    this.setMinNutValue(minNutValue);
    this.setNutValue(random.nextInt(21) + this.getMinNutValue());
  }

}