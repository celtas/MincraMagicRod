package mincra.magicrod.version;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;

public interface EffectInterface {
	public void playeffect(Location loc,String particle);
	public void playeffect2(Location loc,String particle);
	public void playeffect3(Location loc,float eyeheight,String particle);
	public void playSound(Location loc, Sound sound, Float volume, Float pitch);
	public void playSound(Location loc, Sound sound, Integer volume, Integer pitch);
	public void particle(Location loc,String particle,float x,float y,float z,int speed,int count);
	public void strikeLightning(Location loc);
	public void setStats(LivingEntity _entity, double maxhealth, double knockback, double speed, double attack);
	public void setStats(LivingEntity _entity,double speed);
	public void setStats(LivingEntity _entity,double speed, double attack);
	public double getSpeed(LivingEntity _entity);
}
