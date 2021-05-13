package ca.lexandra.lexlib;

import org.bukkit.plugin.java.JavaPlugin;

public class LexLib {

    private static JavaPlugin plugin;

    // For other classes in our library
    public static JavaPlugin getPlugin() {
        return plugin;
    }

    // This method must not be used any where in the library!
    public static void setPlugin(JavaPlugin plugin) {
        LexLib.plugin = plugin;
    }

}
