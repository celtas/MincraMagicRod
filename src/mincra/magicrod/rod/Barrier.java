package mincra.magicrod.rod;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import mincra.magicrod.version.Version;

public class Barrier extends BukkitRunnable{
	Player player,player2;
	public Barrier(Player _player, Player _player2) {
		player=_player;
		player2=_player2;
	}
	@Override
	public void run() {
		Version.playSound(player.getLocation(),Sound.ENTITY_ZOMBIE_INFECT, 0.4F, 1F);
		Version.playeffect(player2.getLocation(),"SPELL_INSTANT");
    	player2.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,20*60,1));
    	player.sendMessage(ChatColor.GREEN+player2.getName()+"に結界の杖lv1を使用しました。");
    	player2.sendMessage(ChatColor.GREEN+player.getName()+"から結界の杖lv1の効果を受けました。");
    	this.cancel();
	}

}
