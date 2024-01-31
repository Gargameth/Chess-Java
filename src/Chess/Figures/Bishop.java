package Chess.Figures;

import Chess.Board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Bishop extends Figure {
    public Bishop(String color, Board board, int[] coord) {
        super(color, board, coord);
        if (Objects.equals(color, "black")){
            setSymbol("♝");
        } else setSymbol("♗");
        type = FigureTypes.BISHOP;
    }

    @Override
    public boolean checkMoveValidity(Board board, int[] startCoords, int[] endCoords) {
        if (startCoords[0] < 0 || startCoords[1] > 7 || endCoords[0] < 0 || endCoords[1] > 7){
            System.out.println("Index out of bounds error. Try with valid coords.");
            return false;
        }

        List<Figure> possibleFieldsNW = fillOutPossibilityCollection(board, startCoords[0], startCoords[1], "NW");
        boolean northWest = findTargetInCollection(possibleFieldsNW, endCoords);
        List<Figure> possibleFieldsNE = fillOutPossibilityCollection(board, startCoords[0], startCoords[1], "NE");
        boolean northEast = findTargetInCollection(possibleFieldsNE, endCoords);
        List<Figure> possibleFieldsSW = fillOutPossibilityCollection(board, startCoords[0], startCoords[1], "SW");
        boolean southWest = findTargetInCollection(possibleFieldsSW, endCoords);
        List<Figure> possibleFieldsSE = fillOutPossibilityCollection(board, startCoords[0], startCoords[1], "SE");
        boolean southEast = findTargetInCollection(possibleFieldsSE, endCoords);

        List<Figure> completeCollection = new ArrayList<>();
        completeCollection.addAll(possibleFieldsNW);
        completeCollection.addAll(possibleFieldsNE);
        completeCollection.addAll(possibleFieldsSW);
        completeCollection.addAll(possibleFieldsSE);
        completeCollection.forEach(s -> System.out.println(Arrays.toString(s.getCoordinate())));

        int xCoord;
        int yCoord;

        if (northWest){
            xCoord = endCoords[0];
            yCoord = endCoords[1];
            Figure targetFigure = board.selectFigure(endCoords);
            if (Objects.equals(targetFigure.getColor(), this.getColor())){
                return false;
            }
            xCoord++;
            yCoord++;
            while (xCoord != startCoords[0] && yCoord != startCoords[1]){
                targetFigure = board.selectFigure(new int[]{xCoord, yCoord});
                if (!(targetFigure instanceof Empty)){
                    return false;
                }
                xCoord++;
                yCoord++;
            }
            return true;

        } else if (northEast){
            xCoord = endCoords[0];
            yCoord = endCoords[1];
            Figure targetFigure = board.selectFigure(endCoords);
            if (Objects.equals(targetFigure.getColor(), this.getColor())){
                return false;
            }
            xCoord++;
            yCoord--;

            while (xCoord != startCoords[0] && yCoord != startCoords[1]){
                targetFigure = board.selectFigure(new int[]{xCoord, yCoord});
                if (!(targetFigure instanceof Empty)){
                    return false;
                }
                xCoord++;
                yCoord--;
            }
            return true;

        } else if (southWest){
            xCoord = endCoords[0];
            yCoord = endCoords[1];
            while (xCoord != startCoords[0]){
                for (Figure figure : possibleFieldsSW){
                    if (Arrays.equals(figure.getCoordinate(), new int[]{endCoords[0], endCoords[1]}) && Objects.equals(figure.getColor(), this.getColor())){
                        return false;
                    } else if (Arrays.equals(figure.getCoordinate(), new int[]{xCoord, yCoord}) && !(figure instanceof Empty)){
                        return false;
                    }
                }
                xCoord--;
                yCoord++;
            }
            return true;
        } else if (southEast){
            xCoord = endCoords[0];
            yCoord = endCoords[1];
            while (xCoord != startCoords[0]){
                for (Figure figure : possibleFieldsSE){
                    if (Arrays.equals(figure.getCoordinate(), new int[]{endCoords[0], endCoords[1]}) && Objects.equals(figure.getColor(), this.getColor())){
                        return false;
                    } else if (Arrays.equals(figure.getCoordinate(), new int[]{xCoord, yCoord}) && !(figure instanceof Empty)){
                        return false;
                    }
                }
                xCoord--;
                yCoord--;
            }
            return true;
        }

        return false;
    }

    private List<Figure> fillOutPossibilityCollection(Board board, int xCoord, int yCoord, String direction){
        int xCoords = xCoord;
        int yCoords = yCoord;
        List<Figure> fields = new ArrayList<>();

        switch (direction){
            case "NW" -> {
                while (xCoords != 0 && yCoords != 0){
                    fields.add(board.selectFigure(new int[]{xCoords - 1, yCoords - 1}));
                    xCoords--;
                    yCoords--;
                }
            }
            case "NE" -> {
                while (xCoords != 0 && yCoords != 7){
                    fields.add(board.selectFigure(new int[]{xCoords - 1, yCoords + 1}));
                    xCoords--;
                    yCoords++;
                }
            }
            case "SW" -> {
                while (xCoords != 7 && yCoords != 0){
                    fields.add(board.selectFigure(new int[]{xCoords + 1, yCoords - 1}));
                    xCoords++;
                    yCoords--;
                }
            }
            case "SE" -> {
                while (xCoords != 7 && yCoords != 7){
                    fields.add(board.selectFigure(new int[]{xCoords + 1, yCoords + 1}));
                    xCoords++;
                    yCoords++;
                }
            }
        }
        return fields;
    }

    private boolean findTargetInCollection(List<Figure> collection, int[] coords){
        for (Figure figure : collection){
            if (Arrays.equals(figure.getCoordinate(), coords)){
                return true;
            }
        }
        return false;
    }
}
