package org.poo.Cards;

import org.poo.fileio.CardInput;

public class Disciple extends Minion {

	public Disciple(CardInput card) {
		super(card);
	}

	public void useAbility(Minion attackedCard) { // God s Plan
		attackedCard.setHealth(attackedCard.getHealth() + 2);
	}
}
