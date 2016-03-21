package chess;
/**
 * @author Christopher Shibles
 */
public class Space {
	
	int file;
	int rank;
	Piece piece;
	
	/**
	 * Initializes new Space object
	 * @param file - file of the spot
	 * @param rank - rank of the spot
	 * @param piece - initial piece object
	 */
	Space(int file, int rank, Piece piece){
		this.file = file;
		this.rank = rank;
		this.piece = piece;
	}
	
	public String toString(){
		
		if(piece == null){
			if((file+rank)%2 == 0)
				return "##";
			else
				return "  ";
		}
		else{
			return piece.getName();
		}
	}
	/**
	 * Method called to determine if Space object is a valid move
	 * for any Piece object of a specified color
	 * @param spaces - spaces on the board
	 * @param isWhite - color of pieces 
	 * @return true if space is considered a valid move by pieces 
	 * of the color specified in the parameters
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
