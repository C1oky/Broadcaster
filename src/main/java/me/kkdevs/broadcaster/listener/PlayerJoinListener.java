package me.kkdevs.broadcaster.listener;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;

import static me.kkdevs.broadcaster.Loader.*;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String replacedMessage = replace(config.getString("join_message", ""), player);
        Server.getInstance().getOnlinePlayers().values().forEach(target -> sendMessage(target, replacedMessage));
    }
}