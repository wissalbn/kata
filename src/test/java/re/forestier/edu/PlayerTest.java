package re.forestier.edu;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        //Arrange
        player player = new player("Test", "Tester", "CREATIVE", 200, new ArrayList<>());
        

        //Assert
        assertNull(player.getAvatarClass());
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
        final int montantARetirer = 50; // Retirer tout l'argent disponible
        
        //ACT
        player.removeMoney(montantARetirer); 

        //ASSERT
        //conffirmer que le résultat est 0. 
        assertEquals(montantInitial - montantARetirer, player.money, "Le joueur doit pouvoir retirer son dernier montant, laissant 0. (Tue le mutant '<= 0').");
    }

    
}
