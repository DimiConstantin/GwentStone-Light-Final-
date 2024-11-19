package org.poo.Game;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.poo.Cards.Hero;
import org.poo.Cards.Minion;
import org.poo.fileio.Coordinates;

import java.util.ArrayList;

public class Commands {

	private static final int MAX_CARDS_ON_ROW = 5;

	public Commands() { }

	public void getPlayerDeck(Player player, int playerIdx , ArrayNode output) {

		ObjectNode node = JsonNodeFactory.instance.objectNode();
		node.put("command", "getPlayerDeck");
		node.put("playerIdx", playerIdx);

		ArrayNode deck = JsonNodeFactory.instance.arrayNode();

		ArrayList<Minion> playerDeck = player.getDeck().getDeck();
		for (Minion card : playerDeck) {
			ObjectNode cardNode = JsonNodeFactory.instance.objectNode();
			cardNode.put("mana", card.getMana());
			cardNode.put("attackDamage", card.getAttackDamage());
			cardNode.put("health", card.getHealth());
			cardNode.put("description", card.getDescription());

			ArrayNode colors = JsonNodeFactory.instance.arrayNode();
			for (String color : card.getColors()) {
				colors.add(color);
			}
			cardNode.set("colors", colors);

			cardNode.put("name", card.getName());

			deck.add(cardNode);
		}

		node.set("output", deck);
		output.add(node);
	}

	public void getPlayerHero(Player player, int idx, ArrayNode output) {
		ObjectNode node = JsonNodeFactory.instance.objectNode();
		node.put("command", "getPlayerHero");
		node.put("playerIdx", idx);

		Hero hero = player.getHero();

		ObjectNode playerHero = JsonNodeFactory.instance.objectNode();

		playerHero.put("mana", hero.getMana());
		playerHero.put("description", hero.getDescription());
		ArrayNode colors = JsonNodeFactory.instance.arrayNode();
		for (String color : hero.getColors()) {
			colors.add(color);
		}
		playerHero.set("colors", colors);
		playerHero.put("name", hero.getName());
		playerHero.put("health", hero.getHealth());
		node.set("output", playerHero);
		output.add(node);

	}

	public void getPlayerTurn(int currentPlayer, ArrayNode output) {
		ObjectNode node = JsonNodeFactory.instance.objectNode();
		node.put("command", "getPlayerTurn");
		node.put("output", currentPlayer);
		output.add(node);
	}


	public void getplayerHand(Player player1, int idx, ArrayNode output) {
		ObjectNode node = JsonNodeFactory.instance.objectNode();
		node.put("command", "getCardsInHand");
		node.put("playerIdx", idx);

		ArrayNode hand = JsonNodeFactory.instance.arrayNode();

		ArrayList<Minion> playerhand = player1.getHand();
		for (Minion card : playerhand) {
			ObjectNode cardNode = JsonNodeFactory.instance.objectNode();
			cardNode.put("mana", card.getMana());
			cardNode.put("attackDamage", card.getAttackDamage());
			cardNode.put("health", card.getHealth());
			cardNode.put("description", card.getDescription());

			ArrayNode colors = JsonNodeFactory.instance.arrayNode();
			for (String color : card.getColors()) {
				colors.add(color);
			}
			cardNode.set("colors", colors);

			cardNode.put("name", card.getName());

			hand.add(cardNode);
		}
		node.set("output", hand);
		output.add(node);
	}


	public void getPlayerMana(Player player, int idx, ArrayNode output) {
		ObjectNode node = JsonNodeFactory.instance.objectNode();
		node.put("command", "getPlayerMana");
		node.put("playerIdx", idx);

		node.put("output", player.getMana());
		output.add(node);
	}

	public void getCardsOnTable(ArrayList<Minion>[] table, ArrayNode output) {
		ObjectNode node = JsonNodeFactory.instance.objectNode();
		node.put("command", "getCardsOnTable");

		ArrayNode cardsOnTable = JsonNodeFactory.instance.arrayNode();
		for (int i = 0; i < 4; i++) {
			ArrayNode row = JsonNodeFactory.instance.arrayNode();
			for (Minion card : table[i]) {
				ObjectNode cardNode = JsonNodeFactory.instance.objectNode();
				cardNode.put("mana", card.getMana());
				cardNode.put("attackDamage", card.getAttackDamage());
				cardNode.put("health", card.getHealth());
				cardNode.put("description", card.getDescription());

				ArrayNode colors = JsonNodeFactory.instance.arrayNode();
				for (String color : card.getColors()) {
					colors.add(color);
				}
				cardNode.set("colors", colors);

				cardNode.put("name", card.getName());

				row.add(cardNode);
			}
			cardsOnTable.add(row);
		}
		node.set("output", cardsOnTable);
		output.add(node);
	}


	public void getCardAtPosition(ArrayList<Minion>[] table, int x, int y, ArrayNode output) {
		ObjectNode node = JsonNodeFactory.instance.objectNode();
		node.put("command", "getCardAtPosition");
		node.put("x", x);
		node.put("y", y);

		ObjectNode cardAtPosition = JsonNodeFactory.instance.objectNode();
		if (table[x].size() < y) {
			node.put("output", "No card available at that position.");
			output.add(node);
			return;
		}
		Minion cardAtGivenIdx = table[x].get(y);

		cardAtPosition.put("mana", cardAtGivenIdx.getMana());
		cardAtPosition.put("attackDamage", cardAtGivenIdx.getAttackDamage());
		cardAtPosition.put("health", cardAtGivenIdx.getHealth());
		cardAtPosition.put("description", cardAtGivenIdx.getDescription());
		ArrayNode colors = JsonNodeFactory.instance.arrayNode();
		for (String color : cardAtGivenIdx.getColors()) {
			colors.add(color);
		}
		cardAtPosition.set("colors", colors);
		cardAtPosition.put("name", cardAtGivenIdx.getName());


		node.set("output", cardAtPosition);
		output.add(node);

	}

	public void getFrozenCardsOnTable(ArrayList<Minion>[] table, ArrayNode output) {
		ObjectNode node = JsonNodeFactory.instance.objectNode();
		node.put("command", "getFrozenCardsOnTable");

		ArrayNode cardsOnTable = JsonNodeFactory.instance.arrayNode();
		for (ArrayList<Minion> row : table) {
			for (Minion card : row) {
				if (card.isFrozen() > 0) {
					ObjectNode cardNode = JsonNodeFactory.instance.objectNode();
					cardNode.put("mana", card.getMana());
					cardNode.put("attackDamage", card.getAttackDamage());
					cardNode.put("health", card.getHealth());
					cardNode.put("description", card.getDescription());

					ArrayNode colors = JsonNodeFactory.instance.arrayNode();
					for (String color : card.getColors()) {
						colors.add(color);
					}
					cardNode.set("colors", colors);

					cardNode.put("name", card.getName());

					cardsOnTable.add(cardNode);
				}
			}
		}
		node.set("output", cardsOnTable);
		output.add(node);
	}


	public void getTotalGamesPlayed(int gamesPlayed, ArrayNode output) {
		ObjectNode node = JsonNodeFactory.instance.objectNode();
		node.put("command", "getTotalGamesPlayed");
		node.put("output", gamesPlayed);
		output.add(node);
	}

	public void getPlayerWins(int playerWins, int idx, ArrayNode output) {
		ObjectNode node = JsonNodeFactory.instance.objectNode();
		if (idx == 1) {
			node.put("command", "getPlayerOneWins");
		}
		else if (idx == 2) {
			node.put("command", "getPlayerTwoWins");
		}
		node.put("output", playerWins);
		output.add(node);
	}

	public void placeCard(ArrayList<Minion>[] table, int handIdx, Player player, int playerIdx, ArrayNode output) {
		ObjectNode node = JsonNodeFactory.instance.objectNode();
		node.put("command", "placeCard");
		node.put("handIdx", handIdx);

		Minion cardToPlace = player.getHand().get(handIdx);

		if (cardToPlace.getMana() > player.getMana()) {
			node.put("error", "Not enough mana to place card on table.");
			output.add(node);
			return;
		}

		int rowIdx = 0;
		if (playerIdx == 1)
			rowIdx = player.getFrontRowIdx() - cardToPlace.getRow_placement();
		if (playerIdx == 2)
			rowIdx = player.getFrontRowIdx() + cardToPlace.getRow_placement();

		if (table[rowIdx].size() >= MAX_CARDS_ON_ROW) {
			node.put("error", "Cannot place card on table since row is full.");
			output.add(node);
			return;
		}

		player.setMana(player.getMana() - cardToPlace.getMana());
		player.getHand().remove(handIdx);
		table[rowIdx].addLast(cardToPlace);
	}

	public void cardUsesAttack(ArrayList<Minion>[] table, Player attacker, Player defender, Coordinates cardAttacker, Coordinates cardDefender, ArrayNode output) {
		ObjectNode node = JsonNodeFactory.instance.objectNode();
		node.put("command", "cardUsesAttack");
		node.putPOJO("cardAttacker", cardAttacker);
		node.putPOJO("cardAttacked", cardDefender);

		int attackerRow = cardAttacker.getX();
		int defenderRow = cardDefender.getX();

		if (defenderRow != defender.getFrontRowIdx() && defenderRow != defender.getBackRowIdx()) {
			node.put("error", "Attacked card does not belong to the enemy.");
			output.add(node);
			return;
		}

		if (table[attackerRow].get(cardAttacker.getY()).hasAttacked()) {
			node.put("error", "Attacker card has already attacked this turn.");
			output.add(node);
			return;
		}

		if (table[attackerRow].get(cardAttacker.getY()).isFrozen() > 0) {
			node.put("error", "Attacker card is frozen.");
			output.add(node);
			return;
		}

		boolean isTank = false;

		for (Minion card : table[defender.getBackRowIdx()]) {
			if (card.getType().equals("Tank")) {
				isTank = true;
				break;
			}
		}

		if (cardDefender.getY() >= table[defenderRow].size())
			return;
		Minion attackedCard = table[defenderRow].get(cardDefender.getY());
		Minion attackerCard = table[attackerRow].get(cardAttacker.getY());
		if (isTank && !attackedCard.getType().equals("Tank")) {
			node.put("error", "Attacked card is not of type 'Tank'.");
			output.add(node);
			return;
		}

		attackerCard.setHasAttacked(true);
		attackedCard.setHealth(attackedCard.getHealth() - attackerCard.getAttackDamage());
		if (attackedCard.getHealth() <= 0) {
			table[defenderRow].remove(cardDefender.getY());
		}

	}

	public void cardUsesAbility(ArrayList<Minion>[] table, Player attacker, Player defender, Coordinates cardAttacker, Coordinates cardDefender, ArrayNode output) {
		ObjectNode node = JsonNodeFactory.instance.objectNode();
		node.put("command", "cardUsesAbility");
		node.putPOJO("cardAttacker", cardAttacker);
		node.putPOJO("cardAttacked", cardDefender);

		int attackerRow = cardAttacker.getX();
		int defenderRow = cardDefender.getX();

		if (table[attackerRow].get(cardAttacker.getY()).isFrozen() > 0) {
			node.put("error", "Attacker card is frozen.");
			output.add(node);
			return;
		}

		if (table[attackerRow].get(cardAttacker.getY()).hasAttacked()) {
			node.put("error", "Attacker card has already attacked this turn.");
			output.add(node);
			return;
		}

		String attackerName = table[attackerRow].get(cardAttacker.getY()).getName();
		switch (attackerName) {
			case "Disciple":
				if (defenderRow != attacker.getFrontRowIdx() && defenderRow != attacker.getBackRowIdx()) {
					node.put("error", "Attacked card does not belong to the current player.");
					output.add(node);
					return;
				}
				break;
			case "The Ripper", "Miraj", "The Cursed One":
				if (defenderRow != defender.getFrontRowIdx() && defenderRow != defender.getBackRowIdx()) {
					node.put("error", "Attacked card does not belong to the enemy.");
					output.add(node);
					return;
				}

				boolean isTank = false;

				for (Minion card : table[defender.getBackRowIdx()]) {
					if (card.getType().equals("Tank")) {
						isTank = true;
						break;
					}
				}

				if (cardDefender.getY() >= table[defenderRow].size())
					return;
				Minion attackedCard = table[defenderRow].get(cardDefender.getY());
				Minion attackerCard = table[attackerRow].get(cardAttacker.getY());
				if (isTank && !attackedCard.getType().equals("Tank")) {
					node.put("error", "Attacked card is not of type 'Tank'.");
					output.add(node);
					return;
				}

				break;

		}

		if (cardDefender.getY() >= table[defenderRow].size())
			return;
		Minion attackedCard = table[defenderRow].get(cardDefender.getY());
		Minion attackerCard = table[attackerRow].get(cardAttacker.getY());

		attackerCard.setHasAttacked(true);
		attackerCard.useAbility(attackedCard);
		if (attackedCard.getHealth() <= 0) {
			table[defenderRow].remove(cardDefender.getY());
		}

	}


	public int useAttackHero(ArrayList<Minion>[] table, Player attackerPlayer, Player defenderPlayer, Coordinates attacker, ArrayNode output) {
		ObjectNode node = JsonNodeFactory.instance.objectNode();
		node.put("command", "useAttackHero");
		node.putPOJO("cardAttacker", attacker);

		int attackerRow = attacker.getX();
		if (table[attackerRow].get(attacker.getY()).isFrozen() > 0) {
			node.put("error", "Attacker card is frozen.");
			output.add(node);
			return 0;
		}

		if (table[attackerRow].get(attacker.getY()).hasAttacked()) {
			node.put("error", "Attacker card has already attacked this turn.");
			output.add(node);
			return 0;
		}

		boolean isTank = false;
		for (Minion card : table[defenderPlayer.getBackRowIdx()]) {
			if (card.getType().equals("Tank")) {
				isTank = true;
				break;
			}
		}

		if (isTank) {
			node.put("error", "Attacked card is not of type 'Tank'.");
			output.add(node);
			return 0;
		}

		Minion attackerCard = table[attackerRow].get(attacker.getY());
		attackerCard.setHasAttacked(true);
		Hero attackedHero = defenderPlayer.getHero();
		attackedHero.setHealth(attackedHero.getHealth() - attackerCard.getAttackDamage());
		if (attackedHero.getHealth() <= 0) {
			return 1;
		}
		return 0;
	}

	public void useHeroAbility(ArrayList<Minion>[] table, int affectedRow, Player attacker, Player defender, ArrayNode output) {
		ObjectNode node = JsonNodeFactory.instance.objectNode();
		node.put("command", "useHeroAbility");
		node.putPOJO("affectedRow", affectedRow);

		if (attacker.getMana() < attacker.getHero().getMana()) {
			node.put("error", "Not enough mana to use hero's ability.");
			output.add(node);
			return;
		}

		if (attacker.getHero().hasAttacked()) {
			node.put("error", "Hero has already attacked this turn.");
			output.add(node);
			return;
		}

		switch (attacker.getHero().getName()) {
			case "Lord Royce", "Empress Thorina":
				if (affectedRow != defender.getBackRowIdx() && affectedRow != defender.getFrontRowIdx()) {
					node.put("error", "Selected row does not belong to the enemy.");
					output.add(node);
					return;
				}
				break;
			case "General Kocioraw", "King Mudface":
				if (affectedRow != attacker.getBackRowIdx() && affectedRow != attacker.getFrontRowIdx()) {
					node.put("error", "Selected row does not belong to the current player.");
					output.add(node);
					return;
				}
				break;
		}

		attacker.getHero().useAbility(table[affectedRow]);
		attacker.getHero().setHasAttacked(true);
		attacker.setMana(attacker.getMana() - attacker.getHero().getMana());
	}
}