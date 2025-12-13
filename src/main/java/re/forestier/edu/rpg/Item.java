package re.forestier.edu.rpg;

import java.util.Objects;

public class Item {
    private final String name;
    private final String description;
    private final double weight;
    private final int value;

    public Item(String name, String description, double weight, int value) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Item name must not be null or blank");
        }
        if (description == null) {
            description = "";
        }
        if (weight < 0) {
            throw new IllegalArgumentException("Item weight must be >= 0");
        }
        if (value < 0) {
            throw new IllegalArgumentException("Item value must be >= 0");
        }
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.value = value;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getWeight() { return weight; }
    public int getValue() { return value; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return name.equals(item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
