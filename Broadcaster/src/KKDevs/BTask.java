package KKDevs;

import cn.nukkit.scheduler.PluginTask;

public class BTask extends PluginTask < Broadcaster > {

 public BTask(Broadcaster owner) {
  super(owner);
 }

 @Override
 public void onRun(int arg0) {
  owner.broadcast();
 }

}