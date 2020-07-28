package boardgame;

public abstract class Piece {

	// Atributos
	protected Position position;
	private Board board;

	// Construtor
	public Piece() {
	}

	public Piece(Board board) {
		this.board = board;
		position = null;
	}

	// M�todos de acesso
	protected Board getBoard() {
		return board;
	}

	// M�todos
	public abstract boolean[][] possibleMoves();

	// M�todo que depende de um m�todo abstrato
	public boolean possibleMove(Position position) {
		return possibleMoves()[position.getRow()][position.getColumn()];
	}

	public boolean isThereAnyPossibleMove() {
		boolean[][] mat = possibleMoves();
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {

				if (mat[i][j]) {
					return true;
				}

			}
		}
		return false;
	}
}
