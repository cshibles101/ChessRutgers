package chess;
/**
 * @author Christopher Shibles
 */
public class Bishop extends Piece{

	/**
	 * Initializes new Bishop object by calling Piece constructor using given parameters
	 * @param file - the initial file of the object
	 * @param rank - the initial rank of the object
	 * @param name - the initial 2 character name of the object
	 * @param isWhite - determines the color of the object
	 */
	public Bishop(int file, int rank, String name, boolean isWhite){
		super(file, rank, name, isWhite);
	}

	@Override
	public boolean validMove(int file, int rank, Space[][] spaces){
		if(super.validMove(file, rank, spaces)){
			if(Math.abs(rank-getRank())==Math.abs(file-getFile())){
				if(file > getFile()){
					if(rank > getRank()){
						file--;
						rank--;
						while(file != getFile()){
							if(spaces[rank][file].piece != null)
								return false;
							file--;
							rank--;
						}
						return true;
						
					}
					else{
						file--;
						rank++;
						while(file != getFile()){
							if(spaces[rank][file].piece != null)
								return false;
							file--;
							rank++;
						}
						return true;
						
					}
				}
				else{
					if(rank > getRank()){
						file++;
						rank--;
						while(file != getFile()){
							if(spaces[rank][file].piece != null)
								return false;
							file++;
							rank--;
						}
						return true;
						
					}
					else{
						file++;
						rank++;
						while(file != getFile()){
							if(spaces[rank][file].piece != null)
								return false;
							file++;
							rank++;
						}
						return true;
						
					}
					
				}
			}
			else
				return false;
		}
		else
			return false;
	}
}
