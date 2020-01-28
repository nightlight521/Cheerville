import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.lang.Math;

/* Graphics.java
 * This program draws the graphics for the Cheerville sim.
 * @version 2.0
 * @author Mr. Mangat, Paula Yuan
 * @date December 1st, 2019
 */


class GraphicsDisplay extends JFrame {
  
  // class variables
  private int maxX, maxY, GridToScreenRatio;
  private Living[][] matrix;
  private int[] coordinates;
  private Town town;
  private boolean needZombie;
  
  
  // methods: getters and setters
  
  /**
   * setMaxX
   * this method sets the int maximum x value of this display based on the given parameter
   * @param int maxX
   * @return no return value
   */
  public void setMaxX(int maxX) {
    this.maxX = maxX;
  }
  
  /**
   * setMaxY
   * this method sets the int maximum y value of this display based on the given parameter
   * @param int maxY
   * @return no return value
   */
  public void setMaxY(int maxY) {
    this.maxY = maxY;
  }
  
  /**
   * getCoordinates
   * this method returns the int[] coordinates of x and y spawn location for the next zombie
   * @return int[] coordinates
   */
  public int[] getCoordinates() {
    return this.coordinates;
  }
  
  /**
   * setGRidToScreenRatio
   * this method sets the int grid to screen ratio based on the int given parameter
   * @param int ratio
   * @return no return value
   */
  public void setGridToScreenRatio(int ratio) {
    this.GridToScreenRatio = ratio;
  }
  
  /**
   * setTown
   * this method sets the Town town based on the given Town parameter
   * @param Town town
   * @return no return value
   */
  public void setTown(Town town) {
    this.town = town;
  }
  
  /**
   * setMatrix
   * this method sets the Living[][] matrix to be drawn based ont the given Livin[][] parameter
   * @param Living[][] matrix
   * @return no return value
   */
  public void setMatrix(Living[][] matrix) {
    this.matrix = matrix;
  }
  
  /**
   * setZombieNeed
   * this method sets whether or not a zombie is needed based on the boolean parameter given
   * @param boolean need
   * @return no return value
   */
  public void setZombieNeed(boolean need) {
    this.needZombie = need;
  }
  
  /**
   * getZombieNeed
   * this method returns a boolean value -- whether or not a zombie is needed based
   * @return boolean needZombie
   */
  public boolean getZombieNeed() {
    return this.needZombie;
  }
  
  // other methods:
  /**
   * refresh
   * this method refreshes the display
   * @return no return value
   */
  public void refresh() { 
    this.repaint();
  }
  
  // Constructor
  GraphicsDisplay(String title, Living[][] matrix, Town town) {
    super(title);
    this.setMatrix(matrix);
    this.coordinates = new int[2];
    this.setTown(town);
    this.setMaxX(Toolkit.getDefaultToolkit().getScreenSize().width);
    this.setMaxY(Toolkit.getDefaultToolkit().getScreenSize().height);
    this.setGridToScreenRatio(maxY / (matrix.length + 2));  //ratio to fit in screen as square map
    
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
    
    this.add(new GraphicsPanel());
    
    this.setVisible(true);
  }
  
  //Inner Class 
  class GraphicsPanel extends JPanel {
    
    GraphicsPanel() { 
      addMouseListener(new GraphicsPanelMouseListener());
    }
    
    public void paintComponent(Graphics g) {        
      super.repaint();
      
      setDoubleBuffered(true); 
      
      // display weather
      g.drawString("Season: " + town.getClimate().getSeason(), 700, 50);
      g.drawString("Is it raining: " + town.getClimate().getRaining(), 900, 50);
      g.drawString("Temperature: " + town.getClimate().getTemperature(), 1100, 50);
      g.drawString("Wind direction: " + town.getClimate().getWindDir(), 700, 25);
      g.drawString("Wind strength: " + town.getClimate().getWindStrength(), 900, 25);
      
      // display turns and population
      g.drawString("Turns passed: " + town.getTurns(), 1100, 25);
      g.drawString("Human population: " + town.count("human"), 700, 75);
      g.drawString("Plant population: " + town.count("plant"), 900, 75);
      g.drawString("Zombie population: " + town.count("zombie"), 1100, 75);
      
      // display legend
      g.drawString("KEY", 1135, 100);
      g.drawRect(1100, 107, 100, 140);
      g.drawString("Man: ", 1110, 125);
      g.setColor(Color.BLUE);
      g.fillRect(1170, 115, 11, 11);
      g.setColor(Color.BLACK);
      g.drawString("Woman: ", 1110, 145);
      g.setColor(Color.MAGENTA);
      g.fillRect(1170, 135, 11, 11);
      g.setColor(Color.BLACK);
      g.drawString("Boy: ", 1110, 165);
      g.setColor(Color.CYAN);
      g.fillRect(1170, 155, 11, 11);
      g.setColor(Color.BLACK);
      g.drawString("Girl: ", 1110, 185);
      g.setColor(Color.PINK);
      g.fillRect(1170, 175, 11, 11);
      g.setColor(Color.BLACK);
      g.drawString("Plant: ", 1110, 205);
      g.setColor(Color.GREEN);
      g.fillRect(1170, 195, 11, 11);
      g.setColor(Color.BLACK);
      g.drawString("Zombie: ", 1110, 225);
      g.setColor(Color.RED);
      g.fillRect(1170, 215, 11, 11);
      
      // calculate population percentages
      double total = town.count("human") + town.count("zombie") + town.count("plant");
      double humanPercent = town.count("human")/total;
      double zombiePercent = town.count("zombie")/total;
      double plantPercent = town.count("plant")/total;
      
      // draw graph
      g.setColor(Color.BLACK);
      g.drawString("%Human", 690, 520);
      g.drawString("%Plant", 790, 520);
      g.drawString("%Zombie", 890, 520);
      g.drawString("%Type of all Living Creatures", 740, 550);
      g.setColor(Color.ORANGE);
      g.fillRect(700, 100, 40, (int) Math.round(humanPercent*400));
      g.setColor(Color.BLACK);
      g.drawRect(700, 100, 40, 400);
      g.setColor(Color.GREEN);
      g.fillRect(800, 100, 40, (int) Math.round(plantPercent*400));
      g.setColor(Color.BLACK);
      g.drawRect(800, 100, 40, 400);
      g.setColor(Color.RED);
      g.fillRect(900, 100, 40, (int) Math.round(zombiePercent*400));
      g.setColor(Color.BLACK);
      g.drawRect(900, 100, 40, 400);
      
      
      // colour squares based on the creature / entity that is in that square
      for (int i = 0; i < matrix.length; i++)  { 
        for (int j = 0; j < matrix[0].length; j++)  { 
          
          if (matrix[i][j] == null) {
            g.setColor(Color.WHITE);
          } else if (matrix[i][j] instanceof Zombie) {
            g.setColor(Color.RED);
          } else if (matrix[i][j] instanceof Plant) {
            g.setColor(Color.GREEN);
          } else if (matrix[i][j] instanceof Human) {
            if (((Human)matrix[i][j]).getGender() == 'f') {
              if (((Human)matrix[i][j]).getAge() < ((Human)matrix[i][j]).MIN_REPRODUCTIVE_AGE) {
                g.setColor(Color.PINK);
              } else {
                g.setColor(Color.MAGENTA);
              }
            } else if (((Human)matrix[i][j]).getAge() < ((Human)matrix[i][j]).MIN_REPRODUCTIVE_AGE) {
              g.setColor(Color.CYAN);
            } else {
              g.setColor(Color.BLUE);
            }
          }
          
          g.fillRect(j*GridToScreenRatio, i*GridToScreenRatio, GridToScreenRatio, GridToScreenRatio);
          g.setColor(Color.BLACK);
          g.drawRect(j*GridToScreenRatio, i*GridToScreenRatio, GridToScreenRatio, GridToScreenRatio);
        }
      }
      
      
    }
  }
  
  
  //Mouse Listener 
  class GraphicsPanelMouseListener implements MouseListener{ 
    
    //Mouse Listener Stuff
    /**
     * mousePressed
     * this method is called when the mouse is pressed. if the mouse press happens within the map, the location is 
     * stored as x, y in an int array as the location where a zombie needs to be spawned, and the need for a zombie
     * is set to true.
     * @param MouseEvent e
     * @return no return value
     */
    public void mousePressed(MouseEvent e) {
      if (e.getPoint().x/GridToScreenRatio < 25 && GridToScreenRatio > 0 && e.getPoint().y/GridToScreenRatio < 25 && 
          e.getPoint().y/GridToScreenRatio >= 0) {
        coordinates[0] = e.getPoint().x/GridToScreenRatio;
        coordinates[1] = e.getPoint().y/GridToScreenRatio;
        setZombieNeed(true);
      }
    }
    
    /**
     * mouseReleased
     * this method is called when the mouse is released.
     * @param MouseEvent e
     * @return no return value
     */
    public void mouseReleased(MouseEvent e) {
    }
    
    /**
     * mouseEnetered
     * this method is called when the cursor enters the panel
     * @param MouseEvent e
     * @return no return value
     */
    public void mouseEntered(MouseEvent e) {
    }
    
    /**
     * mouseReleased
     * this method is called when the cursor exits the panel 
     * @param MouseEvent e
     * @return no return value
     */
    public void mouseExited(MouseEvent e) {
    }
    
    /**
     * mouseReleased
     * this method is called when the mouse is clicked
     * @param MouseEvent e
     * @return no return value
     */
    public void mouseClicked(MouseEvent e) {
    }
    
  }
  
}