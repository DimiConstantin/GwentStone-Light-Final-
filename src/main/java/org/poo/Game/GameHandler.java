package org.poo.Game;

import com.fasterxml.jackson.databind.node.ArrayNode;
import org.poo.fileio.GameInput;
import org.poo.fileio.Input;

import java.util.ArrayList;

public class GameHandler {
    private final ArrayList<GameInput> games;
    private final Input general_input;
    public Statistics stats;
    public ArrayNode output;

    public GameHandler(Input input, ArrayNode output) {
        games = input.getGames();
        general_input = input;
        stats = new Statistics();
        this.output = output;
    }

    public void runAllGames() {
        stats.incrementGamesPlayed();
        for (GameInput game : games) {
            Game runGame = new Game(game, general_input, output, stats);
            runGame.runActions();

            stats.incrementGamesPlayed();
        }
    }

}
