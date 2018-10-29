import java.io.*;
import java.util.ArrayList;

/*
 * The Game class is the main class for running the console version of the Game. We did not use the Plant class or 
 * Sunflower class for this Milestone because this is a console version of the Game. Technically, in the console
 * version the Game class runs the Whole Game. But, keeping the principles of cohesion and coupling in mind we have created a x
 *zombie class for which we are using the basic method. This Game is basically designed as 1D game. And, the Zombie dies as soon 
 *as you shoot the zombie(with one shot).You initially start with 100 points, as you get the sun points you get 25 points with every sun.
 *
 *The Peashooters are used to shoot the Zombies and you can get each Peashooter for 150 sun points. 
 *As this the 1D version of the Game, the Sunflower brings with itself 150 points instead of the like in the real 
 *game the sunflower brings you extra suns which give you more points. As we don't care much about timing in this version of Game.
 *So, Everytime we get a SunFlower we get 150 sun points.
 *
 */
public class Game {
	private Parser parser; // creating a Parser instance
	Game PvZ; // creating the Game instance
	BasicZoombie zombie; // creating a BasicZoombie
	

	private static ArrayList<Zoombie> enemy; //Creating a Arraylist for Storing Zombies
	private static int sun= 50; // the game starts with 50 sun points
	private static int sunFlower = 0; // the Number of sunflower in the starting of the Game is 0
	private static int peaShooter = 0;// The Number of Peashooter in the Game starting are 0
    private static int yardMower = 3; // The Number of YardMowers that initially exist are 3
    private static int cherryBomb = 0; // the Number of cherryBomb are 0
    
	public Game() {
		PvZ.enemy = new ArrayList<Zoombie>(); // create a arraylist of zombies 
		parser = new Parser();  //initialize the parser object 
		
	}
	

	/*
	 *  This PrintWelcome method generated A lot of welcome statements for the console version of the Game
	 *  
	 *  @author Amandeep Singh
	 */
	public void printWelcome() {
		System.out.println();
        System.out.println("->Welcome to the World of PlantsVsZombies!");
        System.out.println("->Welcome to Adventure Level One.");
        System.out.println("Type 'Sunpoints' if you want to gather the falling Sun Points.");
        System.out.println("Type 'DropPeashooter' if you want to a Pea shooter on the grass.");
        System.out.println("Type 'DropSunflower' if you want a Sunflower on the grass.");
        System.out.println("Type 'Shoot' if you want to shoot a Zombie with Pea Shooter.");
        System.out.println("Type 'Quit' if you want to quit.");
        
        System.out.println();
	}
	/*
	 * The gainSun methods adds the falling sunpoints to your account of currPoints.
	 * And show you the Present status of the Game that a how many Points you have.
	 * 
	 * @author Zoha Mehdi
	 */
	
	public void gainSun() {
	    	sun += 25; // every falling sunPoint is 25 points 
	    	currPoints();	// current number of sunpoints we have in the Game
	}
	
	/*
	 * The createEnemy method deals with the putting the zombies in the Enemy Arraylist
	 * For the First level of the Game we just 6 Zombies on the Grass right now that we have to kill.
	 * 
	 * @author Zoha Mehdi
	 */
	public void createEnemy() {

		zombie = new BasicZoombie();
		for(int i=0; i < 6;i++) {
			Game.enemy.add(zombie);
		}
	}

	/*
	 * The ProcessCommand method deals with recognizing the Command and check if it a Valid command if yes 
	 * Follow the particular command.
	 * 
	 * @author Amandeep Singh
	 */
   private boolean processCommand(Command command) 
   {
       
     boolean wantToQuit = false; // set wantToQuit to false
     
     if(command.isUnknown()) {
           System.out.println("I don't know what you mean...");// if the Command is not a valid command print this
           return false;
       }

     String commandWord = command.getCommandWord(); 

       if (commandWord.equals("DropPeashooter")) { //Check if it the commands is "DropPeashooter". If yes, call the Peashooter method.
          peaShooter();
       }
       else if (commandWord.equals("DropSunflower")) { //Check if it the commands is "DropSunflower". If yes, call the Sunflower method.
          sunflower();
       }
       else if (commandWord.equals("Shoot")) { //Check if it the commands is "Shoot". If yes, call the Shoot method.
          shoot();
       }
       else if (commandWord.equals("Sunpoints")) { //Check if it the commands is "Sunpoints". If yes, call the gainSun method.
           gainSun();
        }
       
       else if (commandWord.equals("DropCherrybomb")) { //Check if it the commands is "DropCherrybomb". If yes, call the cherryBomb method.
           cherryBomb();
          
       }
       else if (commandWord.equals("Quit")) {//Check if it the commands is "Quit". If yes, call the Quit method.
    	   wantToQuit = quit(command);
       }
       else if(commandWord.equals("Replay")) {//Check if it the commands is "Replay". If yes, call the Replay method.
    	   replay();
       }
       // else command not recognised.
       return wantToQuit;
   }
   /*
    * The cherryBomb Method is a type of plant for this level one of the Game.
    * If dropped it kill all the Zombies present on the grass.
    * For us it will kill all the 6 Zombies and then we move to the next level.
    * 
    * @authr Amandeep Singh
    */
   private void cherryBomb() {
	   if(sun >= 150) {
  			cherryBomb += 1;
  			System.out.println("A CherryBomb has been dropped on the grass");
  			System.out.println(" ");
  			sun -=150;
  			System.out.println("Cherry Bomb bursted, All Zombies Dead! Move to level 2");
  		
  		}else {
  			System.out.println("Not enough sunpoints to Buy CherryBomb");
  			System.out.println(" ");
  			currPoints();
      }
   }
   
   /*
    * The Quit method if process if the Quit is typed in the console.
    * @author Amandeep Singh
    * 
    */
   
   private boolean quit(Command command) 
   {
       if(command.hasSecondWord()) {
           System.out.println("Quit what?");
           return false;
       }
       else {
    	   System.exit(0);
           return true;  // signal that we want to quit
       }
   }

   /*
    * The currPoints Method shows us how many points and plants we current have on the field.
    * 
    * @author Zoha Mehdi
    * 
    */
   public void currPoints() {
		System.out.println("      ---------------------------------------------" );
		System.out.println("           CURRENT STATUS OF THE GAME          " );
		
		System.out.println("           Current Sun points : "  + this.sun);
		System.out.println("           Current Pea Shooters on the grass : "  + this.peaShooter);
		System.out.println("           Current Sun Flowers on the grass : " + this.sunFlower);
		System.out.println("           Current Number of Zombies on the grass: " + enemy.size());
		System.out.println("      ----------------------------------------------" );
	}
  
   /*
    * The peashooter method drops a peashooter on the Grass an deducts 100 sunpoints for that
    * 
    * @Toluwalope Ajisola
    * 
    */
   public void peaShooter() {
       if(sun >= 100) {
   			peaShooter += 1;
   			System.out.println("A Pea Shooter has been dropped on the grass");
   			System.out.println(" ");
   			sun -=100;
   			currPoints();
   		}else {
   			
   			System.out.println("Not enough sunpoints");
   			System.out.println(" ");
   			currPoints();
   		
       }
    }
	   
   /*
    * The Sunflower method drops the Sunflower on the grass and gives us 150 sunpoints and deducts 50 
    * Buying the sunflower
    * 
    * @author Toluwalope Ajisola
    */
   
  public void sunflower() {
	   sunFlower += 1;
		sun -=50;
		System.out.println("A sunflower has been dropped on the grass");
		sun= sun + 100;
		currPoints();
	
  }
  
  /*
   * The Shoot deals with different conditions how we will win or lose the Game when we kill the Zombies on the Grass.
   * The Shoot method involves the moving of yardMower too.
   * Currently three yardMowers on the Field.
   * 
   * @author Amandeep Singh 
   * @author Zoha Mehdi
   * 
   */
  public void shoot() {
	 
	  if ((peaShooter > enemy.size() && enemy.size() > 0) && !(enemy.size()==0)) { // these conditions decide whether we win or lose the game
		  enemy.remove(0); //remove enemy
		  System.out.println("Zombie dead");
		  peaShooter -= 1; // decrease peaShooter
		  countZombie();
		  }  
	  else if(peaShooter >= 0 && yardMower>=0 && enemy.size()==0) {
		  System.out.println("You win the game. Move to Level 2"); // when all the zombies are killed(zero) and we are still left with peashooter and yardmower
		  // We win the game
	  }
	  else if ((peaShooter <=3 && peaShooter >0) && yardMower ==3) {
		    while(peaShooter >= 1) {
		    	enemy.remove(0);
				System.out.println("Zombie dead");
				peaShooter -=1;
				countZombie();
				System.out.println(" ");
		    }

	  }
	  else if((peaShooter ==0 && yardMower > 0) && enemy.size() > 0) { // 

		    enemy.remove(0);
			System.out.println("Zombie dead");
			yardMower -= 1;
			countZombie();
			System.out.println(" ");
	  }else if ((peaShooter >3 && peaShooter <=6) && yardMower > 0) {
				enemy.remove(0);
				System.out.println("Zombie dead");
				peaShooter -=1;
				countZombie();
				System.out.println(" ");
	  } else if ((peaShooter <=2 )) {
		  while(peaShooter > 0) {
			  enemy.remove(0);
				System.out.println("Zombie dead");
				peaShooter -=1;
				countZombie();
				System.out.println(" ");
		  }
		  while(yardMower == 3 && peaShooter == 0) {
			  enemy.remove(0);
				System.out.println("Zombie dead");
				yardMower -=1;
				countZombie();
				System.out.println(" ");
		  }
		  loseGame();

	  }
	  
	  else if ((peaShooter ==0 && yardMower ==0) && enemy.size() >0) 
		  loseGame(); // other we lose the Game
	  }
  
  /*
   * The lose game method prints that we lost the game and gives us an option if we want to replay or quit the game
   */

    public void loseGame() {
    	System.out.println("Oops! You lost the Game");
    	System.out.println("      xxxxxxxxxxxxxxxxxx");
    	System.out.println("Do you wish to 'Replay' OR 'Quit'");
    	System.out.println("      xxxxxxxxxxxxxxxxxx");
    	boolean finished = false;// is is gonna take either of those and give a chance if we want to quit or replay
        while (! finished) {
            Command command = parser.getCommand();// recognizing command
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /*
     * The countZombie method is a being used as helper method in the shoot method above
     * This method returns the status of plants and zombies present on the field at the current time
     */
  public void countZombie() {
	  System.out.println("      -------------------------------------------------" );
	  System.out.println("            Current Number of Zombies in the Grass:  " + enemy.size());  // number of zombies
	  System.out.println("            Current Number of Peashooter in the Grass:  " + this.peaShooter); // number of peashooters
	  System.out.println("            Current Number of yardMower in the Grass:" + this.yardMower); //number of yardmowers

    
 
	  System.out.println("      --------------------------------------------------" );
  }
  /*
   * The play method is another helper method with which we start the game.It gives us the current status of game.
   * As well as helps us in getting familiar with the game.
   * 
   * @author Amandeep Singh
   */
  public void play() 
  {            
      printWelcome(); // print statements at the starting of the Game.
     
      currPoints();
      
      // Enter the main command loop.  Here we repeatedly read commands and
      // execute them until the game is over.
      boolean finished = false;
      while (! finished) {
          Command command = parser.getCommand();
          finished = processCommand(command);
      }
      System.out.println("Thank you for playing.  Good bye.");
  }
  /*
   * The replay method comes in play when we lose the game,then we have two options either to quit the game or to replay.
   * 
   * @author Zoha Mehdi
   */
  public void replay() 
  {            
      printWelcome(); // welcome statements
      this.sun=50; // starting the game with 50 sun points again
      this.peaShooter =0;
      this.sunFlower=0;
      zombie = new BasicZoombie();
      

		for(int i=enemy.size() -1; i >= 0;i--) {
			Game.enemy.remove(i); // removing the zombies from the old lost game and then putting back 6 zombies in the game as we are replaying the game
		}
      zombie = new BasicZoombie();
      
		for(int i=0; i < 6;i++) {
			Game.enemy.add(zombie); //adding the 6 zombies to the field for the replay version of the game
		}
      currPoints(); // Give current status of the Game
      // Enter the main command loop.  Here we repeatedly read commands and
      // execute them until the game is over.
      boolean finished = false;
      while (! finished) {
          Command command = parser.getCommand();
          finished = processCommand(command);

      }
      
      play();
  }

	public static void main(String[] args){

		Game f = new Game(); // make a new Game object f
		f.createEnemy(); // add the six zombies to the arraylist
			f.play(); //Start the console version of the Game
	}
}
