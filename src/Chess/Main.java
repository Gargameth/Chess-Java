package Chess;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Board board = new Board();
        board.printBoard();
        while(true){
            System.out.println("Enter coordinates of the first piece to move.");
            String startField = scanner.nextLine();
            if (board.checkInputValidity(startField)){
                System.out.println("Enter coordinates of the Second piece to move.");
                String targetField = scanner.nextLine();
                if (board.checkInputValidity(targetField)){

                    int[] startingCoords = new int[]{board.flipCoord(Integer.parseInt(startField.split("")[1])), board.flipLetterCoord(startField.split("")[0])};
                    int[] targetCoords = new int[]{board.flipCoord(Integer.parseInt(targetField.split("")[1])), board.flipLetterCoord(targetField.split("")[0])};

                    board.fireMoveWithFigure(startingCoords, targetCoords);
                }
                board.printBoard();
            }
        }
    }
}
