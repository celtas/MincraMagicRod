package mincra.magicrod.rod;

import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import mincra.magicrod.version.Version;

public class MineCart_1 extends BukkitRunnable{
Player player;
Plugin plugin;
	public MineCart_1(Player _player,Plugin _plugin) {
		player=_player;
		plugin=_plugin;
	}

	@Override
	public void run() {
		List<Entity> li = player.getNearbyEntities(2, 2, 2);
		for(Entity en:li){
			if(en instanceof Minecart){
				if(en.getPassengers().equals(player)){
					final Minecart minecart = (Minecart) en;
					final double maxspeed =minecart.getMaxSpeed();
					final Vector maxvector=minecart.getDerailedVelocityMod();
					double exmaxspeed =1.6;//0.4
					Vector exmaxvector=new Vector(10,10,10);//0.5,0.5,0.5
					minecart.setMaxSpeed(exmaxspeed);
					minecart.setDerailedVelocityMod(exmaxvector);
					new BukkitRunnable(){
						double maxspeed2=maxspeed;
						Vector maxvector2=maxvector;
						Minecart minecart2=minecart;
						public void run() {
							if(minecart2.isDead()==false){
								minecart2.setMaxSpeed(maxspeed2);
								minecart2.setDerailedVelocityMod(maxvector2);
								Version.playeffect(player.getLocation().add(player.getVelocity().multiply(100)),"SPELL_INSTANT");
							}
					    	this.cancel();
						}
					}.runTaskLater(plugin, 60);
					break;
				}
			}
		}
    	this.cancel();
	}

}
