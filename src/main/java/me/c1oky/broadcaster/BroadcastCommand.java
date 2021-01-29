package me.c1oky.broadcaster;

import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;

public class BroadcastCommand extends Command {
    public BroadcastCommand() {
        super("broadcast", "Broadcasting command", "§cUse: /broadcast <message>");
        this.setPermission("broadcast");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (args.length > 0) {
            StringBuilder message = new StringBuilder();
            for (int i = 1; i < args.length; i++) {
                message.append(args[i]).append(" ");
            }

            Server.getInstance().broadcastMessage(Constants.MESSAGE_PREFIX + message);
            return true;
        }
        sender.sendMessage(this.usageMessage);
        return false;
    }
}