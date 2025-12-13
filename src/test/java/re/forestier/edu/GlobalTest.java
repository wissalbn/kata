package re.forestier.edu;

import org.junit.jupiter.api.Test;
import re.forestier.edu.rpg.display.Affichage;
import re.forestier.edu.rpg.UpdatePlayer;
import re.forestier.edu.rpg.player;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GlobalTest {

    @Test
    void testAffichageBase() {
        // ARRANGE: Création d'un joueur
        player player = new player("Florian", "Gnognak le Barbare", "ADVENTURER", 200, new ArrayList<>());
    
        // ACT (SETUP): On ajoute l'XP pour qu'il soit au Niveau 2 (10 <= XP < 27)
        UpdatePlayer.addXp(player, 20); 

        // ACT: Génère la chaîne d'affichage
        String resultat = Affichage.afficherJoueur(player);

        // ASSERT: On vérifie les éléments clés attendus pour un joueur de Niveau 2
        // 1. L'en-tête et le nom
        assertTrue(resultat.contains("Gnognak le Barbare joué par Florian"), "L'en-tête doit être correct.");
        // 2. Le niveau
        assertTrue(resultat.contains("Niveau : 2 (XP totale : 20)"), "Le niveau et l'XP doivent être affichés correctement.");
        // 3. L'inventaire (qui doit maintenant contenir 1 objet aléatoire)
        assertTrue(resultat.contains("Inventaire :"), "La section Inventaire doit être présente.");
    }
}
