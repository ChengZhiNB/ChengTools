package cn.ChengZhi;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.List;

public class PlayerChat_Event implements Listener {
    @EventHandler
    public void PlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String Message = ChatColor.translateAlternateColorCodes('&',event.getMessage());
        if (player.isOp()) {
            if (main.instance.getConfig().getBoolean("ColorChatSetting.AllowAdminUsedColorChat")) {
                event.setMessage(Message);
            }
        }else {
            if (main.instance.getConfig().getBoolean("ColorChatSetting.AllowPlayerUsedColorChat")) {
                event.setMessage(Message);
            }
        }

        List<String> keywords = new ArrayList<>(main.instance.getConfig().getStringList("AntiKeyWords"));
        for (String keyword : keywords) {
            if (Message.contains(keyword)) {
                if (!player.isOp()) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l检测到屏蔽词，已阻止发送\n屏蔽词内容:" + keyword));
                    event.setCancelled(true);
                    for (Player player1 : Bukkit.getOnlinePlayers()) {
                        if (player1.isOp()) {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&l" + player.getName() + "尝试发送屏蔽词，已被阻止发送\n屏蔽词内容:" + keyword));
                        }
                    }
                }
                return;
            }
        }
    }
}
