package Chess.Figures;

import Chess.Board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Castle extends Figure {
    public Castle(String color, Board board, int[] coord) {
        super(color, board, coord);
        if (Objects.equals(color, "black")){
            setSymbol("♜");
        } else setSymbol("♖");
        type = FigureTypes.CASTLE;
    }

    @Override
    public boolean checkMoveValidity(Board board, int[] startCoords, int[] endCoords) {
        if (startCoords[0] < 0 || startCoords[0] > 7 || startCoords[1] < 0 || startCoords[1] > 7 ||
                endCoords[0] < 0 || endCoords[0] > 7 || endCoords[1] < 0 || endCoords[1] > 7){
            System.out.println("Input index out of range. Try again.");
            return false;
        }

        String direction;
        if (endCoords[0] > startCoords[0]){
            direction = "S";
        } else if (endCoords[0] < startCoords[0]) {
            direction = "N";
        } else if (endCoords[1] < startCoords[1]){
            direction = "W";
        } else if (endCoords[1] > startCoords[1]){
            direction = "E";
        } else direction = "Empty";

        List<Figure> possibleSpaces = new ArrayList<>();
        switch (direction){
            case "S" -> {
                for (int i = 0; i < endCoords[0] - startCoords[0]; i++) {
                    possibleSpaces.add(board.selectFigure(new int[]{startCoords[0] + i + 1, startCoords[1]}));
                }
            }
            case "N" -> {
                for (int i = 0; i < startCoords[0] - endCoords[0]; i++) {
                    possibleSpaces.add(board.selectFigure(new int[]{startCoords[0] - i - 1, startCoords[1]}));
                }
            }
            case "W" -> {
                for (int i = 0; i < startCoords[1] - endCoords[1]; i++) {
                    possibleSpaces.add(board.selectFigure(new int[]{startCoords[0], startCoords[1] - i - 1}));
                }
            }
            case "E" -> {
                for (int i = 0; i < endCoords[1] - startCoords[1]; i++) {
                    possibleSpaces.add(board.selectFigure(new int[]{startCoords[0], startCoords[1] + i + 1}));
                }
            }
        }

        if (possibleSpaces.isEmpty()){
            System.out.println("Wrong input. Try again.");
            return false;
        }

        for (Figure figure : possibleSpaces){
            if (Arrays.equals(figure.getCoordinate(), endCoords) && Objects.equals(figure.getColor(), this.getColor())){
                return false;
            }

            if (!Arrays.equals(figure.getCoordinate(), endCoords) && !(figure instanceof Empty)){
                return false;
            }
        }

        return true;
    }
}
