package re.forestier.edu.rpg.display;

import re.forestier.edu.rpg.player;

public class AffichageMarkdown {

    public static String afficherJoueurMarkdown(player player) {
        if (player == null) {
            throw new IllegalArgumentException("Player must not be null");
        }

        StringBuilder sb = new StringBuilder();

        sb.append("# Joueur ").append(player.Avatar_name).append(" joué par ").append(player.playerName).append("\n\n");


        sb.append("## Niveau\n");
        sb.append("* Niveau : ").append(player.retrieveLevel()).append("\n");
        sb.append("* XP totale : ").append(player.getXp()).append("\n\n");

        sb.append("## Capacités\n");

        sb.append("\n");

        sb.append("## Inventaire\n");
        int count = (player.inventory == null) ? 0 : player.inventory.size();
        sb.append("* (").append(count).append(" objet").append(count > 1 ? "s" : "").append(")");

        return sb.toString();
    }
}
