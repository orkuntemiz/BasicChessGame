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
public class King extends ChPieces{

    public King(int x, int y, boolean color) {
        super(x, y, color);
    }
    @Override
    public char toChar() {
        if (isWhite()){
            return 'K';}
        else
        {return 'k';}
        
    }

    @Override
    public boolean isValid(int sourceX, int sourceY, int targetX, int targetY) {
        if ((sourceX==targetX) && Math.abs(targetY-sourceY)==1)
            return true;
        if ((sourceY==targetY) && Math.abs(targetX-sourceX)==1)
            return true;
        if (Math.abs(sourceY-targetY)==1 && Math.abs(targetX-sourceX)==1)
            return true;
        return false;
    }

    
    
     
}
