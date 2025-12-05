package re.forestier.edu.rpg.display;

import re.forestier.edu.rpg.player;

public class Affichage {

    private static final String INDENT = "   ";

    public static String afficherJoueur(player player) {
        if (player == null) {
            throw new IllegalArgumentException("Player must not be null");
        }

        StringBuilder builder = new StringBuilder();

        appendHeader(builder, player);
        appendAbilities(builder, player);
        appendInventory(builder, player);

        return builder.toString();
    }

    private static void appendHeader(StringBuilder builder, player player) {
        builder.append("Joueur ").append(player.Avatar_name).append(" joué par ").append(player.playerName);

        builder.append("\nNiveau : ").append(player.retrieveLevel()).append(" (XP totale : ").append(player.getXp()).append(")");
    }

    private static void appendAbilities(StringBuilder builder, player player) {
        builder.append("\n\nCapacités :");

        if (player.abilities == null || player.abilities.isEmpty()) {
            return;
            // même affichage qu'avant, juste le titre
        }

        player.abilities.forEach((name, level) -> {
            builder.append("\n").append(INDENT).append(name).append(" : ").append(level);
        });
    }

    private static void appendInventory(StringBuilder builder, player player) {
        builder.append("\n\nInventaire :");

        if (player.inventory == null || player.inventory.isEmpty()) {
            return; // juste le titre, comme avant si la liste est vide
        }

        player.inventory.forEach(item -> {
            builder.append("\n").append(INDENT).append(item);
        });
    }
}
