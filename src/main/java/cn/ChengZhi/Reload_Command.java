package cn.ChengZhi;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class Reload_Command implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        main.instance.reloadConfig();
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a重载完成!"));
        return false;
    }
}
