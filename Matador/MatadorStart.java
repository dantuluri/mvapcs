/**
 *  Matador.java
 *  In this game, the user will start with 200 dollars.
 *  The user places a bet (a random number of dollars
 *  from 1 to 10), then chooses a possible outcome for
 *  the rolling of 3 6-sided dice:
 *  (1) Any Triple (betting that all 3 dice will show the same number), at 30 to 1 odds
 *  (2) Go Big (betting that the sum of the dice will be 11 or higher, and not a triple), at 1 to 1 odds
 *  (3) Go Small (betting that the sum of the dice will be 10 or lower, and not a triple), at 1 to 1 odds
 *  (4) Go Extreme (betting that the sum of the dice will be less than 8 or greater than 12), at 1 to 1 odds
 *  The dice are then rolled, and the player either wins or loses,
 *  with the appropriate amount of money either added or subtracted
 *  from the player's total.  The game is then repeated, and this
 *  continues until the player winds up with 0 dollars.
 *  The number of turns (games played) is then displayed
 *  and the game terminates.
 *
 *  @author Surya Dantuluri
 *  @version 1.0
 *  @since 8/21/2017
 */
public class Matador
{
	/**    Declare fields here.    */
	private int money;

	/**
	 *  Creates a Matador object, with three six-sided Dice, one
	 *  ten-sided die for the money bet, and a four-sided die
	 *  for the bet choice.  Sets the amount of money to 200 dollars.
	 */
	public Matador ( )
	{
		money = 1;
	}

	/**
	 *  Sets up and runs the rounds of Matador.
	 *  @param  args     An array of String arguments (not used here).
	 */
	public static void main (String [] args)
	{
		Matador run = new Matador();
		run.playGames();
	}

	/**
	 *  Plays all of the rounds of Matador, until the player has no money left.
	 */
	public void playGames ( )
	{
		System.out.println("\n\n\n");
		do
		{
			System.out.println("+----------------------------------------------------------------+");
			System.out.printf("|  ROUND %5d                                                   |%n",money);
			playASingleGame();
		}
		while(money > 0);
		System.out.println("+----------------------------------------------------------------+\n");
		System.out.println("\n\nSorry, but you know that the house always wins!\n\n\n\n\n");
	}

	/**
	 *  Plays a single game of Matador.
	 */
	public void playASingleGame ( )
	{
		System.out.printf("|  Your money total:  $%4d                                      |%n",money);
		System.out.printf("|  Your wager      :  $%4d                                      |%n",money);
		System.out.print("|  Your choice     :  ");
		System.out.println("Any Triple (30 to 1)                       |");
			//System.out.println("Go Big (sum >= 11, not a triple, 1 to 1)   |");
			//System.out.println("Go Small (sum <= 10, not a triple, 1 to 1) |");
			//System.out.println("Go Extreme (sum < 8 or sum > 12, 1 to 1)   |");
		System.out.printf("|  Roll            :%3d%3d%3d                                    |%n",money,money,money);
		System.out.printf("|  Sum             :  %-2d             +---------------+           |%n",money);
		System.out.print("|  A Triple        :  ");
		System.out.print("YES            ");
			//System.out.print("NO             ");
		System.out.println("|   YOU WIN!    |           |");
			//System.out.println("|   YOU LOSE!   |           |");
		System.out.printf("|  New money total :  $%4d          +---------------+           |%n",money);
	}

	/**
	 *  Split the work up into smaller methods.  Don't write all of your code in
	 *  playASingleGame if you are looking to earn full credit.
	 */
}
