package chess;
/**
 * @author Christopher Shibles
 */
public class Knight extends Piece{

	/**
	 * Initializes new Knight object by calling Piece constructor using given parameters
	 * @param file - the initial file of the object
	 * @param rank - the initial rank of the object
	 * @param name - the initial 2 character name of the object
	 * @param isWhite - determines the color of the object
	 */
	public Knight(int file, int rank, String name, boolean isWhite){
		super(file, rank, name, isWhite);
	}
	
	@Override
	public boolean validMove(int file, int rank, Space[][] spaces){
		if(super.validMove(file, rank, spaces)){
			
			 if(((rank == getRank()+1 || rank == getRank()-1) && (file == getFile()+2||file == getFile()-2)) ||
				((rank == getRank()+2 || rank == getRank()-2) && (file == getFile()+1||file == getFile()-1)))
				return true;
			else
				return false;
		}
		else
			return false;
	}
}

