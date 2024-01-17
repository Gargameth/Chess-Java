package Chess.Figures;

import Chess.Figures.FigureTypes;
import Chess.Board;

public abstract class Figure {
    private String symbol;
    private Board board;
    private String color;
    private int[] coordinate;
    protected FigureTypes type;

    public Figure(String symbol, String color, Board board, int[] coord){
        this.symbol = symbol;
        this.color = color;
        this.board = board;
        coordinate = coord;
    }

    public Figure(Board board, int[] coord){
        this(" \u2011", "no color", board, coord);
    }

    public Figure(String color, Board board, int[] coord){
        this(".", color, board, coord);
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Board getBoard() {
        return board;
    }

    public int[] getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(int[] coordinate) {
        this.coordinate = coordinate;
    }

    public String getColor() {
        return color;
    }

    public Chess.Figures.FigureTypes getType() {
        return type;
    }

    public abstract void move(Board board, int[] startCoords, int[] endCoords);

    public abstract boolean checkMoveValidity(Board board, int[] startCoords, int[] endCoords);
}