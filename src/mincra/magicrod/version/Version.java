package mincra.magicrod.version;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;

import mincra.magicrod.main.Magic;

public class Version{
	// implements EffectInterface
	public static EffectInterface effect;

	public Version() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		switch(Bukkit.getServer().getClass().getPackage().getName()){
			//1.7.10
			/*case "org.bukkit.craftbukkit.v1_7_R4":
				Bukkit.getLogger().info("[MincraMagicRod] v1_7_R4");
				effect = (EffectInterface) Class.forName("mincra.magicrod.version.v_1_7_R4.Effect").newInstance();
				break;*/
			//1.8
			case "org.bukkit.craftbukkit.v1_8_R1":
				effect = (EffectInterface) Class.forName("mincra.magicrod.version.v_1_8_R1.Effect").newInstance();
				break;
			case "org.bukkit.craftbukkit.v1_8_R2":
				effect = (EffectInterface) Class.forName("mincra.magicrod.version.v_1_8_R2.Effect").newInstance();
				break;
			case "org.bukkit.craftbukkit.v1_8_R3":
				effect = (EffectInterface) Class.forName("mincra.magicrod.version.v_1_8_R3.Effect").newInstance();
				break;
			case "org.bukkit.craftbukkit.v1_10_R1":
				effect = (EffectInterface) Class.forName("mincra.magicrod.version.v_1_10_R1.Effect").newInstance();
				break;
			case "org.bukkit.craftbukkit.v1_12_R1":
				effect = (EffectInterface) Class.forName("mincra.magicrod.version.v_1_12_R1.Effect").newInstance();
				break;
			default:
				String name = Bukkit.getServer().getClass().getPackage().getName();
				Bukkit.getLogger().info(ChatColor.YELLOW+"バージョン"+name.substring(name.lastIndexOf('.') + 2)+"は非対応です。");
				Bukkit.getLogger().info(ChatColor.YELLOW+"プラグインを停止させました。");
				Bukkit.getServer().getPluginManager().disablePlugin(Magic.plugin);
				break;
		}
	}

	public static void playeffect(Location loc, String particle) {
		effect.playeffect(loc, particle);
	}

	public static void playeffect2(Location loc,String particle){
		effect.playeffect2(loc, particle);
	}

	public static void playeffect3(Location loc,float eyeheight,String particle){
		effect.playeffect3(loc, eyeheight, particle);
	}

	public static void playSound(Location loc, Sound sound, Float volume, Float pitch) {
		effect.playSound(loc, sound, volume, pitch);
	}

	public static void playSound(Location loc, Sound sound, Integer volume, Integer pitch) {
		effect.playSound(loc, sound, volume, pitch);
	}

	public static void strikeLightning(Location loc) {
		effect.strikeLightning(loc);
	}

	public static void particle(Location loc,String particle,float x,float y,float z,int speed,int count) {
		effect.particle(loc, particle, x, y, z, speed, count);
	}

	public static void setStats(LivingEntity _entity, double maxhealth, double knockback, double speed, double attack){
		effect.setStats(_entity, maxhealth, knockback, speed, attack);
	}

	public static void setStats(LivingEntity _entity,double speed){
		effect.setStats(_entity, speed);
	}

	public static void setStats(LivingEntity _entity,double speed, double attack){
		effect.setStats(_entity, speed, attack);
	}

	public static double getSpeed(LivingEntity _entity){
		return effect.getSpeed(_entity);
	}
}
