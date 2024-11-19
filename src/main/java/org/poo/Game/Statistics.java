package org.poo.Game;

public class Statistics {
    private int gamesPlayed = 0;
    private int playerOneWins = 0;
    private int playerTwoWins = 0;

    public Statistics() {
        this.gamesPlayed = 0;
        this.playerOneWins = 0;
        this.playerTwoWins = 0;
    }

    public void incrementGamesPlayed() {
        this.gamesPlayed++;
    }

    public int getPlayerOneWins() {
        return playerOneWins;
    }

    public void setPlayerOneWins(int playerOneWins) {
        this.playerOneWins = playerOneWins;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public int getPlayerTwoWins() {
        return playerTwoWins;
    }

    public void setPlayerTwoWins(int playerTwoWins) {
        this.playerTwoWins = playerTwoWins;
    }
}
