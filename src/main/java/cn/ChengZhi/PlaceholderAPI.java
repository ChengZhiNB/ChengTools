package cn.ChengZhi;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

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
            Player player1 = player.getPlayer();
            return Objects.requireNonNull(player1).getDisplayName();
        }
        return null;
    }
}
