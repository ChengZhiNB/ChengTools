package cn.ChengZhi;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public class Tpa_Detect extends BukkitRunnable {
    main pluginmain;

    public Tpa_Detect(main main1) {
        this.pluginmain = main1;
    }

    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            String PlayerName = player.getName();
            if (main.instance.getConfig().getString(PlayerName + "_Temp_TpaPlayerName") != null) {
                if (main.instance.getConfig().getBoolean(PlayerName + "_Temp_Tpa")) {
                    if (Bukkit.getPlayer(Objects.requireNonNull(main.instance.getConfig().getString(PlayerName + "_Temp_TpaPlayerName"))) == null) {
                        player.sendMessage(ChatColor.RED + "错误,那个玩家突然下线了!传送已取消");
                        main.instance.getConfig().set(PlayerName + "_Temp_TpaPlayerName",null);
                        main.instance.getConfig().set(PlayerName + "_Temp_Tpa",null);
                        main.instance.getConfig().set(PlayerName + "_Temp_TpaTime",null);
                        main.instance.saveConfig();
                        main.instance.reloadConfig();
                    }else {
                        Player TpaPlayer = Bukkit.getPlayer(Objects.requireNonNull(main.instance.getConfig().getString(PlayerName + "_Temp_TpaPlayerName")));
                        Location TpaLocation = Objects.requireNonNull(TpaPlayer).getLocation();
                        player.teleport(TpaLocation);
                        player.sendMessage(ChatColor.GREEN + "传送成功！");
                        TpaPlayer.sendMessage(ChatColor.GREEN + player.getName() + "传送到了你这里!");
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
