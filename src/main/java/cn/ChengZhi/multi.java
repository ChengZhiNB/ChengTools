package cn.ChengZhi;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class multi {
    public static YamlFile_Utils Yaml;

    public static void SaveAndReloadConfig() {
        main.instance.saveConfig();
        main.instance.reloadConfig();
    }

    public static List<String> getHomeList(Player player) {

        List<String> HomeList = new ArrayList<>();
        File HomeDataFile = new File("/HomeData", player.getName() + ".yml");
        YamlConfiguration HomeData = YamlConfiguration.loadConfiguration(HomeDataFile);

        if (HomeDataFile.exists() && HomeData.get(player.getName() + ".HomeList") != null)

            if (HomeData.get(player.getName() + ".HomeList") instanceof String) {

                String HomeStringList = HomeData.getString(player.getName() + ".HomeList");

                if (Objects.requireNonNull(HomeStringList).contains(" ")) {

                    String[] List = HomeStringList.split(" ");

                    for (String list : List) {
                        if (list.contains(" "))
                            list.replaceAll(" ", "");
                        HomeList.add(list);
                    }

                    HomeList.removeIf(list -> list.equals(""));
                    HomeData.set(player.getName() + ".HomeList", HomeStringList);
                    try {
                        HomeData.save(HomeDataFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            } else {
                HomeList = HomeData.getStringList(player.getName() + ".HomeList");
                HomeList.removeIf(each -> each.equals(""));
                HomeData.set(player.getName() + ".HomeList", HomeList);
                try {
                    HomeData.save(HomeDataFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return HomeList;
            }
        return HomeList;
    }

    public static String ColorMessage(String String1) {
        return ChatColor.translateAlternateColorCodes('&',String1);
    }

    public static void GetLoggerPlus(String String1) {
        CommandSender Console = Bukkit.getConsoleSender();
        Console.sendMessage(ColorMessage(String1));
    }

    static HashMap<String, Integer> IntTemp = new HashMap<>();

    static HashMap<String, Boolean> BooleanTemp = new HashMap<>();

    static HashMap<String, String> StringTemp = new HashMap<>();

    public static void SetStringTemp(String String1,String String2) {
        StringTemp.put(String1,String2);
    }

    public static String getStringTemp(String String1) {
        return StringTemp.get(String1);
    }

    public static void SetBooleanTemp(String String1,boolean Boolean1) {
        BooleanTemp.put(String1,Boolean1);
    }

    public static boolean getBooleanTemp(String String1) {
        return BooleanTemp.get(String1);
    }

    public static void SetIntTemp(String String1, Integer Int1) {
        IntTemp.put(String1,Int1);
    }

    public static Integer GetIntTemp(String String1) {
        return IntTemp.get(String1);
    }

    public static void KickGui(Player player) {
        Inventory UI = Bukkit.createInventory(player,45, ColorMessage("&a选择你要踢出的玩家"));

        ItemStack Bk = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
        ItemMeta Bk_Meta = Bk.getItemMeta();
        Objects.requireNonNull(Bk_Meta).setDisplayName(ColorMessage("&e这只是一个边框"));
        ArrayList<String> Bk_Lore = new ArrayList<>();
        Bk_Lore.add(null);
        Bk_Lore.add(ColorMessage("&e这只是一个边框"));
        Bk_Meta.setLore(Bk_Lore);
        Bk.setItemMeta(Bk_Meta);

        for (int i1 = 0 ; i1 <9 ; i1++) {
            UI.setItem(i1,Bk);
        }

        for (int i2 = 36 ; i2 <40 ; i2++) {
            UI.setItem(i2,Bk);
        }

        for (int i3 = 41 ; i3 <45 ; i3++) {
            UI.setItem(i3,Bk);
        }

        UI.setItem(9,Bk);
        UI.setItem(17,Bk);

        UI.setItem(18,Bk);
        UI.setItem(26,Bk);

        UI.setItem(27,Bk);
        UI.setItem(35,Bk);

        ItemStack Gb = new ItemStack(Material.REDSTONE_BLOCK);
        ItemMeta Gb_Meta = Gb.getItemMeta();
        Objects.requireNonNull(Gb_Meta).setDisplayName(ColorMessage("&c关闭菜单"));
        ArrayList<String> Gb_Lore = new ArrayList<>();
        Gb_Lore.add(null);
        Gb_Lore.add(ColorMessage("&e点击关闭菜单"));
        Gb_Meta.setLore(Gb_Lore);
        Gb.setItemMeta(Gb_Meta);

        UI.setItem(40,Gb);

        ArrayList<Player> players = new ArrayList<>(player.getServer().getOnlinePlayers());
        for (Player value : players) {
            ItemStack PlayerHead = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta PlayerHead_meta = (SkullMeta) PlayerHead.getItemMeta();
            Objects.requireNonNull(PlayerHead_meta).setOwningPlayer(value);
            Objects.requireNonNull(PlayerHead_meta).setDisplayName(ChatColor.RESET + value.getDisplayName() + ChatColor.RESET);
            ArrayList<String> PlayerHead_Lore = new ArrayList<>();
            PlayerHead_Lore.add(null);
            PlayerHead_Lore.add(ColorMessage( "&e点击踢出玩家"));
            PlayerHead_meta.setLore(PlayerHead_Lore);
            PlayerHead.setItemMeta(PlayerHead_meta);

            UI.addItem(PlayerHead);
        }
        player.openInventory(UI);
    }

//    public static void HomeGUI(Player player,int Page) {
//        if (Page == 0) {
//            Page = 1;
//        }
//
//        File HomeDataFile = new File("/HomeData",player.getName() + ".yml");
//        YamlConfiguration HomeData = YamlConfiguration.loadConfiguration(HomeDataFile);
//
//        if (!HomeDataFile.exists()) {
//            Yaml.saveYamlFile("/HomeData", player.getName() + ".yml", "home.yml",true);
//        }
//
//        List<String> HomeNameList = HomeData.getStringList(player.getName() + ".HomeList");
//        int MaxHome = main.instance.getConfig().getInt("Max_Homes");
//
//        Inventory UI = Bukkit.createInventory(player,36, ColorMessage("&a选择你要传送的家"));
//
//        ItemStack Bk = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
//        ItemMeta Bk_Meta = Bk.getItemMeta();
//        Objects.requireNonNull(Bk_Meta).setDisplayName(ColorMessage("&e这只是一个边框"));
//        Bk.setItemMeta(Bk_Meta);
//
//        for (int i=27 ; i<35 ; i++) {
//            UI.setItem(i,Bk);
//        }
//
//        ItemStack BkPage = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
//        ItemMeta BkPage_Meta = BkPage.getItemMeta();
//        Objects.requireNonNull(BkPage_Meta).setDisplayName(ColorMessage("&a" + Page));
//        ArrayList<String> BkPage_Lore = new ArrayList<>();
//        BkPage_Lore.add(null);
//        BkPage_Lore.add(ColorMessage("&e上方为当前页数"));
//        BkPage_Meta.setLore(BkPage_Lore);
//        BkPage.setItemMeta(BkPage_Meta);
//
//        UI.setItem(30,BkPage);
//
//        ItemStack BACK = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
//        ItemMeta BACK_Meta = BACK.getItemMeta();
//        Objects.requireNonNull(BACK_Meta).setDisplayName(ColorMessage("&a上一页"));
//        BACK.setItemMeta(BACK_Meta);
//
//        UI.setItem(27,BACK);
//
//        ItemStack NEXT = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
//        ItemMeta NEXT_Meta = NEXT.getItemMeta();
//        Objects.requireNonNull(NEXT_Meta).setDisplayName(ColorMessage("&a下一页"));
//        NEXT.setItemMeta(NEXT_Meta);
//
//        UI.setItem(35,NEXT);
//
//
//        if (MaxHome>27) {
//            MaxHome = 27;
//        }
//
//        int DangQianHomeBianHao;
//
//        for (int i=0 ; i<MaxHome ; i++){
//            DangQianHomeBianHao = Page*27 + i;
//            String HomeName = ColorMessage("&a" + HomeNameList.get(DangQianHomeBianHao));
//            String WorldName = HomeData.getString(player.getName() + "." + HomeName + ".World");
//            double X = HomeData.getDouble(player.getName() + "." + HomeName + ".X");
//            double Y = HomeData.getDouble(player.getName() + "." + HomeName + ".Y");
//            double Z = HomeData.getDouble(player.getName() + "." + HomeName + ".Z");
//
//            BigDecimal X1 = new BigDecimal(X);
//            double X2 = X1.setScale(2, RoundingMode.HALF_UP).doubleValue();
//
//            BigDecimal Y1 = new BigDecimal(Y);
//            double Y2 = Y1.setScale(2, RoundingMode.HALF_UP).doubleValue();
//
//            BigDecimal Z1 = new BigDecimal(Z);
//            double Z2 = Z1.setScale(2, RoundingMode.HALF_UP).doubleValue();
//
//            ItemStack Home = new ItemStack(Material.ORANGE_BED);
//            ItemMeta Home_Meta = Home.getItemMeta();
//            Objects.requireNonNull(Home_Meta).setDisplayName(ColorMessage("&a" + HomeName));
//            ArrayList<String> Home_Lore = new ArrayList<>();
//            Home_Lore.add(ColorMessage("&9世界:" + WorldName));
//            Home_Lore.add(ColorMessage("&9X:" + X + "&9Y:" + Y + "&9Z:" + Z));
//            Home_Meta.setLore(Home_Lore);
//            Home.setItemMeta(Home_Meta);
//            UI.addItem(Home);
//        }
//
//        player.openInventory(UI);
//    }
}
