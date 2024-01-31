package Chess.Figures;

import Chess.Board;
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
        return false;
    }
}
