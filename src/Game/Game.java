package Game;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

import javax.swing.JOptionPane;

import Computer.Computer;
import view.GameView;

public class Game extends Thread implements Countable {
    private int turn;
    private int size;
    private GameView view;
    private Computer computer;
    private Queue<GameInfo> buff;

    public Game() {
        this.turn = 0;
        this.size = new Random().nextInt(3) + 3;
        this.view = new GameView(size, this);
        this.computer = new Computer();
        this.buff = new ArrayDeque<>();
    }

    @Override
    public void run() {
        int state = 0;

        while (true) {
            while (turn % 2 == 0) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            state = judge();
            if (state != 0) {
                break;
            }

            computerAction();
            state = judge();
            if (state != 0) {
                break;
            }
        }
        view.setEnd();
        if (state == 1) {
            JOptionPane.showMessageDialog(null, "Player Win");
            System.out.println("Player Win");
        }
        if (state == 2) {
            JOptionPane.showMessageDialog(null, "Computer Win");
            System.out.println("Computer Win");
        }
        if (state == 3) {
            JOptionPane.showMessageDialog(null, "Draw");
            System.out.println("Draw");
        }
    }

    @Override
    public void counting(GameInfo info) {
        ++turn;
        buff.add(info);
        System.out.println(info);
    }

    @Override
    public int getTurn() {
        return turn;
    }

    private int judge() {
        int rowState;
        int colState;
        int crossState;
        GameInfo info = buff.poll();

        rowState = judgeRow(info);
        if (rowState != 0) {
            return rowState;
        }
        colState = judgeCol(info);
        if (colState != 0) {
            return colState;
        }
        crossState = judgeCross(info);
        if (crossState != 0) {
            return crossState;
        }
        if (judgeDraw()) {
            return 3;
        }
        return 0;
    }

    private boolean judgeDraw() {
        return (turn == size * size);
    }

    private int judgeCross(GameInfo info) {
        for (int d = 0; d < size; d += size - 1) {
            int winner = info.getTurn();

            for (int i = 0; i < size; ++i) {
                if (view.whoClicked(i, Math.abs(d - i)) != info.getTurn()) {
                    winner = 0;
                }
            }
            if (winner != 0) {
                return winner;
            }
        }
        return 0;
    }

    private int judgeRow(GameInfo info) {
        for (int r = 0; r < size; ++r) {
            if (view.whoClicked(r, info.getCol()) != info.getTurn()) {
                return 0;
            }
        }
        return info.getTurn();
    }

    private int judgeCol(GameInfo info) {
        for (int c = 0; c < size; ++c) {
            if (view.whoClicked(info.getRow(), c) != info.getTurn()) {
                return 0;
            }
        }
        return info.getTurn();
    }

    private void computerAction() {
        int row = computer.getRandInt(size);
        int col = computer.getRandInt(size);

        while (view.whoClicked(row, col) != 0) {
            row = computer.getRandInt(size);
            col = computer.getRandInt(size);
        }
        view.clickComputer(row, col);
    }
}
