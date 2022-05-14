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

public class tpaAccept_Command implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            if (args.length == 1) {
                String AcceptPlayerName = args[0];
                if (Bukkit.getPlayer(AcceptPlayerName) == null) {
                    multi.SetStringTemp(AcceptPlayerName + "_Temp_TpaPlayerName",null);
                    multi.SetBooleanTemp(AcceptPlayerName + "_Temp_Tpa",false);
                    multi.SetIntTemp(AcceptPlayerName + "_Temp_TpaTime",0);
                    sender.sendMessage(ChatColor.RED + "错误,那个玩家突然下线了!传送已取消");
                    return false;
                }
                Player player = (Player) sender;
                if (multi.getStringTemp(AcceptPlayerName + "_Temp_TpaPlayerName") == null|| !Objects.equals(multi.getStringTemp(AcceptPlayerName + "_Temp_TpaPlayerName"), player.getName())) {
                    sender.sendMessage(ChatColor.RED + "这个玩家没有给你发传送请求");
                    return false;
                }
                Player AcceptPlayer = Bukkit.getPlayer(AcceptPlayerName);
                Location SenderLocation = player.getLocation();
                Objects.requireNonNull(AcceptPlayer).teleport(SenderLocation);
                AcceptPlayer.sendMessage(ChatColor.GREEN + "传送成功！");
                player.sendMessage(ChatColor.GREEN + player.getName() + "传送到了你这里!");
                multi.SetStringTemp(AcceptPlayerName + "_Temp_TpaPlayerName",null);
                multi.SetBooleanTemp(AcceptPlayerName + "_Temp_Tpa",false);
                multi.SetIntTemp(AcceptPlayerName + "_Temp_TpaTime",0);
            }else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c用法错误,用法:/tpa <玩家ID>"));
            }
        }else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c这个命令只有玩家才能执行"));
        }
        return false;
    }
}
