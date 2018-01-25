package test;

import game.Board;
import game.Const;
import org.junit.jupiter.api.*;

/**
 * Created by Mateusz on 14.01.2017.
 */
public class GameTest {

    @Test
    void createDefaultGameBoard(){
        Board board = new Board();
        checkIfBoardExists(board);
        checkIfBoardSizeIsCorrect(board.getWidth(), Const.DEFAULT_GAMEBOARD_WIDTH);
        checkIfBoardSizeIsCorrect(board.getHeight(), Const.DEFAULT_GAMEBOARD_HEIGHT);
        checkIfBoardIsClear(board);
    }

    @Test
    void settingCellsValues(){
        Board board = new Board();
        board.setCellValue(5, 5, true);
        Assertions.assertEquals(true, board.getCellValue(5, 5));

        board.setCellValue(0, 0, true);
        Assertions.assertEquals(true, board.getCellValue(0, 0));

        board.setCellValue(0, 1, true);
        Assertions.assertEquals(true, board.getCellValue(0, 1));

        board.setCellValue(1, 0, false);
        Assertions.assertEquals(false, board.getCellValue(1, 0));

        board.setCellValue(1, 1, true);
        Assertions.assertEquals(true, board.getCellValue(1, 1));
    }











    private void checkIfBoardSizeIsCorrect(int size, int correctSize) {
        Assertions.assertEquals(size, correctSize);
    }

    private void checkIfBoardExists(Board board) {
        Assertions.assertNotNull(board);
    }

    void checkIfBoardIsClear(Board board){
        for (int i = 0; i < board.getWidth(); i++) {
            for (int j = 0; j < board.getHeight(); j++) {
                Assertions.assertEquals(false, board.getCellValue(i, j));
            }
        }
    }


}
