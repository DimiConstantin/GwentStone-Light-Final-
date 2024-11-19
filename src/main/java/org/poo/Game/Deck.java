package org.poo.Game;

import org.poo.Cards.*;
import org.poo.fileio.CardInput;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {
    private int nrCardsInDeck;
    private ArrayList<Minion> deck;

    public Deck(ArrayList<CardInput> deckInput, int cardsInDeck, int shuflleSeed) {
        nrCardsInDeck = cardsInDeck;
        deck = new ArrayList<Minion>(cardsInDeck);
        for (CardInput card : deckInput) {
            switch (card.getName()) {
                case "Disciple":
                    deck.add(new Disciple(card));
                    break;
                case "Miraj":
                    deck.add(new Miraj(card));
                    break;
                case "The Ripper":
                    deck.add(new TheRipper(card));
                    break;
                case "The Cursed One":
                    deck.add(new TheCursedOne(card));
                    break;
                default:
                    deck.add(new Minion(card));
                    break;
            }
        }
        shuffleDeck(shuflleSeed);
    }

    private void shuffleDeck(int shuflleSeed) {
        Random rand = new Random(shuflleSeed);
        Collections.shuffle(deck, rand);
    }

    public int getNrCardsInDeck() {
        return nrCardsInDeck;
    }

    public void setNrCardsInDeck(int nrCardsInDeck) {
        this.nrCardsInDeck = nrCardsInDeck;
    }

    public ArrayList<Minion> getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<Minion> deck) {
        this.deck = deck;
    }

}
