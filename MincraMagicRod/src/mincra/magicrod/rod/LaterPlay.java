package mincra.magicrod.rod;

import mincra.magicrod.version.Version;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class LaterPlay extends BukkitRunnable{
	Player player;
	String string;
	public LaterPlay(Player _player, String _string) {
		player=_player;
		string=_string;
	}
	@Override
	public void run() {
		Version.playeffect(player.getLocation(),string);
    	this.cancel();
	}

}
