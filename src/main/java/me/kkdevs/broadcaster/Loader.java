package me.kkdevs.broadcaster;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import me.kkdevs.broadcaster.listener.PlayerJoinListener;
import me.kkdevs.broadcaster.listener.PlayerQuitListener;
import me.kkdevs.broadcaster.task.UpdaterTask;

import java.util.Arrays;

public class Loader extends PluginBase {

    public static Config config;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        config = new Config("plugins/Broadcaster/config.yml", Config.YAML);

        this.registerListeners();
        Server.getInstance().getScheduler().scheduleRepeatingTask(this, new UpdaterTask(), 20 * config.getInt("updateTime", 90));
    }

    private void registerListeners() {
        Arrays.asList(
                new PlayerJoinListener(),
                new PlayerQuitListener()
        ).forEach(listener -> Server.getInstance().getPluginManager().registerEvents(listener, this));
    }

    public static void sendMessage(Player player, String message) {
        switch (config.getString("type")) {
            case "popup":
                player.sendPopup(message);
                break;
            case "tip":
                player.sendTip(message);
                break;
            case "title":
                player.sendTitle(message);
                break;
            case "message":
            default:
                player.sendMessage(message);
                break;
        }
    }

    public static String replace(String text, Player player) {
        return text
                .replace("%player_name%", player.getName())
                .replace("%player_displayname%", player.getDisplayName())
                .replace("%player_ping%", Integer.toString(player.getPing()))
                .replace("%player_health%", Float.toString(player.getHealth()))
                .replace("%player_maxhealth%", Integer.toString(player.getMaxHealth()))
                .replace("%player_x%", Integer.toString(player.getFloorX()))
                .replace("%player_y%", Integer.toString(player.getFloorY()))
                .replace("%player_z%", Integer.toString(player.getFloorZ()))
                .replace("%server_online%", Integer.toString(Server.getInstance().getOnlinePlayers().size()))
                .replace("%server_maxonline%", Integer.toString(Server.getInstance().getMaxPlayers()));
    }
}