package re.forestier.edu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import re.forestier.edu.rpg.Item;

public class ItemTest {
    @Test
    void constructor_ShouldThrowExceptionForNullName() {
        assertThrows(IllegalArgumentException.class, () -> new Item(null, "D", 1.0, 1), 
            "Doit rejeter un nom null.");
    }

    @Test
    void constructor_ShouldThrowExceptionForBlankName() {
        assertThrows(IllegalArgumentException.class, () -> new Item("  ", "D", 1.0, 1), 
            "Doit rejeter un nom vide ou blanc.");
    }

    @Test
    void constructor_ShouldThrowExceptionForNegativeWeight() {
        assertThrows(IllegalArgumentException.class, () -> new Item("N", "D", -1.0, 1), 
            "Doit rejeter un poids négatif.");
    }

    @Test
    void constructor_ShouldThrowExceptionForNegativeValue() {
        assertThrows(IllegalArgumentException.class, () -> new Item("N", "D", 1.0, -1), 
            "Doit rejeter une valeur négative.");
    }

    @Test
    void constructor_ShouldInitializeFieldsCorrectly() {
        // Arrange
        String name = "Holy Elixir";
        String description = "Récupère des PV.";
        double weight = 0.5;
        int value = 10;

        // Act
        Item item = new Item(name, description, weight, value);

        // Assert
        assertEquals(name, item.getName(), "Le nom doit être initialisé.");
        assertEquals(description, item.getDescription(), "La description doit être initialisée.");
        assertEquals(weight, item.getWeight(), 0.001, "Le poids doit être initialisé.");
        assertEquals(value, item.getValue(), "La valeur doit être initialisée.");
    }

    @Test
    void constructor_ShouldHandleNullDescription() {
        // Act
        Item item = new Item("Name", null, 1.0, 10);

        // Assert
        assertEquals("", item.getDescription(), "La description doit être une chaîne vide si null est fourni.");
    }

    @Test
    void equals_ShouldBeTrueIfNamesMatch() {
        // Arrange
        Item item1 = new Item("Épée", "Rouillée", 1.0, 10);
        Item item2 = new Item("Épée", "Neuve", 10.0, 100);

        // Assert
        assertTrue(item1.equals(item2), "Les objets doivent être égaux si seul le nom correspond.");
    }

    @Test
    void equals_ShouldBeFalseIfNamesDiffer() {
        // Arrange
        Item item1 = new Item("Épée", "Rouillée", 1.0, 10);
        Item item2 = new Item("Bâton", "Neuf", 10.0, 100);

        // Assert
        assertFalse(item1.equals(item2), "Les objets ne doivent pas être égaux si les noms diffèrent.");
    }

    @Test
    void hashCode_ShouldBeConsistentWithEquals() {
        // Arrange
        Item item1 = new Item("Potion", "Description 1", 0.5, 5);
        Item item2 = new Item("Potion", "Description 2", 5.0, 50);

        // Assert
        assertEquals(item1.hashCode(), item2.hashCode(), "Les hash codes doivent être identiques si les objets sont égaux.");
    }

    

    @Test
    void equals_ShouldBeFalseIfComparedToDifferentClass() {
        // Arrange
        Item item = new Item("Talisman", "D", 1.0, 10);
        Object nonItem = new String("Pas un item"); 

        // Assert
        assertFalse(item.equals(nonItem), "Un objet ne doit pas être égal à une instance d'une autre classe.");
        assertFalse(item.equals(null), "Un objet ne doit pas être égal à null.");
    }

    @Test
    void equals_ShouldBeTrueIfSameReference() {
        // Arrange
        Item item = new Item("Talisman", "D", 1.0, 10);

        // Assert
        assertTrue(item.equals(item), "Un objet doit être égal à lui-même (même référence).");
    }
    

    
}
