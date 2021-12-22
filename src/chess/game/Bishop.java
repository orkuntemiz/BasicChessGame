/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.game;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class Bishop extends ChPieces {

    public Bishop(int x, int y, boolean color) {
        super(x, y, color);
    }

    @Override
    public char toChar() {
        if (isWhite()) {
            return 'B';
        } else {
            return 'b';
        }

    }

    @Override
    public boolean isValid(int sourceX, int sourceY, int targetX, int targetY) {
        int drX = 0;
        int drY = 0;
        if ((sourceX == targetX) || (sourceY == targetY)) {
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
        for (int i=1;i<Math.abs(targetX-sourceX);i++) {
        if (Board.checkPiece(sourceX+i*drX,sourceY+i*drY)) {
      return false;
    }
  }
        return true;
    }
        return false;
    }
}
