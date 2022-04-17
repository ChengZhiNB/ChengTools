package cn.ChengZhi;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

public class PlaceholderAPI extends PlaceholderExpansion {

    public PlaceholderAPI(main main) {
    }

    @Override
    public @NotNull String getAuthor() {
        return "ChengZhi";
    }

    @Override
    public @NotNull String getIdentifier() {
        return "ChengTools";
    }

    @Override
    public @NotNull String getVersion() {
        return main.getDescriptionFile().getVersion();
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        if (params.equalsIgnoreCase("NickName")) {
            if (main.instance.getConfig().getString(player.getName() + "_NickName") == null) {
                return player.getName();
            } else {
                return main.instance.getConfig().getString(player.getName() + "_NickName");
            }
        }
        return null;
    }
}
