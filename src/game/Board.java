package game;

/**
 * Created by Mateusz on 14.01.2017.
 */
public class Board {

    private Cell[][] cells;
    private int width;
    private int height;

    public Board() {
        setProperlySize(Const.DEFAULT_GAMEBOARD_WIDTH, Const.DEFAULT_GAMEBOARD_HEIGHT);
        cells = new Cell[width][height];
        resetAll();
    }

    public Board(int width, int height) {
        setProperlySize(width, height);
        cells = new Cell[width][height];
        resetAll();
    }

    private void setProperlySize(int width, int height) {
        this.width = width;
        this.height = height;
        if(width>Const.MAX_GAMEBOARD_WIDTH || width<Const.MIN_GAMEBOARD_WIDTH)
            this.width=Const.DEFAULT_GAMEBOARD_WIDTH;
        if(height>Const.MAX_GAMEBOARD_HEIGHT || height<Const.MIN_GAMEBOARD_HEIGHT)
            this.height=Const.DEFAULT_GAMEBOARD_HEIGHT;
    }

    private void resetAll(){
        for(int i=0;i<width;i++)
            for(int j=0;j<height;j++)
                cells[i][j] = new Cell(false);
    }

    // wykonanie aktualnej tury
    public void nextCycle() {

        Cell[][] newBoard = new Cell[width][height];
        copyActualStateTo(newBoard);

        changeCellsStateOn(newBoard);

        cells = newBoard;
    }

    private void changeCellsStateOn(Cell[][] newBoard) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                calculateNewStateAtTheDayBeginning(j, i, newBoard[i][j]);
            }
        }
    }

    private void calculateNewStateAtTheDayBeginning(int j, int i, Cell cell) {
        int neighboursCount = countAliveNeighbours(i, j);
        cell.changeState(neighboursCount);
    }

    private void copyActualStateTo(Cell[][] newBoard) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                newBoard[i][j] = cells[i][j].clone();
            }
        }
    }

    public int countAliveNeighbours(int i, int j) {
        int startX = Math.max(i - 1, 0);
        int startY = Math.max(j - 1, 0);
        int endX = Math.min(i + 1, width - 1);
        int endY = Math.min(j + 1, height - 1);

        int aliveNeighbours = 0;
        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {

                if (cells[x][y].isAlive()) {
                    aliveNeighbours++;
                }

            }
        }

        if (cells[i][j].isAlive()) {
            aliveNeighbours--;
        }

        return aliveNeighbours;
    }


    public void setCellValue(int x, int y, boolean isAlive){
       throwExceptionIfCellNotOnBoard(x,y);

       cells[x][y].setAlive(isAlive);
    }

    public boolean getCellValue(int x,int y){
        throwExceptionIfCellNotOnBoard(x,y);

        return cells[x][y].isAlive();
    }

    private void throwExceptionIfCellNotOnBoard(int x, int y){
        if (!checkIfCellIsInBoardRange(x,y))
            throw new IndexOutOfBoundsException("The cell is not in game board range!");
    }

    private boolean checkIfCellIsInBoardRange(int x, int y){
        return (x >= 0) && (y >= 0) && (x < width) && (y < height);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
