package cn.ChengZhi;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Objects;

public class PlayerQuit_Event implements Listener {
    @EventHandler
    public void PlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        main.instance.getConfig().set(player.getName() + "_Temp_TpaPlayerName",null);
        main.instance.getConfig().set(player.getName() + "_Temp_Tpa",null);
        main.instance.getConfig().set(player.getName() + "_Temp_TpaTime",null);
        main.instance.saveConfig();
        main.instance.reloadConfig();
        String PlayerQuitMessage = main.instance.getConfig().getString("PlayerQuitServerMessage");
        String AdminQuitMessage = main.instance.getConfig().getString("AdminQuitServerMessage");
        main.instance.getConfig().set(player.getName() + "_NickName",ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(main.instance.getConfig().getString(player.getName() + "_NickName")) + "&f"));
        main.instance.saveConfig();
        main.instance.reloadConfig();
        if (player.isOp()) {
            if (AdminQuitMessage == null) {
                main.instance.getLogger().info("检测到管理员进入服务器提示为null已替换成默认提示");
                AdminQuitMessage = "&7[&c&l管理员&7]&a欢迎&e%PlayerName%&c离开了服务器";
            }
            AdminQuitMessage.replace("%PlayerName%",player.getPlayerListName());
            event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(player, AdminQuitMessage)));
        } else {
            if (PlayerQuitMessage == null) {
                main.instance.getLogger().info("检测到管理员进入服务器提示为null已替换成默认提示");
                PlayerQuitMessage = "&7[&f&l玩家&7]&e%PlayerName%&c离开了服务器";
            }
            AdminQuitMessage.replace("%PlayerName%",player.getPlayerListName());
            event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(player, PlayerQuitMessage)));
        }
    }
}
