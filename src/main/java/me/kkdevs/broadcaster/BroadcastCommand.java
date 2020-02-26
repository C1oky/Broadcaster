package me.kkdevs.broadcaster;

import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;

import static me.kkdevs.broadcaster.Loader.*;

public class BroadcastCommand extends Command {
    public BroadcastCommand() {
        super("broadcast", "Broadcasting command", "/broadcast <you message>");
        this.setPermission("broadcast");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (args.length > 0) {
            String message = "";
            for (int i = 1; i < args.length; i++) {
                message += args[i] + " ";
            }

            Server.getInstance().broadcastMessage(config.getString("prefix", "") + message);
            return true;
        } else {
            sender.sendMessage("§l§c>§f Usе: " + this.usageMessage);
        }
        return false;
    }
}