package mincra.magicrod.rod;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class InfernoRod_threed_2 extends BukkitRunnable{
Player player;
	public InfernoRod_threed_2(Player _player) {
		player=_player;
	}

	@SuppressWarnings({ "unused" })
	@Override
	public void run() {
		Location loc=player.getLocation();
		//double Yaw=loc.getYaw();
		//double Pitch=loc.getPitch();
    	//Vector v = new Vector(-Math.sin(Yaw),-Math.sin(Pitch),Math.cos(Yaw));
		Random r =new Random();
		Fireball fire=loc.getWorld().spawn(loc.add(-3, 7+Math.sin(r.nextInt(360))*4, -3), Fireball.class);
		Fireball fire2=loc.getWorld().spawn(loc.add(3, 7+Math.sin(r.nextInt(360))*4, 3), Fireball.class);
		// 矢の発射者を設定する
		fire.setShooter(player);
    	this.cancel();
	}

}
