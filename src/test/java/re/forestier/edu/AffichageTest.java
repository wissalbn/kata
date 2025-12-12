package re.forestier.edu;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import re.forestier.edu.rpg.display.Affichage;
import re.forestier.edu.rpg.player;

public class AffichageTest {
    
    @Test
    void testAffichageIventaire(){
        //Arrange
        ArrayList<String> inventaire = new ArrayList<>();
        inventaire.add("Potion");
        inventaire.add("Sword");
        inventaire.add("Shield");
        player player = new player("Test","tester","ARCHER",10,inventaire);

        //Act
        String affichage = Affichage.afficherJoueur(player);

        //Assert
        assertTrue(affichage.contains("Potion"));
        assertTrue(affichage.contains("Sword"));
        assertTrue(affichage.contains("Shield"));

    }

    @Test
    void forceClassLoadingCoverage() throws Exception {
        try {
            Constructor<Affichage> constructor = Affichage.class.getDeclaredConstructor();
            
            constructor.setAccessible(true);
            
            constructor.newInstance();
        } catch (Exception e) {
            fail("Le chargement de la classe Affichage a échoué via la réflexion: " + e.getMessage());
        }
    }

    @Test
    void pit_Affichage_KillsForEachMutant() {
        //ARRANGE
        player player = new player("KillLoop", "Fighter", "ARCHER", 10, new ArrayList<>());

        //ACT
        String affichage = Affichage.afficherJoueur(player);

        //ASSERT
        
        assertTrue(affichage.contains("ATK : 3"), "L'affichage doit contenir l'entrée de capacité ATK : 3.");
    }

    @Test
    void afficherJoueur_ShouldHandleEmptyAbilities() {
        // Arrange
        player player = new player("KillLoop", "Fighter", "ARCHER", 10, new ArrayList<>());
        // Vider la map abilities pour forcer le chemin if (player.abilities.isEmpty())
        player.abilities = new HashMap<>(); 
        
        // Act
        String output = Affichage.afficherJoueur(player);
        
        // Assert
        assertTrue(output.contains("Capacités :"), "Le titre Capacités doit être présent.");
        assertFalse(output.contains(Affichage.INDENT), "Aucune ligne de capacité ne doit être présente.");
    }

    //tests après refractoring
    @Test
    void afficherJoueur_shouldThrowWhenPlayerIsNull() {
        assertThrows(IllegalArgumentException.class, () -> Affichage.afficherJoueur(null));
    }

    @Test
    void afficherJoueur_shouldHandleNullAbilitiesAndNullInventory() {
        player p = new player("P", "A", "ARCHER", 0, new ArrayList<>());
        p.setXp(111);
        p.abilities = null;  
        p.inventory = null;  

        String out = Affichage.afficherJoueur(p);

        //les titres sont là
        assertTrue(out.contains("\n\nCapacités :"));
        assertTrue(out.contains("\n\nInventaire :"));

        //et pas de lignes de contenu
        assertFalse(out.contains("\n" + Affichage.INDENT));
    }

    
}
