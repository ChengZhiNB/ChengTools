package cn.ChengZhi;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static cn.ChengZhi.multi.GetLoggerPlus;

public final class main extends JavaPlugin implements Listener {

    public static main instance;

    public static YamlFile_Utils Yaml;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null) {
            GetLoggerPlus("&c找不到PlaceholderAPI,请安装PlaceholderAPI后才能使用本插件");
            Bukkit.getPluginManager().disablePlugins();
        }

        if (Bukkit.getPluginManager().getPlugin("ChengToolsPlayerTitleExtend") == null) {
            GetLoggerPlus("&c找不到PlayerTitle扩展，相关功能已关闭");
        }else {
            GetLoggerPlus("&a检测到PlayerTitle扩展，相关功能已启用");
        }

        File Folder = new File(String.valueOf(getDataFolder()));
        File Config_File = new File(getDataFolder(), "config.yml");
        Yaml = new YamlFile_Utils();
        if (!Folder.exists() || !Config_File.exists()) {
            Folder.mkdirs();
            Yaml.saveYamlFile(getDataFolder().getPath(), "config.yml", "config.yml",true);
        }

        List<File> Folders = new ArrayList<>();
        Folders.add(new File(getDataFolder().getPath()));
//        Folders.add(new File(getDataFolder() + "/HomeData"));
        Makedirs(Folders);

        GetLoggerPlus("&f-----------&6Cheng&eTools&a插件&f-----------");
        BukkitTask TpaTime = (new Tpa_Time(this)).runTaskTimer(this, 0L, (20));
        multi.SetIntTemp("TpaTimeTaskId",TpaTime.getTaskId());

        BukkitTask TpaDetect = (new Tpa_Detect(this)).runTaskTimer(this, 0L, (60 * 20));
        multi.SetIntTemp("TpaDetectTaskId",TpaDetect.getTaskId());

        if (getConfig().getBoolean("TimeMessageSetting.TimeMessage")) {
            BukkitTask TimeMessage = (new TimeMessage(this)).runTaskTimer(this, 0L, (getConfig().getInt("TimeMessageSetting.TimeMessageTime") * 20L));
            multi.SetIntTemp("TimeMessageTaskId",TimeMessage.getTaskId());
        }

        Bukkit.getPluginManager().registerEvents(new KickGUI_ClickGUI_Event(),this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoin_Event(),this);
        Bukkit.getPluginManager().registerEvents(new PlayerQuit_Event(),this);
        Bukkit.getPluginManager().registerEvents(new PlayerChat_Event(),this);
        Bukkit.getPluginManager().registerEvents(new SignChange_Event(),this);

        Objects.requireNonNull(getCommand("CTReload")).setExecutor(new Reload_Command());
        Objects.requireNonNull(getCommand("Nick")).setExecutor(new Nick_Command());
        Objects.requireNonNull(getCommand("Stop")).setExecutor(new Stop_Command());
        Objects.requireNonNull(getCommand("gamemode")).setExecutor(new gm_Command());
        Objects.requireNonNull(getCommand("day")).setExecutor(new day_Command());
        Objects.requireNonNull(getCommand("sun")).setExecutor(new sun_Command());
        Objects.requireNonNull(getCommand("spawn")).setExecutor(new spawn_Command());
        Objects.requireNonNull(getCommand("setspawn")).setExecutor(new setspawn_Command());
        Objects.requireNonNull(getCommand("tpa")).setExecutor(new Tpa_Command());
        Objects.requireNonNull(getCommand("tpaaccept")).setExecutor(new tpaAccept_Command());
        Objects.requireNonNull(getCommand("tpadefuse")).setExecutor(new tpaDefuse_Command());
        Objects.requireNonNull(getCommand("vanish")).setExecutor(new vanish_Command());
        Objects.requireNonNull(getCommand("fly")).setExecutor(new fly_Command());
        Objects.requireNonNull(getCommand("mute")).setExecutor(new mute_Command());
        Objects.requireNonNull(getCommand("mute")).setExecutor(new unmute_Command());
        Objects.requireNonNull(getCommand("kick")).setExecutor(new SuperKick_Command());
        Objects.requireNonNull(getCommand("feed")).setExecutor(new feed_Command());
        Objects.requireNonNull(getCommand("heal")).setExecutor(new heal_Command());
        Objects.requireNonNull(getCommand("list")).setExecutor(new SuperList_Command());
//        Objects.requireNonNull(getCommand("sethome")).setExecutor(new sethome_Command());
        Objects.requireNonNull(getCommand("testGUI")).setExecutor(new testGUI());

        GetLoggerPlus("&a插件启动成功");
        GetLoggerPlus("&f-----------&6Cheng&eTools&a插件&f-----------");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        instance = null;
        GetLoggerPlus("&f-----------&6Cheng&eTools&a插件&f-----------");
        GetLoggerPlus("&a插件已卸载");
        GetLoggerPlus("&f-----------&6Cheng&eTools&a插件&f-----------");
    }

    private static void Makedirs(List<File> Folders) {
        for (File EachFolder : Folders) {
            if (!EachFolder.exists())
                EachFolder.mkdirs();
        }
    }
}
