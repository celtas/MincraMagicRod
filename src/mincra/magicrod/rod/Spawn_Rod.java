package mincra.magicrod.rod;

import mincra.magicrod.version.Version;
import org.bukkit.DyeColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Spawn_Rod extends BukkitRunnable{
Player player;
Plugin plugin;
	public Spawn_Rod(Player _player,Plugin _plugin) {
		player=_player;
		plugin=_plugin;
	}
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		final Wolf wolf;
		wolf=(Wolf)player.getWorld().spawnEntity(player.getLocation(),EntityType.WOLF);
		Version.playeffect2(wolf.getLocation(), "SPELL_INSTANT");
		wolf.setCustomName("幻獣");
		wolf.setCollarColor(DyeColor.GREEN);
		wolf.setAdult();
		wolf.setOwner(player);
		wolf.setBreed(false);
		new BukkitRunnable(){
			Wolf wolf2=wolf;
			public void run() {
				Version.playeffect2(wolf2.getLocation(), "SPELL_INSTANT");
				wolf2.remove();
		    	this.cancel();
			}
		}.runTaskLater(plugin,200);
	}

}
