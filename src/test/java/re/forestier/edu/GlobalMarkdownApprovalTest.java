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
        UpdatePlayer.addXp(player, 20);

        // ACT
        String actual = AffichageMarkdown.afficherJoueurMarkdown(player);

        // ASSERT
        assertApproved("GlobalMarkdownTest.testMarkdownToString",actual);
    }

    private static void assertApproved(String baseName, String actual) throws IOException {
        Path dir = Path.of("src", "test", "resources", "re", "forestier", "edu");
        Path approved = dir.resolve(baseName + ".approved.txt");
        Path received = dir.resolve(baseName + ".received.txt");

        Files.createDirectories(dir);

        if (!Files.exists(approved)) {
            Files.writeString(received, actual, StandardCharsets.UTF_8);
            fail("Approved file not found. Created: " + received);
        }

        String expected = Files.readString(approved, StandardCharsets.UTF_8);

        // Normalisation \r\n (Windows) -> \n (Unix)
        String normExpected = expected.replace("\r\n", "\n");
        String normActual   = actual.replace("\r\n", "\n");

        if (!normExpected.equals(normActual)) {
            Files.writeString(received, actual, StandardCharsets.UTF_8);
            fail("Output differs. See: " + received);
        } else {
            if (Files.exists(received)) Files.delete(received);
        }
    }




}
