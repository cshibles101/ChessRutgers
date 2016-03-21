package chess;
/**
 * @author Christopher Shibles
 */
public class King extends Piece{

	/**
	 * Initializes new King object by calling Piece constructor using given parameters
	 * @param file - the initial file of the object
	 * @param rank - the initial rank of the object
	 * @param name - the initial 2 character name of the object
	 * @param isWhite - determines the color of the object
	 */
	public King(int file, int rank, String name, boolean isWhite){
		super(file, rank, name, isWhite);
		hasMoved = false;
	}
	
	@Override
	public boolean validMove(int file, int rank, Space[][] spaces){
		if(super.validMove(file, rank, spaces)){
			if(getFile()+1 >= file && getFile()-1 <= file && getRank()-1 <= rank && getRank()+1 >= rank)			
				return true;
			else if(!hasMoved)
				return castle(file, rank, spaces, isWhite);
			else
				return false;
		}
		else
			return false;
		
	}
	/**
	 * Tests to see if the King instance can perform a legal castle move
	 * @param file - file of destination
	 * @param rank - rank of destination
	 * @param spaces - current spaces on the board
	 * @param isWhite - color of the king to be castled
	 * @return true if King instance can perform a legal castle
	 */
	static boolean castle(int file, int rank, Space[][] spaces, boolean isWhite){
		if(isWhite){
			
			if(rank == 0){
				if(file == 6){
					if(spaces[0][7].piece != null && !spaces[0][7].piece.hasMoved){
						if(spaces[0][5].piece == null && spaces[0][6].piece == null){
							
							if(!spaces[0][4].piece.threatened(spaces, isWhite) && !spaces[0][5].threatened(spaces, isWhite) && !spaces[0][6].threatened(spaces, isWhite)){
								
								spaces[0][5].piece = spaces[0][7].piece;
								spaces[0][7].piece = null;
								spaces[0][5].piece.setLocation(5, 0);
								
								
								return true;
							}
							
						}
					}
					return false;
				}
				else if(file == 2){
					if(spaces[0][0].piece != null && !spaces[0][0].piece.hasMoved){
						
						if(spaces[0][1].piece == null && spaces[0][2].piece == null && spaces[0][3].piece == null){
							
							if(!spaces[0][4].piece.threatened(spaces, isWhite) && !spaces[0][2].threatened(spaces, isWhite) && !spaces[0][3].threatened(spaces, isWhite)){
								
								spaces[0][3].piece = spaces[0][0].piece;
								spaces[0][0].piece = null;
								spaces[0][3].piece.setLocation(3, 0);
								
								
								return true;
							}
							
						}
					}
					return false;
				}
				else 
					return false;
				
				
			}
			else
				return false;
			
		}
		else{
			
			if(rank == 7){
				if(file == 6){
					if(spaces[7][7].piece != null && !spaces[7][7].piece.hasMoved){
						if(spaces[7][5].piece == null && spaces[7][6].piece == null){
							
							if(!spaces[7][4].piece.threatened(spaces, isWhite) && !spaces[7][5].threatened(spaces, isWhite) && !spaces[7][6].threatened(spaces, isWhite)){
								
								spaces[7][5].piece = spaces[7][7].piece;
								spaces[7][7].piece = null;
								spaces[7][5].piece.setLocation(5, 7);
								
								
								return true;
							}
						}
					}
					return false;
				}
				else if(file == 2){
					if(spaces[7][0].piece != null && !spaces[7][0].piece.hasMoved){
						
						if(spaces[7][1].piece == null && spaces[7][2].piece == null && spaces[7][3].piece == null){
							
							if(!spaces[7][4].piece.threatened(spaces, isWhite) && !spaces[7][2].threatened(spaces, isWhite) && !spaces[7][3].threatened(spaces, isWhite)){
								
								spaces[7][3].piece = spaces[7][0].piece;
								spaces[7][0].piece = null;
								spaces[7][3].piece.setLocation(3, 7);
								
								
								return true;
							}
							
						}
					}
					return false;
				}
				else 
					return false;
				
				
			}
			else
				return false;
		}
	}
}
