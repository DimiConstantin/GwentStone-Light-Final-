
# GwentStone Lite

![Logo](https://ocw.cs.pub.ro/courses/_media/poo-ca-cd/teme/banner_poo-01.png?w=800&tok=16a3e9)

## Short description

The game is a two-player, turn-based card game where each player selects a deck and a hero to represent them. Players alternate turns, gaining mana and drawing cards at the start of each round. Cards cost mana to be played onto the table, where they can interact with other cards. Each player’s hero has special abilities and is present on the table throughout the game. The objective is to reduce the opponent's hero's health to zero, winning the game. Players’ wins and games played are tracked.


## Features

- 4x5 game table:
  - Rows 0, 1 assigned to player 2
  - Rows 2, 3 assigned to player 1
  - Front rows (1 , 2): used for offense/defense.
  - Back rows (0 , 3): used for support or ranged units.
- Cards:
  - Types: Minions and Heroes
  - Minions: Have attack, health, and mana cost. Some have unique abilities
    - Tanks (Goliath, Warden): Must be attacked first by opponents.
  - Heroes: Fixed attributes (30 health) and special abilities
    - Abilities are row-specific and may target own or opponent’s rows
    - Heroes can attack once per turn

- Mechanics:
  - Mana: Required to play cards and use abilities
  - Frozen Cards: Cannot act until the current turn ends
  - Abilities: Minions and heroes have predefined effects to enhance strategy
  - Special Cards: Include unique effects like health swaps, attack boosts, or freezing
-  Win Condition : A player wins by reducing the opponent's hero's health to 0.




## Project Structure
- /src/main/java/org.poo/
  - Cards/ : package containing classes about cards
    - Card : class that implements the basic functionalities of a card
    - Hero : inherits the card structure, generic implementation of hero cards
    - Minion : inherits the card structure, generic implementation of minion cards
    - Disciple : implementation of the Disciple minion
    - Miraj : implementation of the Miraj minion
    - TheCursedOne : implementation of the The Cursed One minion
    - The Ripper : implementation of the The Ripper minion
    - EmpressThorina : implementation of the Empress Thorina hero
    - LordRoyce : implementation of the Lord Royce hero
    - KingMudface : implementation of the King Mudface hero
    - GeneralKocioraw : implementation of the General Kocioraw hero
  - Game/ : package containing classes related to the game execution
    - Commands : handles all the actions that are executed in the game
    - Deck : extracts the selected deck from the game input and converts it to cards implemented in the Cards package
    - Game : handles the beginning of every game and starts the execution flow
    - GameHandler : handles all the games played
    - Player : manages the player functionalities (stores the player hand, deck, shuffles the deck , etc)
    - Statistics : class that stores informations about all the games played
## Game Implementation
- The game execution starts with iterating the array containing every game details. At the beginning of every game, the decks are shuffled and assigned to the players, as the heroes. Once the setup is complete, the game proceeds into turn-based play. During a turn, the active player is identified using a command that keeps track of the current turn. At the start of each turn, players receive a mana allocation that increases every round.
- Players can perform several key actions during their turn. First, they can place cards from their hand onto the table. The game checks if the player has enough mana and if there is sufficient space on the selected row. In addition to placing cards, players can initiate attacks. A card on the table can attack an opposing card or the enemy hero. When attacking another card, the game ensures the attacker has not already attacked during the turn, that it is not frozen, and that the targeted card is valid, particularly if tank cards are present. To attack the enemy hero, the player must first ensure no tanks are blocking the path.
- Throughout the game, players have access to commands that let them query the state of the game. They can check the cards in their hand, view the cards on the table, inspect specific cards at given positions, and see which cards are frozen. These queries help players make informed decisions during their turn.
- The game continues in this turn-based manner until one player’s hero’s health drops to zero, at which point the opponent is declared the winner. Statistics, such as the total number of games played and individual player wins, are tracked to provide a record of progress and achievements.
- Error handling is a crucial aspect of the workflow. Every action is validated to ensure compliance with the rules. For example, players cannot perform actions if they lack the required mana or if the target or conditions are invalid. This includes preventing attacks by frozen cards or actions involving full rows.
- At the end of a turn, the game transitions to the next player. This alternation continues until the win condition is met, maintaining a balanced and strategic rhythm throughout the game. The workflow is designed to provide clarity and fairness while allowing for deep strategic play. It is modular and adaptable, making it easy to incorporate new features or mechanics in future updates.

