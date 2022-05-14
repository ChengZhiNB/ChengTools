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
        main.instance.getConfig().set(player.getName() + "_Temp_TpaPlayerName",null);
        main.instance.getConfig().set(player.getName() + "_Temp_Tpa",null);
        main.instance.getConfig().set(player.getName() + "_Temp_TpaTime",null);
        main.instance.saveConfig();
        main.instance.reloadConfig();
        String PlayerQuitMessage = main.instance.getConfig().getString("PlayerQuitServerMessage");
        String AdminQuitMessage = main.instance.getConfig().getString("AdminQuitServerMessage");
        main.instance.getConfig().set(player.getName() + "_NickName",ChatColor.translateAlternateColorCodes('&', player.getDisplayName() + "&f"));
        main.instance.saveConfig();
        main.instance.reloadConfig();
        if (player.isOp()) {
            if (AdminQuitMessage == null) {
                event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(player, "&7[&c&l管理员&7]&e" + player.getDisplayName() + "&a离开了服务器")));
                return;
            }
            AdminQuitMessage = AdminQuitMessage.replaceAll("%PlayerName%",player.getPlayerListName());
            AdminQuitMessage = AdminQuitMessage.replaceAll("%playername%",player.getPlayerListName());
            event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(player, AdminQuitMessage)));
        } else {
            if (PlayerQuitMessage == null) {
                event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(player, "&7[&f&l玩家&7]&e" + player.getDisplayName() + "&a离开了服务器")));
                return;
            }
            PlayerQuitMessage = PlayerQuitMessage.replaceAll("%PlayerName%",player.getPlayerListName());
            PlayerQuitMessage = PlayerQuitMessage.replaceAll("%playername%",player.getPlayerListName());
            event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(player, PlayerQuitMessage)));
        }
    }
}
