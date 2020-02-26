package me.kkdevs.broadcaster.task;

import cn.nukkit.Server;
import cn.nukkit.scheduler.AsyncTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static me.kkdevs.broadcaster.Loader.*;

public class UpdaterTask extends AsyncTask {

    private String prefix = config.get("prefix", "");
    private static List<String> messagesList;
    private int MESSAGES_SIZE = -1;

    static {
        try {
            messagesList = config.getStringList("messages");
        } catch (Exception exception) {
            messagesList = new ArrayList<>();
        }
    }

    public void onRun() {
        String message;

        if (config.getBoolean("random", false)) {
            message = prefix + messagesList.get(new Random().nextInt(messagesList.size()));
        } else {
            MESSAGES_SIZE = MESSAGES_SIZE + 1;

            message = prefix + messagesList.get(MESSAGES_SIZE);

            if (MESSAGES_SIZE == messagesList.size() - 1) {
                MESSAGES_SIZE = -1;
            }
        }

        if(config.getBoolean("consoleVisible", true)) {
            Server.getInstance().broadcastMessage(message);
        } else {
            Server.getInstance().getOnlinePlayers().values().forEach(player -> player.sendMessage(replace(message, player)));
        }
    }
}