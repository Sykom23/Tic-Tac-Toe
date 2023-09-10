package com.sergiosoto.tictactoe.controller;

import com.sergiosoto.tictactoe.model.Game;
import com.sergiosoto.tictactoe.view.GameView;
import java.awt.Color;
import javax.swing.JButton;

public class GameController {

    private final Game game;
    private final GameView gameView;
    private char playerChar = 'X';
    private boolean xTurn = true;
    private final Color xColor = new Color(100, 204, 197);
    private final Color oColor = new Color(238, 238, 238);
    private Color nextColor = xColor;

    public GameController(Game game, GameView gameView) {
        this.game = game;
        this.gameView = gameView;

    }

    public void pressedButton(JButton btn) {
        if (btn == gameView.getPlayAgainBtn()) {
            pressedPlayAgainButton();
        } else {
            pressedBoardField(btn);
        }
    }

    private void pressedBoardField(JButton btn) {
        if (btn.getText().equals("")) {
            gameView.changeButton(btn, nextColor, "" + playerChar);
            if (game.checkWin(btn, gameView.getBoardButtons(), playerChar)) {
                win();
            } else if (game.checkFullBoard(gameView.getBoardButtons())) {
                fullBoard();
            } else {
                changeTurn();
                gameView.changeLabel(gameView.getTurnLabel(), "" + playerChar + "'s Turn", nextColor);
            }
        }
    }

    private void changeTurn() {
        if (xTurn) {
            playerChar = 'O';
            xTurn = false;
            nextColor = oColor;
        } else {
            playerChar = 'X';
            xTurn = true;
            nextColor = xColor;
        }
    }

    private void fullBoard() {
        gameView.changeLabel(gameView.getTurnLabel(), "Draw", xColor);
        gameView.disableButtons();
        gameView.enablePlayAgainButton();
    }

    private void win() {
        String winType = game.getWinType();
        switch (winType) {
            case "VERTICAL" ->
                gameView.verticalWinButtonColor(game.getCol());
            case "HORIZONTAL" ->
                gameView.horizontalWinButtonColor(game.getRow());
            case "DIAGONAL" ->
                gameView.diagonalWinButtonColorButtonColor();
            case "DIAGONALINVERSE" ->
                gameView.diagonalInverseWin();
        }
        gameView.disableButtons();
        gameView.enablePlayAgainButton();
        game.addScore(playerChar);
        gameView.changeLabel(gameView.getTurnLabel(), "" + playerChar + " Wins!", nextColor);
        gameView.changeLabel(gameView.getxScoreLabel(), "X Score: " + String.valueOf(game.getxScore()), xColor);
        gameView.changeLabel(gameView.getoScoreLabel(), "O Score: " + String.valueOf(game.getoScore()), oColor);
    }

    private void pressedPlayAgainButton() {
        // Reiniciar el juego
        changeTurn();
        gameView.newGame();
        gameView.changeLabel(gameView.getTurnLabel(), "" + playerChar + "'s Turn", nextColor);
    }

}
