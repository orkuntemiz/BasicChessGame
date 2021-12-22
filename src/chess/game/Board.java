/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.game;

import chess.common.ChessBoard;
import chess.common.ChessException;
import static chess.common.ChessException.ChessError.*;
import chess.common.ChessPiece;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public class Board implements ChessBoard {

    private String[][] chboard;

    public static List<ChPieces> pieces;
    public static int counter = 1;

    public Board() {

        this.pieces = new ArrayList<>();
        this.chboard = new String[8][8];

        for (int i = 0; i < 8; i++) {
            pieces.add(new Pawn(i, 1, true));
        }

        pieces.add(new Bishop(2, 0, true));
        pieces.add(new Bishop(5, 0, true));

        pieces.add(new Rook(0, 0, true));
        pieces.add(new Rook(7, 0, true));

        pieces.add(new Knight(1, 0, true));
        pieces.add(new Knight(6, 0, true));

        pieces.add(new King(4, 0, true));
        pieces.add(new Queen(3, 0, true));

        for (int i = 0; i < 8; i++) {
            pieces.add(new Pawn(i, 6, false));
        }

        pieces.add(new Bishop(2, 7, false));
        pieces.add(new Bishop(5, 7, false));

        pieces.add(new Rook(0, 7, false));
        pieces.add(new Rook(7, 7, false));

        pieces.add(new Knight(1, 7, false));
        pieces.add(new Knight(6, 7, false));
        pieces.add(new King(4, 7, false));
        pieces.add(new Queen(3, 7, false));

    }

    @Override
    public ChessPiece move(String moveText) throws ChessException {
        final String REGEX = "[abcdefgh][1-8]\\s[abcdefgh][1-8]";
        if (moveText.matches(REGEX) == false) {
            throw new ChessException(MOVE_PARSE_ERROR);
        }

        String splitOn = "[\\s]";
        String[] words = moveText.split(splitOn);
        String from = words[0];
        String to = words[1];

        char tempfrx = from.charAt(0);
        char tempfry = from.charAt(1);
        int fromx = (int) tempfrx - 97;
        int fromy = (int) tempfry - 49;

        char temptox = to.charAt(0);
        char temptoy = to.charAt(1);
        int tox = (int) temptox - 97;
        int toy = (int) temptoy - 49;

        if (checkPiece(fromx, fromy)) {
            int activepiece = grabPiece(fromx, fromy);
            if (isWhitesTurn() == pieces.get(activepiece).isWhite()) {
                if (pieces.get(activepiece).isValid(fromx, fromy, tox, toy)) {
                    if (checkPiece(tox, toy) == false) {
                        pieces.get(activepiece).setX(tox);
                        pieces.get(activepiece).setY(toy);
                        counter = counter + 1;
                        if (pieces.get(activepiece) instanceof Pawn && pieces.get(activepiece).getY()==7 && pieces.get(activepiece).isWhite()==true ){
                            int removenint = grabPiece(tox, toy);
                            
                            pieces.add(new Queen(tox, toy, true));
                            ChPieces removen = pieces.remove(removenint);
                            
                        }
                        if (pieces.get(activepiece) instanceof Pawn && pieces.get(activepiece).getY()==0 && pieces.get(activepiece).isWhite()==false ){
                            int removenint = grabPiece(tox, toy);
                            
                            pieces.add(new Queen(tox, toy, false));
                            ChPieces removen = pieces.remove(removenint);
                            
                        }
                        return null;
                    } else {
                        if (pieces.get(grabPiece(tox, toy)).isWhite() == pieces.get(grabPiece(fromx, fromy)).isWhite()) {
                            throw new ChessException(CANNOT_CAPTURE_OWN_PIECE);
                        } else {
                            int removenint = grabPiece(tox, toy);
                            pieces.get(activepiece).setX(tox);
                            pieces.get(activepiece).setY(toy);
                            counter = counter + 1;
                            ChPieces removen = pieces.remove(removenint);
                            return removen;

                        }
                    }

                } else {
                    throw new ChessException(MOVE_RULE_VIOLATION);
                }
            } else {
                throw new ChessException(CANNOT_MOVE_OPPONENTS_PIECE);
            }
        } else {
            throw new ChessException(NO_PIECE_TO_MOVE);
        }
                
    }

    @Override
    public boolean isWhitesTurn() {
        return counter % 2 == 1;
    }

    @Override
    public int getMoveCount() {
        return (int) Math.floor((counter + 1) / 2);
    }

    @Override
    public String getDisplayString() {

        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                chboard[i][j] = null;
            }
        }

        for (int i = 0; i < 8; i = i + 2) {
            for (int j = 0; j < 8; j = j + 2) {
                chboard[i][j] = ".";

            }
        }
        for (int i = 1; i < 8; i = i + 2) {
            for (int j = 1; j < 8; j = j + 2) {
                chboard[i][j] = ".";
            }
        }

        for (int a = 0; a < pieces.size(); a++) {
            int i = pieces.get(a).getX();
            int j = pieces.get(a).getY();
            chboard[i][j] = pieces.get(a).toChar() + "";

        }

        StringBuilder sb = new StringBuilder();
        sb.append("   a   b   c   d   e   f   g   h");
        sb.append("\n");
        sb.append(" +---+---+---+---+---+---+---+---+");
        sb.append("\n");
        for (int j = 0; j < 8; j++) {
            sb.append(j + 1 + "|");
            for (int i = 0; i < 8; i++) {
                if (chboard[i][j] == null) {
                    sb.append(" " + " " + " |");
                } else {
                    sb.append(" " + chboard[i][j] + " |");
                }
            }
            sb.append(j + 1);
            sb.append("\n");
            sb.append(" +---+---+---+---+---+---+---+---+");
            sb.append("\n");

        }
        sb.append("   a   b   c   d   e   f   g   h");
        sb.append("\n");
        return sb.toString();
    }

    @Override
    public void load(BufferedReader reader) throws IOException {
        pieces.clear();
        String[] readlines = new String[8];
        String[][] chararray = new String[8][8];
        
        for (int a = 0; a < 8; a++) {
            readlines[a] = reader.readLine();
            
        }
       
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                chararray[i][j] = (readlines[j].toCharArray())[i] + "";
            }
        }

        
        ///////
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {

                switch (chararray[i][j]) {
                    case "I":
                        pieces.add(new Pawn(i, j, true));
                        break;
                    case "K":
                        pieces.add(new King(i, j, true));
                        break;
                    case "Q":
                        pieces.add(new Queen(i, j, true));
                        break;
                    case "N":
                        pieces.add(new Knight(i, j, true));
                        break;
                    case "B":
                        pieces.add(new Bishop(i, j, true));
                        break;
                    case "R":
                        pieces.add(new Rook(i, j, true));
                        break;
                    case "i":
                        pieces.add(new Pawn(i, j, false));
                        break;
                    case "k":
                        pieces.add(new King(i, j, false));
                        break;
                    case "q":
                        pieces.add(new Queen(i, j, false));
                        break;
                    case "n":
                        pieces.add(new Knight(i, j, false));
                        break;
                    case "b":
                        pieces.add(new Bishop(i, j, false));
                        break;
                    case "r":
                        pieces.add(new Rook(i, j, false));
                        break;
                        
                }
            }
        }
        
        
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                chboard[i][j] = null;
            }
        }

        for (int i = 0; i < 8; i = i + 2) {
            for (int j = 0; j < 8; j = j + 2) {
                chboard[i][j] = ".";

            }
        }
        for (int i = 1; i < 8; i = i + 2) {
            for (int j = 1; j < 8; j = j + 2) {
                chboard[i][j] = ".";
            }
        }

        for (int b = 0; b < pieces.size(); b++) {
            int i = pieces.get(b).getX();
            int j = pieces.get(b).getY();
            chboard[i][j] = pieces.get(b).toChar() + "";

        }

        StringBuilder sb = new StringBuilder();
        sb.append("   a   b   c   d   e   f   g   h");
        sb.append("\n");
        sb.append(" +---+---+---+---+---+---+---+---+");
        sb.append("\n");
        for (int j = 0; j < 8; j++) {
            sb.append(j + 1 + "|");
            for (int i = 0; i < 8; i++) {
                if (chboard[i][j] == null) {
                    sb.append(" " + " " + " |");
                } else {
                    sb.append(" " + chboard[i][j] + " |");
                }
            }
            sb.append(j + 1);
            sb.append("\n");
            sb.append(" +---+---+---+---+---+---+---+---+");
            sb.append("\n");

        }
        sb.append("   a   b   c   d   e   f   g   h");
        sb.append("\n");
        System.out.println(sb);
    }

    @Override
    public void save(PrintWriter writer) {

        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                if (chboard[i][j] == null || ".".equals(chboard[i][j])) {
                    writer.print(".");

                } else {
                    writer.print(chboard[i][j]);

                }
            }
            writer.println();

        }
        writer.flush();
        writer.close();
    }
    //Check if an piece occupies that square
    public static boolean checkPiece(int x, int y) {
        for (int a = 0; a < pieces.size(); a++) {
            if (pieces.get(a).getX() == x && pieces.get(a).getY() == y) {
                return true;
            }
        }
        return false;

    }
    //Return the index of piece occupying that square
    public int grabPiece(int x, int y) {
        for (int a = 0; a < pieces.size(); a++) {
            if (pieces.get(a).getX() == x && pieces.get(a).getY() == y) {
                return a;
            }
        }
        return 9;
    }
}
