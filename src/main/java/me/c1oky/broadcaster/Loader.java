package me.c1oky.broadcaster;

import cn.nukkit.Server;
import cn.nukkit.plugin.PluginBase;
import me.c1oky.broadcaster.task.SecondUpdaterTask;

public class Loader extends PluginBase {
    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        Constants.initialization();
        Server.getInstance().getPluginManager().registerEvents(new EventListener(), this);
        Server.getInstance().getScheduler().scheduleRepeatingTask(this, new SecondUpdaterTask(), 20 * Constants.UPDATE_TIME);
        Server.getInstance().getCommandMap().register("broadcaster", new BroadcastCommand());
    }
}