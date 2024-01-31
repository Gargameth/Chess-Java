package Chess.Figures;

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

    public Figure(String color, Board board, int[] coord){
        this(".", color, board, coord);
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
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

    public void move(Board board, int[] startCoords, int[] endCoords) {
        boolean canMove = checkMoveValidity(board, startCoords, endCoords);
        if (canMove){
            int indexOfStart = board.getBoard().indexOf(board.selectFigure(startCoords));
            int indexOfEnd = board.getBoard().indexOf(board.selectFigure(endCoords));
            board.getBoard().set(indexOfEnd, this);
            this.setCoordinate(endCoords);
            board.getBoard().set(indexOfStart, new Empty(board, startCoords));
        } else System.out.println("Couldn't move figure. Something went wrong. Try again.");
    }

    public abstract boolean checkMoveValidity(Board board, int[] startCoords, int[] endCoords);
}