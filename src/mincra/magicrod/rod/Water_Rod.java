package mincra.magicrod.rod;

import mincra.magicrod.version.Version;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class Water_Rod extends BukkitRunnable{
	Player player;
	int inte;
	public Water_Rod(Player _player) {
		inte=0;
		player=_player;
	}
	@Override
	public void run() {
		Location loc=player.getLocation();
		if(loc.add(0, 1, 0).getBlock().getType().equals(Material.STATIONARY_WATER)){
			float yaw=loc.getYaw();
			float pitch=loc.getPitch();
			float yswim=(float) (-Math.tan(pitch*Math.PI / 180.0)*0.6F);
			if(yswim>0.6F){
				yswim=0.6F;
			}
			if(yswim<-10F){
				yswim=-10F;
			}
			Vector vec=new Vector(-1*Math.sin(yaw*Math.PI / 180.0)*0.6F,yswim,Math.cos(yaw*Math.PI / 180.0)*0.6F);
			player.setVelocity(vec);
			Version.playSound(player.getLocation(), Sound.WATER, 0.1F, 10F);
			Version.particle(loc, "WATER_BUBBLE", 0.43F, 0.43F, 0.43F, 1, 10);
			//Version.a(loc.getWorld(),"bubble", loc.getX(), loc.getY(), loc.getZ(), 10, 0.43D, 0.43D, 0.43D, 0);
		}
		if(inte>900){
			this.cancel();
		}
		inte++;
	}

}
