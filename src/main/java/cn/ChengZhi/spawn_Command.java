package cn.ChengZhi;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class spawn_Command implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            String worldName = main.instance.getConfig().getString("SpawnSetting.WorldName");
            int locationX = main.instance.getConfig().getInt("SpawnSetting.locationX");
            int locationY = main.instance.getConfig().getInt("SpawnSetting.locationY");
            int locationZ = main.instance.getConfig().getInt("SpawnSetting.locationZ");
            if (worldName == null) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c你好像还没有设置主城呢,使用/setspwan设置主城吧!"));
            }else {
                World world = Bukkit.getWorld(worldName);
                Location location = new Location(world,locationX,locationY,locationZ);
                location.setX(locationX);
                location.setY(locationY);
                location.setZ(locationZ);
                player.teleport(location);
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a欢迎来到主城!"));
            }

        }else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c这个命令只能是玩家执行"));
        }
        return false;
    }
}
