package dev.dankom.plightspigot.listener;

import dev.dankom.plightspigot.PlightPlugin;
import dev.dankom.util.general.Stopwatch;
import org.bukkit.event.Listener;
import org.reflections.Reflections;

public class ListenerScanner {
    public ListenerScanner(PlightPlugin plugin) {
        int listeners = 0;

        Stopwatch stopwatch = new Stopwatch();
        stopwatch.start();

        plugin.logger.info("ListenerScanner", "Looking for listeners!");
        for (Class listener : new Reflections(plugin.getGroupID()).getSubTypesOf(Listener.class)) {
            try {
                Listener l = (Listener) listener.newInstance();
                plugin.getServer().getPluginManager().registerEvents(l, plugin);
                plugin.logger.info("ListenerScanner", "Found listener " + l.getClass().getName() + "!");
                listeners++;
            } catch (Exception e) {
                plugin.logger.error("ListenerScanner", "Failed: " + e.getMessage());
            }
        }

        stopwatch.stop();
        plugin.logger.info("ListenerScanner", "Found " + listeners + " listener" + (listeners == 1 ? "" : "s") + " in " + stopwatch.getTimePassed() + "ms!");
    }
}
