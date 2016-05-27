/* 
 * Player.java 
 * 
 * Version: v 1.0  09/12/2015 
 *     $Id$ 
 * 
 * Revisions: 
 *     $Log$ 
 */
import java.util.Scanner;
/**
 * This program implements the player interface thereby implementing it's
 * methods which pulls the player's name and last move.
 * @author Chirag.Kular
 * @author Gaurav.Gawade
 *
 */
public class Player implements PlayerInterface {

	Scanner sc = new Scanner(System.in);
	char game_p;
	String player_name;

	/**
	 * This constructor intializes the Player name and the game piece
	 * @param aConnect4Field
	 * @param name
	 * @param gamePiece
	 */
	public Player(Connect4Field aConnect4Field, String name, char gamePiece)
	{
		player_name = name;
		game_p = gamePiece;// TODO Auto-generated constructor stub
	}

	/* public Player(Connect4FieldComputer a_comp_Connect4Field, String name, char
	gamePiece) {
		player_name = name;
		game_p = gamePiece;// TODOAuto-generated constructor stub
	} */

	/**
	 * Returns the current game piece of the player
	 */
	public char getGamePiece() {
		return game_p;
	}
	
	/**
	 * Returns the name of the current player
	 */
	public String getName() {
		return player_name;
	}
	
	/**
	 * Returns the next move of the current player
	 */
	public int nextMove() {
		System.out.println("Enter Column :");
		int column = sc.nextInt();
		return column;
	}
}
