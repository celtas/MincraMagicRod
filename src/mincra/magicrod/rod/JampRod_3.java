package mincra.magicrod.rod;

import mincra.magicrod.version.Version;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class JampRod_3 extends BukkitRunnable{
Player player;
	public JampRod_3(Player _player) {
		player=_player;
	}

	@Override
	public void run() {
		if(player.getLocation().getBlockY()<170){
			Version.particle(player.getLocation().add(0, 0.3, 0), "CLOUD", 0.23F, 0, 0.23F, 1, 35);
			//Version.a(player.getWorld(),"cloud",player.getLocation().getX(),player.getLocation().getY()+0.3,player.getLocation().getZ(), 35, 0.23D, 0D, 0.23D, 0);
			Version.playSound(player.getLocation(),Sound.ENDERDRAGON_WINGS, 0.5F, 1F);
		    player.setFallDistance(-16F);
			Vector v=new Vector();
			v.setY(1.3F);
			if(player.getVelocity().getY()>0D){
    		player.setVelocity(player.getVelocity().add(v));
			}else{
				player.setVelocity(player.getVelocity().setY(0.3F));
			}
			Version.playSound(player.getLocation(),Sound.ENDERDRAGON_WINGS, 0.75F, 1F);
		}else{
			player.sendMessage("高すぎるため使えません");
		}
    	this.cancel();
	}
}
