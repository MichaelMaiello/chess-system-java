package boardgame;

public class Piece {
	
	//Atributos
	protected Position position;
	private Board board;
	
	//Construtor
	public Piece() {
	}

	public Piece(Board board) {
		this.board = board;
	}
	
	
	//M�todos
	public boolean[][] possibleMoves(){
		
		return null;
	}
	
	public boolean possibleMove() {
		return true;
	}
	
	public boolean isThereAnyPossibleMove() {
		return true;
	}

	
	//M�todos de acesso
	protected Board getPosition() {
		return board;
	}
}
