package chess;
/**
 * @author Christopher Shibles
 */
public class Rook extends Piece{

	/**
	 * Initializes new Rook object by calling Piece constructor using given parameters
	 * @param file - the initial file of the object
	 * @param rank - the initial rank of the object
	 * @param name - the initial 2 character name of the object
	 * @param isWhite - determines the color of the object
	 */
	public Rook(int file, int rank, String name, boolean isWhite){
		super(file, rank, name, isWhite);
		hasMoved = false; 
	}
	
	@Override
	public boolean validMove(int file, int rank, Space spaces[][]){
		if(super.validMove(file, rank, spaces)){
			if(getFile() == file){
				if(getRank() > rank){
					for(int x = rank+1; x < getRank(); x++){
						if(spaces[x][file].piece != null)
							return false;
					}
					return true;
				}
				else{
					for(int x = rank-1; x > getRank(); x--){
						if(spaces[x][file].piece != null)
							return false;
					}
					return true;
				}
			}
			else if(getRank() == rank){
				if(getFile() > file){
					for(int x = file+1; x < getFile(); x++){
						if(spaces[rank][x].piece != null)
							return false;
					}
					return true;
				}
				else{
					for(int x = file-1; x > getFile(); x--){
						if(spaces[rank][x].piece != null)
							return false;
					}
					return true;
				}
				
			}
			else
				return false;
		}
		else
			return false;
	}
}
