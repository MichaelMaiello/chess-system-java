package chess;

import boardgame.Position;

public class ChessPosition {

	//Atributos
	private char column;
	private int row;
	
	
	//Construtor
	public ChessPosition(char column, int row) {
		if (column < 'a' || column > 'h' || row < 1 || row > 8) {
			throw new ChessException("Erro ao instanciar a posi��o! Valores valodos a1 at� h8 ");
		}
		this.column = column;
		this.row = row;
	}


	//M�todos de acesso
	public char getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}
	
	
	
	//M�todo
	//converte a posi��o do tabuleiro para uma posi��o na matrix
	protected Position toPosition() {
		return new Position(8 - row, column - 'a');
	}
	
	//converte a posi��o da matrix para posi��o do tabuleiro
	protected static ChessPosition fromPosition(Position position) {
		return new ChessPosition((char)('a' + position.getColumn()), 8 - position.getRow());
	}


	@Override
	public String toString() {
		return "" + column + row;
	}
}
