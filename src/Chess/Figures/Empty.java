package Chess.Figures;

import Chess.Board;
public class Empty extends Figure {
    public Empty(Board board, int[] coord) {
        super("‑ ", "No color", board, coord);
        this.type = FigureTypes.EMPTY;
    }

    @Override
    public boolean checkMoveValidity(Board board, int[] startCoords, int[] endCoords) {
        return false;
    }
}
