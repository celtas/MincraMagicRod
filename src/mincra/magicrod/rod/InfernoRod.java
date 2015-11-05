package mincra.magicrod.rod;

import mincra.magicrod.listener.EntityDamageListener;

import org.bukkit.Location;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class InfernoRod extends BukkitRunnable{
Player player;
	public InfernoRod(Player _player) {
		player=_player;
	}

	@SuppressWarnings({ "unused" })
	@Override
	public void run() {
		BukkitTask task=new InfernoRod_threed(player).runTaskLater(EntityDamageListener.plugin, 5);
		BukkitTask task2=new InfernoRod_threed(player).runTaskLater(EntityDamageListener.plugin, 10);
		BukkitTask task3=new InfernoRod_threed(player).runTaskLater(EntityDamageListener.plugin, 15);
		BukkitTask task4=new InfernoRod_threed(player).runTaskLater(EntityDamageListener.plugin, 20);
		BukkitTask task5=new InfernoRod_threed(player).runTaskLater(EntityDamageListener.plugin, 25);
		BukkitTask task6=new InfernoRod_threed(player).runTaskLater(EntityDamageListener.plugin, 30);
		BukkitTask task7=new InfernoRod_threed(player).runTaskLater(EntityDamageListener.plugin, 35);
		Location loc=player.getLocation();
		//double Yaw=loc.getYaw();
		//double Pitch=loc.getPitch();
    	//Vector v = new Vector(-Math.sin(Yaw),-Math.sin(Pitch),Math.cos(Yaw));
		Fireball fire=loc.getWorld().spawn(loc.add(0, 7, 0), Fireball.class);
		// 矢の発射者を設定する
		fire.setShooter(player);
    	this.cancel();
	}

}
