package mincra.magicrod.rod;

import mincra.magicrod.version.Version;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class MoveRod_3 extends BukkitRunnable{
Player player;
	public MoveRod_3(Player _player) {
		player=_player;
	}

	@Override
	public void run() {
		Location loc=player.getLocation();
		if(loc.getBlockY()<170){
			float yaw=loc.getYaw();
			Vector vec=new Vector(-1*Math.sin(yaw*Math.PI / 180.0)*3,-0.5F,Math.cos(yaw*Math.PI / 180.0)*3);
			player.setVelocity(vec);
			Version.playeffect(player.getLocation().add(-1*Math.sin(yaw*Math.PI / 180.0)*8,0,Math.cos(yaw*Math.PI / 180.0)*8),"FIREWORKS_SPARK");
		}else{
			player.sendMessage("高すぎるため使えません");
		}
    	this.cancel();
	}

}
