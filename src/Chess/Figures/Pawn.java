package Chess.Figures;

import Chess.Board;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class Pawn extends Chess.Figures.Figure {
    public Pawn(String color, Board board, int[] coord) {
        super(color, board, coord);
        if (Objects.equals(color, "black")){
            setSymbol("♟");
        } else setSymbol("♙");
        type = Chess.Figures.FigureTypes.PAWN;
    }

    @Override
    public void move(Board board, int[] startCoords, int[] endCoords) {
        boolean canMove = checkMoveValidity(board, startCoords, endCoords);
        if (canMove){
            List<Chess.Figures.Figure> piecesOnBoard = board.getBoard();
            Chess.Figures.Figure targetFigure = board.selectFigure(endCoords);
            int initialIndex = piecesOnBoard.indexOf(this);
            int targetIndex = piecesOnBoard.indexOf(targetFigure);
            this.setCoordinate(endCoords);
            piecesOnBoard.set(targetIndex, this);
            piecesOnBoard.set(initialIndex, new Chess.Figures.Empty(board, startCoords));
        }
    }

    @Override
    public boolean checkMoveValidity(Board board, int[] startCoords, int[] endCoords) {
        var currentFigure = board.selectFigure(startCoords);
        var targetFigure = board.selectFigure(endCoords);
        boolean valid = false;
        List<int[]> possibleEndCoords = new ArrayList<>();
        switch (currentFigure.getColor()){
            case "black" -> {
                if (startCoords[0] == 1 && startCoords[1] != 0 && startCoords[1] != 7){
                    possibleEndCoords.add(new int[]{startCoords[0] + 1, startCoords[1] - 1});
                    possibleEndCoords.add(new int[]{startCoords[0] + 1, startCoords[1]});
                    possibleEndCoords.add(new int[]{startCoords[0] + 1, startCoords[1] + 1});
                    possibleEndCoords.add(new int[]{startCoords[0] + 2, startCoords[1]});
                } else if (startCoords[0] == 1 && startCoords[1] == 0) {
                    possibleEndCoords.add(new int[]{startCoords[0] + 1, startCoords[1]});
                    possibleEndCoords.add(new int[]{startCoords[0] + 1, startCoords[1] + 1});
                    possibleEndCoords.add(new int[]{startCoords[0] + 2, startCoords[1]});
                } else if (startCoords[0] == 1 && startCoords[1] == 7) {
                    possibleEndCoords.add(new int[]{startCoords[0] + 1, startCoords[1] - 1});
                    possibleEndCoords.add(new int[]{startCoords[0] + 1, startCoords[1]});
                    possibleEndCoords.add(new int[]{startCoords[0] + 2, startCoords[1]});
                } else if (startCoords[1] == 0){
                    possibleEndCoords.add(new int[]{startCoords[0] + 1, startCoords[1]});
                    possibleEndCoords.add(new int[]{startCoords[0] + 1, startCoords[1] + 1});
                } else if (startCoords[1] == 7){
                    possibleEndCoords.add(new int[]{startCoords[0] + 1, startCoords[1] - 1});
                    possibleEndCoords.add(new int[]{startCoords[0] + 1, startCoords[1]});
                } else {
                    possibleEndCoords.add(new int[]{startCoords[0] + 1, startCoords[1]});
                    possibleEndCoords.add(new int[]{startCoords[0] + 1, startCoords[1] + 1});
                    possibleEndCoords.add(new int[]{startCoords[0] + 1, startCoords[1] - 1});
                }

                if (possibleEndCoords.isEmpty()){
                    valid = false;
                }

                for (int[] coords : possibleEndCoords){
                    if (Arrays.equals(endCoords, coords)){
                        if ((Objects.equals(targetFigure.getColor(), "white") && !Objects.equals(targetFigure.getType(), Chess.Figures.FigureTypes.EMPTY))
                        && (targetFigure.getCoordinate()[0] == startCoords[0] + 1 && (targetFigure.getCoordinate()[1] == startCoords[1] + 1) || (targetFigure.getCoordinate()[1] == startCoords[1] - 1))){
                            valid =  true;
                        } else if (endCoords[0] == startCoords[0] + 2 && endCoords[1] == startCoords[1]) {
                            if (board.selectFigure(new int[]{startCoords[0] + 1, startCoords[1]}).getType() == Chess.Figures.FigureTypes.EMPTY){
                                valid = true;
                            }
                        } else valid = targetFigure.getType() == Chess.Figures.FigureTypes.EMPTY;
                    }
                }
            }
            case "white" -> {
                // Code for the pawns sitting on their starting line. This collects the first and second fields in front of them,
                // and the spaces to their left and right.
                if (startCoords[0] == 6 && startCoords[1] != 0 && startCoords[1] != 7){
                    possibleEndCoords.add(new int[]{startCoords[0] - 1, startCoords[1] - 1});
                    possibleEndCoords.add(new int[]{startCoords[0] - 1, startCoords[1]});
                    possibleEndCoords.add(new int[]{startCoords[0] - 1, startCoords[1] + 1});
                    possibleEndCoords.add(new int[]{startCoords[0] - 2, startCoords[1]});
                }
                // This checks the left most base-line pawn's available field. Checks the first and second fields
                // in front of it, and the field to it's right.
                else if (startCoords[0] == 6 && startCoords[1] == 0) {
                    possibleEndCoords.add(new int[]{startCoords[0] - 1, startCoords[1]});
                    possibleEndCoords.add(new int[]{startCoords[0] - 1, startCoords[1] + 1});
                    possibleEndCoords.add(new int[]{startCoords[0] - 2, startCoords[1]});
                }
                // This code checks the right most pawn's available spaces.
                else if (startCoords[0] == 6 && startCoords[1] == 7) {
                    possibleEndCoords.add(new int[]{startCoords[0] - 1, startCoords[1] - 1});
                    possibleEndCoords.add(new int[]{startCoords[0] - 1, startCoords[1]});
                    possibleEndCoords.add(new int[]{startCoords[0] - 2, startCoords[1]});
                }
                // This should check the left most pawn's normal movement.
                else if (startCoords[1] == 0){
                    possibleEndCoords.add(new int[]{startCoords[0] - 1, startCoords[1]});
                    possibleEndCoords.add(new int[]{startCoords[0] - 1, startCoords[1] + 1});
                } else if (startCoords[1] == 7){
                    possibleEndCoords.add(new int[]{startCoords[0] - 1, startCoords[1] - 1});
                    possibleEndCoords.add(new int[]{startCoords[0] - 1, startCoords[1]});
                } else {
                    possibleEndCoords.add(new int[]{startCoords[0] - 1, startCoords[1]});
                    possibleEndCoords.add(new int[]{startCoords[0] - 1, startCoords[1] + 1});
                    possibleEndCoords.add(new int[]{startCoords[0] - 1, startCoords[1] - 1});
                }

                if (possibleEndCoords.isEmpty()){
                    valid = false;
                }

                for (int[] coords : possibleEndCoords){
                    if (Arrays.equals(endCoords, coords)){
                        if ((Objects.equals(targetFigure.getColor(), "black") && !Objects.equals(targetFigure.getType(), Chess.Figures.FigureTypes.EMPTY))
                                && (targetFigure.getCoordinate()[0] == startCoords[0] - 1 && (targetFigure.getCoordinate()[1] == startCoords[1] + 1) || (targetFigure.getCoordinate()[1] == startCoords[1] - 1))){
                            valid =  true;
                        } else if (endCoords[0] == startCoords[0] + 2 && endCoords[1] == startCoords[1]) {
                            if (board.selectFigure(new int[]{startCoords[0] + 1, startCoords[1]}).getType() == Chess.Figures.FigureTypes.EMPTY){
                                valid = true;
                            }
                        } else valid = targetFigure.getType() == Chess.Figures.FigureTypes.EMPTY;
                    }
                }
            }
            default -> {
                valid = false;
            }
        }
        return valid;
    }
}
