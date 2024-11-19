package org.poo.Cards;

import org.poo.fileio.CardInput;

import java.util.ArrayList;

public class KingMudface extends Hero {

	public KingMudface(CardInput cardInput) {
		super(cardInput);
	}

	public void useAbility(ArrayList<Minion> affectedRow) {
		System.out.println("abilitate King Mudface");
		for (Minion card : affectedRow) {
			card.setHealth(card.getHealth() + 1);
		}
	}
}
