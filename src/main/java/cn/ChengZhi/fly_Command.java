package cn.ChengZhi;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class fly_Command implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            if (args.length == 1) {
                String flyPlayerName = args[0];
                if (Bukkit.getPlayer(flyPlayerName) == null) {
                    sender.sendMessage(ChatColor.RED + "错误,这个玩家不存在或不在线!");
                    return false;
                }
                Player player = Bukkit.getPlayer(flyPlayerName);
                if (main.instance.getConfig().getBoolean(Objects.requireNonNull(player).getName() + "_Fly")) {
                    player.setAllowFlight(false);
                    main.instance.getConfig().set(Objects.requireNonNull(player).getName() + "_Fly",null);
                    multi.SaveAndReloadConfig();
                    sender.sendMessage(ChatColor.GREEN + "你已将" + flyPlayerName + "的飞行模式关闭");
                }else {
                    player.setAllowFlight(true);
                    main.instance.getConfig().set(Objects.requireNonNull(player).getName() + "_Fly",player.getAllowFlight());
                    multi.SaveAndReloadConfig();
                    sender.sendMessage(ChatColor.GREEN + "你已将" + flyPlayerName + "的飞行模式开启");
                }
            }else {
                if (args.length == 0) {
                    Player player = (Player) sender;
                    String PlayerName = player.getName();
                    if (main.instance.getConfig().getBoolean(Objects.requireNonNull(player).getName() + "_Fly")) {
                        player.setAllowFlight(false);
                        main.instance.getConfig().set(Objects.requireNonNull(player).getName() + "_Fly",null);
                        multi.SaveAndReloadConfig();
                        sender.sendMessage(ChatColor.GREEN + "你已将" + PlayerName + "的飞行模式关闭");
                    }else {
                        player.setAllowFlight(true);
                        main.instance.getConfig().set(Objects.requireNonNull(player).getName() + "_Fly",player.getAllowFlight());
                        multi.SaveAndReloadConfig();
                        sender.sendMessage(ChatColor.GREEN + "你已将" + PlayerName + "的飞行模式开启");
                    }
                }else {
                    sender.sendMessage(ChatColor.RED + "用法错误,正确用法:/fly " + ChatColor.GRAY + "[玩家ID]");
                }
            }
        }else {
            if (args.length == 1) {
                String flyPlayerName = args[0];
                if (Bukkit.getPlayer(flyPlayerName) == null) {
                    sender.sendMessage(ChatColor.RED + "错误,这个玩家不存在或不在线!");
                    return false;
                }
                Player player = Bukkit.getPlayer(flyPlayerName);
                if (main.instance.getConfig().getBoolean(Objects.requireNonNull(player).getName() + "_Fly")) {
                    player.setAllowFlight(false);
                    main.instance.getConfig().set(Objects.requireNonNull(player).getName() + "_Fly",null);
                    multi.SaveAndReloadConfig();
                    sender.sendMessage(ChatColor.GREEN + "你已将" + flyPlayerName + "的飞行模式关闭");
                }else {
                    player.setAllowFlight(true);
                    main.instance.getConfig().set(Objects.requireNonNull(player).getName() + "_Fly",player.getAllowFlight());
                    multi.SaveAndReloadConfig();
                    sender.sendMessage(ChatColor.GREEN + "你已将" + flyPlayerName + "的飞行模式开启");
                }
            }else {
                sender.sendMessage(ChatColor.RED + "用法错误,正确用法:/fly " + ChatColor.GRAY + "<玩家ID>");
            }
        }
        return false;
    }
}
