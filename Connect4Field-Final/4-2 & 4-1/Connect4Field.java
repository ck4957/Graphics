/* 
 * Connect4Field.java 
 * 
 * Version: v 1.0  09/12/2015 
 *     $Id$ 
 * 
 * Revisions: 
 *     $Log$ 
 */
import java.util.Arrays;
import java.util.Scanner;

/**
 * This program is a Connect4Field game between two players which implements
 * the methods of the Connect4FieldInterface.
 * @author Chirag.Kular
 * @author Gaurav.Gawade
 *
 */
public class Connect4Field {

	static int rows_i, column_j, row_size = 9, column_size = 25;
	static char board2D[][] = new char[row_size][column_size];
	static int last_updated_row, last_updated_col;
	static char current_game_piece;
	Player[] thePlayers = new Player[2];
	static Scanner sc = new Scanner(System.in);

	/**
	 * The Constructor has been used to display the Board 
	 * at the start of the game.
	 */
	Connect4Field() {
		for (rows_i = 0; rows_i < row_size; rows_i++) {
			for (column_j = rows_i; column_j < (column_size - rows_i); column_j++) {
				board2D[rows_i][column_j] = 'o';
				System.out.print(board2D[rows_i][column_j]);
			}
			System.out.println();
			//This loop prints the blank spaces on the right side of the board.
			for (int index = 0; index <= rows_i; index++) {
				System.out.print(" ");
			}
		}
		System.out.println();
	}

	public String toString() {
		return this.getClass().getName();
	}

	public boolean isItaDraw() {
		// TODO Auto-generated method stub
		boolean flag = true;
		//if(Arrays.asList(board2D).contains('o'))
		//	flag=false;
		//else
		//	flag=true;
 		for (rows_i = 0; rows_i < row_size; rows_i++) {
			for (column_j = rows_i; column_j < (column_size - rows_i); column_j++) {
				if(board2D[rows_i][column_j]=='o'){// && !didLastMoveWin()){
					flag=false;
				}
			}
		}
		//return Arrays.asList(board2D).contains('o');
		return flag;
	}

	/**
	 * This Method checks whether the last move made by any particular player 
	 * is a win or not and returns boolean value. In each of the for loop used 
	 * there is counter which indicates that if it's '4' then it's a win.
	 * @return
	 */
	public static boolean didLastMoveWin() {
		boolean flag = false;
		int index_start = 0;
		int counter = 0;
		// Check Horizontally left-right side of the move made by the player.
		for (int index = 0; index < column_size; index++) {
			if (board2D[last_updated_row][index] == current_game_piece) {
				counter++;
				if (counter == 4) {
					flag = true;
					return flag;
				}
			} else
				counter = 0;
			continue;

		}

		// Check Vertical downwards side of the move made by the player.
		counter = 0;
		for (int index = last_updated_row; index <= row_size - 1; index++) {
			if (board2D[index][last_updated_col] == current_game_piece) {
				counter++;
				if (counter == 4) {
					flag = true;
					return flag;
				}
			} else {
				counter = 0;
				break;
			}

		}

		//Check diagonally for the left hand side edge of the move made by the player.
		counter = 0;
		index_start = last_updated_col - last_updated_row;
		for (int index_r = 0, index_j = index_start; index_r <= row_size - 1
		&& index_j <= column_size - 1; index_r++, index_j++) {
			if (board2D[index_r][index_j] == current_game_piece
			&& board2D[index_r][index_j] != ' ') {
				counter++;
				if (counter == 4) {
					flag = true;
					return flag;
				}
			} else {
				counter = 0;
				continue;
			}
		}

		//Check diagonally for the left hand side edge of the move made by the player.
		counter = 0;
		index_start = last_updated_col + last_updated_row;
		for (int index_r = 0, index_j = index_start; index_r <= row_size - 1
		&& index_j >= 0; index_r++, index_j--) {
			if (board2D[index_r][index_j] == current_game_piece
			&& board2D[index_r][index_j] != ' ') {
				counter++;
				if (counter == 4) {
					flag = true;
					return flag;
				}
			} else {
				counter = 0;
				continue;
			}
		}
		return flag;
	}
	
	/**
	 * This method checks if the particular piece can be dropped at the
	 * column entered by the user returns the boolean value accordingly.
	 * @param column
	 * @return
	 */
	public boolean checkIfPiecedCanBeDroppedIn(int column) {
		boolean flag = false;
		if (column < 0 || column >= column_size)
			flag = false;
		else {
			// System.out.println("Invalid Column Number");
			for (int row = row_size - 1; row >= 0; row--)
				if (board2D[row][column] == 'o') {
					flag = true;
					last_updated_row = row;
					last_updated_col = column;
					break;
				}
		}
		return flag;
	}
	
	/**
	 * This method takes the row and column of the last move made by any 
	 * player and prints the board accordingly also it displays a message
	 * if the move is out of the board.
	 * @param column
	 * @param gamePiece
	 */
	public void dropPieces(int column, char gamePiece) {
		// TODO Auto-generated method stub
		boolean gameIsOver = false;
		current_game_piece = gamePiece;
		if (checkIfPiecedCanBeDroppedIn(column)) {
			board2D[last_updated_row][last_updated_col] = current_game_piece;
			printBoard();
			/*
			 * do { if (didLastMoveWin()) { gameIsOver = true;
			 * System.out.println("Game Complete.!!! \n Winner is :" +
			 * current_game_piece); System.exit(0); }else{ break; }
			 * 
			 * else { // getInput(); System.exit(0); }
			 * 
			 * } while (!gameIsOver);
			 */

		} else
			System.out.println("This piece cant be dropped");
	}
	
	/**
	 * This method displays the Board.
	 */

	public void printBoard() {
		for (rows_i = 0; rows_i < row_size; rows_i++) {
			for (column_j = rows_i; column_j < (column_size - rows_i); column_j++) {
				System.out.print(board2D[rows_i][column_j]);
			}
			System.out.println();
			for (int index = 0; index <= rows_i; index++) {
				System.out.print(" ");
			}
		}
		System.out.println();
	}


	/**
	 * This method takes input from the two players such as player name and 
	 * starts the game bewteen them.
	 * @param aPlayer
	 * @param bPlayer
	 */
	public void init(PlayerInterface aPlayer, PlayerInterface bPlayer) {
		//For 4.1 program and testit()
		/* System.exit(0);
		String playerAname = "A", playerBname = "B";
		Connect4Field aConnect4Field = new Connect4Field();
		aPlayer = new Player(aConnect4Field, playerAname, '+');
		bPlayer = new Player(aConnect4Field, playerBname, '*');
		thePlayers[0] = (Player) aPlayer;
		thePlayers[1] = (Player) bPlayer;
		playTheGame(); */

		//For 4.2 program ad testit2()
		 String playerAname = "", playerBname = "";
		Connect4Field aConnect4Field = new Connect4Field();
		System.out.println("\nEnter player 1's name:");
		playerAname = sc.next();
		System.out.println("\nEnter player 2's name:");
		playerBname = sc.next();
		// thePlayers[0]=new Player(aConnect4Field,playerAname,'+');
		// thePlayers[0]=new Player(aConnect4Field,playerBname,'*');
		aPlayer = new Player(aConnect4Field, playerAname, '+');
		bPlayer = new Player(aConnect4Field, playerBname, '*');
		// thePlayers[0]=new Player();
		thePlayers[0] = (Player) aPlayer;
		// thePlayers[1]=new Player();
		thePlayers[1] = (Player) bPlayer;
		playTheGame();
		
	}

	/**
	 * This method shows the actual implementation of game by calling several
	 * methods such as getGamePiece, didLastMoveWin etc.
	 */
	public void playTheGame() {
		int column;
		char game_piece;

		boolean gameIsOver = false;
		//Do-while for 4.1 program
		/* do {
				if ( isItaDraw() )      {
					System.out.println("Draw");
					gameIsOver = true;
				} 
				else {
					if ( didLastMoveWin() ) {
							gameIsOver = true;
							System.out.println("The winner is: " );//+ //thePlayers[index].getName());
							break;
							}
						}
		}  while ( ! gameIsOver  ); */

	//Do-while for 4.2 program
 		do {
			for (int index = 0; index < 2; index++) {
				// System.out.println(this);
				if (isItaDraw()) {
					System.out.println("Draw");
					gameIsOver = true;
				} else {
					// thePlayers[index]=new Player();
					column = thePlayers[index].nextMove();
					// thePlayers[index]=new Player();
					game_piece = thePlayers[index].getGamePiece();
					// System.out.println("Index: "+thePlayers[index].getName()
					// );
					dropPieces(column, game_piece);
					if (didLastMoveWin()) {
						gameIsOver = true;
						System.out.println("The winner is: "
						+ thePlayers[index].getName());
						break;
					}
				}
			}
		} while (!gameIsOver);
 	}
}