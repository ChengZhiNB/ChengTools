package cn.ChengZhi;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Tpa_Command implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            if (args.length == 1) {
                String TpaPlayerName = args[0];
                if (Bukkit.getPlayer(TpaPlayerName) == null) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c这个玩家不存在"));
                    return false;
                }

                Player player = (Player) sender;
                Player TpaPlayer = Bukkit.getPlayer(TpaPlayerName);
                Location TpaLocation = Objects.requireNonNull(TpaPlayer).getLocation();
                if (player.isOp()) {
                    player.sendMessage(ChatColor.GREEN + "传送成功！");
                    TpaPlayer.sendMessage(ChatColor.GREEN + player.getName() + "传送到了你这里!");
                    player.teleport(TpaLocation);
                }else {
                    String PlayerName = player.getName();
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a传送请求已发送"));
                    TpaPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&',"&7--------------&9Tpa系统&7--------------\n&a玩家:" + PlayerName + "想来你这里看看\n&a同意/tpaAccept "+ PlayerName + "   " + "&c拒绝/tpaRefuse " + PlayerName + "\n&7--------------&9Tpa系统&7--------------\n"));
                    main.instance.getConfig().set(PlayerName + "_Temp_TpaPlayerName",TpaPlayer.getName());
                    main.instance.getConfig().set(PlayerName + "_Temp_Tpa",false);
                    main.instance.getConfig().set(PlayerName + "_Temp_TpaTime",0);
                }
            }else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c用法错误,用法:/tpa <玩家ID>"));
            }
        }else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c这个命令只有玩家才能执行"));
        }
        return false;
    }
}
