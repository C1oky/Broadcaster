package me.c1oky.broadcaster;


import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.utils.Config;

import java.util.ArrayList;
import java.util.List;

public class Constants {

    public static Config config = new Config("plugins/Broadcaster/config.yml", Config.YAML);
    public static final int UPDATE_TIME = config.getInt("updateTime", 90);
    public static final String MESSAGE_PREFIX = config.get("prefix", "");
    public static final String JOIN_MESSAGE = config.getString("join_message", "");
    public static final String QUIT_MESSAGE = config.get("quit_message", "");
    public static final boolean RANDOM = config.getBoolean("random", false);
    public static final boolean CONSOLE_VISIBLE = config.getBoolean("consoleVisible", true);
    public static List<String> MESSAGE_LIST;

    public static void initialization() {
        try {
            Constants.MESSAGE_LIST = config.getStringList("messages");
        } catch (Exception exception) {
            Constants.MESSAGE_LIST = new ArrayList<>();
        }
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
                .replace("%player_ping%", (player.isOnline() ? Integer.toString(player.getPing()) : "null"))
                .replace("%player_health%", Float.toString(player.getHealth()))
                .replace("%player_maxhealth%", Integer.toString(player.getMaxHealth()))
                .replace("%player_x%", Integer.toString(player.getFloorX()))
                .replace("%player_y%", Integer.toString(player.getFloorY()))
                .replace("%player_z%", Integer.toString(player.getFloorZ()))
                .replace("%server_online%", Integer.toString(Server.getInstance().getOnlinePlayers().size()))
                .replace("%server_maxonline%", Integer.toString(Server.getInstance().getMaxPlayers()));
    }
}
