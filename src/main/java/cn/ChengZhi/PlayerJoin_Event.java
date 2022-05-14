package cn.ChengZhi;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import java.util.Objects;

public class PlayerJoin_Event implements Listener {
    @EventHandler
    public void PlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        main.instance.getConfig().set(player.getName() + "_Temp_TpaPlayerName",null);
        main.instance.getConfig().set(player.getName() + "_Temp_Tpa",null);
        main.instance.getConfig().set(player.getName() + "_Temp_TpaTime",null);
        main.instance.saveConfig();
        main.instance.reloadConfig();
        String args1;
        if (main.instance.getConfig().getString(player.getName() + "_NickName") == null) {
            args1 = player.getName();
        }else {
            args1 = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(main.instance.getConfig().getString(player.getName() + "_NickName")));
        }
        java.util.Objects.requireNonNull(player).setDisplayName(ChatColor.translateAlternateColorCodes('&',args1 + "&f"));
        java.util.Objects.requireNonNull(player).setPlayerListName(ChatColor.translateAlternateColorCodes('&',args1 + "&f"));
        String PlayerJoinMessage = main.instance.getConfig().getString("PlayerJoinServerMessage");
        String AdminJoinMessage = main.instance.getConfig().getString("AdminJoinServerMessage");
        if (player.isOp()) {
            if (AdminJoinMessage == null) {
                event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(player, "&7[&c&l管理员&7]&e" + player.getDisplayName() + "&a进入了服务器")));
                return;
            }
            AdminJoinMessage = AdminJoinMessage.replaceAll("%PlayerName%",player.getPlayerListName());
            AdminJoinMessage = AdminJoinMessage.replaceAll("%playername%",player.getPlayerListName());
            event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(player, AdminJoinMessage)));
        } else {
            if (PlayerJoinMessage == null) {
                event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(player, "&7[&f&l玩家&7]&e" + player.getDisplayName() + "&a进入了服务器")));
                return;
            }
            PlayerJoinMessage = PlayerJoinMessage.replaceAll("%PlayerName%",player.getPlayerListName());
            PlayerJoinMessage = PlayerJoinMessage.replaceAll("%playername%",player.getPlayerListName());
            event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(player, PlayerJoinMessage)));
        }
    }
}
