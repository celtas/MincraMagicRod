package mincra.magicrod.rod;

import java.util.List;
import mincra.magicrod.version.Version;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class DestroyRod_4 extends BukkitRunnable{
Player player;
	public DestroyRod_4(Player _player) {
		player=_player;
	}
	@Override
	public void run() {
		List<Entity> entitylist = player.getNearbyEntities(9, 5, 9);
		for(Entity entity:entitylist){
			if(entity instanceof Player){
				Player player2=(Player)entity;
				Version.playSound(player2.getLocation(),Sound.ANVIL_USE, 0.3F, 1F);
		    	player2.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING,20*120,4));
		    	Version.playeffect(player2.getLocation(),"VILLAGER_HAPPY");
		    	player.sendMessage(ChatColor.GOLD+player2.getName()+"に破壊の書lv4を使用しました。");
		    	player2.sendMessage(ChatColor.GOLD+player.getName()+"から破壊の書lv4の効果を受けました。");
			}
		}
    	this.cancel();
	}

}
