package me.kkdevs.broadcaster;

import cn.nukkit.Server;
import cn.nukkit.plugin.PluginBase;
import me.kkdevs.broadcaster.task.UpdaterTask;

public class Loader extends PluginBase {

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        Server.getInstance().getScheduler().scheduleRepeatingTask(this, new UpdaterTask(), 20 * UpdaterTask.config.getInt("updateTime", 90));
    }
}