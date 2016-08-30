package mincra.magicrod.rod;

import mincra.magicrod.version.Version;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class DestroyRod_2 extends BukkitRunnable{
Player player,player2;
	public DestroyRod_2(Player _player,Player _player2) {
		player=_player;
		player2=_player2;
	}

	@Override
	public void run() {
    	Version.playSound(player2.getLocation(),Sound.BLOCK_ANVIL_USE, 0.3F, 1F);
    	player2.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING,20*60,2));
    	Version.playeffect(player2.getLocation(),"VILLAGER_HAPPY");
    	player.sendMessage(ChatColor.GREEN+player2.getName()+"に破壊の杖lv2を使用しました。");
    	player2.sendMessage(ChatColor.GREEN+player.getName()+"から破壊の杖lv2の効果を受けました。");
    	this.cancel();
	}

}
