package com.sergiosoto.tictactoe.model;

import javax.swing.JButton;

public class Game {

    private String winType = null;
    private int row;
    private int col;
    private int xScore = 0;
    private int oScore = 0;

    public boolean checkWin(JButton pressedBtn, JButton[][] boardButtons, char playerChar) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (boardButtons[i][j] == pressedBtn) {
                    row = i;
                    col = j;
                    break;
                }
            }
        }

        return checkHorizontalWin(boardButtons, row, playerChar)
                || checkVerticalWin(boardButtons, col, playerChar)
                || checkDiagonalWin(boardButtons, playerChar);
    }

    public void newGame() {
        row = 0;
        col = 0;
        winType = null;
    }

    private boolean checkHorizontalWin(JButton[][] boardButtons, int row, char playerChar) {

        int count = 0;
        for (int i = 0; i < 3; i++) {
            if (!boardButtons[row][i].getText().equals("")) {
                if (boardButtons[row][i].getText().charAt(0) == playerChar) {
                    count++;
                }
            } else {
                break;
            }
        }
        if (count == 3) {
            winType = "HORIZONTAL";
            return true;
        }
        return false;
    }

    private boolean checkVerticalWin(JButton[][] boardButtons, int col, char playerChar) {

        int count = 0;
        for (int i = 0; i < 3; i++) {
            if (!boardButtons[i][col].getText().equals("")) {
                if (boardButtons[i][col].getText().charAt(0) == playerChar) {
                    count++;
                }
            } else {
                break;
            }
        }
        if (count == 3) {
            winType = "VERTICAL";
            return true;
        }
        return false;

    }

    public boolean checkFullBoard(JButton[][] boardButtons) {
        int symbolCount = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!boardButtons[i][j].getText().equals("")) {
                    symbolCount++;
                }
            }
            if (symbolCount == 9) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonalWin(JButton[][] boardButtons, char playerChar) {

        int count = 0;
        for (int j = 0; j < 3; j++) {
            if (!boardButtons[j][j].getText().equals("")) {
                if (boardButtons[j][j].getText().charAt(0) == playerChar) {
                    count++;
                }
            } else {
                count = 0;
            }
        }
        if (count == 3) {
            winType = "DIAGONAL";
            return true;
        }

        count = 0;
        int i = 2;
        for (int j = 0; j < 3; j++) {
            if (!boardButtons[i][j].getText().equals("")) {
                if (boardButtons[i][j].getText().charAt(0) == playerChar) {
                    count++;
                }
            } else {
                count = 0;
            }
            i--;
        }
        if (count == 3) {
            winType = "DIAGONALINVERSE";
            return true;
        }

        return false;
    }

    public void addScore(char playerChar) {
        if (playerChar == 'X') {
            xScore = xScore + 1;
        } else {
            oScore = oScore + 1;
        }
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public String getWinType() {
        return winType;
    }

    public int getxScore() {
        return xScore;
    }

    public int getoScore() {
        return oScore;
    }

}
