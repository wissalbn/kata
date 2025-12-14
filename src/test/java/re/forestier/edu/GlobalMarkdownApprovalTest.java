package re.forestier.edu;

import org.junit.jupiter.api.Test;
import re.forestier.edu.rpg.UpdatePlayer;
import re.forestier.edu.rpg.display.AffichageMarkdown;
import re.forestier.edu.rpg.player;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.fail;

public class GlobalMarkdownApprovalTest {

    @Test
    void testToStringMarkdown() throws IOException {
        // ARRANGE
        player player = new player("Florian", "Gnognak le Barbare", "ADVENTURER", 200, new ArrayList<>());
        UpdatePlayer.addXp(player, 20); // niveau 2

        // ACT
        String actual = AffichageMarkdown.afficherJoueurMarkdown(player);

        // ASSERT
        assertApproved("GlobalMarkdownTest.testMarkdownToString.approved", actual);
    }

    private static void assertApproved(String name, String actual) throws IOException {
        Path approved = Path.of("src", "test", "resources", name + ".approved.txt");
        Path received = Path.of("src", "test", "resources", name + ".received.txt");

        if (!Files.exists(approved)) {
            Files.writeString(received, actual, StandardCharsets.UTF_8);
            fail("Approved file not found. Created: " + received);
        }

        String expected = Files.readString(approved, StandardCharsets.UTF_8);
        if (!expected.equals(actual)) {
            Files.writeString(received, actual, StandardCharsets.UTF_8);
            fail("Output differs. See: " + received);
        } else {
            if (Files.exists(received)) Files.delete(received);
        }
    }
}
