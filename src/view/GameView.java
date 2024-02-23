package view;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import Game.Countable;
import Game.GameInfo;
import img.Image;
import view.component.CheckAbleButton;

public class GameView extends JFrame {
    private static final int PLAYER = 1;
    private static final int COMPUTER = 2;
    private static final int PADDING = 20;
    private Countable counter;
    private CheckAbleButton[][] gameBoard;

    public GameView(int size, Countable counter) {
        this.counter = counter;
        this.gameBoard = new CheckAbleButton[size][size];
        setTitle("TikTekTok");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setMapView();
        setVisible(true);
    }

    public int getBoardSize() {
        return gameBoard.length;
    }

    public void clickPlayer(CheckAbleButton button) {
        button.setIcon(getResizedImage(Image.CAT.getImage()));
        button.setEnabled(false);
        button.click(PLAYER);
        counter.counting(new GameInfo(1, button.getRow(), button.getCol()));
        updateTurn();
    }

    public void clickComputer(int r, int c) {
        CheckAbleButton button = gameBoard[r][c];
        button.setIcon(getResizedImage(Image.DOG.getImage()));
        button.setEnabled(false);
        button.click(COMPUTER);
        counter.counting(new GameInfo(2, r, c));
        updateTurn();
    }

    public int whoClicked(int r, int c) {
        return gameBoard[r][c].checkClick();
    }

    public void setEnd() {
        for (CheckAbleButton[] row : gameBoard) {
            for (CheckAbleButton button : row) {
                button.setEnabled(false);
            }
        }
    }

    private void updateTurn() {
        for (CheckAbleButton[] row : gameBoard) {
            for (CheckAbleButton button : row) {
                if (button.checkClick() == 0) {
                    button.setEnabled(counter.getTurn() % 2 == 0);
                }
            }
        }
    }

    private void setMapView() {
        Container mainContainer = getContentPane();
        mainContainer.setLayout(new GridLayout(getBoardSize(), getBoardSize()));

        for (int r = 0; r < getBoardSize(); ++r) {
            for (int c = 0; c < getBoardSize(); ++c) {
                CheckAbleButton button = new CheckAbleButton(r, c);
                button.addActionListener(e -> clickPlayer((CheckAbleButton) e.getSource()));
                gameBoard[r][c] = button;
                mainContainer.add(button);
            }
        }
    }

    private ImageIcon getResizedImage(ImageIcon icon) {
        int buttonSize = 500 / getBoardSize() - PADDING;
        return new ImageIcon(icon.getImage().getScaledInstance(buttonSize, buttonSize, 0));
    }
}
