package org.poo.Game;

import org.poo.Cards.*;
import org.poo.fileio.CardInput;
import org.poo.fileio.DecksInput;

import java.util.ArrayList;

public class Player {
    private ArrayList<Minion> hand;
    private Hero hero;
    private Deck deck;
    private int mana;
    private final int frontRowIdx;
    private final int backRowIdx;

    public Player(DecksInput playerDecks, int playerDeckIdx, CardInput playerHero , int shuffleSeed, int frontIdx, int backIdx) {
        ArrayList<CardInput> inputDeck = playerDecks.getDecks().get(playerDeckIdx);
        this.deck = new Deck(inputDeck, playerDecks.getNrCardsInDeck(), shuffleSeed);
        switch (playerHero.getName()) {
            case "Lord Royce":
                this.hero = new LordRoyce(playerHero);
                break;
            case "Empress Thorina":
                this.hero = new EmpressThorina(playerHero);
                break;
            case "King Mudface":
                this.hero = new KingMudface(playerHero);
                break;
            case "General Kocioraw":
                this.hero = new GeneralKocioraw(playerHero);
                break;
            default:
                this.hero = new Hero(playerHero);
                break;
        }
        //this.hero = new Hero(playerHero);
        hand = new ArrayList<Minion>();
        hand.addLast(this.deck.getDeck().removeFirst());
        this.mana = 1;
        this.frontRowIdx = frontIdx;
        this.backRowIdx = backIdx;
    }


    public int getBackRowIdx() {
        return backRowIdx;
    }

    public ArrayList<Minion> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Minion> hand) {
        this.hand = hand;
    }

    public void addToHand(Minion minion) {
        hand.addLast(minion);
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public Deck getDeck() {
        return deck;
    }

    public ArrayList<Minion> getPlayerDeck() { return getDeck().getDeck(); }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public int getFrontRowIdx() {
        return frontRowIdx;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }
}
