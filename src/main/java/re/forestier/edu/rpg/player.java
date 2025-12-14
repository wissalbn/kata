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
    public ArrayList<Item> itemsInventory = new ArrayList<>();
    private double maxCarryWeight = 10.0;

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
        for (int i = 0; i < LEVEL_THRESHOLDS.length; i++) {
            if (xp < LEVEL_THRESHOLDS[i]) {
                return i + 1;
            }
        }

        int level = LEVEL_THRESHOLDS.length + 1;
        int previousThreshold = LEVEL_THRESHOLDS[LEVEL_THRESHOLDS.length - 1];
        int nextThreshold = level * 10 + ((level + 1) * previousThreshold) / 4;

        while (xp >= nextThreshold) {
            level++;
            previousThreshold = nextThreshold;
            nextThreshold = level * 10 + ((level + 1) * previousThreshold) / 4;
        }

        return level;
    }

    public int getXp() {
        return this.xp;
    }

    public void setXp(int xp) {
    this.xp = xp;
    }

    public void setMaxCarryWeight(double maxCarryWeight) {
        if (maxCarryWeight < 0) {
            throw new IllegalArgumentException("maxCarryWeight must be >= 0");
        }
        this.maxCarryWeight = maxCarryWeight;
    }

    public double getCurrentCarryWeight() {
        double sum = 0.0;
        for (Item i : itemsInventory) {
            sum += i.getWeight();
        }
        return sum;
    }

    public boolean addItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item must not be null");
        }

        double newWeight = getCurrentCarryWeight() + item.getWeight();
        if (newWeight > maxCarryWeight) {
            return false;
        }

        itemsInventory.add(item);
        return true;
    }

    public boolean sell(String itemName) {
        if (itemName == null || itemName.isBlank()) {
            return false;
        }

        for (int i = 0; i < itemsInventory.size(); i++) {
            Item item = itemsInventory.get(i);
            if (itemName.equals(item.getName())) {
                itemsInventory.remove(i);
                this.money += item.getValue();
                return true;
            }
        }
        return false;
    }


}
