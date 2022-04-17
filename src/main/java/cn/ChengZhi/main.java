package cn.ChengZhi;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

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
        reloadConfig();

        Bukkit.getPluginManager().registerEvents(this, this);
        new PlaceholderAPI(this).register();

        descriptionFile = getDescription();
        getLogger().info("-----------ChengTools插件-----------");
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
