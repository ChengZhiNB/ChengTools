//package cn.ChengZhi;
//
//import org.bukkit.command.Command;
//import org.bukkit.command.CommandExecutor;
//import org.bukkit.command.CommandSender;
//import org.bukkit.configuration.file.YamlConfiguration;
//import org.bukkit.entity.Player;
//import org.jetbrains.annotations.NotNull;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.List;
//
//import static cn.ChengZhi.multi.ColorMessage;
//import static cn.ChengZhi.multi.getHomeList;
//
//public class sethome_Command implements CommandExecutor {
//    public static YamlFile_Utils Yaml;
//
//    @Override
//    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
//        if (sender instanceof Player) {
//            Player player = (Player) sender;
//
//            File HomeDataFile = new File("/HomeData",player.getName() + ".yml");
//            YamlConfiguration HomeData = YamlConfiguration.loadConfiguration(HomeDataFile);
//
//            if (!HomeDataFile.exists()) {
//                Yaml.saveYamlFile("/HomeData", player.getName() + ".yml", "home.yml",true);
//            }
//
//            if (getHomeList(player).size() > main.instance.getConfig().getInt("Max_Homes")) {
//                player.sendMessage(ColorMessage("&c最多只能设置" + main.instance.getConfig().getInt("Max_Homes") + "而你已经设置满了,请删除掉其他的家再创建"));
//            }
//            if (args.length == 1) {
//                String HomeName = args[0];
//                if (getHomeList(player).contains(HomeName)) {
//                    player.sendMessage(ColorMessage("&c这个家的名字被占用了"));
//                    return false;
//                }
//
//                List<String> HomeList = HomeData.getStringList(player.getName() + ".HomeList");
//                String WorldName = player.getWorld().getName();
//                double X = player.getLocation().getX();
//                double Y = player.getLocation().getY();
//                double Z = player.getLocation().getZ();
//
//                HomeList.add(WorldName);
//                HomeData.set(player.getName() + ".HomeList",HomeList);
//                HomeData.set(player.getName() + "." + HomeName + ".WorldName",WorldName);
//                HomeData.set(player.getName() + "." + HomeName + ".X",X);
//                HomeData.set(player.getName() + "." + HomeName + ".Y",Y);
//                HomeData.set(player.getName() + "." + HomeName + ".Z",Z);
//
//                try {
//                    HomeData.save(HomeDataFile);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                player.sendMessage(ColorMessage("&a创建成功!"));
//            }else {
//                player.sendMessage(ColorMessage("&c用法错误，用法:/sethome <名字>"));
//            }
//        }else {
//            sender.sendMessage(ColorMessage("&C只有玩家才可以执行这个指令!"));
//        }
//        return false;
//    }
//
//}
