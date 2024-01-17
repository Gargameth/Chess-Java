package Chess.Figures;

import Chess.Board;
public class Empty extends Figure {
    public Empty(Board board, int[] coord) {
        super("\u2011 ", "No color", board, coord);
        this.type = FigureTypes.EMPTY;
    }

    @Override
    public void move(Board board, int[] startCoords, int[] endCoords) {

    }

    @Override
    public boolean checkMoveValidity(Board board, int[] startCoords, int[] endCoords) {
        return false;
    }
}
