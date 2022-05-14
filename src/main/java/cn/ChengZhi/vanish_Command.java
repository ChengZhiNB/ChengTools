package cn.ChengZhi;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class vanish_Command implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            if (args.length == 1) {
                String flyPlayerName = args[0];
                if (Bukkit.getPlayer(flyPlayerName) == null) {
                    sender.sendMessage(ChatColor.RED + "错误,这个玩家不存在或不在线!");
                    return false;
                }
                Player player = Bukkit.getPlayer(flyPlayerName);
                if (main.instance.getConfig().getBoolean(Objects.requireNonNull(player).getName() + "_Vanish")) {
                    for (Player AllPlayer : Bukkit.getOnlinePlayers()) {
                        AllPlayer.showPlayer(main.instance,player);
                    }
                    player.removePotionEffect(PotionEffectType.INVISIBILITY);
                    player.sendMessage(ChatColor.GREEN + "你的隐身状态已被关闭");
                    multi.SetBooleanTemp(Objects.requireNonNull(player).getName() + "_Vanish",false);
                    sender.sendMessage(ChatColor.GREEN + "你已将" + flyPlayerName + "的隐身状态关闭");
                }else {
                    for (Player AllPlayer : Bukkit.getOnlinePlayers()) {
                        AllPlayer.hidePlayer(main.instance,player);
                    }
                    PotionEffect pot = new PotionEffect(PotionEffectType.INVISIBILITY, 99999, 255, true);
                    player.addPotionEffect(pot);
                    player.sendMessage(ChatColor.GREEN + "你的隐身状态已被开启");
                    multi.SetBooleanTemp(Objects.requireNonNull(player).getName() + "_Vanish",true);
                    sender.sendMessage(ChatColor.GREEN + "你已将" + flyPlayerName + "的隐身状态开启");
                }
            }else {
                if (args.length == 0) {
                    Player player = (Player) sender;
                    String PlayerName = player.getName();
                    if (main.instance.getConfig().getBoolean(Objects.requireNonNull(player).getName() + "_Vanish")) {
                        for (Player AllPlayer : Bukkit.getOnlinePlayers()) {
                            AllPlayer.showPlayer(main.instance,player);
                        }
                        player.removePotionEffect(PotionEffectType.INVISIBILITY);
                        multi.SetBooleanTemp(Objects.requireNonNull(player).getName() + "_Vanish",false);
                        sender.sendMessage(ChatColor.GREEN + "你已将" + PlayerName + "的隐身状态关闭");
                    }else {
                        for (Player AllPlayer : Bukkit.getOnlinePlayers()) {
                            AllPlayer.hidePlayer(main.instance,player);
                        }
                        PotionEffect pot = new PotionEffect(PotionEffectType.INVISIBILITY, 99999, 255, true);
                        player.addPotionEffect(pot);
                        multi.SetBooleanTemp(Objects.requireNonNull(player).getName() + "_Vanish",true);
                        sender.sendMessage(ChatColor.GREEN + "你已将" + PlayerName + "的隐身状态开启");
                    }
                }else {
                    sender.sendMessage(ChatColor.RED + "用法错误,正确用法:/fly " + ChatColor.GRAY + "[玩家ID]");
                }
            }
        }else {
            if (args.length == 1) {
                String flyPlayerName = args[0];
                if (Bukkit.getPlayer(flyPlayerName) == null) {
                    sender.sendMessage(ChatColor.RED + "错误,这个玩家不存在或不在线!");
                    return false;
                }
                Player player = Bukkit.getPlayer(flyPlayerName);
                if (main.instance.getConfig().getBoolean(Objects.requireNonNull(player).getName() + "_Vanish")) {
                    for (Player AllPlayer : Bukkit.getOnlinePlayers()) {
                        AllPlayer.showPlayer(main.instance,player);
                    }
                    player.removePotionEffect(PotionEffectType.INVISIBILITY);
                    player.sendMessage(ChatColor.GREEN + "你的隐身状态已被关闭");
                    multi.SetBooleanTemp(Objects.requireNonNull(player).getName() + "_Vanish",false);
                    sender.sendMessage(ChatColor.GREEN + "你已将" + flyPlayerName + "的隐身状态关闭");
                }else {
                    for (Player AllPlayer : Bukkit.getOnlinePlayers()) {
                        AllPlayer.hidePlayer(main.instance,player);
                    }
                    PotionEffect pot = new PotionEffect(PotionEffectType.INVISIBILITY, 99999, 255, true);
                    player.addPotionEffect(pot);
                    player.sendMessage(ChatColor.GREEN + "你的隐身状态已被开启");
                    multi.SetBooleanTemp(Objects.requireNonNull(player).getName() + "_Vanish",true);
                    sender.sendMessage(ChatColor.GREEN + "你已将" + flyPlayerName + "的隐身状态开启");
                }
            }else {
                sender.sendMessage(ChatColor.RED + "用法错误,正确用法:/fly " + ChatColor.GRAY + "<玩家ID>");
            }
        }
        return false;
    }
}
