package cn.ChengZhi;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class setspawn_Command implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            World world = player.getWorld();
            String worldName = world.getName();
            int locationX = (int) player.getLocation().getX();
            int locationY = (int) player.getLocation().getY();
            int locationZ = (int) player.getLocation().getZ();
            main.instance.getConfig().set("SpawnSetting.WorldName",worldName);
            main.instance.getConfig().set("SpawnSetting.locationX",locationX);
            main.instance.getConfig().set("SpawnSetting.locationY",locationY);
            main.instance.getConfig().set("SpawnSetting.locationZ",locationZ);
            main.instance.saveConfig();
            main.instance.reloadConfig();
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a设置完成!"));
        }else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c这个命令只能是玩家执行"));
        }
        return false;
    }
}
