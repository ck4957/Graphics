/* 
 * TestConnect4Field.java 
 * 
 * Version: v 1.0  09/12/2015 
 *     $Id$ 
 * 
 * Revisions: 
 *     $Log$ 
 */
public class TestConnect4Field {
/**
* This program tests several functionalities of the Connect4Field game.
* @author Chirag.Kular
* @author Gaurav.Gawade
*
*/
	
	public Connect4Field aConnect4Field = new Connect4Field();
	//public Connect4FieldComputer a_comp_Connect4Field = new Connect4FieldComputer();
	public Player aPlayer = new Player(aConnect4Field, "A", '+');
	public Player bPlayer = new Player(aConnect4Field, "B", '*');

	/**
	 * Checks whether the particular column can be entered into the 
	 * board or not.
	 * @param column
	 */
	public void dropTest(int column) {

		System.out.println("Can it be dropped in " +
		column + ": " + aConnect4Field.checkIfPiecedCanBeDroppedIn(column));

	}

	/**
	 * Starts the game between two players.
	 */
	public void testIt2()
	{
		aConnect4Field.init(aPlayer, bPlayer);
	}

	/**
	 * Test the funcitonality of Connect4Field game
	 */
	public void testIt() {
		// aConnect4Field = new Connect4Field();
		System.out.println(aConnect4Field);
		dropTest(-1);
		dropTest(0);
		dropTest(1);
		aConnect4Field.dropPieces(1, '+');
		System.out.println(aConnect4Field);
		aConnect4Field.dropPieces(1, '*');
		System.out.println(aConnect4Field);
		aConnect4Field.didLastMoveWin();
		aConnect4Field.isItaDraw();
		//System.exit(0);
		aConnect4Field.init(aPlayer, bPlayer);
	}

	/**
	 * The main program.
	 * @param args
	 */
	public static void main(String[] args) {
		//new TestConnect4Field().testIt();
		new TestConnect4Field().testIt2();
	}
}