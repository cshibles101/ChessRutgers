package chess;
/**
 * @author Christopher Shibles
 */
public class Pawn extends Piece{

	/**
	 * Initializes new Pawn object by calling Piece constructor using given parameters
	 * @param file - the initial file of the object
	 * @param rank - the initial rank of the object
	 * @param name - the initial 2 character name of the object
	 * @param isWhite - determines the color of the object
	 */
	public Pawn(int file, int rank, String name, boolean isWhite){
		super(file, rank, name, isWhite);
		hasMoved = false;
	}
	
	@Override
	public boolean validMove(int file, int rank, Space[][] spaces){
		if(super.validMove(file, rank, spaces)){
			if(isWhite){
				if(file == getFile() && rank == getRank()+2){
					if(!hasMoved){
						if(spaces[rank-1][file].piece == null && spaces[rank][file].piece == null)
							return true;
						else 
							return false;
					}
					else
						return false;
				}
				else if(file == getFile()){
					if(rank == getRank()+1 && spaces[rank][file].piece == null){
						return true;
					}
					else
						return false;
				}
				else if((file == getFile()+1 || file == getFile()-1) && rank == getRank()+1){
					if(spaces[rank][file].piece != null)
						return true;
					else{
						return enPassantWhite(file, rank, spaces);
					}
				}
				else
					return false;
			}
			else{ //black piece
				if(file == getFile() && rank == getRank()-2){
					if(!hasMoved){
						if(spaces[rank+1][file].piece == null && spaces[rank][file].piece == null)
							return true;
						else 
							return false;
					}
					else
						return false;
				}
				else if(file == getFile()){
					if(rank == getRank()-1 && spaces[rank][file].piece == null){
						return true;
					}
					else
						return false;
				}
				else if((file == getFile()-1 || file == getFile()+1) && rank == getRank()-1){
					if(spaces[rank][file].piece != null)
						return true;
					else{
						
						return enPassantBlack(file, rank, spaces);
						
					}
				}
				else
					return false;
			}
		}
		else
			return false;
	}
	
	/**
	 * Tests to see if a white pawn can perform an en passant
	 * attack on a black piece
	 * @param file - file of destination
	 * @param rank - rank of destination
	 * @param spaces - current spaces on the board
	 * @return true if piece can perform an enPassant attack
	 */
	static boolean enPassantWhite(int file, int rank, Space[][] spaces){
		
		if(Chess.lastMove[4] == 1 && Chess.lastMove[0] == file && Chess.lastMove[1] == rank+1 && 
				Chess.lastMove[2] == file && Chess.lastMove[3] == rank-1){
			
			spaces[Chess.lastMove[3]][Chess.lastMove[2]].piece = null;
			
			
			Chess.lastMove[5] = 1;
			return true;
		}
		
		return false;
	}
	
	/**
	 * Tests to see if a black pawn can perform an en passant
	 * attack on a white piece
	 * @param file - file of destination
	 * @param rank - rank of destination
	 * @param spaces - current spaces on the board
	 * @return true if piece can perform an enPassant attack
	 */
	static boolean enPassantBlack(int file, int rank, Space[][] spaces){
		
		if(Chess.lastMove[4] == 1 && Chess.lastMove[0] == file && Chess.lastMove[1] == rank-1 && 
				Chess.lastMove[2] == file && Chess.lastMove[3] == rank+1){
			
			spaces[Chess.lastMove[3]][Chess.lastMove[2]].piece = null;
			
			
			Chess.lastMove[5] = 1;
			return true;
		}
		
		return false;
	}

}
