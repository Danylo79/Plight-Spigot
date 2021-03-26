package dev.dankom.plightspigot.scanner;

import dev.dankom.plightspigot.PlightPlugin;
import dev.dankom.plightspigot.command.ICommand;
import dev.dankom.util.general.Stopwatch;
import org.reflections.Reflections;

public class CommandScanner {
    public CommandScanner(PlightPlugin plugin) throws IllegalAccessException, InstantiationException {
        this(plugin, plugin.getGroupID());
    }

    public CommandScanner(PlightPlugin plugin, String groupID) throws IllegalAccessException, InstantiationException {
        int commands = 0;

        Stopwatch stopwatch = new Stopwatch();
        stopwatch.start();

        plugin.logger.info("CommandScanner", "Looking for commands!");
        for (Class command : new Reflections(groupID).getSubTypesOf(ICommand.class)) {
            System.out.println("Plugin: " + plugin);
            System.out.println("Server: " + plugin.getServer());
            ICommand c = (ICommand) command.newInstance();
            plugin.setCommandExecutor(c);
            plugin.logger.info("CommandScanner", "Found command " + c.getClass().getName() + "!");
            commands++;
        }

        stopwatch.stop();
        plugin.logger.info("CommandScanner", "Found " + commands + " command" + (commands == 1 ? "" : "s") + " in " + stopwatch.getTimePassed() + "ms!");
    }
}
