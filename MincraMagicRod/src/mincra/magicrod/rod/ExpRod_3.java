package mincra.magicrod.rod;

import mincra.magicrod.version.Version;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ExpRod_3 extends BukkitRunnable{
Player player;
	public ExpRod_3(Player _player) {
		player=_player;
	}

	@Override
	public void run() {
    	if(player.getLevel()<5){
    		player.setLevel(5);
    		player.setExp(0);
    		Version.playSound(player.getLocation(),Sound.LEVEL_UP, 1F, 1F);
    	}
    	this.cancel();
	}

}
