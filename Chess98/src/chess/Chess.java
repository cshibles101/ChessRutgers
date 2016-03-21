package chess;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Christopher Shibles
 */
public class Chess {
	
	
	static int[] lastMove = new int[6]; //lastMove[5] = 1 if enpassant happens
	static Scanner input = new Scanner(System.in);
	
										
	public static void main(String[] args) throws IOException{
		
		boolean checkmate = false;
		
		FileReader reader = new FileReader("Untitled 2");
    	
    	BufferedReader bufferedReader = new BufferedReader(reader);
		
		int toFile, toRank, fromFile, fromRank;
		char toF, toR, fromF, fromR;
		String trail;
		int[] whiteKing = {4, 0}; //keeps track of file [0] and rank [1] of white king
		int[] blackKing = {4, 7}; //keeps track of file [0] and rank [1] of black king
		
		Piece holder; //holds attacked piece until determined if move made is legal and not putting King in check
		
		String turn = "white";
		
		String move;
		
		Board board = new Board();
		
		System.out.println(board+"\n");
		
		while (!checkmate){
			
			
			while(turn.equals("white")){
				
				
					
				System.out.print("White's move:  ");
				
				trail = "";
				
				move = input.nextLine().trim();
				//move = bufferedReader.readLine().trim();
				
				if(move.equalsIgnoreCase("resign"))
					System.exit(0);
				
				if(move.length() < 5){
					System.out.println("Error: Move not entered in correct format. "
							+ "Please enter in the format, 'FileRank FileRank'\n");
					continue;
				}
				else{
					
					toF = move.charAt(3);
					toR = move.charAt(4);
					fromF = move.charAt(0);
					fromR = move.charAt(1);
					if(move.length() > 5)
						trail = move.substring(5).trim();
					
					if(Character.isLetter(toF) && Character.isLetter(fromF) 
							&& Character.isDigit(toR) && Character.isDigit(fromR)){
						toFile =  Character.getNumericValue(toF)-10;
						toRank = Character.getNumericValue(toR)-1;
						fromFile = Character.getNumericValue(fromF)-10;
						fromRank = Character.getNumericValue(fromR)-1;
						
						if(toFile < 8 && toRank < 8 && fromFile < 8 && fromRank < 8){
							if(validMove(board, fromFile, fromRank, toFile, toRank, true)){
								
								holder = board.spaces[toRank][toFile].piece;
								
								board.spaces[toRank][toFile].piece = board.spaces[fromRank][fromFile].piece;
								board.spaces[toRank][toFile].piece.setLocation(toFile, toRank);
								
								board.spaces[fromRank][fromFile].piece = null;
								
								if(board.spaces[toRank][toFile].piece instanceof King){
									whiteKing[0] = toFile;
									whiteKing[1] = toRank;
								}
								
								/**
								 * checks to see if piece moved leaves the king in check
								 * if it does, reverses the move and continues loop
								 * from beginning, prompting for another move
								 */
								if(board.spaces[whiteKing[1]][whiteKing[0]].piece.threatened(board.spaces, true)){
									
									System.out.println("\nYou're leaving your King in check.");
									System.out.println("Illegal move, try again\n");
									
									undoMove(board.spaces, toFile, toRank, fromFile, fromRank, holder);
									
									continue;
								}
								
								board.spaces[toRank][toFile].piece.hasMoved = true;
								
								if(board.spaces[toRank][toFile].piece instanceof Pawn)
									lastMove[4] = 1;
								else
									lastMove[4] = 0;
								
								if(trail.equalsIgnoreCase("draw?") && Draw()){
										System.exit(0);
								}
								
								if(board.spaces[toRank][toFile].piece instanceof Pawn && toRank == 7){
									if(trail.equalsIgnoreCase("N")){
										board.spaces[toRank][toFile].piece = new Knight(toFile, toRank, "wN", true);
									}
									else if(trail.equalsIgnoreCase("B")){
										board.spaces[toRank][toFile].piece = new Bishop(toFile, toRank, "wB", true);
									}
									else if(trail.equalsIgnoreCase("R")){
										board.spaces[toRank][toFile].piece = new Rook(toFile, toRank, "wR", true);
									}
									else{
										board.spaces[toRank][toFile].piece = new Queen(toFile, toRank, "wQ", true);
									}
										
								}
								
								
							}
							else{
								System.out.println("\nIllegal move, try again\n");
								continue;
							}
							
							
						}
						else{
							System.out.println("Error: Move is not on the board. "
									+ "Please enter correct spaces in the format, 'FileRank FileRank'\n");
							continue;
						}
					}
					else{
						System.out.println("Error: Move not entered in correct format. "
								+ "Please enter in the format, 'FileRank FileRank'\n");
						continue;
					}
				
				}
				
				lastMove[0] = fromFile;
				lastMove[1] = fromRank;
				lastMove[2] = toFile;
				lastMove[3] = toRank;
				lastMove[5] = 0;
				
				System.out.println();
				System.out.println(board+"\n");
				turn = "black";
				
				
			}
			
			if(checkmate(board.spaces, blackKing[0], blackKing[1], false)){
				System.out.println("White wins");
				break;
			}else if(stalemate(board.spaces, blackKing[0], blackKing[1], false)){
				System.out.println("Stalemate");
				break;
			}
			
			while(turn.equals("black")){
				
				
				
				System.out.print("Black's move:  ");
				
				trail = "";
				
				move = input.nextLine().trim();
				//move = bufferedReader.readLine().trim();
				
				
				if(move.equalsIgnoreCase("resign"))
					System.exit(0);
				
				
				
				if(move.length() < 5){
					System.out.println("Error: Move not entered in correct format. "
							+ "Please enter in the format, 'FileRank FileRank'\n");
					continue;
				}
				else{
					
					toF = move.charAt(3);
					toR = move.charAt(4);
					fromF = move.charAt(0);
					fromR = move.charAt(1);
					if(move.length() > 5)
						trail = move.substring(5);
					
					if(Character.isLetter(toF) && Character.isLetter(fromF) 
							&& Character.isDigit(toR) && Character.isDigit(fromR)){
						toFile =  Character.getNumericValue(toF)-10;
						toRank = Character.getNumericValue(toR)-1;
						fromFile = Character.getNumericValue(fromF)-10;
						fromRank = Character.getNumericValue(fromR)-1;
						
						if(toFile < 8 && toRank < 8 && fromFile < 8 && fromRank < 8){
							if(validMove(board, fromFile, fromRank, toFile, toRank, false)){
								
								holder = board.spaces[toRank][toFile].piece;
								
								board.spaces[toRank][toFile].piece = board.spaces[fromRank][fromFile].piece;
								board.spaces[toRank][toFile].piece.setLocation(toFile, toRank);
								
								board.spaces[fromRank][fromFile].piece = null;
								
								if(board.spaces[toRank][toFile].piece instanceof King){
									blackKing[0] = toFile;
									blackKing[1] = toRank;
								}
								
								/**
								 * checks to see if piece moved leaves the king in check
								 * if it does, reverses the move and continues loop
								 * from beginning, prompting for another move
								 */
								if(board.spaces[blackKing[1]][blackKing[0]].piece.threatened(board.spaces, false)){
									
									System.out.println("\nYou're leaving your King in check.");
									System.out.println("Illegal move, try again\n");
									
									undoMove(board.spaces, toFile, toRank, fromFile, fromRank, holder);
									
									continue;
								}
								
								board.spaces[toRank][toFile].piece.hasMoved = true;
								
								
								
								if(board.spaces[toRank][toFile].piece instanceof Pawn)
									lastMove[4] = 1;
								else
									lastMove[4] = 0;
								
								if(trail.equalsIgnoreCase("draw?") && Draw()){
									System.exit(0);
								}
								
								if(board.spaces[toRank][toFile].piece instanceof Pawn && toRank == 7){
									if(trail.equalsIgnoreCase("N")){
										board.spaces[toRank][toFile].piece = new Knight(toFile, toRank, "bN", false);
									}
									else if(trail.equalsIgnoreCase("B")){
										board.spaces[toRank][toFile].piece = new Bishop(toFile, toRank, "bB", false);
									}
									else if(trail.equalsIgnoreCase("R")){
										board.spaces[toRank][toFile].piece = new Rook(toFile, toRank, "bR", false);
									}
									else{
										board.spaces[toRank][toFile].piece = new Queen(toFile, toRank, "bQ", false);
									}
										
								}
								
							}
							else{
								System.out.println("\nIllegal move, try again\n");
								continue;
							}
							
							
						}
						else{
							System.out.println("Error: Move is not on the board. "
									+ "Please enter correct spaces in the format, 'FileRank FileRank'\n");
							continue;
						}
					}
					else{
						System.out.println("Error: Move not entered in correct format. "
								+ "Please enter in the format, 'FileRank FileRank'\n");
						continue;
					}
				
				}
				
				lastMove[0] = fromFile;
				lastMove[1] = fromRank;
				lastMove[2] = toFile;
				lastMove[3] = toRank;
				lastMove[5] = 0;
				
				System.out.println();
				System.out.println(board+"\n");
				turn = "white";
				
				
			}
			
			if(checkmate(board.spaces, whiteKing[0], whiteKing[1], true)){
				System.out.println("Black wins");
				break;
			}
			else if(stalemate(board.spaces, whiteKing[0], whiteKing[1], true)){
				System.out.println("Stalemate");
				break;
			}
		}
		
    	
    	
		bufferedReader.close();
		
	}
	
	/**
	 * Method called when one player attempts to conclude the game
	 * with a draw.
	 * @return true when other player agrees to draw by entering "draw"
	 */
	public static boolean Draw(){
		
		if(input.nextLine().equalsIgnoreCase("Draw"))
			return true;
		else
			return false;
		
	}

	/**
	 * Method called to check if move input is legal
	 * @param board - whole board
	 * @param fromFile - file of piece to be moved
	 * @param fromRank - rank of piece to be moved
	 * @param toFile - file of space to be moved to
	 * @param toRank - rank of space to be moved to
	 * @param isWhite - boolean value for whose turn it is
	 * @return true when there's a piece to be moved, the piece belongs
	 * to the color whose turn it is, and the movement is legal for the
	 * piece
	 */
	public static boolean validMove(Board board, int fromFile, int fromRank, int toFile, int toRank, boolean isWhite){
		
		
		if(board.spaces[fromRank][fromFile].piece == null){
			System.out.print("\nNo Piece to move. ");
			return false;
		}
		
		if(board.spaces[fromRank][fromFile].piece.isWhite != isWhite){
			System.out.print("\nNot your piece. ");
			return false;
		}
		
		return board.spaces[fromRank][fromFile].piece.validMove(toFile, toRank, board.spaces);
	}
	
	/**
	 * Method to call before either player's turn to check if they have any legal moves that won't
	 * put themselves in check
	 * @param spaces - the board spaces 2D array
	 * @param file - file of king to be checked
	 * @param rank - rank of king to be checked
	 * @param isWhite - determines which king needs to be checked, true for white
	 * @return true if player whose king is being checked cannot make any legal moves
	 * without ending in check against its own king
	 */
	public static boolean stalemate(Space[][] spaces, int file, int rank, boolean isWhite){
		
		int rankHold = rank, fileHold = file;
		Piece holder;
		
		if(isWhite){
			for(int x = 0; x < 8; x++){//rank
				
				for(int y = 0; y < 8; y++){//file
					
					if(spaces[x][y].piece != null && spaces[x][y].piece.isWhite){
						
						for(int s = 0; s < 8; s++){//rank
							
							for(int t = 0; t < 8; t++){//file
								
								if(spaces[x][y].piece.validMove(t, s, spaces)){
									
									holder = spaces[s][t].piece;
									
									spaces[s][t].piece = spaces[x][y].piece;
									spaces[s][t].piece.setLocation(s, t);
									
									spaces[x][y].piece = null;
									
									if(spaces[s][t].piece instanceof King){
										rank = s;
										file = t;
										
									}

									if(!spaces[rank][file].piece.threatened(spaces, isWhite)){
										
										undoMove(spaces, t, s, y, x, holder);
										lastMove[5] = 0;
										
										return false;   
									}
									
									undoMove(spaces, t, s, y, x, holder);
									rank = rankHold;
									file = fileHold;
									lastMove[5] = 0;
								}
							}
						}
					}
				}
			}
		}
		else{
			for(int x = 0; x < 8; x++){//rank
				
				for(int y = 0; y < 8; y++){//file
					
					if(spaces[x][y].piece != null && !spaces[x][y].piece.isWhite){
						
						for(int s = 0; s < 8; s++){//rank
							
							for(int t = 0; t < 8; t++){//file
								
								if(spaces[x][y].piece.validMove(t, s, spaces)){
									
									holder = spaces[s][t].piece;
									
									spaces[s][t].piece = spaces[x][y].piece;
									spaces[s][t].piece.setLocation(s, t);
									
									spaces[x][y].piece = null;
									
									if(spaces[s][t].piece instanceof King){
										rank = s;
										file = t;
										
									}
									
									if(!spaces[rank][file].piece.threatened(spaces, isWhite)){
										
										undoMove(spaces, t, s, y, x, holder);
										lastMove[5] = 0;
										
										return false;
									}
									
									undoMove(spaces, t, s, y, x, holder);
									rank = rankHold;
									file = fileHold;
									lastMove[5] = 0;
								}
							}
						}
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * Method called to test if a specified king is in checkmate
	 * @param spaces - current spaces on the board
	 * @param file - file of the king object to be tested
	 * @param rank - rank of the king object to be tested
	 * @param isWhite - specifies which color king
	 * @return true if king piece at [rank, file] is in checkmate
	 */
	public static boolean checkmate(Space[][] spaces, int file, int rank, boolean isWhite){
		if(spaces[rank][file].piece.threatened(spaces, isWhite)){
			if(stalemate(spaces, file, rank, isWhite))
				return true;
			System.out.println("Check\n");	
		}	
		return false;
	}
	/**
	 * Method called to undo moves that were determined illegal or
	 * were temporary used for testing for checkmate
	 * @param spaces - spaces on the board
	 * @param toFile - destination file that was input by user
	 * @param toRank - destination rank that was input by user
	 * @param fromFile - starting file that was input by user
	 * @param fromRank - starting rank that was input by user
	 * @param holder - Piece object held during move that may have been an active piece
	 * that was overridden during a move and needs to be replaced on the board
	 */
	public static void undoMove(Space[][] spaces, int toFile, int toRank, int fromFile, int fromRank, Piece holder){
		
		if(lastMove[5] == 1){
			
			if(toFile > fromFile){
				spaces[fromFile+1][fromRank].piece = new Pawn(fromFile+1, fromRank, "bp", false);
			}
			if(toFile < fromFile){
				spaces[fromFile-1][fromRank].piece = new Pawn(fromFile-1, fromRank, "bp", false);
			}
		}
		
		spaces[fromRank][fromFile].piece = spaces[toRank][toFile].piece;
		spaces[fromRank][fromFile].piece.setLocation(fromFile, fromRank);
		
		spaces[toRank][toFile].piece = holder;
		
	}
}
