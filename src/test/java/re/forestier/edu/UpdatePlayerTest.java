package re.forestier.edu;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import re.forestier.edu.rpg.UpdatePlayer;
import re.forestier.edu.rpg.player;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

public class UpdatePlayerTest {
    @Test
    void addXp_shouldntLevelUpWhenXpIsInsufficient() {
        //Arrange
        player player = new player("Test", "Tester", "ADVENTURER", 200, new ArrayList<>());

        //Setup
        int oldLevel = player.retrieveLevel();
        int oldXp = player.getXp();
        int oldAbilitiesSize = player.abilities.size();
        int oldInventorySize = player.inventory.size();

        //Act
        boolean result = UpdatePlayer.addXp(player, 5);

        //Assert
        assertEquals(oldXp + 5, player.getXp(), "L'XP du joueur devrait avoir augmenté de 5.");
        assertFalse(result, "Le joueur ne devrait pas avoir monté de niveau.");
        assertEquals(oldLevel, player.retrieveLevel(), "Le niveau de joueur devrait rester le meme.");
        assertEquals(oldAbilitiesSize, player.abilities.size(), "les abilities du joueur devraient rester les memes.");
        assertEquals(oldInventorySize, player.inventory.size(), "l'inventaire du joueur devrait rester le meme;");
    }

    @Test
    void forceClassLoadingCoverage() throws Exception {
        try {
            Constructor<re.forestier.edu.rpg.UpdatePlayer> constructor = re.forestier.edu.rpg.UpdatePlayer.class.getDeclaredConstructor();
            
            constructor.setAccessible(true);
            
            constructor.newInstance();
        } catch (Exception e) {
            fail("Le chargement de la classe UpdatePlayer a échoué via la réflexion: " + e.getMessage());
        }
    }

    @Test
    void addXp_shouldLevelUpWhenXpIsSufficient() {
        //Arrange
        player player = new player("Test","tester","ARCHER",500,new ArrayList<>());

        //Setup
        int oldLevel = player.retrieveLevel();
        int oldXp = player.getXp();
        int oldAbilitiesSize = player.abilities.size();
        int oldInventorySize = player.inventory.size();

        //Act
        boolean result = UpdatePlayer.addXp(player, 15);

        //Assert
        assertTrue(result, "Le joueur devrait avoir monté de niveau.");
        assertTrue(player.abilities.size() > oldAbilitiesSize, "les abilities du joueur devraient avoir augmenté.");
        assertEquals(oldXp + 15, player.getXp(), "L'XP du joueur devrait avoir augmenté de 15.");
        assertEquals(oldLevel + 1, player.retrieveLevel(), "Le niveau de joueur devrait avoir augmenté de 1.");
        assertEquals(oldInventorySize + 1, player.inventory.size());
    }

    @Test
    void majFinDeTour_shouldDoNothingWhenPlayerIsKO(){
        //Arrange
        player player = new player("Test","tester","ARCHER",500,new ArrayList<>());

        //Setup
        player.currenthealthpoints = 0;
        int oldHp = player.currenthealthpoints;
        int oldMaxHp = player.healthpoints;
        int oldLevel = player.retrieveLevel();
        int oldAbilitiesSize = player.abilities.size();
        int oldInventorySize = player.inventory.size();

        //Act
        UpdatePlayer.majFinDeTour(player);

        //Assert
        assertEquals(oldHp, player.currenthealthpoints, "Les points de vie du joueur devraient rester les memes.");
        assertEquals(oldMaxHp, player.healthpoints, "Les points de vie max du joueur devraient rester les mêmes.");
        assertEquals(oldLevel, player.retrieveLevel(), "Le niveau du joueur ne devrait pas avoir changé.");
        assertEquals(oldAbilitiesSize, player.abilities.size(), "Les abilities du joueur devraient rester les memes.");
        assertEquals(oldInventorySize, player.inventory.size(), "L'inventaire du joueur devrait rester le meme");
    }

    @Test
    void majFinDeTour_HealingForAdventurerBeLowHalfHpForLevel3OrHigher() {
        //Arrange
        player player = new player("Test","tester","ADVENTURER",500,new ArrayList<>());

        //Setup
        player.setXp(57);
        player.healthpoints = 100;
        player.currenthealthpoints =  20;
        int oldHp = player.currenthealthpoints;

        //Act
        UpdatePlayer.majFinDeTour(player);

        //Assert
        assertEquals(oldHp + 2, player.currenthealthpoints, "Les points de vie du joueur devraient avoir augmenté de 2.");
    }

    @Test
    void majFinDeTour_HealingForAdventurerBeLowHalfHpForLevelLowerThan3() {
        //Arrange
        player player = new player("Test","tester","ADVENTURER",500,new ArrayList<>());

        //Setup
        player.setXp(10);
        player.healthpoints = 100;
        player.currenthealthpoints =  20;
        int oldHp = player.currenthealthpoints;

        //Act
        UpdatePlayer.majFinDeTour(player);

        //Assert
        assertEquals(oldHp + 1, player.currenthealthpoints, "Les points de vie du joueur devraient avoir augmenté de 1.");
    }

    @Test
    void majFinDeTour_HealingForDwarfBeLowHalfHpHolyElixir() {
        //Arrange
        player player = new player("Test","tester","DWARF",500,new ArrayList<>());
        player.inventory.add("Holy Elixir");

        //Setup
        player.healthpoints = 100;
        player.currenthealthpoints =  20;
        int oldHp = player.currenthealthpoints;

        //Act
        UpdatePlayer.majFinDeTour(player);

        //Assert
        assertEquals(oldHp + 2, player.currenthealthpoints, "Les points de vie du joueur devraient avoir augmenté de 2.");
    }

    @Test
    void majFinDeTour_HealingForDwarfBeLowHalfHp() {
        //Arrange
        player player = new player("Test","tester","DWARF",500,new ArrayList<>());

        //Setup
        player.healthpoints = 100;
        player.currenthealthpoints =  20;
        int oldHp = player.currenthealthpoints;

        //Act
        UpdatePlayer.majFinDeTour(player);

        //Assert
        assertEquals(oldHp + 1, player.currenthealthpoints, "Les points de vie du joueur devraient avoir augmenté de 2.");
    }

    @Test
    void majFinDeTour_HealingForArcherBeLowHalfHpMagicBow() {
        //Arrange
        player player = new player("Test","tester","ARCHER",500,new ArrayList<>());
        player.inventory.add("Magic Bow");

        //Setup
        player.healthpoints = 100;
        player.currenthealthpoints =  20;

        //Act
        UpdatePlayer.majFinDeTour(player);

        //Assert
        assertEquals(22, player.currenthealthpoints, "Les points de vie du joueur devraient avoir augmenté de 13.");
    }

    @Test
    void majFinDeTour_HealingForArcherBeLowHalfHp() {
        //Arrange
        player player = new player("Test","tester","ARCHER",500,new ArrayList<>());

        //Setup
        player.healthpoints = 100;
        player.currenthealthpoints =  20;
        int oldHp = player.currenthealthpoints;

        //Act
        UpdatePlayer.majFinDeTour(player);

        //Assert
        assertEquals(oldHp + 1, player.currenthealthpoints, "Les points de vie du joueur devraient avoir augmenté de 13.");
    }

    @Test
    void majFinDeTour_HealingWhenHpAboveHalf() {
        //Arrange
        player player = new player("Test","tester","ARCHER",500,new ArrayList<>());

        //Setup
        player.healthpoints = 100;
        player.currenthealthpoints =  60;
        int oldHp = player.currenthealthpoints;

        //Act
        UpdatePlayer.majFinDeTour(player);

        //Assert
        assertEquals(oldHp, player.currenthealthpoints, "Les points de vie du joueur devraient rester les memes.");
    }

    @Test
    void majFinDeTour_HealingWhenHpAtMax() {
        //Arrange
        player player = new player("Test","tester","ARCHER",500,new ArrayList<>());

        //Setup
        player.healthpoints = 100;
        player.currenthealthpoints =  100;
        // int oldHp = player.currenthealthpoints;

        //Act
        UpdatePlayer.majFinDeTour(player);

        //Assert
        assertEquals(player.healthpoints, player.currenthealthpoints, "Les points de vie du joueur devraient rester les memes.");
    }

    @Test
    void majFinDeTour_HealingShouldNotExceedMaxHp() {
        //Arrange
        player player = new player("Test","tester","ARCHER",500,new ArrayList<>());

        //Setup
        player.healthpoints = 100;
        player.currenthealthpoints =  150;

        //Act
        UpdatePlayer.majFinDeTour(player);

        //Assert
        assertEquals(100,player.currenthealthpoints, "Les points de vie du joueur ne devraient pas dépasser le maximum.");
    }

    //pitest
    @Test
    void pit_majFinDeTour_AdventurerLevel3KillsMutant() {
        //ARRANGE
        player player = new player("MutantSlayer", "Adventurer", "ADVENTURER", 0, new ArrayList<>());

        //SETUP
        player.setXp(27); 
        player.healthpoints = 100;
        player.currenthealthpoints = 20; 
        int oldHp = player.currenthealthpoints;

        // ACT
        UpdatePlayer.majFinDeTour(player);

        //ASSERT
        assertEquals(oldHp + 2, player.currenthealthpoints, "Le Niveau 3 ne doit pas activer le malus de -1 PV. (Tue le mutant '<=').");
    }

    @Test
    void pit_addXp_addInventoryKillMutant(){
        //arrange
        player player = new player("MutantSlayer", "Adventurer", "ADVENTURER", 0, new ArrayList<>());

        //setup
        player.setXp(27); 
        int oldInventorySize = player.inventory.size();

        //act
        UpdatePlayer.addXp(player, 30);

        //assert
        assertEquals(oldInventorySize + 1, player.inventory.size(), "L'inventaire doit augmenter de 1 en tuant le mutant.");
    }
    //Pour ce code ↑ le résultat de A - 0 est toujours le même que A + 0. Le code modifié est logiquement identique à l'original
    // donc aucun test ne peut le tuer.



        
}
