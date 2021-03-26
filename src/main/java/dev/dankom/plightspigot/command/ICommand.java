package dev.dankom.plightspigot.command;

import org.bukkit.command.CommandExecutor;

public abstract class ICommand implements CommandExecutor {
    public abstract String getName();

    @Override
    public String toString() {
        return "ICommand={Name=" + getName() + "}}";
    }
}
