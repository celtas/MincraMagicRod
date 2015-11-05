package mincra.magicrod.effect;

import mincra.magicrod.version.Version;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

public class PlayEffect {
	static public void playblood(Entity entity){
		Location loc=((LivingEntity)entity).getEyeLocation();
		//"blockcrack_152_0"
		Version.particle(loc,"REDSTONE", 0.2F, 0.2F, 0.2F, 1, 25);
		//Version.a(loc.getWorld(),EnumParticle.BLOCK_CRACK, loc.getX(), loc.getY(), loc.getZ(), 100, 0D, 0.22D, 0D, 0);
	}
}
