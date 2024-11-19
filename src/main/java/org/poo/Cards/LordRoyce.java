package org.poo.Cards;

import org.poo.fileio.CardInput;

import java.util.ArrayList;

public class LordRoyce extends Hero {
	public LordRoyce(CardInput cardInput) {
		super(cardInput);
	}

	public void useAbility(ArrayList<Minion> affectedRow) {
		System.out.println("abilitate Lord Royce");
		for (Minion card : affectedRow) {
			card.setFrozen(2);
		}
	}
}
