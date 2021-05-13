package ca.lexandra.lexlib.utils;

import ca.lexandra.lexlib.LexLib;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Consumer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class UpdateChecker {

    private final JavaPlugin plugin;
    private static int resourceId;

    public static void checkUpdate(int id) {
        new UpdateChecker(LexLib.getPlugin(), id).getVersion(version -> {
            if (LexLib.getPlugin().getDescription().getVersion().equalsIgnoreCase(version)) {
                Commons.log("There is not a new update available! Download it here: https://www.spigotmc.org/resources/" + UpdateChecker.resourceId);
            } else {
                Commons.log("You're using the latest version of " + LexLib.getPlugin().getName() + "! Good job :D!");
            }
        });
    }

    public UpdateChecker(JavaPlugin plugin, int resourceId) {
        this.plugin = plugin;
        UpdateChecker.resourceId = resourceId;
    }

    public void getVersion(final Consumer<String> consumer) {
        Bukkit.getScheduler().runTaskAsynchronously(this.plugin, () -> {
            try (InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + UpdateChecker.resourceId).openStream(); Scanner scanner = new Scanner(inputStream)) {
                if (scanner.hasNext()) {
                    consumer.accept(scanner.next());
                }
            } catch (IOException exception) {
                this.plugin.getLogger().info("Cannot look for updates: " + exception.getMessage());
            }
        });
    }

}
