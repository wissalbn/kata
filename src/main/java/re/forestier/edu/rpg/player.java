package re.forestier.edu.rpg;

import java.util.ArrayList;
import java.util.HashMap;

public class player {
    public final String playerName;
    public String Avatar_name;
    private String AvatarClass;

    public int money;


    public int level;
    public int healthpoints;
    public int currenthealthpoints;
    protected int xp;

    private static final int[] LEVEL_THRESHOLDS = {10, 27, 57, 111};
    // (lvl-1) * 10 + round((lvl * xplvl-1)/4) 
    // xp < 10  -> level 1
    // xp < 27  -> level 2
    // xp < 57  -> level 3
    // xp < 111 -> level 4
    // else     -> level 5


    public HashMap<String, Integer> abilities;
    public ArrayList<String> inventory;
    private AvatarType avatarEnum;

    public player(String playerName, String avatar_name, String avatarClass, int money, ArrayList<String> inventory) {
        try {
            this.avatarEnum = AvatarType.valueOf(avatarClass.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid avatar class: " + avatarClass);
        }


        this.playerName = playerName;
        Avatar_name = avatar_name;
        AvatarClass = avatarClass;
        this.money = Integer.valueOf(money);
        this.inventory = inventory;
        this.abilities = UpdatePlayer.abilitiesPerTypeAndLevel().get(AvatarClass).get(1);

        this.level = 1;
        this.healthpoints = 100;
        this.currenthealthpoints = 100;
        this.xp = 0;
    }

    public String getAvatarClass () {
        return AvatarClass;
    }

    public void removeMoney(int amount) throws IllegalArgumentException {
        if (money - amount < 0) {
            throw new IllegalArgumentException("Player can't have a negative money!");
        }

        money -= amount;
    }

    public void addMoney(int amount) {
        money += amount;
    }
    public int retrieveLevel() {
        
        //TODO : ajouter les prochains niveaux

        for (int i = 0; i < LEVEL_THRESHOLDS.length; i++) {
            if (xp < LEVEL_THRESHOLDS[i]) {
                return i + 1;
            }
        }
        return LEVEL_THRESHOLDS.length + 1; // Niveau 5
    }

    public int getXp() {
        return this.xp;
    }

    public void setXp(int xp) {
    this.xp = xp;
    }

}