package mincra.magicrod.rod;

import mincra.magicrod.version.Version;

import org.bukkit.DyeColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Spawn_Rod_2 extends BukkitRunnable{
Player player;
Plugin plugin;
	public Spawn_Rod_2(Player _player,Plugin _plugin) {
		player=_player;
		plugin=_plugin;
	}
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		final Wolf wolf,wolf2,wolf3;
		wolf=(Wolf)player.getWorld().spawnCreature(player.getLocation(),EntityType.WOLF);
		Version.playeffect2(wolf.getLocation(), "SPELL_INSTANT");
		wolf.setCustomName("幻獣");
		wolf.setCollarColor(DyeColor.GREEN);
		wolf.setAdult();
		wolf.setOwner(player);
		wolf.setBreed(false);
		wolf2=(Wolf)player.getWorld().spawnCreature(player.getLocation(),EntityType.WOLF);
		Version.playeffect2(wolf2.getLocation(), "SPELL_INSTANT");
		wolf2.setCustomName("幻獣");
		wolf2.setCollarColor(DyeColor.BLUE);
		wolf2.setAdult();
		wolf2.setOwner(player);
		wolf2.setBreed(false);
		wolf3=(Wolf)player.getWorld().spawnCreature(player.getLocation(),EntityType.WOLF);
		Version.playeffect2(wolf3.getLocation(), "SPELL_INSTANT");
		wolf3.setCustomName("幻獣");
		wolf3.setCollarColor(DyeColor.YELLOW);
		wolf3.setAdult();
		wolf3.setOwner(player);
		wolf3.setBreed(false);
		new BukkitRunnable(){
			Wolf wolf_1=wolf;
			Wolf wolf_2=wolf2;
			Wolf wolf_3=wolf3;
			public void run() {
				Version.playeffect2(wolf_1.getLocation(), "SPELL_INSTANT");
				Version.playeffect2(wolf_2.getLocation(), "SPELL_INSTANT");
				Version.playeffect2(wolf_3.getLocation(), "SPELL_INSTANT");
				wolf_1.remove();
				wolf_2.remove();
				wolf_3.remove();
		    	this.cancel();
			}
		}.runTaskLater(plugin,400);
	}

}
