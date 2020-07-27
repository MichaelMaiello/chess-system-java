package boardgame;

public class Board {
	
	//Atributos
	private Integer rows;
	private Integer columns;
	private Piece[][] pieces;
	
	
	//construtor
	public Board() {
	}
	
	public Board(Integer rows, Integer columns) {
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}
	
	
	//Métodos de acesso
	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getColumns() {
		return columns;
	}

	public void setColumns(Integer columns) {
		this.columns = columns;
	}
	
	//Métodos
	public Piece pieces(int row, int column) {
		return pieces[row][column];
	}
	
	public Piece pieces(Position position) {
		return pieces[position.getRow()] [position.getColumn()];
	}
	
	public void placepieces(Piece piece, Position position) {
		pieces[position.getRow()] [position.getColumn()] = piece;
		piece.position = position;
	}
}
