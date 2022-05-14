package cn.ChengZhi;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static cn.ChengZhi.multi.*;

public class testGUI implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.getName().equals("ChengZhiYa")) {
                if (args.length == 1) {
                    String GUIName = args[0];
                    if (GUIName.equals("KickGUI")) {
                        KickGui(player);
                        return false;
                    }

//                    if (GUIName.equals("HomeGUI")) {
//                        HomeGUI(player,1);
//                        return false;
//                    }

                    sender.sendMessage(ColorMessage("&c这个命令只有插件作者才能使用"));
                }else {
                    sender.sendMessage(ColorMessage("&c这个命令只有插件作者才能使用"));
                }
            }else {
                sender.sendMessage(ColorMessage("&c这个命令只有插件作者才能使用"));
            }
        }else {
            sender.sendMessage(ColorMessage("&c这个命令只有插件作者才能使用"));
        }
        return false;
    }
}
