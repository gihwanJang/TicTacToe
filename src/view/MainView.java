package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Game.Game;

public class MainView extends JFrame {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;

    public MainView() {
        setTitle("tic tac toe");
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(null);

        JLabel mainTitleLabel = getMainTitleLabel();
        JButton startButton = getStartButton();

        centerPanel.add(mainTitleLabel);
        centerPanel.add(startButton);
        contentPane.add(centerPanel, BorderLayout.CENTER);
    }

    private JLabel getMainTitleLabel() {
        JLabel mainTitleLabel = new JLabel("Tic Tac Toe", JLabel.CENTER);
        mainTitleLabel.setFont(new Font("SERIF", Font.BOLD, 25));
        mainTitleLabel.setBounds(WIDTH / 2 - 125, HEIGHT / 8 + 20, 250, 40);
        return mainTitleLabel;
    }

    private JButton getStartButton() {
        JButton startButton = new JButton("Start");
        startButton.setBounds(WIDTH / 2 - 75, HEIGHT / 2 + 25, 150, 50);
        startButton.addActionListener(e -> new Game().start());
        return startButton;
    }
}