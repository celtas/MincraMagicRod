package mincra.magicrod.rod;

import mincra.magicrod.listener.EntityDamageListener;
import mincra.magicrod.version.Version;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.List;

public class InfernoRod_3 extends BukkitRunnable{
Player player;
	public InfernoRod_3(Player _player) {
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
		BukkitTask task16=new InfernoRod_threed_2(player).runTaskLater(EntityDamageListener.plugin, 46);
		BukkitTask task17=new InfernoRod_threed_2(player).runTaskLater(EntityDamageListener.plugin, 49);
		BukkitTask task18=new InfernoRod_threed_2(player).runTaskLater(EntityDamageListener.plugin, 52);
		BukkitTask task19=new InfernoRod_threed_2(player).runTaskLater(EntityDamageListener.plugin, 55);
		BukkitTask task20=new InfernoRod_threed_2(player).runTaskLater(EntityDamageListener.plugin, 58);
		BukkitTask task21=new InfernoRod_threed_2(player).runTaskLater(EntityDamageListener.plugin, 61);
		BukkitTask task22=new InfernoRod_threed_2(player).runTaskLater(EntityDamageListener.plugin, 63);
		BukkitTask task23=new InfernoRod_threed_2(player).runTaskLater(EntityDamageListener.plugin, 66);
		BukkitTask task24=new InfernoRod_threed_2(player).runTaskLater(EntityDamageListener.plugin, 69);
		BukkitTask task25=new InfernoRod_threed_2(player).runTaskLater(EntityDamageListener.plugin, 72);
		BukkitTask task26=new InfernoRod_threed_2(player).runTaskLater(EntityDamageListener.plugin, 75);
		BukkitTask task27=new InfernoRod_threed_2(player).runTaskLater(EntityDamageListener.plugin, 78);
		BukkitTask task28=new InfernoRod_threed_2(player).runTaskLater(EntityDamageListener.plugin, 81);
		BukkitTask task29=new InfernoRod_threed_2(player).runTaskLater(EntityDamageListener.plugin, 84);
		BukkitTask task30=new InfernoRod_threed_2(player).runTaskLater(EntityDamageListener.plugin, 87);
		Location loc=player.getLocation();
		List<Entity> en=player.getNearbyEntities(25, 5, 25);
		for(Entity entity:en){
			if(entity instanceof Player||entity instanceof Horse||entity instanceof Ocelot||entity instanceof Wolf||entity instanceof Sheep||entity instanceof Chicken||entity instanceof Cow||entity instanceof ItemFrame||entity instanceof Villager){
			}else{
				entity.setFireTicks(1250);
				Version.particle(entity.getLocation().add(0, 0.3, 0), "EXPLOSION_HUGE", 0.35F, 0.35F, 0.35F, 1, 35);
				//Version.a(entity.getWorld(),"hugeexplosion",entity.getLocation().getX(),entity.getLocation().getY()+0.3,entity.getLocation().getZ(), 25, 0.35D, 0.35D, 0.35D, 0);
			}
		}
		//double Yaw=loc.getYaw();
		//double Pitch=loc.getPitch();
    	//Vector v = new Vector(-Math.sin(Yaw),-Math.sin(Pitch),Math.cos(Yaw));
		Fireball fire=loc.getWorld().spawn(loc.add(0, 7, 0), Fireball.class);
		// 矢の発射者を設定する
		fire.setShooter(player);
    	this.cancel();
	}

}
