package me.c1oky.broadcaster.task;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.CommandSender;
import cn.nukkit.scheduler.AsyncTask;
import me.c1oky.broadcaster.Constants;

import java.util.*;

public class SecondUpdaterTask extends AsyncTask {

    private final Set<CommandSender> recipients = new HashSet<>();
    private final List<String> messages = Constants.MESSAGE_LIST;
    private int messagesSize = -1;

    public SecondUpdaterTask() {
        if (Constants.CONSOLE_VISIBLE) {
            recipients.add(Server.getInstance().getConsoleSender());
        }
    }

    public void onRun() {
        String message;
        if (Constants.RANDOM) {
            message = Constants.MESSAGE_PREFIX + messages.get(new Random().nextInt(messages.size()));
        } else {
            messagesSize += 1;

            message = Constants.MESSAGE_PREFIX + messages.get(messagesSize);

            if (messagesSize == messages.size() - 1) {
                messagesSize = -1;
            }
        }

        new ArrayList<>(Server.getInstance().getOnlinePlayers().values())
                .stream().filter(player -> !recipients.contains(player))
                .forEach(recipients::add);
        Iterator<CommandSender> iterator = recipients.iterator();
        while (iterator.hasNext()) {
            CommandSender sender = iterator.next();
            if (sender instanceof Player) {
                if (!((Player) sender).isOnline()) {
                    iterator.remove();
                    return;
                }
                sender.sendMessage(Constants.replace(message, (Player) sender));
                return;
            }
            sender.sendMessage(message);
        }
    }
}