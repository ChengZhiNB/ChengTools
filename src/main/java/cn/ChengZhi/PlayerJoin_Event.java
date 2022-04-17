package cn.ChengZhi;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Objects;

public class PlayerJoin_Event implements Listener {
    @EventHandler
    public void PlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String TheIs = main.instance.getConfig().getString("TheIs");
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
                main.instance.getLogger().info("检测到管理员进入服务器提示为null已替换成默认提示");
                AdminJoinMessage = "&7[&c&l管理员&7]&e%ChengTools_NickName%&a进入了服务器";
            }
            event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(player, AdminJoinMessage)));
        } else {
            if (PlayerJoinMessage == null) {
                main.instance.getLogger().info("检测到管理员进入服务器提示为null已替换成默认提示");
                PlayerJoinMessage = "&7[&f&l玩家&7]&e%ChengTools_NickName%&a进入了服务器";
            }
            event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(player, PlayerJoinMessage)));
        }

        boolean player_AlreadyInitializationInventory = main.instance.getConfig().getBoolean(player.getName() + "_AlreadyInitializationInventory");
        if (!player_AlreadyInitializationInventory) {
            if (Objects.equals(TheIs, "登录服")) {
                ItemStack JoinServer = new ItemStack(Material.CLOCK);
                ItemMeta JoinServer_Meta = JoinServer.getItemMeta();
                Objects.requireNonNull(JoinServer_Meta).setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&l跨服&a&l菜单"));
                JoinServer.setItemMeta(JoinServer_Meta);
                player.getInventory().addItem(JoinServer);
                main.instance.getConfig().set(player.getName() + "_AlreadyInitializationInventory", true);
                main.instance.saveConfig();
                main.instance.reloadConfig();
            } else {
                ItemStack HELMET = new ItemStack(Material.LEATHER_HELMET);
                ItemMeta HELMET_Meta = HELMET.getItemMeta();
                Objects.requireNonNull(HELMET_Meta).setUnbreakable(true);
                HELMET.setItemMeta(HELMET_Meta);

                ItemStack CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE);
                ItemMeta CHESTPLATE_Meta = CHESTPLATE.getItemMeta();
                Objects.requireNonNull(CHESTPLATE_Meta).setUnbreakable(true);
                CHESTPLATE.setItemMeta(CHESTPLATE_Meta);

                ItemStack LEGGINGS = new ItemStack(Material.LEATHER_LEGGINGS);
                ItemMeta LEGGINGS_Meta = LEGGINGS.getItemMeta();
                Objects.requireNonNull(LEGGINGS_Meta).setUnbreakable(true);
                LEGGINGS.setItemMeta(LEGGINGS_Meta);

                ItemStack BOOTS = new ItemStack(Material.LEATHER_BOOTS);
                ItemMeta BOOTS_Meta = BOOTS.getItemMeta();
                Objects.requireNonNull(BOOTS_Meta).setUnbreakable(true);
                BOOTS.setItemMeta(BOOTS_Meta);

                ItemStack PICKAXE = new ItemStack(Material.DIAMOND_PICKAXE);
                ItemMeta PICKAXE_Meta = PICKAXE.getItemMeta();
                Objects.requireNonNull(PICKAXE_Meta).addEnchant(Enchantment.DIG_SPEED, 3, true);
                Objects.requireNonNull(PICKAXE_Meta).setUnbreakable(true);
                PICKAXE.setItemMeta(PICKAXE_Meta);

                ItemStack AXE = new ItemStack(Material.DIAMOND_AXE);
                ItemMeta AXE_Meta = AXE.getItemMeta();
                Objects.requireNonNull(AXE_Meta).addEnchant(Enchantment.DIG_SPEED, 3, true);
                Objects.requireNonNull(AXE_Meta).setUnbreakable(true);
                AXE.setItemMeta(AXE_Meta);

                player.getInventory().addItem(HELMET);
                player.getInventory().addItem(CHESTPLATE);
                player.getInventory().addItem(LEGGINGS);
                player.getInventory().addItem(BOOTS);
                player.getInventory().addItem(PICKAXE);
                player.getInventory().addItem(AXE);

                main.instance.getConfig().set(player.getName() + "_AlreadyInitializationInventory", true);
                main.instance.saveConfig();
                main.instance.reloadConfig();
            }
        }

        List<String> PlayerJoinSendMessages = main.instance.getConfig().getStringList("PlayerJoinSendMessage");
        for (String PlayerJoinSendMessage : PlayerJoinSendMessages) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(player, PlayerJoinSendMessage)));
        }
    }
}
