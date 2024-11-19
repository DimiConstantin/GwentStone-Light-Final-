package org.poo.Cards;

import org.poo.fileio.CardInput;

import java.util.ArrayList;

public class GeneralKocioraw extends Hero {

	public GeneralKocioraw(CardInput input) {
		super(input);
	}

	public void useAbility(ArrayList<Minion> affectedRow) {
		System.out.println("abilitate General Kocioraw");
		for (Minion card : affectedRow) {
			card.setAttackDamage(card.getAttackDamage() + 1);
		}
	}
}
