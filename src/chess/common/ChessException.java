/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.common;

public class ChessException extends Exception {

    private final ChessError error;

    public enum ChessError {

        MOVE_PARSE_ERROR,
        NO_PIECE_TO_MOVE,
        CANNOT_MOVE_OPPONENTS_PIECE,
        CANNOT_CAPTURE_OWN_PIECE,
        JUMP_OVER_PIECE_NOT_ALLOWED,
        MOVE_RULE_VIOLATION
    }

    public ChessException(ChessError error) {
        super();
        this.error = error;
    }

    @Override
    public String getMessage() {
        switch (error) {
            case MOVE_PARSE_ERROR:
                return "Invalid move.";
            case NO_PIECE_TO_MOVE:
                return "No piece to move.";
            case CANNOT_MOVE_OPPONENTS_PIECE:
                return "You cannot move opponent's piece";
            case CANNOT_CAPTURE_OWN_PIECE:
                return "You cannot capture your own piece.";
            case JUMP_OVER_PIECE_NOT_ALLOWED:
                return "This piece cannot jump over other pieces.";
            case MOVE_RULE_VIOLATION:
                return "This piece cannot move to here.";
            default:
                throw new AssertionError(error.name());
        }
    }
}
