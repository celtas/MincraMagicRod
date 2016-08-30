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

public class DestroyRod_5 extends BukkitRunnable{
Player player;
	public DestroyRod_5(Player _player) {
		player=_player;
	}
	@Override
	public void run() {
		List<Entity> entitylist = player.getNearbyEntities(9, 5, 9);
		int cnt = 0;
		for(Entity entity:entitylist){
			if(entity instanceof Player){
				if(cnt<=4){
					cnt++;
					Player player2=(Player)entity;
					Version.playSound(player2.getLocation(),Sound.BLOCK_ANVIL_USE, 0.3F, 1F);
			    	player2.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING,20*60,4));
			    	Version.playeffect(player2.getLocation(),"VILLAGER_HAPPY");
			    	player.sendMessage(ChatColor.GOLD+player2.getName()+"に破壊のつるはしlv5を使用しました。");
			    	player2.sendMessage(ChatColor.GOLD+player.getName()+"から破壊のつるはしlv5の効果を受けました。");
				}
			}
		}
    	this.cancel();
	}

}
