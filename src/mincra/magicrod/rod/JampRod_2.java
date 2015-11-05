package mincra.magicrod.rod;

import mincra.magicrod.version.Version;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class JampRod_2 extends BukkitRunnable{
Player player;
	public JampRod_2(Player _player) {
		player=_player;
	}

	@Override
	public void run() {
		if(player.getLocation().getBlockY()<170){
		Version.particle(player.getLocation().add(0, 0.3, 0), "EXPLOSION_HUGE", 0, 0, 0, 0, 1);
		//Version.a(player.getWorld(),"hugeexplosion",player.getLocation().getX(),player.getLocation().getY()+0.3,player.getLocation().getZ(), 1, 0D, 0D, 0D, 0);
		Version.playSound(player.getLocation(),Sound.EXPLODE, 0.5F, 1F);
		    player.setFallDistance(-12F);
			Vector v=new Vector();
			v.setY(1.1);
			if(player.getVelocity().getY()>0D){
    		player.setVelocity(player.getVelocity().add(v));
			}else{
				player.setVelocity(player.getVelocity().setY(1.1F));
			}
			Version.playSound(player.getLocation(),Sound.ENDERDRAGON_WINGS, 0.25F, 1F);
		}else{
			player.sendMessage("高すぎるため使えません");
		}
    	this.cancel();
	}

}
