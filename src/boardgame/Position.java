package boardgame;

public class Position {
	
	//Atributos
	private Integer row;
	private Integer column;
	
	
	//Construtor
	public Position() {
	}

	public Position(Integer row, Integer column) {
		this.row = row;
		this.column = column;
	}
	
	
	//Métodos
	public void setValues(int row, int column) {
		
	}

	
	//Métodos de acesso
	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	public Integer getColumn() {
		return column;
	}

	public void setColumn(Integer column) {
		this.column = column;
	}

	@Override
	public String toString() {
		return  row + ", " + column;
	}
	
	
}
