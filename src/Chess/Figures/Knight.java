package Chess.Figures;

import Chess.Board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Knight extends Chess.Figures.Figure {
    public Knight(String color, Board board, int[] coord) {
        super(color, board, coord);
        if (Objects.equals(color, "black")){
            setSymbol("♞");
        } else setSymbol("♘");
        type = Chess.Figures.FigureTypes.KNIGHT;
    }

    @Override
    public boolean checkMoveValidity(Board board, int[] startCoords, int[] endCoords) {
        if (endCoords[0] < 0 || endCoords[0] > 7 || endCoords[1] < 0 || endCoords[1] > 7){
            System.out.println("End index out of range. Pick something that's on the board.");
            return false;
        }

        String startingColor = board.selectFigure(startCoords).getColor();
        String endColor = board.selectFigure(endCoords).getColor();

        List<Figure> possibleTargetSpaces = new ArrayList<>();
        possibleTargetSpaces.add(board.selectFigure(new int[]{startCoords[0] - 2, startCoords[1] + 1}));
        possibleTargetSpaces.add(board.selectFigure(new int[]{startCoords[0] - 1, startCoords[1] + 2}));
        possibleTargetSpaces.add(board.selectFigure(new int[]{startCoords[0] + 1, startCoords[1] + 2}));
        possibleTargetSpaces.add(board.selectFigure(new int[]{startCoords[0] + 2, startCoords[1] + 1}));
        possibleTargetSpaces.add(board.selectFigure(new int[]{startCoords[0] + 2, startCoords[1] - 1}));
        possibleTargetSpaces.add(board.selectFigure(new int[]{startCoords[0] + 1, startCoords[1] - 2}));
        possibleTargetSpaces.add(board.selectFigure(new int[]{startCoords[0] - 1, startCoords[1] - 2}));
        possibleTargetSpaces.add(board.selectFigure(new int[]{startCoords[0] - 2, startCoords[1] - 1}));

        for(Figure figure : possibleTargetSpaces){
            if (figure == null) {
                continue;
            }
            if (Arrays.equals(figure.getCoordinate(), endCoords) && !Objects.equals(startingColor, endColor)){
                return true;
            }
        }

        return false;
    }
}
