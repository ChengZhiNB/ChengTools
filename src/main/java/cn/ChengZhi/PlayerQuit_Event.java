package cn.ChengZhi;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit_Event implements Listener {
    @EventHandler
    public void PlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        String PlayerQuitMessage = main.instance.getConfig().getString("PlayerQuitServerMessage");
        String AdminQuitMessage = main.instance.getConfig().getString("AdminQuitServerMessage");
        String args1 = player.getDisplayName();
        main.instance.getConfig().set(player.getName() + "_NickName",ChatColor.translateAlternateColorCodes('&',args1));
        main.instance.saveConfig();
        main.instance.reloadConfig();
        if (player.isOp()) {
            if (AdminQuitMessage == null) {
                main.instance.getLogger().info("检测到管理员进入服务器提示为null已替换成默认提示");
                AdminQuitMessage = "&7[&c&l管理员&7]&a欢迎&e%ChengTools_NickName%&c离开了服务器";
            }
            event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(player, AdminQuitMessage)));
        } else {
            if (PlayerQuitMessage == null) {
                main.instance.getLogger().info("检测到管理员进入服务器提示为null已替换成默认提示");
                PlayerQuitMessage = "&7[&f&l玩家&7]&e%ChengTools_NickName%&c离开了服务器";
            }
            event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(player, PlayerQuitMessage)));
        }
    }
}
