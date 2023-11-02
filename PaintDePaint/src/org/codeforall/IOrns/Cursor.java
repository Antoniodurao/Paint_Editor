package org.codeforall.IOrns;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Cursor extends Rectangle {

    static Rectangle player;
    private static Color color = Color.BLACK;

    static int currentX = 10;
    static int currentY = 10;

    public Cursor(){
        player = new Rectangle(10,10,20,20);
        color = Color.BLACK;
        playerSetColor();
        player.fill();
    }

    public static Color getColor(){
        return color;
    }

    private static void playerSetColor(){
        player.setColor(color);
    }

    public static void changeColor(int color){
        switch (color){
            case 1:
                Cursor.color = Color.BLACK;
                playerSetColor();
                break;
            case 2:
                Cursor.color = Color.BLUE;
                playerSetColor();
                break;
            case 3:
                Cursor.color = Color.RED;
                playerSetColor();
                break;
        }
    }

}
