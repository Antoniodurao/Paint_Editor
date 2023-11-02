package org.codeforall.IOrns;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

import java.io.*;
import java.util.HashMap;

public class Squares {

    private int X = 10;
    private int Y = 10;
    private static boolean painted = false;


    private static HashMap mapX = new HashMap<>();
    private static HashMap mapY = new HashMap<>();
    private static HashMap mapPaint = new HashMap<>();
    private static Rectangle[] arrRec = new Rectangle[577];
    private static Rectangle[] paintedArray = new Rectangle[577];
    private static Rectangle[] undoArray = new Rectangle[577];

    public void tryIt() {
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
        if (X > 480) {
            return true;
        }
        return false;
    }

    private boolean checkTheBordersY() {
        if (Y > 480) {
            return true;
        }
        return false;
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
            if ((int) mapPaint.get(square) != 1) {
                square.setColor(color);
                square.fill();
                mapPaint.replace(square, 1);
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
        } else if (color.equals(Color.BLUE)) {
            if ((int) mapPaint.get(square) != 2) {
                square.setColor(color);
                square.fill();
                mapPaint.replace(square, 2);
                for (int i = 0; i < paintedArray.length; i++) {
                    if (paintedArray[i] == null) {
                        paintedArray[i] = square;
                    }
                }
                for( int i = 0; i < undoArray.length; i++){
                    if (undoArray[i] != null) {
                        continue;
                    }
                    undoArray[i] = square;
                    return;
                }
            }

        } else if (color.equals(Color.RED)) {
            if ((int) mapPaint.get(square) != 3) {
                square.setColor(color);
                square.fill();
                mapPaint.replace(square, 3);
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
            } else{
                mapPaint.replace(arrRec[i], 0);
                arrRec[i].setColor(Color.BLACK);
                arrRec[i].draw();
            }
        }
    }

}

