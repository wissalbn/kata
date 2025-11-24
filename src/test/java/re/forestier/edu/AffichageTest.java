package re.forestier.edu;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import re.forestier.edu.rpg.Affichage;
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
            Constructor<re.forestier.edu.rpg.Affichage> constructor = re.forestier.edu.rpg.Affichage.class.getDeclaredConstructor();
            
            constructor.setAccessible(true);
            
            constructor.newInstance();
        } catch (Exception e) {
            fail("Le chargement de la classe Affichage a échoué via la réflexion: " + e.getMessage());
        }
    }
}
