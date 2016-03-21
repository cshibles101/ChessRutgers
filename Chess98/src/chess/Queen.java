package chess;
/**
 * @author Christopher Shibles
 */
public class Queen extends Piece{

	/**
	 * Initializes new Queen object by calling Piece constructor using given parameters
	 * @param file - the initial file of the object
	 * @param rank - the initial rank of the object
	 * @param name - the initial 2 character name of the object
	 * @param isWhite - determines the color of the object
	 */
	public Queen(int file, int rank, String name, boolean isWhite){
		super(file, rank, name, isWhite);
	}
	
	@Override
	public boolean validMove(int file, int rank, Space[][] spaces){
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
			else if(Math.abs(rank-getRank())==Math.abs(file-getFile())){
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
