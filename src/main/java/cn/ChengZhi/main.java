package cn.ChengZhi;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.Objects;

public final class main extends JavaPlugin implements Listener {

    public static main instance;
    private static PluginDescriptionFile descriptionFile;
    private static Plugin plugin;

    public static PluginDescriptionFile getDescriptionFile() {
        return descriptionFile;
    }

    public static main getInstance() {
        return instance;
    }

    public static Plugin getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        plugin = this;
        descriptionFile = getDescription();

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null) {
            getLogger().info("错误,您没有安装的PlaceholderAPI,插件已关闭");
            Bukkit.getPluginManager().disablePlugin(this);
        }

        saveDefaultConfig();
        reloadConfig();

        Bukkit.getPluginManager().registerEvents(this, this);
        new PlaceholderAPI(this).register();

        descriptionFile = getDescription();
        getLogger().info("-----------ChengTools插件-----------");
        BukkitTask TpaTime = (new Tpa_Time(this)).runTaskTimer(this, 0L, (20));
        BukkitTask TpaDetect = (new Tpa_Detect(this)).runTaskTimer(this, 0L, (60 * 20));
        if (getConfig().getBoolean("TimeMessageSetting.TimeMessage")) {
            BukkitTask TimeMessage = (new TimeMessage(this)).runTaskTimer(this, 0L, (getConfig().getInt("TimeMessageSetting.TimeMessageTime") * 20L));
            multi.SetTimeMessageIntTemp("TaskId",TimeMessage.getTaskId());
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
        getLogger().info("插件启动成功");
        getLogger().info("-----------ChengTools插件-----------");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        instance = null;
        getLogger().info("-----------ChengTools插件-----------");
        getLogger().info("插件已卸载");
        getLogger().info("-----------ChengTools插件-----------");
    }
}
