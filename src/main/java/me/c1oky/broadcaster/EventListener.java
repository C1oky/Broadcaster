package me.c1oky.broadcaster;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;

public class EventListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        for (Player target : new ArrayList<>(Server.getInstance().getOnlinePlayers().values())) {
            Constants.sendMessage(target, Constants.replace(Constants.JOIN_MESSAGE, player));
        }
        event.setJoinMessage("");
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        for (Player target : new ArrayList<>(Server.getInstance().getOnlinePlayers().values())) {
            Constants.sendMessage(target, Constants.replace(Constants.QUIT_MESSAGE, player));
        }
        event.setQuitMessage("");
    }
}