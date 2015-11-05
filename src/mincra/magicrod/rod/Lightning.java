package mincra.magicrod.rod;

import mincra.magicrod.version.Version;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Lightning  extends BukkitRunnable{
	Player player;
	Plugin plugin;
	public Lightning(Player _player,Plugin _plugin) {
		player=_player;
		plugin=_plugin;
	}
	@Override
	public void run() {
		final Location loc=player.getLocation();
		float yaw=loc.getYaw();
		final Location loc2=new Location(player.getWorld(),-1*Math.sin(yaw*Math.PI / 180.0)*2,-0.5F,Math.cos(yaw*Math.PI / 180.0)*2);
		final Location loc3=new Location(player.getWorld(),-1*Math.sin(yaw*Math.PI / 180.0)*4,-0.5F,Math.cos(yaw*Math.PI / 180.0)*4);
		final Location loc4=new Location(player.getWorld(),-1*Math.sin(yaw*Math.PI / 180.0)*6,-0.5F,Math.cos(yaw*Math.PI / 180.0)*6);
		new BukkitRunnable(){
			public void run() {
				Version.strikeLightning(loc.add(loc2));
			}
		}.runTaskLater(plugin, 5);
		new BukkitRunnable(){
			public void run() {
				Version.strikeLightning(loc.add(loc3));
			}
		}.runTaskLater(plugin, 10);
		new BukkitRunnable(){
			public void run() {
				Version.strikeLightning(loc.add(loc4));
			}
		}.runTaskLater(plugin, 15);
    	this.cancel();
	}

}
