package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		ChessMatch chessMath = new ChessMatch();
		List<ChessPiece> captured = new ArrayList<ChessPiece>();

		while (!chessMath.getCheckMate()) {
			try {
				UI.clearScreen();
				UI.printMatch(chessMath, captured);
				System.out.println();
				System.out.print("Source: ");
				ChessPosition source = UI.readChessPosition(sc);
				
				boolean[][] possibleMoves = chessMath.possibleMoves(source);
				UI.clearScreen();
				UI.printBoard(chessMath.getPieces(), possibleMoves);
				System.out.println();
				System.out.print("Target: ");
				ChessPosition target = UI.readChessPosition(sc);

				ChessPiece capturedPiece = chessMath.performChessMove(source, target);
				
				if (capturedPiece !=null) {
					captured.add(capturedPiece);
				}
				
				if (chessMath.getPromoted() != null) {
					System.out.print("Enter piece for promotion (B/C/R/Q): ");
					String type = sc.nextLine();
					chessMath.replacePromotedPiece(type);
				}
				
			} catch (ChessException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
		
		UI.clearScreen();
		UI.printMatch(chessMath, captured);
	}
}
