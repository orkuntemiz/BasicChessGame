/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.game;

import java.util.List;

/**
 *
 * @author User
 */
public class Knight extends ChPieces {

    public Knight(int x, int y, boolean color) {
        super(x, y, color);
    }
    @Override
    public char toChar() {
        if (isWhite()){
            return 'N';}
        else
        {return 'n';}
        
    }

    @Override
    public boolean isValid(int sourceX, int sourceY, int targetX, int targetY) {
        if(targetX != sourceX - 1 && targetX != sourceX + 1 && targetX != sourceX + 2 && targetX != sourceX - 2)
            return false;
        if(targetY != sourceY - 2 && targetY != sourceY + 2 && targetY != sourceY - 1 && targetY != sourceY + 1)
            return false;
        return true;
    }

   
    
    
}
