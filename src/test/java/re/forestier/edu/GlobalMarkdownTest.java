package re.forestier.edu;

import org.junit.jupiter.api.Test;
import re.forestier.edu.rpg.UpdatePlayer;
import re.forestier.edu.rpg.player;
import re.forestier.edu.rpg.display.AffichageMarkdown;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GlobalMarkdownTest {

    @Test
    void testAffichageMarkdownBase() {
        //ARRANGE
        player player = new player("Florian", "Gnognak le Barbare", "ADVENTURER", 200, new ArrayList<>());

        //ACT
        UpdatePlayer.addXp(player, 20);

        //ACT
        String resultat = AffichageMarkdown.afficherJoueurMarkdown(player);

        //ASSERT
        assertTrue(resultat.contains("# Joueur Gnognak le Barbare joué par Florian"));
        assertTrue(resultat.contains("## Niveau"));
        assertTrue(resultat.contains("* Niveau : 2"));
        assertTrue(resultat.contains("* XP totale : 20"));
        assertTrue(resultat.contains("## Capacités"));
        assertTrue(resultat.contains("## Inventaire"));
    }
}
