package org.poo.Cards;

import org.poo.fileio.CardInput;

import java.util.ArrayList;

public class Hero extends Card {
    public static final int HERO_HEALTH = 30;
    public Hero(CardInput cardInput) {
        super(cardInput);
        this.setHealth(HERO_HEALTH);
    }


    public void useAbility(ArrayList<Minion> affectedRow) {
        return;
    }
}