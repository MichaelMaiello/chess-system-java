package chess;

import boardgame.Board;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
	
	//Atributos 
	private Board board;
	
	
	//construtor
	public ChessMatch() {
		board = new Board(8, 8);
		initialSetup();
	}

	
	//M�todo
	public ChessPiece[][] getPieces(){
		ChessPiece[][] mat = new ChessPiece[board.getRows()] [board.getColumns()];
		for(int i=0; i<board.getRows(); i++) {
			for (int j = 0; j <board.getColumns(); j++) {
				mat[i][j] = (ChessPiece) board.pieces(i, j);
			}
		}
		return mat;
	}
	
	private void initialSetup() {
		board.placepieces(new Rook(board, Color.WHITE), new Position(2,1));
		board.placepieces(new King(board, Color.BLACK), new Position(0,4));
		board.placepieces(new King(board, Color.WHITE), new Position(7,4));
	}
}
