package cn.ChengZhi;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Nick_Command implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 2) {
            String args1 = args[0];
            String args2 = args[1];
            for (String AntiNickNames : main.instance.getConfig().getStringList("AntiNickNameList")) {
                if (args1.equals(AntiNickNames)) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c你不能Nick这个名字"));
                    return false;
                }
            }
            if (sender.isOp()) {
                if (main.instance.getConfig().getBoolean("NickNameSetting.AllowAdminUsedNick")) {
                    if (Bukkit.getPlayer(args1) == null) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c这个玩家不存在或不在线！"));
                    }else {
                        Player player = Bukkit.getPlayer(args1);
                        java.util.Objects.requireNonNull(player).setDisplayName(ChatColor.translateAlternateColorCodes('&',args2 + "&f"));
                        java.util.Objects.requireNonNull(player).setPlayerListName(ChatColor.translateAlternateColorCodes('&',args2 + "&f"));
                        main.instance.getConfig().set(player.getName() + "_NickName",args2);
                        main.instance.saveConfig();
                        main.instance.reloadConfig();
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a设置成功！"));
                    }
                }else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c这个服务器禁止管理员使用Nick命令！"));
                }
            }else {
                if (main.instance.getConfig().getBoolean("NickNameSetting.AllowPlayerUsedNick")) {
                    if (Bukkit.getPlayer(args1) == null) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c这个玩家不存在或不在线！"));
                    }else {
                        Player player = Bukkit.getPlayer(args1);
                        java.util.Objects.requireNonNull(player).setDisplayName(ChatColor.translateAlternateColorCodes('&',args2 + "&f"));
                        java.util.Objects.requireNonNull(player).setPlayerListName(ChatColor.translateAlternateColorCodes('&',args2 + "&f"));
                        main.instance.getConfig().set(player.getName() + "_NickName",args2);
                        main.instance.saveConfig();
                        main.instance.reloadConfig();
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a设置成功！"));
                    }
                }else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c这个服务器禁止玩家使用Nick命令！"));
                }
            }
        }else {
            if (args.length == 1) {
                String args1 = args[0];
                for (String AntiNickNames : main.instance.getConfig().getStringList("AntiNickNameList")) {
                    if (args1.equals(AntiNickNames)) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c你不能Nick这个名字"));
                        return false;
                    }
                }
                if (sender.isOp()) {
                    if (main.instance.getConfig().getBoolean("NickNameSetting.AllowAdminUsedNick")) {
                        if (sender instanceof Player) {
                            Player player = (Player) sender;
                            java.util.Objects.requireNonNull(player).setDisplayName(ChatColor.translateAlternateColorCodes('&',args1 + "&f"));
                            java.util.Objects.requireNonNull(player).setPlayerListName(ChatColor.translateAlternateColorCodes('&',args1 + "&f"));
                            main.instance.getConfig().set(player.getName() + "_NickName",args1);
                            main.instance.saveConfig();
                            main.instance.reloadConfig();
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a设置成功！"));
                        }else {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c用法错误,用法：/nick <玩家ID> <要设置的名称>"));
                        }
                    }else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c这个服务器禁止管理员使用Nick命令！"));
                    }
                }else {
                    if (main.instance.getConfig().getBoolean("NickNameSetting.AllowPlayerUsedNick")) {
                        if (sender instanceof Player) {
                            Player player = (Player) sender;
                            java.util.Objects.requireNonNull(player).setDisplayName(ChatColor.translateAlternateColorCodes('&',args1 + "&f"));
                            java.util.Objects.requireNonNull(player).setPlayerListName(ChatColor.translateAlternateColorCodes('&',args1 + "&f"));
                            main.instance.getConfig().set(player.getName() + "_NickName",args1);
                            main.instance.saveConfig();
                            main.instance.reloadConfig();
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a设置成功！"));
                        }else {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c用法错误,用法：/nick <玩家ID> <要设置的名称>"));
                        }
                    }else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c这个服务器禁止玩家使用Nick命令！"));
                    }
                }
            }else {
                if (sender instanceof Player) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c用法错误,用法1：/nick <玩家ID> <要设置的名称>\n用法2：/nick <要设置的名称>"));
                }else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c用法错误,用法：/nick <玩家ID> <要设置的名称>"));
                }
            }
        }
        return false;
    }
}
