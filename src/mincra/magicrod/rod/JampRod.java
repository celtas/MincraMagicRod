package mincra.magicrod.rod;

import mincra.magicrod.version.Version;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class JampRod extends BukkitRunnable{
Player player;
	public JampRod(Player _player) {
		player=_player;
	}

	@Override
	public void run() {
		if(player.getLocation().getBlockY()<170){
			player.setFallDistance(-5F);
			Vector v=new Vector();
			v.setY(0.5);
			if(player.getVelocity().getY()>0D){
    		player.setVelocity(player.getVelocity().add(v));
			}else{
				player.setVelocity(player.getVelocity().setY(0.5F));
			}
			Version.playSound(player.getLocation(),Sound.ENTITY_ENDERMEN_TELEPORT, 0.1F, 1F);
		}else{
			player.sendMessage("高すぎるため使えません");
		}
    	this.cancel();
	}	

}
