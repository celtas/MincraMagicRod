package mincra.magicrod.rod;

import org.bukkit.Location;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownExpBottle;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class Attack_1 extends BukkitRunnable{
	Plugin plugin;
	Player player;
	public Attack_1(Player _player,Plugin _plugin) {
		player=_player;
		plugin=_plugin;
	}
	@Override
	public void run() {
			Location loc=player.getLocation();
			float yaw=loc.getYaw();
			EnderPearl item=player.getWorld().spawn(player.getLocation().add(new Vector(-1*Math.sin(yaw*Math.PI / 180.0)*1.2F,1.5F,Math.cos(yaw*Math.PI / 180.0)*1.2F)),EnderPearl.class);
			new At(item,player).runTaskTimer(plugin, 0, 1);
			ThrownExpBottle item2=player.getWorld().spawn(player.getLocation().add(new Vector(-1*Math.sin(yaw*Math.PI / 180.0)*1.2F+0.2F,1.5F+0.2F,Math.cos(yaw*Math.PI / 180.0)*1.2F+0.2F)),ThrownExpBottle.class);
			new At2(item2,player).runTaskTimer(plugin, 0, 1);
	}
	
}
class At extends BukkitRunnable{
	EnderPearl orb;
	Player player;
	Float yaw,pitch;
	Vector v;
	public At(EnderPearl orb,Player player) {
		Location loc=player.getLocation();
		yaw=loc.getYaw();
		pitch=loc.getPitch();
		float yswim=(float) (-Math.tan(pitch*Math.PI / 180.0)*2F);
		if(yswim>1.2F){
			yswim=1.2F;
		}
		if(yswim<-1.2F){
			yswim=-1.2F;
		}
    	v = new Vector(-1*Math.sin(yaw*Math.PI / 180.0)*2F,yswim,Math.cos(yaw*Math.PI / 180.0)*2F);
    	orb.setVelocity(v);
	}
	@Override
	public void run() {
		if(orb==null){
			this.cancel();
		}
	}
}
class At2 extends BukkitRunnable{
	ThrownExpBottle orb;
	Player player;
	Float yaw,pitch;
	Vector v;
	public At2(ThrownExpBottle orb,Player player) {
		Location loc=player.getLocation();
		yaw=loc.getYaw();
		pitch=loc.getPitch();
		float yswim=(float) (-Math.tan(pitch*Math.PI / 180.0)*2F);
		if(yswim>1.2F){
			yswim=1.2F;
		}
		if(yswim<-1.2F){
			yswim=-1.2F;
		}
    	v = new Vector(-1*Math.sin(yaw*Math.PI / 180.0)*2F,yswim,Math.cos(yaw*Math.PI / 180.0)*2F);
    	orb.setVelocity(v);
	}
	@Override
	public void run() {
		if(orb==null){
			this.cancel();
		}
	}
}
