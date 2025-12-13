package re.forestier.edu;

import re.forestier.edu.rpg.player;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerItemTest {

    @Test
    void addItem_shouldAddItem_whenWithinMaxWeight() {
        player p = new player("P", "A", "ARCHER", 100, new ArrayList<>());

        Item apple = new Item("Apple", "Just an apple", 1.0, 5);

        boolean ok = p.addItem(apple);

        assertTrue(ok);
        assertEquals(1, p.itemsInventory.size());
        assertEquals(1.0, p.getCurrentCarryWeight(), 1e-9);
    }

    @Test
    void addItem_shouldFail_whenExceedingMaxWeight() {
        player p = new player("P", "A", "ARCHER", 100, new ArrayList<>());
        p.setMaxCarryWeight(2.0);

        Item heavy = new Item("Anvil", "Very heavy", 3.0, 50);

        boolean ok = p.addItem(heavy);

        assertFalse(ok);
        assertEquals(0, p.itemsInventory.size());
        assertEquals(0.0, p.getCurrentCarryWeight(), 1e-9);
    }

    @Test
    void sell_shouldRemoveItem_andIncreaseMoney() {
        player p = new player("P", "A", "ARCHER", 100, new ArrayList<>());
        p.setMaxCarryWeight(10.0);

        Item ring = new Item("Ring", "Gold ring", 0.2, 30);
        assertTrue(p.addItem(ring));

        int moneyBefore = p.money;

        boolean sold = p.sell("Ring");

        assertTrue(sold);
        assertEquals(0, p.itemsInventory.size());
        assertEquals(moneyBefore + 30, p.money);
        assertEquals(0.0, p.getCurrentCarryWeight(), 1e-9);
    }

    @Test
    void sell_shouldReturnFalse_whenItemNotFound() {
        player p = new player("P", "A", "ARCHER", 100, new ArrayList<>());

        boolean sold = p.sell("Missing");

        assertFalse(sold);
        assertEquals(100, p.money);
    }

    @Test
    void addItem_shouldThrow_whenItemIsNull() {
        player p = new player("P", "A", "ARCHER", 100, new ArrayList<>());

        assertThrows(IllegalArgumentException.class, () -> p.addItem(null));
    }
}
