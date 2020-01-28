import java.util.Random; 
import java.util.Scanner;
import java.util.ArrayList;                                                          

/* Town.java
 * @version 3.0
 * @author Paula Yuan
 * @date Dec. 5, 2019
 * This class is a blueprint for the entire town of Cheerville and all actions associated with it.
 */

class Town {            
  Scanner input = new Scanner(System.in);
  Random random = new Random(); // new random number generator                   
  
  // class variables                                        
  private Living[][] map;          
  private ArrayList<Living> beings;      
  private ArrayList<Human> unborn;
  private int turns;                                                             
  private GraphicsDisplay display;
  private Climate climate;
  private boolean justDied;
  
  // methods: getters and setters  
  
  /**
   * getClimate
   * This method gets the climate object.
   * @return climate object for this class
   */
  public Climate getClimate() {
    return this.climate;
  }
  
  /**
   * getJustDied
   * This method gets the justDied variable, true or false based on if something has just died.
   * @return justDied value.
   */
  public Boolean getJustDied() {
    return this.justDied;
  }
  
  /**
   * setJustDied
   * This method sets the boolean justDied value
   * @param boolean justDied
   * @return no return value
   */
  public void setJustDied(boolean justDied) {
    this.justDied = justDied;
  }
  
  /**
   * setTurns
   * This method sets the amount of turns take.
   * @param int turns
   * @return no return value
   */
  public void setTurns(int turns) {
    this.turns = turns;
  }
  
  /**
   * getTurns
   * This method gets the amount of turns taken.
   * @return int turns variable, being the amount of turns.
   */
  public int getTurns() {
    return this.turns;
  }
  
  // other methods:
  /**
   * spawnPlantsRand
   * This method spawns more plants randomly on empty spaces if there are less than 30 plants in the town. No return
   * value, is void.
   */
  public void spawnPlantsRand() {
    int plantCount = 0;
    int spawnX, spawnY;
    int minHP = 0;
    int maxHP = 0;
    int minNutValue = 0;
    for (int i = 0; i < this.beings.size(); i++) {
      if (this.beings.get(i) instanceof Plant) {
        if (plantCount == 0) {
          minHP = this.beings.get(i).getMinHP();
          maxHP = this.beings.get(i).getMaxHP();
          minNutValue = ((Plant)this.beings.get(i)).getNutValue();
        }
        plantCount++;
      }
    }
    if (plantCount < 30) {
      do {
        spawnX = random.nextInt(25);
        spawnY = random.nextInt(25);
      } while (!isEmptySpace(spawnX, spawnY));
      Plant plant = new Plant(spawnX, spawnY, maxHP, minHP, minNutValue);
      this.beings.add(plant);
    }
  }
  
  /**
   * spawn
   * This method creates creature objects, as decided by random roll of a ten-sided die. Objects are spawned and
   * added to the list of living beings based on the die roll. Parameters determining the number below which a plant or
   * a human will spawn. No return value, is void.
   * @param int maxPlantRoll, int maxHumanRoll
   */
  public void spawn(int maxPlantRoll, int maxHumanRoll, int maxHumanHP, int minHumanHP, int maxPlantHP, int minPlantHP,
                   int minPlantNutValue) {                                                          
    int dieRoll = random.nextInt(10);
    int spawnX = random.nextInt(25);
    int spawnY = random.nextInt(25);
    
    // spawn and assign objects according to the die roll
    if (dieRoll < maxPlantRoll) {
      Plant plant = new Plant(spawnX, spawnY, maxPlantHP, minPlantHP, minPlantNutValue);
      this.beings.add(plant);
      this.map[spawnY][spawnX] = plant;
    } else if (dieRoll < maxHumanRoll) {
      Human human = new Human(spawnX, spawnY, maxHumanHP, minHumanHP);
      this.beings.add(human);
      this.map[spawnY][spawnX] = human;
    }
  }
  
  /**
   * makeTown
   * This method creates the town by spawning all necessary items using the spawn method.
   * @return no return value.
   */
  public void makeTown() {
    
    int maxHumanHP, minHumanHP;
    int maxPlantHP, minPlantHP, minPlantNutValue;
    
    // take in and process necessary input
    
    // spawning die roll values
    System.out.println("Spawning is done by random number generation from 0 to 9.");
    System.out.println("If no valid input is given, the default values of 6 and 7 respectively will be used.");
    System.out.println("Enter the number below which you want plants to spawn: ");
    int maxPlantRoll = input.nextInt();
    if (maxPlantRoll > 9 || maxPlantRoll < 0) {
      maxPlantRoll = 6;
    }
    System.out.println("Enter the number below which you want humans to spawn (plants come first, so if this number");
    System.out.println("is smaller than the last one, no humans will spawn:) ");
    int maxHumanRoll = input.nextInt();
    if (maxHumanRoll > 9 || maxHumanRoll < 0) {
      maxPlantRoll = 7;
    }
    
    // min, max HP values for plants and humans, and min nutritional values for plants
    System.out.println("If invalid values given, default values of 100 max human HP and 20 min human HP will be used");
    System.out.println("where possible. For plants, the default values are 25 max HP and 5 min HP, and 0 min");
    System.out.println("nutritional value, used when possible if invalid values are given.");
    System.out.println("Enter min human health (must be above 0): ");
    minHumanHP = input.nextInt();
    if (minHumanHP <= 0) {
      minHumanHP = 20;
    }
    System.out.println("Enter max human health (must be above min HP): ");
    maxHumanHP = input.nextInt();
    if (maxHumanHP <= minHumanHP) {
      if (minHumanHP < 100) {
        maxHumanHP = 100;
      } else {
        maxHumanHP = minHumanHP + 10;
      }
    }
    System.out.println("Enter min plant health (must be above 0): ");
    minPlantHP = input.nextInt();
    if (minPlantHP <= 0) {
      minPlantHP = 5;
    }
    System.out.println("Enter max plant health (must be above min HP): ");
    maxPlantHP = input.nextInt();
    if (maxPlantHP <= minPlantHP) {
      if (minPlantHP < 25) {
        maxPlantHP = 25;
      } else {
        maxPlantHP = minPlantHP + 10;
      } 
    }
    System.out.println("Enter min plant nutritional value (must be above or equal to 0): ");
    minPlantNutValue = input.nextInt();
    if (minPlantNutValue < 0) {
      minPlantNutValue = 0;
    }
    
    // spawn and assign spaces for all the objects created
    for (int i = 0; i < 625; i++) {
      this.spawn(maxPlantRoll, maxHumanRoll, maxHumanHP, minHumanHP, maxPlantHP, minPlantHP, minPlantNutValue);
    }
  }
  
  /**
   * findIndex
   * Given a living thing object of class Living, this method returns the index of that object if it is in the ArrayList
   * of living things, otherwise returns -1.
   * @param object Living thing
   * @return the index or -1 based on the success of the algorithm
   */
  public int findIndex(Living thing) {
    for (int i = 0; i < this.beings.size(); i++) {
      if (thing == this.beings.get(i)) {
        return i;
      }
    }
    return -1;
  }
  
  /**
   * findView
   * Given an object of Living class and its index in the ArrayLIst of living beings, this method finds the part of the
   * world that this object can see (all adjacent -- including diagonal -- spaces,) and store and return them as an 
   * ArrayList of Living things.
   * @param object Living thing, int the index of that thing
   * @return an ArrayList<Living> view, which is the view of that object
   */
  public ArrayList<Living> findView(Living thing, int thingIndex) {
    ArrayList<Living> view = new ArrayList<Living>();
    for (int i = 0; i < this.beings.size(); i++) {
      if (this.beings.get(i).getX() - thing.getX() <= 1 && this.beings.get(i).getX() - thing.getX() >= -1 && 
          this.beings.get(i).getY() - thing.getY() <= 1 && this.beings.get(i).getY() - thing.getY() >= -1 && 
          i != thingIndex) {
        view.add(this.beings.get(i));
      }
    }
    return view;
  }
  
  /**
   * isEmptySpace
   * Given an x, y, coordinate, this method determines whether or not it is an empty space and returns a boolean value
   * based on that assessment.
   * @param int x, int y
   * @return true or false based on the algorithm
   */
  public boolean isEmptySpace(int x, int y) {
    for (int i = 0; i < this.beings.size(); i++) {
      if (this.beings.get(i).getX() == x && this.beings.get(i).getY() == y) {
        return false;
      }
    }
    if (x > 24 || x < 0 || y > 24 || y < 0) {  // check if the space is within the bounds of the map
      return false;
    }
    return true;
  }
  
  /**
   * spawnZombie
   * Given an x, y, coordinate, this method spawns a zombie in the corresponding location and adds it to the list of
   * all living beings.
   * @param int spawnX, int spawnY
   * @return no return value
   */
  public void spawnZombie(int spawnX, int spawnY) {
    Zombie zombie = new Zombie(spawnX, spawnY);
    this.beings.add(zombie);
  }
  
  /**
   * updateGraphics
   * This method refreshes the graphics display.
   */
  public void updateGraphics() {
    this.display.refresh();      
    
    //Small delay
    try{ Thread.sleep(100); }catch(Exception e) {};
    this.updateMap();
  }
  
  /**
   * clearMap
   * this method clears the map by setting all values to null
   * @return no return value
   */
  public void clearMap() {
    for (int i = 0; i < this.map.length; i++) {
      for (int j = 0; j < this.map[i].length; j++) {
        this.map[i][j] = null;
      }
    }
  }
  
  /**
   * updateMap
   * This method updates the map by clearing it and re-filling it according to the current list of all living beings.
   * @return no return value
   */
  public void updateMap() {
    this.clearMap();
    for (int i = 0; i < this.beings.size(); i++) {
      this.map[beings.get(i).getY()][beings.get(i).getX()] = this.beings.get(i);
    }
  }
  
  /**
   * count
   * Given a string that determines what type of object we're counting, this method counts the amount of beings of that 
   * type there are that are currently living and returns that value.
   * @param String type
   * @returns int amount of beings of that type
   */
  public int count(String type) {
    int count = 0;
    if (type.equals("human")) {
      for (int i = 0; i < this.beings.size(); i++) {
        if (this.beings.get(i) instanceof Human) {
          count++;
        }
      }
    } else if (type.equals("zombie")) {
      for (int i = 0; i < this.beings.size(); i++) {
        if (this.beings.get(i) instanceof Zombie) {
          count++;
        }
      }
    } else if (type.equals("plant")) {
      for (int i = 0; i < this.beings.size(); i++) {
        if (this.beings.get(i) instanceof Plant) {
          count++;
        }
      }
    }
    return count;
  }
  
  /**
   * updatePlant
   * Given a plant and its view, this method grows the plant accordingly and spawns new plants as dictated by plant  
   * procreation method.
   * @param Plant object plant, ArrayList<Living> view being the view of the plant
   * @return no return value
   */
  public void updatePlant(Plant plant, ArrayList<Living> view) {
    plant.grow(this.climate.getRaining());
    int nearbyPlants = 0;
    int dieRoll = random.nextInt(6);
    for (int i = 0; i < view.size(); i++) {
      if (view.get(i) instanceof Plant) {
        nearbyPlants++;
      }
    }
    if (this.climate.getRaining() && this.checkNearEmpty(plant)) {
      Plant newPlant = this.plantProcreate(plant);
      this.beings.add(newPlant);
    } else if (dieRoll < nearbyPlants && dieRoll % 2 == 0 && this.checkNearEmpty(plant)) { // dieRoll % 2 to limit plant prowess
      Plant newPlant = this.plantProcreate(plant);
      this.beings.add(newPlant);
    }
  }
  
  /**
   * plantProcreate
   * Given a Plant object, this method procreates, taking into consideration the existence of nearby empty spaces, wind  
   * strength, whether or not its raining, and the wind direction. Procreates by spawning a new plant in a spawn 
   * location. Returns the new plant.
   * @param Plant plant
   * @return the new plant, of type Plant
   */
  public Plant plantProcreate(Plant plant) {
    int deltaX, deltaY;
    int dieRoll = random.nextInt(10);
    int xBias = 0;
    int yBias = 0;
    
    // change direction that plants are spawning in given the direction of the wind
    if (this.climate.getWindDir() == 'N') {
      yBias = -1;
    } else if (this.climate.getWindDir() == 'S') {
      yBias = 1;  
    } else if (this.climate.getWindDir() == 'E') {
      xBias = 1;
    } else if (this.climate.getWindDir() == 'W') {
      xBias = -1;
    }
    
    // find a spawning x, y location until there's one that is valid
    do {
      deltaX = random.nextInt(3) - 1;
      deltaY = random.nextInt(3) - 1;
      
      // spawning location moves different amounts based on wind strength
      if (1 < this.climate.getWindStrength() && this.climate.getWindStrength() < 4) {
        if (dieRoll < 7 && isEmptySpace(plant.getX() + deltaX + xBias, plant.getY() + yBias)) {
          deltaX += xBias;
          deltaY += yBias;
        } else if (dieRoll == 9 && isEmptySpace(plant.getX() + 2 * xBias, plant.getY() + 2 * yBias)) {
          deltaX += 2 * xBias;
          deltaY += 2 * yBias;
        }
      } else if (3 < this.climate.getWindStrength() && this.climate.getWindStrength() < 6) {
        if (dieRoll < 7 && isEmptySpace(plant.getX() + 2 * xBias, plant.getY() + 2 * yBias)) {
          deltaX += 2 * xBias;
          deltaY += 2 * yBias;
        } else if (dieRoll < 9 && isEmptySpace(plant.getX() + xBias, plant.getY() + yBias)) {
          deltaX += xBias;
          deltaY += yBias;
        }
      }
    } while ((deltaX == 0 && deltaY == 0) || !this.isEmptySpace(plant.getX() + deltaX, plant.getY() + deltaY));
    Plant newPlant = new Plant(plant.getX() + deltaX, plant.getY() + deltaY, plant.getMaxHP(), plant.getMinHP(), 
                               plant.getMinNutValue());
    return newPlant;
  }
  
  /**
   * updateHuman
   * Given a human person, this method grows that person and updates the amount of energy required for them to live, as  
   * well as decrements the procreation cooldown as necessary.
   * @param Human person.
   * @return no return value
   */
  public void updateHuman(Human person) {
    person.grow();
    person.updateEnergyToLive();
    if (person.getProcreateCooldown() > 0) {
      person.setProcreateCooldown(person.getProcreateCooldown() - 1);
    }
  }
  
  /**
   * checkNearEmpty
   * given a Living thing, this method checks if there is an empty space in the adjacent spaces (including diagonals)
   * @param Living thing
   * @return true or false based on the success of the algorithm
   */
  public boolean checkNearEmpty(Living thing) {
    if (isEmptySpace(thing.getX() + 1, thing.getY()) || isEmptySpace(thing.getX() + 1, thing.getY() + 1) ||
        isEmptySpace(thing.getX() + 1, thing.getY() - 1) || isEmptySpace(thing.getX(), thing.getY() + 1) || 
        isEmptySpace(thing.getX(), thing.getY() - 1) ||isEmptySpace(thing.getX() - 1, thing.getY()) || 
        isEmptySpace(thing.getX() - 1, thing.getY() + 1) || isEmptySpace(thing.getX() - 1, thing.getY() - 1)) {
      return true;
    }
    return false;
  }
  
  /**
   * updateMovement
   * given a Living thing, this method updates its movement
   * @param Living thing
   * @return no return value
   */
  public void updateMovement(Living thing) {
    if (thing instanceof Human) {
      ((Human)thing).move();
      thing.setHealth(thing.getHealth() - 1); // it takes energy to move, so decrement health of the living being
    } else if (thing instanceof Zombie) {
      ((Zombie)thing).move();
      thing.setHealth(thing.getHealth() - 1);
    }
  }
  
  /**
   * setDead
   * given a Living thing, and its index in the list of beings, this method sets it as dead and remove it from the list 
   * of beings
   * @param Living thing, int thingIndex
   * @return no return value
   */
  public void setDead(Living thing, int thingIndex) { 
    this.setJustDied(true);
    thing = null;
    this.beings.remove(thingIndex);
  }
  
  /**
   * processCollision
   * given a Living collider, a Living collidee, an int colliderIndex and an int collideeIndex, this method processes 
   * the collision that occurs based on what type of Living thing they are respectively and update statuses respectively.
   * @param Living collider, Living collidee, itn colliderIndex, int collideeIndex
   * @return no return value
   */
  public void processCollision(Living collider, Living collidee, int colliderIndex, int collideeIndex) {
    if (collider instanceof Human) {     // human collider interactions
      if (collidee instanceof Human) {
        if (((Human)collider).validateProcreation((Human)collidee)) {
          Human child = ((Human)collider).procreate((Human)collidee);
          this.unborn.add(child);
        }
      } else if (collidee instanceof Zombie) {
        if (collidee.getHealth() > collider.getHealth()) {
          ((Zombie)collidee).eat((Human)collider);
        } else {
          Zombie infected = ((Zombie)collidee).infectHuman((Human)collider); 
          this.beings.add(infected);
        }
        setDead(collider, colliderIndex);
      } else if (collidee instanceof Plant) {
        ((Human)collider).eat((Plant)collidee);
        setDead(collidee, collideeIndex);
      }
    } else if (collider instanceof Zombie) { // zombie collider interactions
      if (collidee instanceof Human) {
        if (collider.getHealth() > collidee.getHealth()) {
          ((Zombie)collider).eat((Human)collidee);
        } else {
          Zombie infected = ((Zombie)collider).infectHuman((Human)collidee);
          this.beings.add(infected);
        }
      } else if (collidee instanceof Plant) {
        ((Zombie)collider).trample((Plant)collidee);
      }
      setDead(collidee, collideeIndex);
    } else if (collider instanceof Plant) { // plant collider interactions
      if (collidee instanceof Human) {
        ((Human)collidee).eat((Plant)collider);
      } else if (collidee instanceof Zombie) {
        ((Zombie)collidee).trample((Plant)collider);
      }
      setDead(collider, colliderIndex);
    }
  }
  
  /**
   * updateTown
   * This method updates the entire town, changing the climate, processing collisions, changing health values, and 
   * everything else related to running the sim.
   * @return no return value
   */
  public void updateTown() {
    do {
      // spawn Zombies as needed with given mouse input
      if (display.getZombieNeed()) { 
        this.spawnZombie(display.getCoordinates()[0], display.getCoordinates()[1]);
        display.setZombieNeed(false);
      }
      
      // spawn extra plants if necessary
      this.spawnPlantsRand();
      
      // update weather
      if (this.getTurns() % 20 == 0) {
        this.climate.changeSeason();
        this.climate.setClimate();
      }
      this.climate.setRaining();
      this.climate.setTemperature();
      this.climate.setWindStrength(random.nextInt(6));
      this.climate.setWindDir();
      
      // update unborn children
      for (int i = this.unborn.size() - 1; i >= 0; i--) {
        Human current = this.unborn.get(i);
        if (current.getMother().isLiving()) {
          current.setLocation(current.getMother().getX(), current.getMother().getY());
          current.setTimeToBirth(current.getTimeToBirth()-1);
          if (current.getTimeToBirth() == 0) {
            this.beings.add(current);
            this.unborn.remove(i);
          }
        } else {
          this.unborn.remove(i);
        }
      }
      
      // update already born beings
      for (int i = this.beings.size() - 1; i >= 0; i--) {
        this.setJustDied(false);
        Living current = this.beings.get(i);
        if (!current.isLiving()) {
          this.setDead(current, i);                                
        }
        if (current.isLiving()) {
          ArrayList<Living> view = this.findView(current, i);
          if (current instanceof Human) { 
            this.updateHuman((Human)current);
          }
          if (current instanceof Plant) {
            this.updatePlant((Plant)current, view);
          }
          this.updateMovement(current);
          if (current.collide(view) != null) {
            this.processCollision(current, current.collide(view), i, this.findIndex(current.collide(view)));
          } 
          
          // update HP
          if (current instanceof Plant) {
            current.setHealth(current.getHealth() - 1);
            if (this.climate.getTemperature() > 25) { // decrement health more if it's too hot or too cold
              current.setHealth(current.getHealth() - (this.climate.getTemperature() - 25)); 
            } else if (this.climate.getTemperature() < 5) {
              current.setHealth(current.getHealth() - (5 - this.climate.getTemperature()));
            } else if (current instanceof Human) {
              current.setHealth(current.getHealth() - ((Human)current).getEnergyToLive());
              if (this.climate.getTemperature() > 20 || this.climate.getTemperature() < 10) {
                current.setHealth(current.getHealth() - 3);
              } 
            } else {
              current.setHealth(current.getHealth() - 1);
            }  
          }
          // check to see if anything died from the above updates that wasn't dead already
          if (!this.getJustDied() && !current.isLiving()) {
            this.setDead(current, i);                                
          }
        }
      }
      this.updateGraphics();
      this.setTurns(this.getTurns() + 1);
    } while (this.count("human") > 0); 
  }
  
  //Constructors
  
  /**
   * Town()
   * constructor for this class when no information is given
   */
  Town() {
    this.setTurns(0);
    this.map = new Living[25][25];
    this.beings = new ArrayList<Living>();
    this.unborn = new ArrayList<Human>();
    System.out.println("Enter current season: 'Spring' or 'Summer' or 'Winter' or 'Fall'. If no valid input given,");
    System.out.println("spring wll be used as the default season.");
    String season = input.nextLine();
    if (season.equals("Spring") || season.equals("Summer") || season.equals("Fall") || season.equals("Winter")) {
      this.climate = new Climate(season);
    } else {
      this.climate = new Climate();
    }
    this.display = new GraphicsDisplay("running sim", this.map, this);
  } 
}