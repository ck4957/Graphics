/* 
 * PlayeInterface.java 
 * 
 * Version: v 1.0  09/12/2015 
 *     $Id$ 
 * 
 * Revisions: 
 *     $Log$ 
 */
public interface PlayerInterface {
/**
* This is Player interface which has several methods defined in it
* which are further implemented in Player Class.
* @param column
* @return
* 
* @author Chirag.Kular
* @author Gaurav.Gawade
*/
	
	public char getGamePiece();
	public String getName();
	public int  nextMove();
}