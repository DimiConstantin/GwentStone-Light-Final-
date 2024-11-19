package org.poo.Cards;

import com.fasterxml.jackson.databind.node.ArrayNode;
import org.poo.Game.Player;
import org.poo.fileio.CardInput;
import lombok.Getter;
import org.poo.fileio.Coordinates;

import java.util.ArrayList;

public class Minion extends Card {
    private int attackDamage;
    private int isFrozen;
    private int row_placement;
    private String type;

    public Minion(CardInput card) {
        super(card);
        this.attackDamage = card.getAttackDamage();
        this.isFrozen = 0;
        switch (this.getName()) {
            case "Sentinel", "Berserker":
                this.type = "passive";
                this.row_placement = 0;
                break;
            case "Goliath", "Warden":
                this.type = "Tank";
                this.row_placement = 1;
                break;
            case "The Ripper", "Miraj":
                this.type = "special";
                this.row_placement = 1;
                break;
            case "The Cursed One", "Disciple":
                this.type = "special";
                this.row_placement = 0;
        }
    }


    public String getType() {
        return type;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public int isFrozen() {
        return isFrozen;
    }

    public void setFrozen(int frozen) {
        this.isFrozen = frozen;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public int getRow_placement() {
        return row_placement;
    }

    public void setRow_placement(int row_placement) {
        this.row_placement = row_placement;
    }

    public void useAbility (Minion attackedCard) {
        return;
    }

    public int getIsFrozen() {
        return isFrozen;
    }
}
