package org.codeforall.IOrns;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Color;

public class Grid extends Rectangle {

    Rectangle grid;
    Squares squares = new Squares();
    private final int width;
    private final  int height;


    public Grid(int width, int height) {
        grid = new Rectangle(10, 10, width, height);
        this.width = width;
        this.height = height;
        grid.setColor(Color.BLACK);
        grid.draw();

    }

    public void init() {
        squares.tryIt(this);
        KeyHandle walk = new KeyHandle(this);
        Thread t = new Thread(walk);
        t.start();
        Cursor player = new Cursor();
    }

    public int getNumOfSquares(){
        int x = ((getWidth()/2)/10);
        return x * x + 1;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
