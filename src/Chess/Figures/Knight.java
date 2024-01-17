package Chess.Figures;

import Chess.Board;
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
    public void move(Board board, int[] startCoords, int[] endCoords) {

    }

    @Override
    public boolean checkMoveValidity(Board board, int[] startCoords, int[] endCoords) {
        return false;
    }
}
