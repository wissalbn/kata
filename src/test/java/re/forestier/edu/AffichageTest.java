package re.forestier.edu;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
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
        
        //Le code d'UpdatePlayer.abilitiesPerTypeAndLevel() montre que l'ARCHER niveau 1 a ATK: 3.
        //Cette assertion vérifie que la boucle FOR EACH a bien ajouté l'entree ATK.
        assertTrue(affichage.contains("ATK : 3"), "L'affichage doit contenir l'entrée de capacité ATK : 3.");
    }
}
