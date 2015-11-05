package mincra.magicrod.rod;

import mincra.magicrod.version.Version;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ExpRod_2 extends BukkitRunnable{
Player player;
	public ExpRod_2(Player _player) {
		player=_player;
	}

	@Override
	public void run() {
    	if(player.getLevel()<10){
    		player.setLevel(10);
    		player.setExp(0);
    		Version.playSound(player.getLocation(),Sound.LEVEL_UP, 1F, 1F);
    	}
    	this.cancel();
	}

}
