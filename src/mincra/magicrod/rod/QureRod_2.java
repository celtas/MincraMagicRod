package mincra.magicrod.rod;

import java.util.List;

import mincra.magicrod.version.Version;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class QureRod_2 extends BukkitRunnable{
Player player;
	public QureRod_2(Player _player) {
		player=_player;
	}

	@Override
	public void run() {
		List<Entity> entitylist = player.getNearbyEntities(6, 5, 6);
		entitylist.add(player);
		for(Entity entity:entitylist){
			int cnt=0;
			if(entity instanceof Player){
				if(cnt<=4){
					cnt++;
					Player player2=(Player)entity;
					Version.playSound(player2.getLocation(),Sound.VILLAGER_YES, 0.3F, 1F);
			    	player2.addPotionEffect(new PotionEffect(PotionEffectType.HEAL,1,2));
			    	player2.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST,200,1));
			    	Location loc=player2.getLocation();
			    	Version.particle(loc.add(0, 1.7, 0), "HEART", 0.42F,0.42F,0.42F, 1, 100);
			    	//Version.a(loc.getWorld(),"heart", loc.getX(), loc.getY()+1.7, loc.getZ(), 100, 0.42D, 0.42D, 0.42D, 0);
			    	Version.playeffect(player2.getLocation(),"VILLAGER_HAPPY");
			    	if(!(player.equals(player2))){
				    	player.sendMessage(ChatColor.GREEN+player2.getName()+"に癒しの杖lv2を使用しました。");
				    	player2.sendMessage(ChatColor.GREEN+player.getName()+"から癒しの杖lv2を効果を受けました。");
			    	}
				}
			}
		}
    	this.cancel();
	}

}
