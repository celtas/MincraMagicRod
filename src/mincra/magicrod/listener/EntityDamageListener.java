package mincra.magicrod.listener;

import mincra.magicrod.main.Magic;
import mincra.magicrod.version.Version;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Random;

public class EntityDamageListener implements Listener {
	public static JavaPlugin plugin;
	@SuppressWarnings("static-access")
	public EntityDamageListener(JavaPlugin plugin) {
	    this.plugin = plugin;
	}

	@EventHandler
	public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent e) {
		/*if(e.getDamager() instanceof Player){
			if(e.getEntity() instanceof Monster){
				if(e.getCause() == DamageCause.ENTITY_ATTACK&&((Player) e.getDamager()).getLevel()<=15){
						((Player) e.getDamager()).giveExp(2);
				}
			}
		}*/
		if(e.getDamager() instanceof Fireball&&e.getEntity() instanceof LivingEntity){
			if(e.getEntity() instanceof Player){
				return;
			}
			if ((((Projectile) e.getDamager()).getShooter() instanceof Player)&&(!(e.getEntity() instanceof Player)&&!(e.getEntity() instanceof Horse)&&!(e.getEntity() instanceof Wolf)&&!(e.getEntity() instanceof Sheep)&&!(e.getEntity() instanceof Chicken)&&!(e.getEntity() instanceof Cow))){
				if(e.getEntity() instanceof Player){
					e.setCancelled(true);
				}else{
				Entity en=e.getEntity();
				en.setFireTicks(1000);
				Random r =new Random();
				float x=(float) Math.sin(r.nextInt(360));
				float z=(float) Math.sin(r.nextInt(360));
				Vector v=new Vector(x*3,r.nextFloat()*5,z*3);
				en.setVelocity(v);
				//((CraftWorld) en.getWorld()).getHandle().a("hugeexplosion",en.getLocation().getX(),en.getLocation().getY()+0.3,en.getLocation().getZ(), 1, 0, 0, 0, 0);
				en.setFallDistance(15F);
				}
			}
		}else if(e.getDamager() instanceof Snowball&&e.getEntity() instanceof LivingEntity){
			if(e.getEntity() instanceof Player)
				return;
			final LivingEntity le=(LivingEntity) e.getEntity();
			for(MetadataValue ma:e.getDamager().getMetadata("lod")){
				if(ma.asString().equals("aice")){
					if(le instanceof Monster||le instanceof WaterMob){
						if(e.getEntity() instanceof Player){
							e.setCancelled(true);
						}else{
							blizard(le);
							break;
						}
					}
				}
			}
		}else if(e.getDamager() instanceof Arrow){
			if(!(e.getEntity() instanceof LivingEntity))
				return;

			final LivingEntity le = (LivingEntity) e.getEntity();
			if(le instanceof Player){
				if(le.hasMetadata("disablearrow")&&le.getMetadata("disablearrow").get(0).asBoolean()){
						Player player = (Player) le;
						player.playSound(player.getLocation(), Sound.ENTITY_ZOMBIE_INFECT, 0.1f, 1f);
						Version.particle(e.getDamager().getLocation(), "SPELL_INSTANT", 0.1f, 0.1f, 0.1f, 1, 8);
						e.setCancelled(true);
						return;
				}else{
					if(!(e.getDamager().hasMetadata("MagicMob"))) return;
					for(MetadataValue ma:e.getDamager().getMetadata("MagicMob")){
						switch(ma.asInt()){
							case 1:
								blizard(le);
						default:
							break;
						}
					}
				}
			}else{
				//プレイヤーが巻き込まれるのを防ぐ.
				if(e.getDamager() instanceof Player||e.getDamager() instanceof ArmorStand)
					return;

				if(e.getDamager().hasMetadata("MagicArrow")){

				}

				if(!(e.getDamager().hasMetadata("MagicWeapon")))
					return;

				for(MetadataValue ma:e.getDamager().getMetadata("MagicWeapon")){
					switch(ma.asInt()){
						case 3:
							blizard(le);
							break;
						case 5:
							paralysis(le);
							break;
						case 8:
							sleep(le);
							break;
						default:
							break;
					}
				}
			}
		}
	}
	private void blizard(final LivingEntity le){
		//大氷樹の効果のついた矢
		{
			Version.playSound(le.getLocation(), Sound.BLOCK_PORTAL_TRAVEL, 0.1F, 10F);
			le.setVelocity(new Vector(0,0,0));
			final Location loc2=le.getLocation().add(-2,0,-2);
			//Version.playeffect2(le,"fireworksSpark");
			int cnt=0;
			for(Location l:Magic.ice){
				loc2.add(l.getX(),l.getY(),l.getZ());
				final Block blo=loc2.getBlock();
				if(cnt<10){
					if(blo.getType().equals(Material.AIR)||blo.getType().equals(Material.LONG_GRASS)||blo.getType().equals(Material.RED_ROSE)){
						blo.setType(Material.ICE);
				        new BukkitRunnable() {
				        	Block clearblo=blo;
				            public void run() {
				            	clearblo.setType(Material.AIR);
				            }
				        }.runTaskLater(plugin, 300);
					}
				}else if(cnt<19){
					new BukkitRunnable(){
						Block blo2=blo;
						public void run() {
							if(blo2.getType().equals(Material.AIR)||blo2.getType().equals(Material.LONG_GRASS)||blo2.getType().equals(Material.RED_ROSE)){
								blo2.setType(Material.ICE);
						        new BukkitRunnable() {
						        	Block clearblo=blo2;
						            public void run() {
						            	clearblo.setType(Material.AIR);
						            }
						        }.runTaskLater(plugin, 300);
							}
						}
					}.runTaskLater(plugin, 5);
				}else{
					new BukkitRunnable(){
						Block blo2=blo;
						public void run() {
							if(blo2.getType().equals(Material.AIR)||blo2.getType().equals(Material.LONG_GRASS)||blo2.getType().equals(Material.RED_ROSE)){
								blo2.setType(Material.ICE);
						        new BukkitRunnable() {
						        	Block clearblo=blo2;
						            public void run() {
						            	clearblo.setType(Material.AIR);
						            }
						        }.runTaskLater(plugin, 300);
							}
						}
					}.runTaskLater(plugin, 10);
				}
				loc2.add(-l.getX(),-l.getY(),-l.getZ());
				cnt++;
				}
				cnt=0;
				for(Location l:Magic.ice_2){
					loc2.add(l.getX(),l.getY(),l.getZ());
					final Block blo=loc2.getBlock();
				if(cnt<10){
					if(blo.getType().equals(Material.AIR)||blo.getType().equals(Material.LONG_GRASS)||blo.getType().equals(Material.RED_ROSE)){
						blo.setType(Material.ICE);
				        new BukkitRunnable() {
				        	Block clearblo=blo;
				            public void run() {
				            	clearblo.setType(Material.AIR);
				            }
				        }.runTaskLater(plugin, 300);
					}
				}else if(cnt<19){
					new BukkitRunnable(){
						Block blo2=blo;
						public void run() {
							if(blo2.getType().equals(Material.AIR)||blo2.getType().equals(Material.LONG_GRASS)||blo2.getType().equals(Material.RED_ROSE)){
								blo2.setType(Material.ICE);
						        new BukkitRunnable() {
						        	Block clearblo=blo2;
						            public void run() {
						            	clearblo.setType(Material.AIR);
						            }
						        }.runTaskLater(plugin, 300);
							}
						}
					}.runTaskLater(plugin, 5);
				}else{
					new BukkitRunnable(){
						Block blo2=blo;
						public void run() {
							if(blo2.getType().equals(Material.AIR)||blo2.getType().equals(Material.LONG_GRASS)||blo2.getType().equals(Material.RED_ROSE)){
								blo2.setType(Material.ICE);
						        new BukkitRunnable() {
						        	Block clearblo=blo2;
						            public void run() {
						            	clearblo.setType(Material.AIR);
						            }
						        }.runTaskLater(plugin, 300);
							}
						}
					}.runTaskLater(plugin, 10);
				}
				loc2.add(-l.getX(),-l.getY(),-l.getZ());
				cnt++;
			}
	        new BukkitRunnable() {
	        	LivingEntity le2=le;
	        	int a=0;
	            public void run() {
	            	if(!le.isDead()&&le2.getLocation().add(0, 1, 0).getBlock().getType().equals(Material.ICE)){
	            		le2.damage(2D);
	            	}else {
	            		this.cancel();
	            	}
	            	if(a>12){
	            		this.cancel();
	            	}
	            	a++;
	            }
	        }.runTaskTimer(plugin,25,25);
		}
	}
	private void paralysis(final LivingEntity le){
		final double speed = Version.getSpeed(le);
		if(speed>0){
			Version.setStats((LivingEntity) le, 0F);
			new BukkitRunnable(){
				int cnt=0;
				@Override
				public void run() {
					if(le.isValid()){
						Version.particle(le.getLocation().add(0, 1.3, 0), "VILLAGER_ANGRY", 0.28F, 0.30F, 0.28F, 1, 12);
						cnt++;
						//200tick後に抜ける
						if(cnt>=20){
							Version.setStats((LivingEntity) le, speed);
							this.cancel();
						}
					}else{
						this.cancel();
					}
				}
			//0tick後から5tick毎に処理を行う
			}.runTaskTimer(plugin, 0, 20);
		}
	}
	private	void sleep(final LivingEntity le){
		final double speed = Version.getSpeed(le);
		if(speed>0){
			Version.setStats(le, 0F);
			new BukkitRunnable(){
				int cnt=0;
				double health = le.getHealth();
				@Override
				public void run() {
					if(le.isValid()){
						Version.particle(le.getLocation().add(0, ((LivingEntity) le).getEyeHeight()+0.3, 0), "ENCHANTMENT_TABLE", 0F, 0F, 0F, 1, 25);
						cnt++;
						//200tick後に抜ける
						if(cnt>=100||(health-le.getHealth())>=10){
							Version.setStats((LivingEntity) le, speed);
							this.cancel();
						}
					}else{
						this.cancel();
					}
				}
			}.runTaskTimer(plugin, 10, 10);
		}
	}
}
