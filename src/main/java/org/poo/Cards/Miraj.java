package org.poo.Cards;

import org.poo.fileio.CardInput;

public class Miraj extends Minion {
	public Miraj(CardInput card) {
		super(card);
	}

	public void useAbility(Minion attackedCard) {  // Skyjack
		int health = this.getHealth();
		this.setHealth(attackedCard.getHealth());
		attackedCard.setHealth(health);
	}
}
