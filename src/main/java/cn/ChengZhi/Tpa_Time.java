package cn.ChengZhi;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public class Tpa_Time extends BukkitRunnable {
    main pluginmain;

    public Tpa_Time(main main1) {
        this.pluginmain = main1;
    }

    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            String PlayerName = player.getName();
            if (main.instance.getConfig().getString(PlayerName + "_Temp_TpaPlayerName") != null) {
                if (!main.instance.getConfig().getBoolean(PlayerName + "_Temp_Tpa")) {
                    int TpaTime = main.instance.getConfig().getInt(PlayerName + "_Temp_TpaTime");
                    main.instance.getConfig().set(PlayerName + "_Temp_TpaTime",TpaTime+1);
                    if (TpaTime+1 >= 60) {
                        String TpaPlayerName = main.instance.getConfig().getString(PlayerName + "_Temp_TpaPlayerName");
                        Player TpaPlayer = Bukkit.getPlayer(Objects.requireNonNull(TpaPlayerName));
                        Objects.requireNonNull(TpaPlayer).sendMessage(ChatColor.translateAlternateColorCodes('&',"&c" + PlayerName + "给你发的的传送请求已超时"));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c你发给" + TpaPlayerName + "的传送请求已超时"));
                        main.instance.getConfig().set(PlayerName + "_Temp_TpaPlayerName",null);
                        main.instance.getConfig().set(PlayerName + "_Temp_Tpa",null);
                        main.instance.getConfig().set(PlayerName + "_Temp_TpaTime",null);
                        main.instance.saveConfig();
                        main.instance.reloadConfig();
                    }
                }
            }
        }
    }
}