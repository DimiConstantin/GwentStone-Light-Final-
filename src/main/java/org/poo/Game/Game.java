package org.poo.Game;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.poo.Cards.Minion;
import org.poo.fileio.*;

import java.util.ArrayList;

public class Game {
    public ArrayList<ActionsInput> actions;
    public Commands commands;
    public ArrayNode finalOutput;
    public Statistics statistics;

    public static final int MAX_MANA = 10;
    public static final int MAX_ROWS = 4;
    public static final int MAX_COLUMNS = 5;


    private final Player player1;
    private final Player player2;
    private final int startingPlayer;
    private final int PLAYER1_FRONT_ROW = 3;
    private final int PLAYER2_FRONT_ROW = 0;
    private final int PLAYER1_BACK_ROW = 2;
    private final int PLAYER2_BACK_ROW = 1;

    private final ArrayList<Minion>[] table = new ArrayList[MAX_ROWS];


    private int currentPlayer;
    private int mana;
    private int victoriousPlayer;

    public Game(GameInput game, Input generalInput, ArrayNode output, Statistics stats) {
        StartGameInput startGameInput = game.getStartGame();
        player1 = new Player(generalInput.getPlayerOneDecks(), startGameInput.getPlayerOneDeckIdx(), startGameInput.getPlayerOneHero(), startGameInput.getShuffleSeed(), PLAYER1_FRONT_ROW, PLAYER1_BACK_ROW);
        player2 = new Player(generalInput.getPlayerTwoDecks(), startGameInput.getPlayerTwoDeckIdx(), startGameInput.getPlayerTwoHero(), startGameInput.getShuffleSeed(), PLAYER2_FRONT_ROW, PLAYER2_BACK_ROW);
        this.startingPlayer = startGameInput.getStartingPlayer();
        this.currentPlayer = startingPlayer;
        this.actions = game.getActions();
        for (int i = 0; i < MAX_ROWS; i++) {
            table[i] = new ArrayList<Minion>();
        }
        this.commands = new Commands();
        this.finalOutput = output;
        this.victoriousPlayer = 0;
        this.statistics = stats;
    }

    public void runActions() {
        mana = 1;
        for (ActionsInput action : actions) {
            String command = action.getCommand();
            switch (command) {
                case "endPlayerTurn":
                    if (currentPlayer != startingPlayer) { // new round
                        mana++;
                        if (mana > MAX_MANA) {
                            mana = MAX_MANA;  // update mana
                        }
                        player1.setMana(player1.getMana() + mana);
                        player2.setMana(player2.getMana() + mana);

                        if (!player1.getPlayerDeck().isEmpty()) {
                            Minion removedCard = player1.getPlayerDeck().removeFirst();
                            player1.addToHand(removedCard); // updated player hands
                        }
                        if (!player2.getPlayerDeck().isEmpty()) {
                            Minion removedCard = player2.getPlayerDeck().removeFirst();
                            player2.addToHand(removedCard);
                        }
                    }

                    for (ArrayList<Minion> row : table) { // defrost cards
                        for (Minion card : row) {
                            if (card.isFrozen() > 0)
                                card.setFrozen(card.isFrozen() - 1);
                            if (card.hasAttacked())
                                card.setHasAttacked(false);
                        }
                    }
                    if (currentPlayer == 1)
                        player1.getHero().setHasAttacked(false);
                    if (currentPlayer == 2)
                        player2.getHero().setHasAttacked(false);
                    switchCurrentPlayer(currentPlayer);
                    break;
                case "placeCard":
                    if (currentPlayer == 1) {
                        commands.placeCard(table, action.getHandIdx(), player1, 1, finalOutput);
                    }
                    else if (currentPlayer == 2) {
                        commands.placeCard(table, action.getHandIdx(), player2, 2, finalOutput);
                    }
                    break;
                case "cardUsesAttack":
                    Coordinates cardAttacker = action.getCardAttacker();
                    Coordinates cardDefender = action.getCardAttacked();
                    if (currentPlayer == 1)
                        commands.cardUsesAttack(table, player1, player2, cardAttacker, cardDefender, finalOutput);
                    if (currentPlayer == 2)
                        commands.cardUsesAttack(table, player2, player1, cardAttacker, cardDefender, finalOutput);
                    break;
                case "cardUsesAbility":
                    Coordinates attackerCard = action.getCardAttacker();
                    Coordinates defenderCard= action.getCardAttacked();
                    if (currentPlayer == 1)
                        commands.cardUsesAbility(table, player1, player2, attackerCard, defenderCard, finalOutput);
                    if (currentPlayer == 2)
                        commands.cardUsesAbility(table, player2, player1, attackerCard, defenderCard, finalOutput);
                    break;
                case "useAttackHero":
                    Coordinates attacker = action.getCardAttacker();
                    if (currentPlayer == 1) {
                        int result = commands.useAttackHero(table, player1, player2, attacker, finalOutput);
                        if (result != 0) {
                            ObjectNode node = JsonNodeFactory.instance.objectNode();
                            node.put("gameEnded", "Player one killed the enemy hero.");
                            finalOutput.add(node);
                            this.victoriousPlayer = 1;
                            statistics.setPlayerOneWins(statistics.getPlayerOneWins() + 1);
                        }
                    }
                    if (currentPlayer == 2) {
                        int result = commands.useAttackHero(table, player2, player1, attacker, finalOutput);
                        if (result != 0) {
                            ObjectNode node = JsonNodeFactory.instance.objectNode();
                            node.put("gameEnded", "Player two killed the enemy hero.");
                            finalOutput.add(node);
                            this.victoriousPlayer = 2;
                            statistics.setPlayerTwoWins(statistics.getPlayerTwoWins() + 1);
                        }
                    }
                    break;
                case "useHeroAbility":
                    int affectedRow = action.getAffectedRow();
                    if (currentPlayer == 1) {
                        System.out.println("am player1 si bag abilitatea la hero");
                        commands.useHeroAbility(table, affectedRow, player1, player2, finalOutput);
                    }
                    if (currentPlayer == 2) {
                        System.out.println("am player2 si bag abilitatea la hero");
                        commands.useHeroAbility(table, affectedRow, player2, player1, finalOutput);
                    }
                    break;
                case "getPlayerDeck":
                    if (action.getPlayerIdx() == 1)
                        commands.getPlayerDeck(player1, 1, finalOutput);
                    if (action.getPlayerIdx() == 2)
                        commands.getPlayerDeck(player2, 2, finalOutput);
                    break;
                case "getPlayerHero":
                    if (action.getPlayerIdx() == 1)
                        commands.getPlayerHero(player1, 1, finalOutput);
                    if (action.getPlayerIdx() == 2)
                        commands.getPlayerHero(player2, 2, finalOutput);
                    break;
                case "getPlayerTurn":
                    commands.getPlayerTurn(currentPlayer, finalOutput);
                    break;
                case "getCardsInHand":
                    if (action.getPlayerIdx() == 1)
                        commands.getplayerHand(player1, 1, finalOutput);
                    if (action.getPlayerIdx() == 2)
                        commands.getplayerHand(player2, 2, finalOutput);
                    break;
                case "getPlayerMana":
                    if (action.getPlayerIdx() == 1)
                        commands.getPlayerMana(player1, 1, finalOutput);
                    if (action.getPlayerIdx() == 2)
                        commands.getPlayerMana(player2, 2, finalOutput);
                    break;
                case "getCardsOnTable":
                    commands.getCardsOnTable(table, finalOutput);
                    break;
                case "getCardAtPosition":
                    commands.getCardAtPosition(table, action.getX(), action.getY(), finalOutput);
                    break;
                case "getFrozenCardsOnTable":
                    commands.getFrozenCardsOnTable(table, finalOutput);
                    break;
                case "getTotalGamesPlayed":
                    commands.getTotalGamesPlayed(statistics.getGamesPlayed(), finalOutput);
                    break;
                case "getPlayerOneWins":
                    commands.getPlayerWins(statistics.getPlayerOneWins(), 1, finalOutput);
                    break;
                case "getPlayerTwoWins":
                    commands.getPlayerWins(statistics.getPlayerTwoWins(), 2, finalOutput);
                    break;
            }
        }
    }

    private void switchCurrentPlayer(int currentPlayer) {
        if (currentPlayer == 1)
            this.currentPlayer = 2;
        if (currentPlayer == 2)
            this.currentPlayer = 1;
    }

}
