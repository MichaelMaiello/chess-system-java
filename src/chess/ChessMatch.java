package chess;

import java.nio.channels.IllegalSelectorException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.border.Border;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
	
	//Atributos 
	private Board board;
	private int turn;
	private Color currentPlayer;
	private boolean check;
	private boolean checkmate;
	
	
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();
	
	//construtor
	public ChessMatch() {
		board = new Board(8, 8);
		turn = 1;
		currentPlayer = Color.WHITE;
		initialSetup();
	}

	
	//Métodos de acesso 
	public int getTurn() {
		return turn;
	}
	
	public Color getCurrentPlayer() {
		return currentPlayer;
	}
	
	public boolean getCheck() {
		return check;
	}
	
	public boolean getCheckMate() {
		return checkmate;
	}
	
	
	
	//Método
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);
		validateTargetPosition(source, target);
		Piece capturedPiece = makeMove(source, target);
		
		if(testCheck(currentPlayer)) {
			undoMove(source, target, capturedPiece);
			throw new ChessException("Você não pode se colocar em check");
		}
		check = (testCheck(opponent(currentPlayer))) ? true : false;
		if(testCheckMate(opponent(currentPlayer))){
			checkmate = true;
		}
		nextTurn();
		return (ChessPiece)capturedPiece;
	}
	
	
	private Piece makeMove(Position source, Position target) {
		Piece p = board.removePiece(source);
		Piece capturedPiece = board.removePiece(target);
		board.placepieces(p, target);
		if (capturedPiece !=null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
		return capturedPiece;
	}

	private void undoMove(Position source, Position target, Piece capturedPiece) {
		Piece p =  board.removePiece(target);
		board.placepieces(p, source);
		
		if (capturedPiece != null) {
			board.placepieces(capturedPiece, target); 
			capturedPieces.remove(capturedPiece);
			piecesOnTheBoard.add(capturedPiece);
		}
	}

	private void validateSourcePosition(Position position) {
		if (!board.thereIsAPiece(position)) {
			throw new ChessException("Não existe peça na posição de origem");
		}
		if (currentPlayer != ((ChessPiece)board.pieces(position)).getColor()) {
			throw new ChessException("Peça escolhida não é sua");
		}
		if (!board.pieces(position).isThereAnyPossibleMove()) {
			throw new ChessException("Não existe movimento possivel para peça");
		}
	}


	
	public ChessPiece[][] getPieces(){
		ChessPiece[][] mat = new ChessPiece[board.getRows()] [board.getColumns()];
		for(int i=0; i<board.getRows(); i++) {
			for (int j = 0; j <board.getColumns(); j++) {
				mat[i][j] = (ChessPiece) board.pieces(i, j);
			}
		}
		return mat;
	}
	
	public boolean[][] possibleMoves(ChessPosition sourcePosition){
		Position position = sourcePosition.toPosition();
		validateSourcePosition(position);
		return board.pieces(position).possibleMoves();
	}
	
	private void validateTargetPosition(Position source, Position target) {
		if (!board.pieces(source).possibleMove(target)){
			throw new ChessException("A peça escolhida não pode se mover para posição de destino");
		}
	}
	
	private void nextTurn() {
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	private Color opponent(Color color) {
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	private ChessPiece King(Color color) {
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		for (Piece p : list) {
			if (p instanceof King) {
				return (ChessPiece)p;
			}
		}
		throw new IllegalStateException("There in no "+ color + " King on the board");
	}
	
	private boolean testCheck(Color color) {
		Position kingPosition = King(color).getChessPosition().toPosition();
		List<Piece> opponentPirces  = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList());
		for (Piece p : opponentPirces) {
			boolean[][] mat = p.possibleMoves();
			if (mat[kingPosition.getRow()][kingPosition.getColumn()]) {
				return true;
			}
		}
		return false;
	}
	
	private boolean testCheckMate(Color color) {
		if (!testCheck(color)) {
			return false;
		}
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		for (Piece p : list) {
			boolean[][] mat = p.possibleMoves();
			for (int i = 0; i < board.getRows(); i++) {
				for (int j = 0; j < board.getColumns(); j++) {
					if (mat[i][j]) {
						Position source = ((ChessPiece)p).getChessPosition().toPosition();
						Position target = new Position(i, j);
						Piece capturedPiece = makeMove(source, target);
						boolean testCheck = testCheck(color);
						undoMove(source, target, capturedPiece);
						if (!testCheck) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placepieces(piece, new ChessPosition(column, row).toPosition());
		piecesOnTheBoard.add(piece);
	}
	
	private void initialSetup() {
		placeNewPiece('h', 7, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new King(board, Color.WHITE));
        
        placeNewPiece('b', 8, new Rook(board, Color.BLACK));
        placeNewPiece('a', 8, new King(board, Color.BLACK));
       		
	}
}
