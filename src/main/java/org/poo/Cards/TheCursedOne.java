package org.poo.Cards;

import org.poo.fileio.CardInput;

public class TheCursedOne extends Minion {
	public TheCursedOne(CardInput card) {
		super(card);
	}

	public void useAbility(Minion attackedCard) {
		int health = attackedCard.getHealth();
		attackedCard.setHealth(attackedCard.getAttackDamage());
		attackedCard.setAttackDamage(health);
	}
}
