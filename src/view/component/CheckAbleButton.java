package view.component;

import javax.swing.JButton;

public class CheckAbleButton extends JButton {
    private int row;
    private int col;
    private int clicked;

    public CheckAbleButton(int row, int col) {
        super();
        this.row = row;
        this.col = col;
        clicked = 0;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int checkClick() {
        return clicked;
    }

    public void click(int who) {
        clicked = who;
    }
}
