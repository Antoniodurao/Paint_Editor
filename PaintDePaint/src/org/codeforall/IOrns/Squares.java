package org.codeforall.IOrns;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

import java.io.*;
import java.util.HashMap;

public class Squares {

    private int X = 10;
    private int Y = 10;
    private static boolean painted = false;

    private Grid grid;
    private static HashMap mapX = new HashMap<>();
    private static HashMap mapY = new HashMap<>();
    private static HashMap mapPaint = new HashMap<>();
    private static Rectangle[] arrRec = new Rectangle[625];
    private static Rectangle[] paintedArray = new Rectangle[625];
    private static Rectangle[] undoArray = new Rectangle[625];

    public void tryIt(Grid grid) {
        this.grid = grid;
        arrRec = new Rectangle[grid.getNumOfSquares()];
        paintedArray = new Rectangle[grid.getNumOfSquares()];
        undoArray = new Rectangle[grid.getNumOfSquares()];
        for (int i = 0; i < arrRec.length; i++) {
            if (checkTheBordersX()) {
                X = 10;
                Y += 20;
            }
            if (checkTheBordersY()) {
                return;
            }
            Rectangle square = new Rectangle(X, Y, 20, 20);
            arrRec[i] = square;
            mapX.put(arrRec[i], X);
            mapY.put(arrRec[i], Y);
            mapPaint.put(arrRec[i], 0);
            X += 20;
            square.setColor(Color.BLACK);
            square.draw();
        }
    }

    private boolean checkTheBordersX() {
        return X > grid.getHeight();
    }

    private boolean checkTheBordersY() {
        return Y > grid.getWidth();
    }

    public static Rectangle getSquare(int X, int Y) {
        for (Rectangle square : arrRec) {
            if (mapX.get(square).equals(X) && mapY.get(square).equals(Y)) {
                return square;
            }
        }
        return null;
    }

    public static void changeColor(Rectangle square, Color color) {
        if (color.equals(Color.BLACK)) {
            paintSquare(square, 1, color);
        } else if (color.equals(Color.BLUE)) {
            paintSquare(square, 2, color);
        } else if (color.equals(Color.RED)) {
            paintSquare(square, 3, color);
        } else if (color.equals(Color.GREEN)) {
            paintSquare(square, 4, color);
        } else if (color.equals(Color.CYAN)) {
            paintSquare(square, 5, color);
        } else if (color.equals(Color.DARK_GRAY)) {
            paintSquare(square, 6, color);
        } else if (color.equals(Color.LIGHT_GRAY)) {
            paintSquare(square, 7, color);
        } else if (color.equals(Color.ORANGE)) {
            paintSquare(square, 8, color);
        } else {
            square.setColor(Color.BLACK);
            square.draw();
            mapPaint.replace(square, 0);
            for (Rectangle squares : paintedArray) {
                if (squares.equals(square)) {
                    square = null;
                }
            }
            for(int i = 0; i < undoArray.length; i++){
                if(undoArray[i] != null) {
                    continue;
                }
                undoArray[i] = square;
                return;
            }
        }
    }

    private static void paintSquare(Rectangle square, int num, Color color ){
        square.setColor(color);
        square.fill();
        mapPaint.replace(square, num);
        for (int i = 0; i < paintedArray.length; i++) {
            if (paintedArray[i] == null) {
                paintedArray[i] = square;
            }
        }
        for(int i = 0; i < undoArray.length; i++){
            if(undoArray[i] != null) {
                continue;
            }
            undoArray[i] = square;
            return;
        }
    }

    public static void undo(){
        for(int i = undoArray.length-1; i >= 0; i--){
            if(undoArray[i] == null){
                continue;
            }
            if(undoArray[i] != null){
                undoArray[i].setColor(Color.BLACK);
                undoArray[i].draw();
                mapPaint.replace(undoArray[i], 0);
                undoArray[i] = null;
                return;
            }
        }
    }

    public static void clearAll() {
        for (int i = 0; i < arrRec.length - 1; i++) {
            mapPaint.replace(arrRec[i], 0);
            arrRec[i].setColor(Color.BLACK);
            arrRec[i].draw();
            undoArray[i] = null;
        }
    }

    public static void save() throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("/Users/codecadet/Documents/MyExercises/PaintDePaint/src/org/codeforall/IOrns/PaintedArray.txt"));
        for (int i = 0; i < arrRec.length; i++) {
            if (mapPaint.get(arrRec[i]) == null) {
                continue;
            }
            if (mapPaint.get(arrRec[i]).equals(1)) {
                bufferedWriter.write("1");
            } else if (mapPaint.get(arrRec[i]).equals(2)) {
                bufferedWriter.write("2");
            } else if (mapPaint.get(arrRec[i]).equals(3)) {
                bufferedWriter.write("3");
            }  else if (mapPaint.get(arrRec[i]).equals(4)) {
                bufferedWriter.write("4");
            } else if (mapPaint.get(arrRec[i]).equals(5)) {
                bufferedWriter.write("5");
            } else if (mapPaint.get(arrRec[i]).equals(6)) {
                bufferedWriter.write("6");
            } else if (mapPaint.get(arrRec[i]).equals(7)) {
                bufferedWriter.write("7");
            } else if (mapPaint.get(arrRec[i]).equals(8)) {
                bufferedWriter.write("8");
            } else if(mapPaint.get(arrRec[i]).equals(0)){
                bufferedWriter.write("0");
            }

        }
        bufferedWriter.close();
    }

    public static void returnSavedGame() throws IOException {
        String Code = "";
        BufferedReader bf = new BufferedReader(new FileReader("/Users/codecadet/Documents/MyExercises/PaintDePaint/src/org/codeforall/IOrns/PaintedArray.txt"));
        String line = "";
        while (line != null) {
            line = bf.readLine();
            if (line == null) {

            } else {
                Code += line;
            }
        }
        bf.close();
        String[] oneAndZero = Code.split("");
        for (int i = 0; i < arrRec.length - 1; i++) {
            if (oneAndZero[i].equals("1")) {
                mapPaint.replace(arrRec[i], 1);
                arrRec[i].setColor(Color.BLACK);
                arrRec[i].fill();
            } else if(oneAndZero[i].equals("2")){
                mapPaint.replace(arrRec[i], 2);
                arrRec[i].setColor(Color.BLUE);
                arrRec[i].fill();
            } else if(oneAndZero[i].equals("3")){
                mapPaint.replace(arrRec[i], 3);
                arrRec[i].setColor(Color.RED);
                arrRec[i].fill();
            } else if(oneAndZero[i].equals("4")){
                mapPaint.replace(arrRec[i], 4);
                arrRec[i].setColor(Color.GREEN);
                arrRec[i].fill();
            }else if(oneAndZero[i].equals("5")){
                mapPaint.replace(arrRec[i], 5);
                arrRec[i].setColor(Color.CYAN);
                arrRec[i].fill();
            }else if(oneAndZero[i].equals("6")){
                mapPaint.replace(arrRec[i], 6);
                arrRec[i].setColor(Color.DARK_GRAY);
                arrRec[i].fill();
            }else if(oneAndZero[i].equals("7")){
                mapPaint.replace(arrRec[i], 7);
                arrRec[i].setColor(Color.LIGHT_GRAY);
                arrRec[i].fill();
            }else if(oneAndZero[i].equals("8")){
                mapPaint.replace(arrRec[i], 8);
                arrRec[i].setColor(Color.ORANGE);
                arrRec[i].fill();
            } else{
                mapPaint.replace(arrRec[i], 0);
                arrRec[i].setColor(Color.BLACK);
                arrRec[i].draw();
            }
        }
    }

}

