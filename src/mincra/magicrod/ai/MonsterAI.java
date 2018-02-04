package mincra.magicrod.ai;

import java.util.ArrayList;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import mincra.magicrod.main.Magic;
import mincra.magicrod.version.Version;

public class MonsterAI{
	private Magic plugin;

	public MonsterAI(Magic plugin2){
		plugin = plugin2;
	}

	protected void fire(final LivingEntity le){

		new BukkitRunnable(){
			@Override
			public void run() {
				if(le.isValid()){
			    	boolean existsplayer = false;
					for(Entity en:le.getNearbyEntities(25, 15, 25)){
						if(en instanceof Player){
							existsplayer = true;
							break;
						}
					}
					if(existsplayer){
						Version.playeffect(le.getLocation(),"FLAME");
				    	Version.playSound(le.getLocation(),Sound.BLOCK_PORTAL_TRAVEL, 0.1F, 4F);
						new BukkitRunnable(){
							int angle=0;
							int angleInterval=10;
							int shot=4;
							int amount=36;
							ArrayList<Entity> arrows=new ArrayList<Entity>();
							@Override
							public void run() {
								angle+=angleInterval;
								Location loc = le.getLocation();
								loc.setYaw(-angle);
								le.teleport(loc);
								loc.add(0,1.3f,0);
								loc.getWorld().playEffect(loc,Effect.BOW_FIRE,0,angle);
								for(int n=0;n != shot;n++){
									Vector v = loc.getDirection();
									Arrow arrow=le.getWorld().spawnArrow(loc.add(v),v,1.5F,12);
									arrow.setFireTicks(140);
									arrow.setShooter(le);
									arrows.add(arrow);
								}
								// 射る回数をひとつ減らす
								amount--;
								if(amount<=0){
									//矢を削除
									new BukkitRunnable(){
										@Override
										public void run() {
											for(Entity arrow:arrows){
												arrow.remove();
											}
										}
									}.runTaskLater(plugin, 200);
									this.cancel();
								}
							}
						}.runTaskTimer(plugin, 1, 1);
					}
				}else{
					if(!le.isDead()) le.remove();
					this.cancel();
				}
			}
		}.runTaskTimer(plugin, 200, 200);
	}
	protected void heal(final LivingEntity le) {
		new BukkitRunnable(){
			@Override
			public void run() {
				if(le.isValid()){
			    	boolean existsplayer = false;
					for(Entity en:le.getNearbyEntities(24, 10, 24)){
						if(en instanceof Player){
							existsplayer = true;
							break;
						}
					}
					if(existsplayer){
					    Version.playeffect(le.getLocation(),"VILLAGER_HAPPY");
				    	Version.playSound(le.getLocation(),Sound.BLOCK_PORTAL_TRAVEL, 0.1F, 4F);
				    	int cnt = 0;
				    	for(Entity en:le.getNearbyEntities(12, 6, 12)){
							if(existsplayer){
							if(en instanceof Monster){
								Location loc = en.getLocation();
								Version.particle(loc.add(0, 1.7, 0), "HEART", 0.42F,0.42F,0.42F, 1, 10);
								if((((Monster)en).getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()-((Monster) en).getHealth())>=40){
									((Monster) en).setHealth(((Monster) en).getHealth()+40);
								}else{
									((Monster) en).setHealth(((Monster) en).getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
								}
								cnt++;
								if(cnt>=12){
									break;
								}
							}
						}
					}

					}
				}else{
					if(!le.isDead()) le.remove();
					this.cancel();
				}
			}
		}.runTaskTimer(plugin, 300, 300);
	}
	protected void blizard(final LivingEntity le) {
		new BukkitRunnable(){
			@Override
			public void run() {
				if(le.isValid()){
					for(Entity en:le.getNearbyEntities(24, 10, 24)){
						if(en instanceof Player){
							Location loc = le.getLocation();
							Location loc2=en.getLocation().add(0, 1.8F, 0);
					    	Location loc3= loc2.subtract(loc);
					    	Vector v = new Vector(loc3.getX(),loc3.getY(),loc3.getZ()).normalize();
					    	Location loc4 = le.getLocation();
					    	loc4.setYaw(loc3.getYaw());
					    	loc.add(0, 1.5F, 0);
					    	le.teleport(loc4);
					    	//le.getLocation().setYaw(loc3.getYaw());
					    	Arrow arrow=le.getWorld().spawnArrow(loc.add(v),  v, 1.5F, 12F);
							Version.playeffect(le.getLocation(),"ENCHANTMENT_TABLE");
							Version.playSound(le.getLocation(),Sound.BLOCK_GLASS_BREAK, 1.5F, 2F);
							MetadataValue arrowmetadata = new FixedMetadataValue(plugin, "1");
							arrow.setMetadata("MagicMob", arrowmetadata);
							break;
						}
					}
				}else{
					if(!le.isDead()) le.remove();
					this.cancel();
				}
			}
		}.runTaskTimer(plugin, 100,100);
	}
}
