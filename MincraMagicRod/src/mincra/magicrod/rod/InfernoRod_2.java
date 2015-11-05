package mincra.magicrod.rod;

import mincra.magicrod.listener.EntityDamageListener;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class InfernoRod_2 extends BukkitRunnable{
Player player;
	public InfernoRod_2(Player _player) {
		player=_player;
	}
	@SuppressWarnings("unused")
	@Override
	public void run() {
		BukkitTask task=new InfernoRod_threed_2(player).runTaskLater(EntityDamageListener.plugin, 1);
		BukkitTask task2=new InfernoRod_threed_2(player).runTaskLater(EntityDamageListener.plugin, 4);
		BukkitTask task3=new InfernoRod_threed_2(player).runTaskLater(EntityDamageListener.plugin, 7);
		BukkitTask task4=new InfernoRod_threed_2(player).runTaskLater(EntityDamageListener.plugin, 10);
		BukkitTask task5=new InfernoRod_threed_2(player).runTaskLater(EntityDamageListener.plugin, 13);
		BukkitTask task6=new InfernoRod_threed_2(player).runTaskLater(EntityDamageListener.plugin, 16);
		BukkitTask task7=new InfernoRod_threed_2(player).runTaskLater(EntityDamageListener.plugin, 19);
		BukkitTask task8=new InfernoRod_threed_2(player).runTaskLater(EntityDamageListener.plugin, 22);
		BukkitTask task9=new InfernoRod_threed_2(player).runTaskLater(EntityDamageListener.plugin, 25);
		BukkitTask task10=new InfernoRod_threed_2(player).runTaskLater(EntityDamageListener.plugin, 28);
		BukkitTask task11=new InfernoRod_threed_2(player).runTaskLater(EntityDamageListener.plugin, 31);
		BukkitTask task12=new InfernoRod_threed_2(player).runTaskLater(EntityDamageListener.plugin, 34);
		BukkitTask task13=new InfernoRod_threed_2(player).runTaskLater(EntityDamageListener.plugin, 37);
		BukkitTask task14=new InfernoRod_threed_2(player).runTaskLater(EntityDamageListener.plugin, 40);
		BukkitTask task15=new InfernoRod_threed_2(player).runTaskLater(EntityDamageListener.plugin, 43);
		Location loc=player.getLocation();
		//double Yaw=loc.getYaw();
		//double Pitch=loc.getPitch();
    	//Vector v = new Vector(-Math.sin(Yaw),-Math.sin(Pitch),Math.cos(Yaw));
    	this.cancel();
	}

}
