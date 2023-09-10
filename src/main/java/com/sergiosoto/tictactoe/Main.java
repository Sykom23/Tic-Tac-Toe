package com.sergiosoto.tictactoe;

import com.sergiosoto.tictactoe.controller.GameController;
import com.sergiosoto.tictactoe.model.Game;
import com.sergiosoto.tictactoe.view.GameView;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        GameView gameWindow = new GameView();
        GameController gameController = new GameController(game, gameWindow);
        gameWindow.setController(gameController);
        gameWindow.initializeUI();
    }
}
