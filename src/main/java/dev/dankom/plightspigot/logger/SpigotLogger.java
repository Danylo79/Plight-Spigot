package dev.dankom.plightspigot.logger;

import dev.dankom.logger.abztract.DefaultLogger;
import dev.dankom.logger.type.LogLevel;
import dev.dankom.plightspigot.PlightPlugin;
import dev.dankom.util.general.ColorUtil;
import net.md_5.bungee.api.ChatColor;

import java.util.Date;

public class SpigotLogger extends DefaultLogger {

    private PlightPlugin plugin;

    public SpigotLogger(PlightPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void log(LogLevel level, String loc, Object msg) {
        Date date = new Date();
        String time = "[" + date.getHours() + ":" + date.getMinutes() + "." + date.getSeconds() + "]";
        loc = "[" + loc + "]";
        String print = level.getColor() + time + " [" + level.getName() + "] " + loc + " " + msg + ColorUtil.ANSI_RESET;
        plugin.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', print));
        if (level.equals(LogLevel.FATAL)) {
            Runtime.getRuntime().exit(-1);
        }
    }
}
