package cn.ChengZhi;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class tpaDefuse_Command implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            if (args.length == 1) {
                String AcceptPlayerName = args[0];
                if (Bukkit.getPlayer(AcceptPlayerName) == null) {
                    main.instance.getConfig().set(AcceptPlayerName + "_Temp_TpaPlayerName",null);
                    main.instance.getConfig().set(AcceptPlayerName + "_Temp_Tpa",null);
                    main.instance.getConfig().set(AcceptPlayerName + "_Temp_TpaTime",null);
                    main.instance.saveConfig();
                    main.instance.reloadConfig();
                    sender.sendMessage(ChatColor.RED + "错误,那个玩家突然下线了!传送已取消");
                    return false;
                }
                Player player = (Player) sender;
                if (main.instance.getConfig().getString(AcceptPlayerName + "_Temp_TpaPlayerName") == null|| !Objects.equals(main.instance.getConfig().getString(AcceptPlayerName + "_Temp_TpaPlayerName"), player.getName())) {
                    sender.sendMessage(ChatColor.RED + "这个玩家没有给你发传送请求");
                    return false;
                }
                Player AcceptPlayer = Bukkit.getPlayer(AcceptPlayerName);
                Objects.requireNonNull(AcceptPlayer).sendMessage(ChatColor.RED + "对方拒绝了你的请求！");
                main.instance.getConfig().set(AcceptPlayerName + "_Temp_TpaPlayerName",null);
                main.instance.getConfig().set(AcceptPlayerName + "_Temp_Tpa",null);
                main.instance.getConfig().set(AcceptPlayerName + "_Temp_TpaTime",null);
                main.instance.saveConfig();
                main.instance.reloadConfig();
            }else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c用法错误,用法:/tpaDefuse <玩家ID>"));
            }
        }else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c这个命令只有玩家才能执行"));
        }
        return false;
    }
}
