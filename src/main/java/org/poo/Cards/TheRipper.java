package org.poo.Cards;

import org.poo.fileio.CardInput;

public class TheRipper extends Minion {
	public TheRipper(CardInput card) {
		super(card);
	}

	public void useAbility(Minion attackedCard) { // Weak Knees
		attackedCard.setAttackDamage(attackedCard.getAttackDamage() - 2);
		if (attackedCard.getAttackDamage() < 2)
			attackedCard.setAttackDamage(0);
	}
}
