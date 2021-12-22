/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.game;


/**
 *
 * @author User
 */
public class Queen extends ChPieces {

    public Queen(int x, int y, boolean color) {
        super(x, y, color);
    }

    @Override
    public char toChar() {
        if (isWhite()) {
            return 'Q';
        } else {
            return 'q';
        }

    }

    @Override
    public boolean isValid(int sourceX, int sourceY, int targetX, int targetY) {
        int drX ;
        int drY ;
        if ((sourceX == targetX) && (sourceY == targetY)) {
            return false;
        }

        if (Math.abs(sourceX - targetX) == Math.abs(sourceY - targetY)) {
            if (targetY > sourceY) {
                drY = 1;
            } else {
                drY = -1;
            }
            if (targetX > sourceX) {
                drX = 1;
            } else {
                drX = -1;
            }
            for (int i = 1; i < Math.abs(targetX - sourceX); i++) {
                if (Board.checkPiece(sourceX + i * drX, sourceY + i * drY)) {
                    return false;
                }
            }
            return true;
        } else if (sourceX == targetX) {
            if (targetY > sourceY) {
                drY = 1;
            } else {
                drY = -1;
            }
            for (int i = 1; i < Math.abs(targetY - sourceY); i++) {
                if (Board.checkPiece(sourceX, sourceY + i * drY)) {
                    return false;
                }
            }
            return true;
        }
        else if (sourceY == targetY) {
            if (targetX > sourceX) {
                drX = 1;
            } else {
                drX = -1;
            }
        
        for (int i = 1; i < Math.abs(targetX - sourceX) ; i++) {
            if (Board.checkPiece(sourceX + i * drX, sourceY)) {
                return false;
            }

        }
        return true;
    }
        return false;
    }

}
