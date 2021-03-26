package dev.dankom.plightspigot;

import dev.dankom.logger.LogManager;
import dev.dankom.logger.interfaces.ILogger;
import dev.dankom.logger.profiler.Profiler;
import dev.dankom.plightspigot.listener.ListenerScanner;
import dev.dankom.plightspigot.logger.SpigotLogger;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class PlightPlugin extends JavaPlugin {

    public final ILogger logger = LogManager.addLogger(getPluginName(), new SpigotLogger(this));
    public final Profiler profiler = LogManager.addProfiler(getPluginName(), new Profiler());

    @Override
    public void onEnable() {
        new ListenerScanner(this);

        startup();
    }

    @Override
    public void onDisable() {
        shutdown();
    }

    public abstract String getPluginName();
    public abstract String getGroupID();

    public abstract void startup();
    public abstract void shutdown();
}
