package chess;
/**
 * @author Christopher Shibles
 */
public class Board {

	Space[][] spaces = new Space[8][8];
	
	/**
	 * Initializes a Board object, adding all 64 spaces and 32 pieces
	 */
	public Board(){
		
		for(int x = 2; x < 6; x++){
			for(int y = 0; y < 8; y++){
				spaces[x][y] = new Space(x, y, null);
			}
		}
		spaces[7][0] = new Space(0, 7, new Rook(0, 7, "bR", false));
		spaces[7][1] = new Space(1, 7, new Knight(1, 7, "bN", false));
		spaces[7][2] = new Space(2, 7, new Bishop(2, 7, "bB", false));
		spaces[7][3] = new Space(3, 7, new Queen(3, 7, "bQ", false));
		spaces[7][4] = new Space(4, 7, new King(4, 7, "bK", false));
		spaces[7][5] = new Space(5, 7, new Bishop(5, 7, "bB", false));
		spaces[7][6] = new Space(6, 7, new Knight(6, 7, "bN", false));
		spaces[7][7] = new Space(7, 7, new Rook(7, 7, "bR", false));
		for(int x = 0; x < 8; x++){
			spaces[6][x] = new Space(x, 6, new Pawn(x, 6, "bp", false));
		}
		spaces[0][0] = new Space(0, 0, new Rook(0, 0, "wR", true));
		spaces[0][1] = new Space(1, 0, new Knight(1, 0, "wN", true));
		spaces[0][2] = new Space(2, 0, new Bishop(2, 0, "wB", true));
		spaces[0][3] = new Space(3, 0, new Queen(3, 0, "wQ", true));
		spaces[0][4] = new Space(4, 0, new King(4, 0, "wK", true));
		spaces[0][5] = new Space(5, 0, new Bishop(5, 0, "wB", true));
		spaces[0][6] = new Space(6, 0, new Knight(6, 0, "wN", true));
		spaces[0][7] = new Space(7, 0, new Rook(7, 0, "wR", true));
		for(int x = 0; x < 8; x++){
			spaces[1][x] = new Space(x, 1, new Pawn(x, 1, "wp", true));
		}
		
	}
	
	public String toString(){
		
		for(int x = 7; x >= 0; x--){
			for(int y = 0; y < 8; y++){
				System.out.print(spaces[x][y]+" ");
			}
			System.out.print(x+1+"\n");
		}
		
		return " a  b  c  d  e  f  g  h";
		
	}
	
	
}
