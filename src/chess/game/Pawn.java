/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.game;

import chess.common.ChessPiece;
import java.util.List;

/**
 *
 * @author User
 */
public class Pawn extends ChPieces {

    public Pawn(int x, int y, boolean color) {
        super(x, y, color);
    }

    @Override
    public char toChar() {
        if (isWhite()) {
            return 'I';
        } else {
            return 'i';
        }
    }

    @Override
    public boolean isValid(int sourceX, int sourceY, int targetX, int targetY) {
        if ((sourceX == targetX) && (sourceY == targetY)) {
            return false;
        }

        if (isWhite()) {
            if (sourceY == 1) {
                if ((targetY - sourceY == 1) && (sourceX == targetX) && Board.checkPiece(targetX, targetY) == false) {
                    return true;
                }
                if ((targetY - sourceY == 2) && (sourceX == targetX) && (Board.checkPiece(targetX, targetY) == false) && (Board.checkPiece(targetX, targetY-1) == false)) {
                    return true;
                }

            }
            if (targetY - sourceY == 1 && Board.checkPiece(targetX, targetY) == false && sourceX == targetX) {
                return true;
            }
            if (targetY - sourceY == 1 && Math.abs(sourceX - targetX) == 1 && Board.checkPiece(targetX, targetY) == true) {
                return true;
            }
        }
        if (isWhite() == false) {
            if (sourceY == 6) {
                if ((sourceY - targetY == 1) && (sourceX == targetX) && Board.checkPiece(targetX, targetY) == false) {
                    return true;
                }
                if ((sourceY - targetY == 2) && (sourceX == targetX) && (Board.checkPiece(targetX, targetY) == false) && (Board.checkPiece(targetX, targetY + 1) == false)) {
                    return true;
                }

            }
            if (sourceY - targetY == 1 && Board.checkPiece(targetX, targetY) == false && sourceX == targetX) {
                return true;
            }
            if (sourceY - targetY == 1 && Math.abs(sourceX - targetX) == 1 && Board.checkPiece(targetX, targetY) == true) {
                return true;
            }
        }
        return false;
    }

}
