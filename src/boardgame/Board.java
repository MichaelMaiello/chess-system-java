package boardgame;

public class Board {

	// Atributos
	private Integer rows;
	private Integer columns;
	private Piece[][] pieces;

	// construtor
	public Board() {
	}

	public Board(Integer rows, Integer columns) {
		if (rows < 1 || columns < 1) {
			throw new BoardException("Erro criando tabuleiro");
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	// Métodos de acesso
	public Integer getRows() {
		return rows;
	}

	public Integer getColumns() {
		return columns;
	}

	// Métodos
	public Piece pieces(int row, int column) {
		if (!positionExists(row, column)) {
			throw new BoardException("Posição não existe!");
		}
		return pieces[row][column];
	}

	public Piece pieces(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Posição não existe!");
		}
		return pieces[position.getRow()][position.getColumn()];
	}

	public void placepiece(Piece piece, Position position) {
		if (thereIsAPiece(position)) {
			throw new BoardException("Já existe uma peça na posição " + position);
		}
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}
	
	public Piece removePiece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Posição não existe!");
		}
		
		if (pieces(position) == null) {
			return null;
		}
		
		Piece aux = pieces(position);
		aux.position = null;
		pieces[position.getRow()][position.getColumn()] = null;
		return aux;
	}

	public boolean positionExists(int row, int column) {
		return row >= 0 && row < rows && column >= 0 && column < columns;
	}

	public boolean positionExists(Position position) {
		return positionExists(position.getRow(), position.getColumn());
	}

	public boolean thereIsAPiece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Posição não existe!");
		}
		return pieces(position) != null;
	}
}
