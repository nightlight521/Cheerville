import java.util.Random;

/* Zombie.java
 * @version 1.0
 * @author Paula Yuan
 * @date Nov. 21, 2019
 * This class is a blueprint for all the zombies spawned in Cheerville.
 */

public class Zombie extends Living implements Moveable, CanEat<Human>{
  
  Random random = new Random(); // new random number generator
  
  // Methods
  
  /**
   * eat
   * this method eats a Human human, taken as an argument, adding as much of the human's hp to its hp as possible
   * @param Human human
   * @return no return value
   */
  public void eat(Human human) {
    this.setHealth(this.getHealth() + human.getHealth());
    if (this.getHealth() > this.getMaxHP()) {
      this.setHealth(this.getMaxHP()); 
    }
    human.setHealth(0);
  }
  
  /**
   * infectHuman
   * this method infects a Human human, taken as an argument, turning it into a zombie with its stats, and returns that
   * new zombie.
   * @param Human human
   * @return Zombie infected
   */
  public Zombie infectHuman(Human human) {
    Zombie infected = new Zombie(this.getX(), this.getY(), human.getHealth());
    human.setHealth(0);
    return infected;
  }
  
  /**
   * trample
   * this method tramples a Plant plant, taken as an argument, and that plant dies
   * @param Plant plant
   * @return no return value
   */
  public void trample(Plant plant) {
    plant.setHealth(0);
  }
  
  /**
   * move 
   * this method moves the zombie, same as a human -- using a pseudorandom number and checking if the new space would be
   * valid
   * @return no return value
   */
  public void move() {
    // generates random x or y change using a random integer betwen 0 to 2 inclusive, then minus 1 for accurate movement
    int moveOrNot = random.nextInt(2);
    if (moveOrNot == 1) {
      int deltaX, deltaY;
      int newX, newY;
      do {
        deltaX = random.nextInt(3) - 1;
        deltaY = random.nextInt(3) - 1;
        if (this.getX() + deltaX <= 24 && this.getX() + deltaX >= 0) {
          newX = this.getX()+deltaX;
        } else {
          newX = this.getX();
        }
        if (this.getY() + deltaY <= 24 && this.getY() + deltaY >= 0) {
          newY = this.getY()+deltaY;
        } else {
          newY = this.getY();
        }
      } while (newX == this.getX() && newY == this.getY());
      this.setLocation(newX, newY);
    }
  }
  
  // Constructors
  
  /**
   * Zombie()
   * constructor for this class when no information is given, should never be used
   */
  Zombie() {
    this.setMaxHP(100);
    this.setHealth(random.nextInt(101));
    this.setLocation(0, 0);
  }
  
  /**
   * Zombie(int x, int y)
   * constructor for this class when int x and y locations are given, used when zombies are spawned by mouse click
   */
  Zombie(int x, int y) {
    this.setMaxHP(100);
    this.setHealth(random.nextInt(101));
    this.setLocation(x, y);
  }
  
  
  /**
   * Zombie(int x, int y, int hp)
   * constructor for this class when int x, y, and hp are given, used when zombies are spawned by infection
   */
  Zombie(int x, int y, int hp) {
    this.setMaxHP(100);
    this.setHealth(hp);
    this.setLocation(x, y);
  }
  
}