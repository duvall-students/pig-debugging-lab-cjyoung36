import java.util.Random;

public class Game {
	private Player player1;
	private Player player2;
	private Random die;
	private Spinner spinner;
//	private final String LOSER_SPIN = "grunt";
	private final String LOSER_SPIN = "GRUNT"; 	// changed to uppercase grunt to match
	private final int LOSER_ROLL = 1;
	
	public Game(){
//		Player player1 = new GUIPlayer();
//		Player player2 = new ComputerPlayer();
		player1 = new GUIPlayer();							// Changed constructor to initialize field
		player2 = new ComputerPlayer();						// instead of new local instance variables
		die = new Random();
		spinner = new Spinner();
	}
	
	/*
	 * Method will play one game between the players.
	 */
	public void playGame(){
		printStartGameMessage();
		Player whoseTurn = player1;
		while(!winner()){
			int roundScore = takeATurn(whoseTurn);
			whoseTurn.addToScore(roundScore);
			// Switch whose turn it is.
			if(whoseTurn == player1){
				whoseTurn = player2;
			}
			else{
				whoseTurn = player1;
			}
		}
		printEndGameMessage();
	}
	
	/*
	 * One player's turn.  Ends because
	 * - roll a 1
	 * - spin a "GRUNT"
	 * - or Player decides to stop
	 * 
	 * Return the number of points to add to the player
	 */
	public int takeATurn(Player whoseTurn){
		int roundScore = 0;
		boolean keepGoing = true;
		printStartRoundMessage(whoseTurn);
		while(keepGoing){
//			int roll = die.nextInt(7);
			int roll = die.nextInt(6 - 1) + 1;		// changed to make values 1 - 6
			String spin = spinner.spin();
			System.out.println(roll+ " "+ spin);
			
			if(roll == LOSER_ROLL){
				System.out.println("Lose a turn.");
				return 0;
			}
//			else if(spin == LOSER_SPIN.toUpperCase()){
			else if (spin.equals(LOSER_SPIN.toUpperCase())) {				// changed to .equals
				System.out.println("Too bad!  Lose all your points.");
				whoseTurn.resetScore();
				return 0;
			}
			else{
				roundScore = roundScore + roll;
				System.out.println("Round total is currently: "+roundScore);
				keepGoing = whoseTurn.rollAgain(roundScore);
			}
		}
		return roundScore;
	}
	
	// True if one of the players has won the game.
	public boolean winner(){
//		return player1.hasWon() && player2.hasWon();
		return player1.hasWon() || player2.hasWon();  	// changed from && to ||
	}
	
	/* 
	 * These methods are for printing messages to the console to follow the game.
	 */
	public void printStartRoundMessage(Player whoseTurn){
		System.out.println("New Round!  "+ whoseTurn.getName()+" 's turn."); 
		System.out.println(player1);
		System.out.println(player2);
	}
	
	public void printEndGameMessage(){
		if(player1.hasWon()){
			System.out.println("Winner is "+player1.getName());
		}
		else{
			System.out.println("Winner is "+player2.getName());
		}
	}
	
	public void printStartGameMessage(){
		System.out.println("Let's Play Pig!");
	}
}
