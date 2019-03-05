package KKDevs;

import java.util.List;

import cn.nukkit.Player;
import cn.nukkit.event.Listener;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;

public class Broadcaster extends PluginBase implements Listener {

 private List < String > texts;
 private int delay = 60;

 @Override
 public void onEnable() {
  this.getServer().getPluginManager().registerEvents(this, this);
  this.saveDefaultConfig();
  String cfg = "span-time";
  try {
   this.delay = this.getConfig().getInt(cfg);
  } catch (Exception e) {
   this.logLoadException(cfg);
  }
  if (this.delay < 1) {
   this.delay = 1;
  }
  cfg = "texts";
  try {
   this.texts = this.getConfig().getStringList(cfg);
  } catch (Exception e) {
   this.logLoadException(cfg);
  }
  if (this.texts.isEmpty()) {
   this.texts.add("§aBroadcaster");
  }
  for (int i = 0; i < this.texts.size(); i++) {
   this.texts.set(i, TextFormat.colorize(this.texts.get(i)));
  }


  this.getServer().getScheduler().scheduleRepeatingTask(new BTask(this), this.delay * 20);
 }

 private void logLoadException(String text) {
  this.getLogger().alert("An error occurred while reading the configuration '" + text + "'. Use the default value.");
 }

 public void broadcast() {
  for (Player p: getServer().getOnlinePlayers().values()) {
   int max = texts.size();
   final int rnd = (int) rnd(max);
   p.sendMessage(texts.get(rnd));
  }
 }

 public static double rnd(final double max) {
  return Math.random() * max;
 }


}