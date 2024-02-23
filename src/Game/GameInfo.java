package Game;

public class GameInfo {
    private int turn;
    private int row;
    private int col;

    public GameInfo(int turn, int row, int col) {
        this.turn = turn;
        this.row = row;
        this.col = col;
    }

    public int getTurn() {
        return turn;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String rule = turn == 1 ? "Player" : "Computer";
        sb.append(rule + " : " + "(" + row + " , " + col + ")");
        return sb.toString();
    }
}
