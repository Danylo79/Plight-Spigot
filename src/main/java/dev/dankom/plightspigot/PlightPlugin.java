package dev.dankom.plightspigot;

import dev.dankom.logger.LogManager;
import dev.dankom.logger.interfaces.ILogger;
import dev.dankom.logger.profiler.Profiler;
import dev.dankom.plightspigot.command.ICommand;
import dev.dankom.plightspigot.logger.SpigotProfiler;
import dev.dankom.plightspigot.scanner.CommandScanner;
import dev.dankom.plightspigot.scanner.ListenerScanner;
import dev.dankom.plightspigot.logger.SpigotLogger;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class PlightPlugin extends JavaPlugin {

    public final ILogger logger = LogManager.addLogger(getPluginName(), new SpigotLogger(this));
    public final Profiler profiler = LogManager.addProfiler(getPluginName(), new SpigotProfiler(this));

    @Override
    public void onEnable() {
        try {
            logger.info("PlightPlugin", "Scanning default groupID");
            new ListenerScanner(this, "dev.dankom.plightspigot");
            new CommandScanner(this, "dev.dankom.plightspigot");

            logger.info("PlightPlugin", "Scanning plugin groupID");
            new ListenerScanner(this);
            new CommandScanner(this);

            startup();
        } catch (Exception e) {
            profiler.crash("Failed: " + e.getMessage(), e);
        }
    }

    @Override
    public void onDisable() {
        shutdown();
    }

    public abstract String getPluginName();
    public abstract String getGroupID();

    public abstract void startup();
    public abstract void shutdown();

    public void registerListener(Listener listener) {
        getServer().getPluginManager().registerEvents(listener, this);
    }

    public void setCommandExecutor(ICommand c) {
        getCommand(c.getName()).setExecutor(c);
    }
}
