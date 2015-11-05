package mincra.magicrod.rod;

import mincra.magicrod.version.Version;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Magicsquare2 extends BukkitRunnable{
	Player player;
	String s;
	float kakeru,kakeru2,kakeru3,p,y;
	Location loc;
	public Magicsquare2(Player _player, String _s, float _kakeru, float _kakeru2, float _kakeru3, float _p, Location _loc) {
		player=_player;
		s=_s;
		kakeru=_kakeru;
		kakeru2=_kakeru2;
		kakeru3=_kakeru3;
		p=_p;
		loc=_loc;
	}
	public void run() {
		Version.playeffect(loc, s);
    	this.cancel();
	}
}
