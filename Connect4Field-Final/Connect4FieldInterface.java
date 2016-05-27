/* 
 * Connect4FieldInterface.java 
 * 
 * Version: v 1.0  09/12/2015 
 *     $Id$ 
 * 
 * Revisions: 
 *     $Log$ 
 */
public interface Connect4FieldInterface {
/**
 * This is Connect4Field interface which has several methods defined in it
 * which are further implemented in Connect4Field Class.
 * @param column
 * @return
 */
	public boolean checkIfPiecedCanBeDroppedIn(int column);

	public void dropPieces(int column, char gamePiece);

	boolean didLastMoveWin();

	public boolean isItaDraw();

	public void init(PlayerInterface playerA, PlayerInterface playerB);

	public String toString();

	public void playTheGame();
}