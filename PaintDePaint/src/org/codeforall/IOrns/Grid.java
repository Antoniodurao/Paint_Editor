package org.codeforall.IOrns;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Color;

public class Grid extends Rectangle {

    Rectangle grid;
    Squares squares = new Squares();
    private int width = 480;
    private int height = 480;

    public Grid() {
        grid = new Rectangle(10, 10, 480, 480);
        grid.setColor(Color.BLACK);
        grid.draw();

    }

    public void init() {
        squares.tryIt();
        KeyHandle walk = new KeyHandle();
        Thread t = new Thread(walk);
        t.start();
        Cursor player = new Cursor();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
