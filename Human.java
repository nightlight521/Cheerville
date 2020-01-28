import java.util.Random;

/* Human.java
 * @version 1.0
 * @author Paula Yuan
 * @date Nov. 21, 2019
 * This class is a blueprint for all humans spawned in Cheerville.
 */

public class Human extends Living implements Moveable, CanEat<Plant>{
  
  // class variables
  private char gender;
  private int age;
  private int childHPBound;
  private int procreateCooldown;
  private int timeToBirth;
  private Human mother;
  private int energyToLive;
  public static final int MIN_REPRODUCTIVE_AGE = 16;
  public static final int PREGNANCY_PERIOD = 9;
  
  Random random = new Random(); // new random number generator
  
  // methods: getters and setters
  
  /**
   * setEnergyToLive
   * this method sets the amount of energy it takes for humans to live based on the given int parameter
   * @param int energy
   * @return no return value
   */
  public void setEnergyToLive(int energy) {
    this.energyToLive = energy;
  }
  
  /**
   * getEnergyToLive
   * this method returns the amount of energy it takes for humans to live
   * @return int energyToLive
   */
  public int getEnergyToLive() {
    return this.energyToLive;
  }
  
  /**
   * setMother
   * this method sets the mother of this human based on the Human parameter given
   * @param Human mother
   * @return no return value
   */
  public void setMother(Human mother) {
    this.mother = mother;
  }
  
  /**
   * getMother
   * this method returns the Human mother of this human
   * @return Human mother
   */
  public Human getMother() {
    return this.mother;
  }
  
  /**
   * setTimeToBirth
   * this method sets the amount of time left until the child is born based on given int parameter
   * @param int turnsLeft
   * @return no return value
   */
  public void setTimeToBirth(int turnsLeft) {
    this.timeToBirth = turnsLeft;
  }
  
  /**
   * getTimeToBirth
   * this method returns the amount of time left until the child is born (in turns)
   * @return int timeToBirth
   */
  public int getTimeToBirth() {
    return this.timeToBirth;
  }
  
  /**
   * setAge
   * this method sets the age of the Human based on the integer parameter given
   * @param int age
   * @return no return value
   */
  public void setAge(int age) {
    this.age = age;
  }
  
  /**
   * getAge
   * this method returns the integer age of the human
   * @return int age
   */
  public int getAge() {
    return this.age;
  }
  
  /**
   * setChildHPBound
   * this method sets the upper limit for a child's hp based on the int parameter given
   * @param int bound
   * @return no return value
   */
  public void setChildHPBound(int bound) {
    this.childHPBound = bound;
  }
  
  /**
   * getChildHPBound
   * this method gets the upper limit for a child's hp
   * @return no int childHPBound
   */
  public int getChildHPBound() {
    return this.childHPBound;
  }
  
  /**
   * setProcreateCooldown
   * this method sets the procreation cooldown time for a human based on the given int parameter
   * @param int cooldown
   * @return no return value
   */
  public void setProcreateCooldown(int cooldown) {
    this.procreateCooldown = cooldown;
  }
  
  /**
   * getProcreateCooldown
   * this method returns the int procreation cooldown of the human
   * @return int procreateCooldown
   */
  public int getProcreateCooldown() {
    return this.procreateCooldown;
  }
  
  /**
   * setGender
   * this method sets the char gender of the human -- 'f' is female, 'm' is male -- based on the given int parameter
   * @param int num
   * @return no return value
   */
  public void setGender(int num) { 
    if (num == 0) {
      this.gender = 'f';
    } else {
      this.gender = 'm';
    }
  }
  
  /**
   * getGender
   * this method gets the char gender of the human
   * @return char gender
   */
  public char getGender() {
    return this.gender;
  }
  
  // other methods
  
  /**
   * updateEnergyToLive
   * this method updates the amount of energy it takes for the human to live based on their age
   * @return no return value
   */
  public void updateEnergyToLive() {
    if (this.getAge() < 60) {
      this.setEnergyToLive(1);
    } else if (this.getAge() < 80) {
      this.setEnergyToLive(2);
    } else if (this.getAge() < 100) {
      this.setEnergyToLive(5);
    } else {
      this.setEnergyToLive(10);
    }
  }
  
  /**
   * grow
   * this method grows the human's age based on their current age. If they are a child, their hp bound also increases
   * @return no return value
   */
  public void grow() {
    if (this.getAge() < this.MIN_REPRODUCTIVE_AGE) {
      this.setChildHPBound(this.getChildHPBound() + 10);
    }
    this.setAge(this.getAge() + 1);
  }
  
  /**
   * validateProcreation
   * this method checks if procreation between a given Human person and this person is valid based on their genders,
   * their ages, and their procreation cooldowns, returning true if it is valid and no otherwise
   * @param Human person -- the other person that this person is procreating with (possibly)
   * @return true or false based on the algorithm
   */
  public boolean validateProcreation(Human person) {
    if (((this.gender == 'f' && person.getGender() == 'm') || (this.gender == 'm' && person.getGender() == 'f')) && 
        person.getAge() > MIN_REPRODUCTIVE_AGE && this.age > MIN_REPRODUCTIVE_AGE && this.getProcreateCooldown() == 0 &&
        person.getProcreateCooldown() == 0) {
      return true;
    }
    return false;
  }
  
  /**
   * move
   * this method moves the human by generating a random number direction in which to move x and y until a valid movement
   * is found.
   * @return no return value
   */
  public void move() {
    // generates random x or y change using a random integer betwen 0 to 2 inclusive, then minus 1 for accurate movement
    
    int moveOrNot = random.nextInt(2);
    if (moveOrNot == 1) {
      int deltaX, deltaY;
      int newX, newY;
      do {
        deltaX = random.nextInt(3) - 1; // i.e. -1, 0, 1 -- possible movement changes in x or y, where 0 is rejected
        deltaY = random.nextInt(3) - 1;
        if (this.getX() + deltaX <= 24 && this.getX() + deltaX >= 0) { // check if in grid
          newX = this.getX() + deltaX;
        } else {
          newX = this.getX();
        }
        if (this.getY() + deltaY <= 24 && this.getY() + deltaY >= 0) {
          newY = this.getY() + deltaY;
        } else {
          newY = this.getY();
        }
      } while (newX == this.getX() && newY == this.getY());
      this.setLocation(newX, newY);
    }
  }
  
  /**
   * eat
   * this method consumes a Plant parameter plant and adds as much of the plant's nutritional value as possible to the
   * human.
   * @param Plant plant
   * @return no return value
   */
  public void eat(Plant plant) {
    if (this.getAge() < MIN_REPRODUCTIVE_AGE) {
      this.setHealth(this.getHealth() + plant.getNutValue());
      if (this.getHealth() > this.getChildHPBound()) {
        this.setHealth(this.getChildHPBound());
      }
    } else {
      this.setHealth(this.getHealth() + plant.getNutValue());
      if (this.getHealth() > this.getMaxHP()) {
        this.setHealth(this.getMaxHP());
      }
    }
    plant.setHealth(0); // the plant is now dead
  }
  
  /**
   * procreate
   * this method computes human procreation with another Human person, passed in as an argument, sets the procreation
   * countdowns based on gender, and creates and returns a new child with a period of time for which they are not born
   * @param Human partner
   * @return Human child
   */
  public Human procreate(Human partner) { 
    this.setHealth(this.getHealth() - 3); // having sex takes energy, so reduce health
    partner.setHealth(this.getHealth() - 3);
    if (this.getGender() == 'f' && partner.getGender() == 'm') { // females have longer cooldowns than males
      this.setProcreateCooldown(20);
      partner.setProcreateCooldown(2);
      Human child = new Human(PREGNANCY_PERIOD, this, this.getMaxHP(), this.getMinHP());
      return child;
    } else {
      partner.setProcreateCooldown(20);
      this.setProcreateCooldown(2);
      Human child = new Human(PREGNANCY_PERIOD, partner, this.getMaxHP(), this.getMinHP());
      return child;
    }
  }
  
  // constructors
  
  /**
   * Human()
   * base constructor for this class, when no other information is given
   */
  Human() { 
    this.setMaxHP(100);
    this.setMinHP(20);
    this.setAge(0);
    this.setHealth(random.nextInt(31) + this.getMinHP());
    this.setChildHPBound(50);
    this.setLocation(0, 0);
    this.setProcreateCooldown(0);
    this.setGender(random.nextInt(2));
    this.setTimeToBirth(0);
  }
  
  /**
   * Human(int x, int y)
   * constructor for this class, spawning x and y location is given
   * used at the beginning of the sim
   */
  Human(int x, int y, int maxHP, int minHP) { 
    this.setMaxHP(maxHP);
    this.setMinHP(minHP);
    this.setAge(14);
    this.setHealth(random.nextInt(31) + this.getMinHP());
    this.setChildHPBound(50);
    this.setLocation(x, y);
    this.setProcreateCooldown(0);
    this.setGender(random.nextInt(2));
    this.setTimeToBirth(0);
    this.setEnergyToLive(1);
    
  }
  
  /**
   * Human()
   * constructor for this class, when a mother and time until birth is given
   * used when a child is created through procreation
   */
  Human(int timeToBirth, Human mother, int maxHP, int minHP) {
    this.setMaxHP(maxHP);
    this.setMinHP(minHP);
    this.setAge(0);
    this.setHealth(random.nextInt(31) + this.getMinHP());
    this.setChildHPBound(50);
    this.setMother(mother);
    this.setLocation(mother.getX(), mother.getY());
    this.setProcreateCooldown(0);
    this.setGender(random.nextInt(2));
    this.setTimeToBirth(timeToBirth);
    this.setEnergyToLive(1);
  }
  
}