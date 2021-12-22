/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.game;

import chess.common.ChessPiece;
import java.lang.*;
import java.util.List;

/**
 *
 * @author User
 */
public abstract class ChPieces implements ChessPiece {
    private int x;
    private int y;
    private boolean color;
    
    public ChPieces(int x, int y, boolean color) {
    this.x= x;
    this.y= y;
    this.color=color;
    }
    
    public void setX(int x){
    this.x=x;
}
    public void setY(int y){
    this.y=y;
}
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public boolean isWhite() {
        return color;
    }
    @Override
    public abstract char toChar() ;

    public abstract boolean isValid(int sourceX, int sourceY, int targetX, int targetY);
    
}
