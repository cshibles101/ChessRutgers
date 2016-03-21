package chess;
/**
 * @author Christopher Shibles
 */

public class Piece{
	
	boolean hasMoved;
	private int file;
	private int rank;
	private String name;
	final boolean isWhite;
	
	
	/**
	 * Initializes new Piece object
	 * @param file - the initial file of the object
	 * @param rank - the initial rank of the object
	 * @param name - the initial 2 character name of the object
	 * @param isWhite - determines the color of the object
	 */
	Piece(int file, int rank, String name, boolean isWhite){
		this.file = file;
		this.rank = rank;
		this.name = name;
		this.isWhite = isWhite;
	}
	
	/**
	 * @return Returns the value of file for an instance
	 */
	public int getFile(){
		return file;
	}
	
	/**
	 * @return Returns the value of rank for an instance
	 */
	public int getRank(){
		return rank;
	}
	
	/**
	 * Takes in new values for the rank and file of an instance
	 * and sets them
	 * @param file - new file value for instance
	 * @param rank - new rank value for instance
	 */
	public void setLocation(int file, int rank){
		this.file = file;
		this.rank = rank;
	}
	
	/**
	 * @return Returns the 2 letter name of the piece, used
	 * for printing reasons
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Method to check if move attempted is either stationary or
	 * not on the board at all
	 * @param file - destination file
	 * @param rank - destination rank
	 * @param spaces - board's current spaces
	 * @return false when move attempted is stationary
	 * or if an attempted attack on one's own piece occurs
	 */
	public boolean validMove(int file, int rank, Space[][] spaces){
		if(spaces[rank][file].piece != null && spaces[rank][file].piece.isWhite == spaces[this.rank][this.file].piece.isWhite){
			return false;
		}
		return !(this.file == file && this.rank == rank);	
		
	}
	
	public String toString(){
		return name;
	}
	/**
	 * Tests if the piece is on a spot [rank, file] that is considered a valid move
	 * for another piece instance
	 * @param spaces 
	 * @param isWhite 
	 * @return true if another piece on another spot [rank, file] can legally
	 * move to this spot
	 */
	public boolean threatened(Space[][] spaces, boolean isWhite){
		
		for(int x = 0; x < 8; x++){
			
			for(int y = 0; y < 8; y++){
				
				if(spaces[x][y].piece != null && spaces[x][y].piece.isWhite != isWhite){
					
					if(spaces[x][y].piece.validMove(file, rank, spaces))
						return true;
					
				}
				
			}
			
		}
		
		return false;
	}
	
}
