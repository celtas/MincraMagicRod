package mincra.magicrod.rod;

import mincra.magicrod.version.Version;

import org.bukkit.DyeColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Spawn_Rod_3 extends BukkitRunnable{
Player player;
Plugin plugin;
	public Spawn_Rod_3(Player _player,Plugin _plugin) {
		player=_player;
		plugin=_plugin;
	}
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		final Wolf wolf,wolf2,wolf3,wolf4,wolf5;
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
		wolf4=(Wolf)player.getWorld().spawnCreature(player.getLocation(),EntityType.WOLF);
		Version.playeffect2(wolf4.getLocation(), "SPELL_INSTANT");
		wolf4.setCustomName("幻獣");
		wolf4.setCollarColor(DyeColor.ORANGE);
		wolf4.setAdult();
		wolf4.setOwner(player);
		wolf4.setBreed(false);
		wolf5=(Wolf)player.getWorld().spawnCreature(player.getLocation(),EntityType.WOLF);
		Version.playeffect2(wolf5.getLocation(), "SPELL_INSTANT");
		wolf5.setCustomName("幻獣");
		wolf5.setCollarColor(DyeColor.RED);
		wolf5.setAdult();
		wolf5.setOwner(player);
		wolf5.setBreed(false);
		new BukkitRunnable(){
			Wolf wolf_1=wolf;
			Wolf wolf_2=wolf2;
			Wolf wolf_3=wolf3;
			Wolf wolf_4=wolf4;
			Wolf wolf_5=wolf5;
			public void run() {
				Version.playeffect2(wolf_1.getLocation(), "SPELL_INSTANT");
				Version.playeffect2(wolf_2.getLocation(), "SPELL_INSTANT");
				Version.playeffect2(wolf_3.getLocation(), "SPELL_INSTANT");
				Version.playeffect2(wolf_4.getLocation(), "SPELL_INSTANT");
				Version.playeffect2(wolf_5.getLocation(), "SPELL_INSTANT");
				wolf_1.remove();
				wolf_2.remove();
				wolf_3.remove();
				wolf_4.remove();
				wolf_5.remove();
		    	this.cancel();
			}
		}.runTaskLater(plugin,600);
	}

}
