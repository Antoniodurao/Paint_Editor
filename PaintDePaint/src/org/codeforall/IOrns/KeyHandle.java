package org.codeforall.IOrns;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;

import java.io.IOException;

public class KeyHandle implements KeyboardHandler, Runnable {


    private Grid grid;
    private int colorNum = 1;

    KeyHandle(Grid grid) {
        this.grid = grid;
    }

    public void init() {
        Keyboard kb = new Keyboard(this);

        KeyboardEvent left = new KeyboardEvent();
        left.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        left.setKey(KeyboardEvent.KEY_A);
        kb.addEventListener(left);

        KeyboardEvent right = new KeyboardEvent();
        right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        right.setKey(KeyboardEvent.KEY_D);
        kb.addEventListener(right);

        KeyboardEvent up = new KeyboardEvent();
        up.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        up.setKey(KeyboardEvent.KEY_W);
        kb.addEventListener(up);

        KeyboardEvent down = new KeyboardEvent();
        down.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        down.setKey(KeyboardEvent.KEY_S);
        kb.addEventListener(down);

        KeyboardEvent paint = new KeyboardEvent();
        paint.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        paint.setKey(KeyboardEvent.KEY_SPACE);
        kb.addEventListener(paint);

        KeyboardEvent clear = new KeyboardEvent();
        clear.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        clear.setKey(KeyboardEvent.KEY_BACK_SLASH);
        kb.addEventListener(clear);

        KeyboardEvent save = new KeyboardEvent();
        save.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        save.setKey(KeyboardEvent.KEY_C);
        kb.addEventListener(save);

        KeyboardEvent load = new KeyboardEvent();
        load.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        load.setKey(KeyboardEvent.KEY_ENTER);
        kb.addEventListener(load);

        KeyboardEvent change = new KeyboardEvent();
        change.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        change.setKey(KeyboardEvent.KEY_M);
        kb.addEventListener(change);

        KeyboardEvent undo = new KeyboardEvent();
        undo.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        undo.setKey(KeyboardEvent.KEY_U);
        kb.addEventListener(undo);
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_W:
                if (Cursor.currentX <= 10) {
                    break;
                }
                Cursor.currentX -= 20;
                Cursor.player.translate(0, -20);
                break;
            case KeyboardEvent.KEY_S:
                if (Cursor.currentX >= grid.getHeight() - 10) {
                    break;
                }
                Cursor.currentX += 20;
                Cursor.player.translate(0, 20);
                break;
            case KeyboardEvent.KEY_A:
                if (Cursor.currentY <= 10) {
                    break;
                }
                Cursor.currentY -= 20;
                Cursor.player.translate(-20, 0);
                break;
            case KeyboardEvent.KEY_D:
                if (Cursor.currentY >= grid.getWidth() - 10) {
                    break;
                }
                Cursor.currentY += 20;
                Cursor.player.translate(20, 0);
                break;
            case KeyboardEvent.KEY_SPACE:
                Squares.changeColor(Squares.getSquare(Cursor.currentY, Cursor.currentX), Cursor.getColor());
                break;
            case KeyboardEvent.KEY_BACK_SLASH:
                Squares.clearAll();
                break;
            case KeyboardEvent.KEY_C:
                try {
                    Squares.save();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            case KeyboardEvent.KEY_ENTER:
                try {
                    Squares.returnSavedGame();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            case KeyboardEvent.KEY_M:
                colorNum++;
                if (colorNum > 8) {
                    colorNum = 1;
                }
                Cursor.changeColor(colorNum);
                break;
            case KeyboardEvent.KEY_U:
                Squares.undo();
                break;
        }

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }

    @Override
    public void run() {
        init();
    }
}