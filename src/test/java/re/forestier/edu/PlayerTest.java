package re.forestier.edu;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import re.forestier.edu.rpg.Item;
import re.forestier.edu.rpg.player;
import java.util.ArrayList;

public class PlayerTest {
    @Test
    void retrieveLevel_1_whenXPis9() {
        //Arrange
        player player = new player("Test", "Tester", "ADVENTURER", 200, new ArrayList<>());
        
        //Setup
        // Simulate adding XP to the player
        player.setXp(9); // Directly setting xp for testing purposes

        //Act
        //recupération du niveau
        int actualLevel = player.retrieveLevel();

        //Assert
        // Vérification que le niveau est correct
        assertEquals(1, actualLevel, "Le niveau du joueur devrait être 1 avec 9 XP.");
    }

    @Test
    void retrieveLevel_2_whenXPis10() {
        //Arrange
        player player = new player("Test", "Tester", "ADVENTURER", 200, new ArrayList<>());
        
        //Setup
        // Simulate adding XP to the player
        player.setXp(10); // Directly setting xp for testing purposes

        //Act
        //recupération du niveau
        int actualLevel = player.retrieveLevel();

        //Assert
        // Vérification que le niveau est correct
        assertEquals(2, actualLevel, "Le niveau du joueur devrait être 2 avec 10 XP.");
    }

    @Test
    void retrieveLevel_3_whenXPis27() {
        //Arrange
        player player = new player("Test", "Tester", "ADVENTURER", 200, new ArrayList<>());
        
        //Setup
        // Simulate adding XP to the player
        player.setXp(27); // Directly setting xp for testing purposes

        //Act
        //recupération du niveau
        int actualLevel = player.retrieveLevel();

        //Assert
        // Vérification que le niveau est correct
        assertEquals(3, actualLevel, "Le niveau du joueur devrait être 3 avec 27 XP.");
    }

    @Test
    void retrieveLevel_4_whenXPis57() {
        //Arrange
        player player = new player("Test", "Tester", "ADVENTURER", 200, new ArrayList<>());
        
        //Setup
        // Simulate adding XP to the player
        player.setXp(57); // Directly setting xp for testing purposes

        //Act
        //recupération du niveau
        int actualLevel = player.retrieveLevel();

        //Assert
        // Vérification que le niveau est correct
        assertEquals(4, actualLevel, "Le niveau du joueur devrait être 4 avec 57 XP.");
    }

    @Test
    void retrieveLevel_5_whenXPis111() {
        //Arrange
        player player = new player("Test", "Tester", "ADVENTURER", 200, new ArrayList<>());
        
        //Setup
        // Simulate adding XP to the player
        player.setXp(111); // Directly setting xp for testing purposes

        //Act
        //recupération du niveau
        int actualLevel = player.retrieveLevel();

        //Assert
        // Vérification que le niveau est correct
        assertEquals(5, actualLevel, "Le niveau du joueur devrait être 5 avec 111 XP.");
    }

    @Test
    void constructorInvalid() {        

        //Assert
        assertThrows(IllegalArgumentException.class, () -> { 
            new player("Test", "NoClass", "MAGE", 100, new ArrayList<>());
           }, "Une exception doit être levée pour une classe invalide.");
    }
    @Test
    void construtorValidAdventurer(){
        //Arrange
        player player = new player("Test", "Tester", "ADVENTURER", 200, new ArrayList<>());

        //Assert
        assertEquals("ADVENTURER", player.getAvatarClass());
    }

    @Test
    void constructorValidArcher(){
        //Arrange
        player player = new player("Test","Tester","ARCHER",200,new ArrayList<>());

        //Assert 
        assertEquals("ARCHER", player.getAvatarClass()); 
    }

    @Test
    void constructorValidDwarf(){
        //Arrange
        player player = new player("Test","tester","DWARF",200,new ArrayList<>());

        //Assert 
        assertEquals("DWARF", player.getAvatarClass());
    }

    @Test
    void getXPValid(){
        //Arrange
        player player = new player("Test","Tester","DWARF",100,new ArrayList<>());

        //Setup
        player.setXp(10);

        //Assert
        assertEquals(10,player.getXp());
    }

    @Test
    void removeMoneyThrowsError(){
        //Arrange
        player player = new player("Test","tester","ADVENTURER",50,new ArrayList<>());

        //Assert
        assertThrows(IllegalArgumentException.class, ()->{
            player.removeMoney(100);
        });
    }

   @Test
    void addMoneyValid(){
        //Arrange
        player player =  new player("Test","tester","ARCHER",500,new ArrayList<>());

        //Setup
        player.addMoney(100);

        //Assert
        assertEquals(600, player.money);
    }

    

    @Test
    void removeMoneyValid(){
        //Arrange
        player player = new player("Test","tester","ARCHER",500,new ArrayList<>());

        //Setup
        player.removeMoney(100);

        //Assert
        assertEquals(400, player.money);
    }

    @Test
    void pit_removeMoney_KillsBoundaryMutant() {
        //ARRANGE
        player player = new player("KillLimit", "Slayer", "ARCHER", 50, new ArrayList<>());
        
        //SETUP
        final int montantInitial = 50;
        final int montantARetirer = 50;
        
        //ACT
        player.removeMoney(montantARetirer); 

        //ASSERT
        //conffirmer que le résultat est 0. 
        assertEquals(montantInitial - montantARetirer, player.money, "Le joueur doit pouvoir retirer son dernier montant, laissant 0. (Tue le mutant '<= 0').");
    }

    //test après l'ajout de nouvelles fonctionnalités
    @Test
    void retrieveLevel_shouldIncreaseLevelMultipleTimes_whenXpIsHuge() {
        player p = new player("Test", "Avatar", "ARCHER", 0, new ArrayList<>());

        p.setXp(1000);

        int level = p.retrieveLevel();

        assertTrue(level >= 7);
    }

    @Test
    void sell_shouldReturnFalse_whenNameIsNullOrBlank() {
        player p = new player("T", "A", "ARCHER", 100, new ArrayList<>());

        assertFalse(p.sell(null));
        assertFalse(p.sell(""));
        assertFalse(p.sell("   "));
    }

    @Test
    void sell_shouldReturnFalse_whenItemNotFound() {
        player p = new player("T", "A", "ARCHER", 100, new ArrayList<>());
        p.addItem(new Item("Potion", "heal", 1.0, 10));

        assertFalse(p.sell("NotExisting"));
    }

    @Test
    void sell_shouldRemoveItem_andIncreaseMoney_whenItemExists() {
        player p = new player("T", "A", "ARCHER", 100, new ArrayList<>());
        Item sword = new Item("Sword", "basic sword", 2.0, 25);

        p.addItem(sword);

        int moneyBefore = p.money;
        boolean sold = p.sell("Sword");

        assertTrue(sold);
        assertEquals(moneyBefore + 25, p.money);

        // vendre une deuxième fois doit échouer car l'objet est retiré
        assertFalse(p.sell("Sword"));
    }

    @Test
    void setMaxCarryWeight_shouldThrowException_whenNegative() {
        player p = new player("Test", "Avatar", "ARCHER", 0, new ArrayList<>());

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> p.setMaxCarryWeight(-1.0)
        );

        assertEquals("maxCarryWeight must be >= 0", ex.getMessage());
    }

    @Test
    void setMaxCarryWeight_shouldSetValue_whenNonNegative() throws Exception {
        player p = new player("Test", "Avatar", "ARCHER", 0, new ArrayList<>());

        p.setMaxCarryWeight(42.5);

        var field = player.class.getDeclaredField("maxCarryWeight");
        field.setAccessible(true);

        double value = field.getDouble(p);
        assertEquals(42.5, value, 1e-9);
    }






    
}
