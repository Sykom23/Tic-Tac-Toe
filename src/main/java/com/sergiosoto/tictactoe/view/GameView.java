package com.sergiosoto.tictactoe.view;

import com.sergiosoto.tictactoe.controller.GameController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GameView extends JFrame implements ActionListener {

    private JPanel leftPanel, rightPanel, bottomPanel, topPanel;
    private final int WINDOW_HEIGHT = 600;
    private final int WINDOW_WIDTH = 1000;
    private final Color backgroundColor = new Color(5, 59, 80);
    private final Color winColor = new Color(255, 74, 74);
    private JLabel xScoreLabel;
    private JLabel oScoreLabel;
    private JLabel turnLabel = new JLabel();
    private JPanel mainPanel = new JPanel();
    private JButton playAgainBtn = new JButton();
    private JPanel board = new JPanel();
    private GameController gameController;
    private JButton[][] boardButtons;

    public GameView() {

    }

    public void initializeUI() {

        setMainFramePorperties();
        createPanels();
        initializePlayAgainButton();
        createSidePanels();
        createLabels();
        add(mainPanel);
        setVisible(true);
    }

    private void setMainFramePorperties() {
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Tic-Tac-Toe");
    }

    private void createPanels() {
        mainPanel.setBackground(backgroundColor);
        mainPanel.setLayout(new BorderLayout());
        newGame();
        initBoard();
    }

    private void createSidePanels() {
        leftPanel = createPanel();
        rightPanel = createPanel();
        bottomPanel = createPanel();
        topPanel = createPanel();
        bottomPanel.setPreferredSize(new Dimension(1000, 70));
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.EAST);
    }

    private void createLabels() {
        xScoreLabel = createScoreLabel();
        oScoreLabel = createScoreLabel();
        xScoreLabel.setText("X score: " + 0);
        oScoreLabel.setText("O Score: " + 0);
        xScoreLabel.setForeground(new Color(100, 204, 197));
        oScoreLabel.setForeground(new Color(238, 238, 238));
        leftPanel.add(xScoreLabel, BorderLayout.CENTER);
        rightPanel.add(playAgainBtn, BorderLayout.PAGE_END);
        rightPanel.add(oScoreLabel, BorderLayout.CENTER);
        topPanel.add(turnLabel, BorderLayout.CENTER);
    }

    private JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(backgroundColor);
        panel.setLayout(new BorderLayout());
        return panel;
    }

    public void newGame() {
        board.removeAll();
        boardButtons = new JButton[3][3];
        initBoardButtons();
        board.revalidate();
        hidePlayAgainButton();
    }

    private void initBoard() {
        board.setSize(450, 450);
        board.setPreferredSize(new Dimension(400, 400));
        board.setBackground(new Color(100, 204, 197));
        board.setLayout(new GridLayout(3, 3));
        initTurnLabel();
        mainPanel.add(board, BorderLayout.CENTER);
    }

    private JLabel createScoreLabel() {
        JLabel label = new JLabel();
        label.setBorder(new EmptyBorder(20, 20, 20, 20));
        label.setSize(200, 100);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 40));
        return label;
    }

    private void initTurnLabel() {
        turnLabel.setHorizontalAlignment(JLabel.CENTER);
        turnLabel.setFont(new Font("Arial", Font.BOLD, 60));
        turnLabel.setBorder(new EmptyBorder(20, 20, 20, 20));
        turnLabel.setText("X's Turn");
        turnLabel.setForeground(new Color(100, 204, 197));

    }

    public void hidePlayAgainButton() {
        playAgainBtn.setVisible(false);
        playAgainBtn.setEnabled(false);
    }

    private void initBoardButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardButtons[i][j] = new JButton();
                boardButtons[i][j].setFont(new Font("Arial", Font.BOLD, 60));
                boardButtons[i][j].setBackground(new Color(23, 107, 135));
                boardButtons[i][j].setFocusPainted(false);
                boardButtons[i][j].addActionListener(this);
                board.add(boardButtons[i][j]);
            }
        }
    }

    public void changeLabel(JLabel label, String newText, Color newColor) {
        label.setText(newText);
        label.setForeground(newColor);
    }

    public void changeButton(JButton btn, Color color, String btnText) {
        btn.setForeground(color);
        btn.setText(btnText);
    }

    public void enablePlayAgainButton() {
        playAgainBtn.setVisible(true);
        playAgainBtn.setEnabled(true);
    }

    public void initializePlayAgainButton() {
        playAgainBtn.setText("Play Again");
        playAgainBtn.addActionListener(this);
        playAgainBtn.setPreferredSize(new Dimension(40, 40));
        playAgainBtn.setEnabled(false);
        playAgainBtn.setVisible(false);

    }

    public void verticalWinButtonColor(int col) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardButtons[i][col].setBackground(winColor);
            }
        }
    }

    public void horizontalWinButtonColor(int row) {

        for (int i = 0; i < 3; i++) {
            boardButtons[row][i].setBackground(winColor);
        }
    }

    public void diagonalWinButtonColorButtonColor() {
        for (int i = 0; i < 3; i++) {
            boardButtons[i][i].setBackground(winColor);
        }
    }

    public void diagonalInverseWin() {
        int j = 2;
        for (int i = 0; i < 3; i++) {
            boardButtons[i][j].setBackground(winColor);
            j--;
        }
    }

    public void disableButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardButtons[i][j].setEnabled(false);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameController.pressedButton((JButton) e.getSource());
    }

    public void setController(GameController gameController) {
        this.gameController = gameController;
    }

    public JButton[][] getBoardButtons() {
        return this.boardButtons;
    }

    public JButton getPlayAgainBtn() {
        return playAgainBtn;
    }

    public JLabel getTurnLabel() {
        return turnLabel;
    }

    public JLabel getxScoreLabel() {
        return xScoreLabel;
    }

    public JLabel getoScoreLabel() {
        return oScoreLabel;
    }

}
