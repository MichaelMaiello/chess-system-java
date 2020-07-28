package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece {

	// Atributos
	private Color color;

	// construtor
	public ChessPiece() {
	}

	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	// Métodos de acesso
	public Color getColor() {
		return color;
	}
	
	
	//Métodos
	protected boolean isThereOpponentPiece(Position position) {
		ChessPiece p = (ChessPiece)getBoard().pieces(position);
		return p != null && p.getColor() !=color;
	}
}
