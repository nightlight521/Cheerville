import java.util.Random;

/* Climate.java
 * @version 1.0
 * @author Paula Yuan
 * @date Nov. 28, 2019
 * This class is a blueprint for the climate of Cheerville.
 */

class Climate {
  // class variables
  private int rainChance;
  private String season;
  private int temperature;
  private int minTemp;
  private int maxTemp;
  private int windStrength;
  private char windDirection;
  private boolean isRaining;
  
  Random random = new Random(); // new random number generator
  
  // methods: getters and setters:
  
  /**
   * getWindDir
   * this method returns the char direction of the wind (NSEW aka north eaast south west)
   * @return char windDirection
   */
  public char getWindDir() {
    return this.windDirection;
  }
  
  /**
   * setWindDir
   * this method sets the char direction of the wind (NSEW aka north eaast south west) using a pseudorandom number 
   * generator
   * @return no return value
   */
  public void setWindDir() {
    int dieRoll = random.nextInt(4);
    if (dieRoll == 0) {
      this.windDirection = 'N';
    } else if (dieRoll == 1) {
      this.windDirection = 'S';
    } else if (dieRoll == 2) {
      this.windDirection = 'E';
    } else if (dieRoll == 3) {
      this.windDirection = 'W';
    }
  }
  
  /**
   * getRaining
   * this method returns the boolean value of whether or not it's raining
   * @return boolean isRaining
   */
  public boolean getRaining() {
    return this.isRaining;
  }
  
  /**
   * setRaining
   * this method sets the boolean value of whether or not it's raining using a pseudorandom number generator and the
   * chance of rain
   * @return no return value
   */
  public void setRaining() {
    int dieRoll = random.nextInt(6);
    if (dieRoll < this.getRainChance()) {
      this.isRaining = true;
    } else {
      this.isRaining = false;
    }
  }
  
  /**
   * getWindStrength
   * this method returns the int strength of the wind
   * @return int windStrength
   */
  public int getWindStrength() {
    return this.windStrength;
  }
  
  /**
   * setWindStrength
   * this method sets the integer strength of the wind based on the given int parameter
   * @param int strength
   * @return no return value
   */
  public void setWindStrength(int strength) {
    this.windStrength = strength;
  }
  
  /**
   * getRainChance
   * this method returns the int chance of rain
   * @return int rainChance
   */
  public int getRainChance() {
    return this.rainChance;
  }
  
  /**
   * setRainChance
   * this method sets the int chance for rain given an int parameter
   * @param int chance
   * @return no return value
   */
  public void setRainChance(int chance) {
    this.rainChance = chance;
  }
  
  /**
   * getSeason
   * this method returns the String season (spring summer fall winter)
   * @return String season
   */
  public String getSeason() {
    return this.season;
  }
  
  /**
   * setSeason
   * this method sets the String season given a String parameter
   * @return no return value
   */
  public void setSeason(String season) {
    this.season = season;
  }
  
  /**
   * getTemperature
   * this method returns the int temperature
   * @return int temperature
   */
  public int getTemperature() {
    return this.temperature;
  }
  
  /**
   * setTemperature
   * this method sets the int temperature based on a pseudorandomly generated number and the minimum temperature
   * @return no return value
   */
  public void setTemperature() {
    this.temperature = random.nextInt(11) + this.getMinTemp();
  }
  
  /**
   * getMinTemp
   * this method returns int minimum temperature
   * @return int minTemp
   */
  public int getMinTemp() {
    return this.minTemp;
  }
  
  /**
   * setMinTemp
   * this method sets the minimum temperature based on the given int parameter
   * @param int temp
   * @return no return value
   */
  public void setMinTemp(int temp) {
    this.minTemp = temp;
  }
  
  /**
   * getMaxTemp
   * this method returns the int max temperature
   * @return int maxTemp
   */
  public int getMaxTemp() {
    return this.maxTemp;
  }
  
  /**
   * setMaxTemp
   * this method sets the int maximum temperature based on the given int parameter
   * @param int temp
   * @return no return value
   */
  public void setMaxTemp(int temp) {
    this.maxTemp = temp;
  }
  
  // other methods
  /**
   * changeSeason
   * this method changes the String season based on the current season and sets the climate accordingly
   * @return no return value
   */
  public void changeSeason() {
    if (this.getSeason().equals("Spring")) {
      this.setSeason("Summer");
    } else if (this.getSeason().equals("Summer")) {
      this.setSeason("Fall");
    } else if (this.getSeason().equals("Fall")) {
      this.setSeason("Winter");
    } else {
      this.setSeason("Spring");
    }
    this.setClimate();
  }
  
  /**
   * setClimate
   * this method sets the climate (max and min temp, chance of rain,) based on the current String season
   * @return no return value
   */
  public void setClimate() {
    if (this.getSeason().equals("Spring")) {
      this.setMaxTemp(20);
      this.setMinTemp(10);
      this.setRainChance(4);
    } else if (this.getSeason().equals("Summer")) {
      this.setMaxTemp(30);
      this.setMinTemp(20);
      this.setRainChance(3);
    } else if (this.getSeason().equals("Fall")) {
      this.setMaxTemp(20);
      this.setMinTemp(10);
      this.setRainChance(3);
    } else if (this.getSeason().equals("Winter")) {
      this.setMaxTemp(10);
      this.setMinTemp(0);
      this.setRainChance(2);
    }
  }
  
  // constructors
  /**
   * Climate()
   * constructor for this class when no other information given
   * used in the sim if invalid input is entered when creating the town
   */
  Climate() {
    this.setSeason("Spring");
    this.setClimate();
    this.setTemperature();
    this.setWindStrength(0);
    this.setWindDir();
    this.setRaining();
  }
  
   /**
   * Climate()
   * constructor for this class given the current season
   */
  Climate(String season) {
    this.setSeason(season);
    this.setClimate();
    this.setTemperature();
    this.setWindStrength(0);
    this.setWindDir();
    this.setRaining();
  }
}