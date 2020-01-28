/* Simulation.java
 * @version 1.0
 * @author Paula Yuan
 * @date Nov. 21, 2019
 * This class is the simulation that runs my cheerville town sim.
 */

class Simulation {
  public static void main(String[] args) {
    Town cheerville = new Town();
    cheerville.makeTown(); 
    cheerville.updateTown();    // run sim
  }
}