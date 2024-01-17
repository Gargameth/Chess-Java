package Chess;

import Chess.Figures.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
    List<Figure> board = new ArrayList<>();

    public Board(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == 0){
                    switch (j){
                        case 0, 7 -> board.add(new Castle("black", this, new int[]{i, j}));
                        case 1, 6 -> board.add(new Knight("black", this, new int[]{i, j}));
                        case 2, 5 -> board.add(new Bishop("black", this, new int[]{i, j}));
                        case 3 -> board.add(new Queen("black", this, new int[]{i, j}));
                        case 4 -> board.add(new King("black", this, new int[]{i, j}));
                    }
                } else if (i == 1){
                    board.add(new Pawn("black", this, new int[]{i, j}));
                } else if (i == 6){
                    board.add(new Pawn("white", this, new int[]{i, j}));
                } else if (i == 7){
                    switch (j){
                        case 0, 7 -> board.add(new Castle("white", this, new int[]{i, j}));
                        case 1, 6 -> board.add(new Knight("white", this, new int[]{i, j}));
                        case 2, 5 -> board.add(new Bishop("white", this, new int[]{i, j}));
                        case 3 -> board.add(new Queen("white", this, new int[]{i, j}));
                        case 4 -> board.add(new King("white", this, new int[]{i, j}));
                    }
                } else board.add(new Empty(this, new int[]{i, j}));
            }
        }
    }

    public List<Figure> getBoard(){
        return board;
    }

    public void printBoard(){
        int number = 8;
        System.out.println("  " + " A " + "  B " + "  C " + " D " + "  E " + "  F " + " G " + "  H ");
        for (Figure figure : board){
            if (figure.getCoordinate()[1] == 0){
                System.out.print(number);
                number--;
            }

            System.out.print("  " + figure.getSymbol());

            if (figure.getCoordinate()[1] == 7){
                System.out.println();
            }
        }
    }

    public boolean checkInputValidity(String input){
        String validLetters = "ABCDEFGH";
        int[] validNumbers = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        if (input.length() != 2){
            System.out.println("Input is too long, or too short.");
            return false;
        } else if(!validLetters.contains(input.split("")[0].toUpperCase()) || !Arrays.toString(validNumbers).contains(input.split("")[1])){
            System.out.println("Input out of range.");
            return false;
        }
        return true;
    }

    public Figure selectFigure(int[] coord){
        for (Figure figure : board){
            if (Arrays.equals(figure.getCoordinate(), new int[]{coord[0], coord[1]})){
                System.out.println(figure.getClass());
                System.out.println(Arrays.toString(figure.getCoordinate()));
                System.out.println(figure.getColor());
                return figure;
            }
        }
        return null;
    }

    public int flipCoord(int coord){
        int returnedCoord;
        switch (coord){
            case 1 -> returnedCoord = 7;
            case 2 -> returnedCoord = 6;
            case 3 -> returnedCoord = 5;
            case 4 -> returnedCoord = 4;
            case 5 -> returnedCoord = 3;
            case 6 -> returnedCoord = 2;
            case 7 -> returnedCoord = 1;
            case 8 -> returnedCoord = 0;
            default -> returnedCoord = -1;
        }
        return returnedCoord;
    }

    public int flipLetterCoord(String letter){
        int returnedNumber;
        switch (letter.toUpperCase()){
            case "A" -> returnedNumber = 0;
            case "B" -> returnedNumber = 1;
            case "C" -> returnedNumber = 2;
            case "D" -> returnedNumber = 3;
            case "E" -> returnedNumber = 4;
            case "F" -> returnedNumber = 5;
            case "G" -> returnedNumber = 6;
            case "H" -> returnedNumber = 7;
            default -> returnedNumber = -1;
        }
        return returnedNumber;
    }

    public void fireMoveWithFigure(int[] startCoords, int[] targetCoords){
        selectFigure(startCoords).move(this, startCoords, targetCoords);
    }
}
