package mincra.magicrod.rod;

import mincra.magicrod.version.Version;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class Barrier_3 extends BukkitRunnable{
Player player;
	public Barrier_3(Player _player) {
		player=_player;
	}
	@Override
	public void run() {
		List<Entity> entitylist = player.getNearbyEntities(9, 5, 9);
		for(Entity entity:entitylist){
			if(entity instanceof Player){
				Player player2=(Player)entity;
				Version.playSound(player2.getLocation(),Sound.ENTITY_ZOMBIE_INFECT, 0.3F, 1F);
		    	player2.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,20*180,2));
		    	Version.playeffect(player2.getLocation(),"INSTANT_SPELL");
		    	player.sendMessage(ChatColor.GOLD+player2.getName()+"に結界の書lv3を使用しました。");
		    	player2.sendMessage(ChatColor.GOLD+player.getName()+"から結界の書lv3の効果を受けました。");
			}
		}
    	this.cancel();
	}

}
