package mincra.magicrod.rod;

import mincra.magicrod.version.Version;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class AiceRod extends BukkitRunnable{
	Player player;
	Plugin plugin;
	public AiceRod(Player _player,Plugin _plugin) {
		player=_player;
		plugin=_plugin;
	}
	@Override
	public void run() {
		Location loc=player.getLocation().add(0,1.3D,0);
		float yaw=loc.getYaw();
		float pitch=loc.getPitch();
		Vector vec=new Vector(-Math.sin(yaw*Math.PI / 180.0)*2F,-Math.tan(pitch*Math.PI / 180.0)*2F,Math.cos(yaw*Math.PI / 180.0)*2F);
		final Snowball snow=loc.getWorld().spawn(loc, Snowball.class);
		snow.setShooter((ProjectileSource) player);
		snow.setVelocity(vec);
		MetadataValue metadata = new FixedMetadataValue(plugin, "aice");
		snow.setMetadata("lod", metadata);
    	Version.playSound(player.getLocation(),Sound.BLOCK_GLASS_BREAK, 0.5F, 1F);
    	new BukkitRunnable(){
			@Override
			public void run() {
				Location loc=snow.getLocation();
				Version.particle(loc, "SNOWBALL", 0.13F, 0.13F, 0.13F, 1, 20);
				//Version.a(loc.getWorld(),"snowballpoof", loc.getX(), loc.getY(), loc.getZ(), 20, 0.13D, 0.13D, 0.13D, 0);
				if(snow.isDead()){
					this.cancel();
				}
			}
    	}.runTaskTimer(plugin, 1, 1);
	}
}
