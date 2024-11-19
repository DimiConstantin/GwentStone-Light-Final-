package org.poo.Cards;

import org.poo.fileio.CardInput;

import java.util.ArrayList;

public class EmpressThorina extends Hero{
	public EmpressThorina(CardInput cardInput) {
		super(cardInput);
	}

	public void useAbility(ArrayList<Minion> affectedRow) { // low blow
		System.out.println("abilitate Empress Thorina");
		this.setHasAttacked(true);
		int maxHealth = 0;
		int maxHealthIdx = 0;
		for (int i = 0; i < affectedRow.size(); i++) {
			Minion card = affectedRow.get(i);
			if (card.getHealth() > maxHealth) {
				maxHealth = card.getHealth();
				maxHealthIdx = i;
			}
		}
		affectedRow.remove(maxHealthIdx);
	}
}
