package dev.dankom.plightspigot.scanner;

import dev.dankom.plightspigot.PlightPlugin;
import dev.dankom.util.general.Stopwatch;
import org.bukkit.event.Listener;
import org.reflections.Reflections;

public class ListenerScanner {
    public ListenerScanner(PlightPlugin plugin) throws IllegalAccessException, InstantiationException {
        this(plugin, plugin.getGroupID());
    }

    public ListenerScanner(PlightPlugin plugin, String groupID) throws IllegalAccessException, InstantiationException {
        int listeners = 0;

        Stopwatch stopwatch = new Stopwatch();
        stopwatch.start();

        plugin.logger.info("ListenerScanner", "Looking for listeners!");
        for (Class listener : new Reflections(groupID).getSubTypesOf(Listener.class)) {
            Listener l = (Listener) listener.newInstance();
            plugin.registerListener(l);
            plugin.logger.info("ListenerScanner", "Found listener " + l.getClass().getName() + "!");
            listeners++;

        }

        stopwatch.stop();
        plugin.logger.info("ListenerScanner", "Found " + listeners + " listener" + (listeners == 1 ? "" : "s") + " in " + stopwatch.getTimePassed() + "ms!");
    }
}
