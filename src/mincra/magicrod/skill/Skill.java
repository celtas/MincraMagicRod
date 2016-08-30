package mincra.magicrod.skill;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;

import mincra.magicrod.bar.Bar;
import mincra.magicrod.item.MagicItem;
import mincra.magicrod.listener.DeathListener;
import mincra.magicrod.main.Magic;
import mincra.magicrod.rod.ExpRod;
import mincra.magicrod.version.Version;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.WaterMob;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class Skill{
	protected static HashMap<String,Timestamp> boostPlayers = new HashMap<String,Timestamp>();
	protected static HashMap<String,Timestamp> disableArrowPlayers = new HashMap<String,Timestamp>();
	Random r = new Random();
	Magic plugin;
	public Skill(Magic plugin){
		this.plugin = plugin;
	}

	boolean gacha(float probability){
		float hash = (float) ((r.nextInt(10000)+1)*0.01);
		if(hash<=probability){
			return true;
		}else{
			return false;
		}
	}
	protected void boost(final Player player){
		player.setMaxHealth(60);
		player.playSound(player.getLocation(), Sound.LEVEL_UP, 1F, 1F);
		final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		boostPlayers.put(player.getName(), timestamp);
		new BukkitRunnable(){
			@Override
			public void run() {
				if(Skill.boostPlayers.containsKey(player.getName())){
					if(timestamp.equals(Skill.boostPlayers.get(player.getName()))){
						Skill.boostPlayers.remove(player.getName());
						player.setMaxHealth(40);
					}
				}
			}
		}.runTaskLater(plugin, 12000);
	}

	protected void paralysis(final Entity entity,float percent) {
		if(gacha(percent)){
			final double speed = Version.getSpeed((LivingEntity) entity);
			if(speed>0){
				Version.setStats((LivingEntity) entity, 0F);
				new BukkitRunnable(){
					int cnt=0;
					@Override
					public void run() {
						if(entity.isValid()){
							Version.particle(entity.getLocation().add(0, ((LivingEntity) entity).getEyeHeight(), 0), "VILLAGER_ANGRY", 0.33F, 0.26F, 0.33F, 1, 12);
							cnt++;
							//200tick後に抜ける
							if(cnt>=10){
								Version.setStats((LivingEntity) entity, speed);
								this.cancel();
							}
						}else{
							this.cancel();
						}
					}
				//0tick後から5tick毎に処理を行う
				}.runTaskTimer(Magic.plugin, 0, 20);
			}
		}
	}

	protected void sleep(){

	}

	protected void disableArrow(final Player player,int disableTicks){
		player.setMetadata("disablearrow", new FixedMetadataValue(plugin, "true"));
    	final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    	disableArrowPlayers.put(player.getName(), timestamp);
    	new BukkitRunnable(){
			@Override
			public void run() {
				if(disableArrowPlayers.containsKey(player.getName())){
					if(timestamp.equals(disableArrowPlayers.get(player.getName()))){
						disableArrowPlayers.remove(player.getName());
						player.removeMetadata("disablearrow", plugin);
					}
				}
			}
		}.runTaskLater(plugin, disableTicks);
	}

	protected void ressurection(final Player player,int x,int y,int z,int limit,int ressurectionTicks){
		final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		DeathListener.resurrectionPlayers.put(player.getName(), timestamp);
		Version.playeffect(player.getLocation(), "FIREWORKS_SPARK");
		player.removePotionEffect(PotionEffectType.NIGHT_VISION);
		player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, ressurectionTicks, 1));
		new BukkitRunnable(){
			@Override
			public void run() {
				if(DeathListener.resurrectionPlayers.containsKey(player.getName())){
					if(timestamp.equals(DeathListener.resurrectionPlayers.get(player.getName()))){
						DeathListener.resurrectionPlayers.remove(player.getName());
					}
				}
			}
		}.runTaskLater(plugin, ressurectionTicks);

    	int cnt = 0;
    	for(Entity en:player.getNearbyEntities(x, y, z)){
			if(en instanceof Player){
				final Player ressurectionPlayer = (Player) en;
				Version.playeffect(ressurectionPlayer.getLocation(), "FIREWORKS_SPARK");
				cnt++;
				DeathListener.resurrectionPlayers.put(ressurectionPlayer.getName(), timestamp);
				ressurectionPlayer.removePotionEffect(PotionEffectType.NIGHT_VISION);
				ressurectionPlayer.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, ressurectionTicks, 1));
				ressurectionPlayer.sendMessage(ChatColor.GRAY+player.getName()+"からリザレクションをかけられました.");
				new BukkitRunnable(){
					@Override
					public void run() {
						if(DeathListener.resurrectionPlayers.containsKey(ressurectionPlayer.getName())){
							if(timestamp.equals(DeathListener.resurrectionPlayers.get(ressurectionPlayer.getName()))){
								DeathListener.resurrectionPlayers.remove(ressurectionPlayer.getName());
							}
						}
					}
				}.runTaskLater(plugin, ressurectionTicks);
				if(cnt >= limit){
					break;
				}
			}

		}
	}
	protected void heal(Player player,int x,int y,int z,int limit,int heal){
		int cnt = 0;
    	for(Entity en:player.getNearbyEntities(x, y, z)){
			if(en instanceof Player){
				Location loc = en.getLocation();
				Player healPlayer = (Player) en;
				Version.particle(loc.add(0, 1.7, 0), "HEART", 0.42F,0.42F,0.42F, 1, 15);
				if((healPlayer.getMaxHealth()-healPlayer.getHealth())>=heal){
					healPlayer.setHealth(healPlayer.getHealth()+heal);
				}else{
					healPlayer.setHealth(healPlayer.getMaxHealth());
				}
				cnt++;
				if(cnt>=limit){
					break;
				}
			}
		}
    	Version.particle(player.getLocation().add(0, 1.7, 0), "HEART", 0.42F,0.42F,0.42F, 1, 15);
    	if((player.getMaxHealth()-player.getHealth())>=heal){
			player.setHealth(player.getHealth()+heal);
		}else{
			player.setHealth(player.getMaxHealth());
		}
	}

	protected void thunder(Player player,int x,int y,int z,int limit){
		int cnt = 0;
		Vector nockback = player.getLocation().getDirection();
		for(Entity en:player.getNearbyEntities(x, y, z)){
			if(en instanceof Monster){
				player.playSound(player.getLocation(), Sound.AMBIENCE_THUNDER, 8, 1);
				player.playSound(player.getLocation(), Sound.EXPLODE, 8, 1);
				en.getLocation().getWorld().strikeLightningEffect(en.getLocation());
				((Monster) en).damage(8D);
				en.setVelocity(nockback);
				cnt++;
				//麻痺処理
				this.paralysis(en, 20.0f);
				if(cnt>=limit){
					break;
				}
			}
		}
	}

	protected void blizard(final Player player,int x,int y,int z,int limit,final int coldTicks){
		int cnt = 0;
		for(final Entity le:player.getNearbyEntities(x, y, z)){
			if(le instanceof Monster||le instanceof WaterMob){
				Version.playSound(player.getLocation(),Sound.GLASS, 1.2F, 1F);
				Vector v=new Vector(0,0,0);
				le.setVelocity(v);
				Location loc2=le.getLocation().add(-2,0,-2);
				for(Location l:Magic.ice){
					loc2.add(l.getX(),l.getY(),l.getZ());
					final Block blo=loc2.getBlock();
					if(blo.getType().equals(Material.AIR)||blo.getType().equals(Material.LONG_GRASS)||blo.getType().equals(Material.RED_ROSE)||blo.getType().equals(Material.SNOW)){
						blo.setType(Material.ICE);
				        new BukkitRunnable() {
				        	Block clearblo=blo;
				            public void run() {
				            	clearblo.setType(Material.AIR);
				            }
				        }.runTaskLater(Magic.plugin, coldTicks);
					}
					loc2.add(-1*l.getX(),-1*l.getY(),-1*l.getZ());
				}
				for(Location l:Magic.ice_2){
					loc2.add(l.getX(),l.getY(),l.getZ());
					final Block blo=loc2.getBlock();
					if(blo.getType().equals(Material.AIR)||blo.getType().equals(Material.LONG_GRASS)||blo.getType().equals(Material.RED_ROSE)||blo.getType().equals(Material.SNOW)){
						blo.setType(Material.ICE);
				        new BukkitRunnable() {
				        	Block clearblo=blo;
							@Override
							public void run() {
								clearblo.setType(Material.AIR);
							}
				        }.runTaskLater(Magic.plugin, coldTicks);
					}
					loc2.add(-1*l.getX(),-1*l.getY(),-1*l.getZ());
				}
		        new BukkitRunnable() {
		        	Entity le2=le;
		        	int cancelCount=0;
		        	int cancel = coldTicks/10;
		            public void run() {
		            	if(!le.isDead()&&le2.getLocation().add(0, 1, 0).getBlock().getType().equals(Material.ICE)){
		            		((LivingEntity) le2).damage(1D);
		            	}else {
		            		this.cancel();
		            	}
		            	if(cancelCount>cancel){
		            		this.cancel();
		            	}
		            	cancelCount++;
		            }
		        }.runTaskTimer(Magic.plugin,10,10);
		        cnt++;
				if(cnt>=limit){
					break;
				}
	    	}
		}
	}
	protected void fire(Player player,int x,int y,int z,int limit,int fireTicks){
		int cnt = 0;
    	Vector nockback = player.getLocation().getDirection().add(new Vector(0,1.2,0));
		for(Entity en:player.getNearbyEntities(x, y, z)){
			if(en instanceof Monster){
				en.setFireTicks(fireTicks);
				en.setVelocity(nockback);
				cnt++;
				if(cnt>=limit){
					break;
				}
			}
		}
	}
	protected void taunt(Player player,int x,int y,int z,int limit){
    	int cnt = 0;
		for(Entity en:player.getNearbyEntities(x, y, z)){
			if(en instanceof Monster){
				((Monster) en).setTarget(player);
				en.setVelocity(new Vector(0,0.5F,0));
				cnt++;
				if(cnt >= limit){
					break;
				}
			}
		}
	}
	protected void holyWave(Player player,int x,int y,int z,int limit){
		player.playSound(player.getLocation(), Sound.ZOMBIE_INFECT, 1, 1);
		for(Entity en:player.getWorld().getNearbyEntities(player.getLocation(), x, y, z)){
			int cnt = 0;
			if(en instanceof Zombie||en instanceof Skeleton){
				Version.strikeLightning(en.getLocation());
				((LivingEntity)en).damage(30D,player);
				cnt++;
				if(cnt >= limit){
					break;
				}
			}
		}
	}
	protected void holy(final Player player){
		Location loc=player.getLocation();
		float yaw=loc.getYaw();
		final Location loc2=new Location(player.getWorld(),-1*Math.sin(yaw*Math.PI / 180.0)*2,-0.5F,Math.cos(yaw*Math.PI / 180.0)*2).add(loc);
		final Location loc3=new Location(player.getWorld(),-1*Math.sin(yaw*Math.PI / 180.0)*4,-0.5F,Math.cos(yaw*Math.PI / 180.0)*4).add(loc);
		final Location loc4=new Location(player.getWorld(),-1*Math.sin(yaw*Math.PI / 180.0)*6,-0.5F,Math.cos(yaw*Math.PI / 180.0)*6).add(loc);
		new BukkitRunnable(){
			public void run() {
				Version.strikeLightning(loc2);
				player.playSound(player.getLocation(), Sound.ZOMBIE_INFECT, 1, 1);
				for(Entity en:player.getWorld().getNearbyEntities(loc4, 2, 10, 2)){
					if(en instanceof Zombie||en instanceof Skeleton){
						((LivingEntity)en).damage(10D,player);
					}
				}
			}
		}.runTaskLater(plugin, 5);
		new BukkitRunnable(){
			public void run() {
				Version.strikeLightning(loc3);
				player.playSound(player.getLocation(), Sound.ZOMBIE_INFECT, 1, 1);
				for(Entity en:player.getWorld().getNearbyEntities(loc4, 2, 10, 2)){
					if(en instanceof Zombie||en instanceof Skeleton){
						((LivingEntity)en).damage(15D,player);
					}
				}
			}
		}.runTaskLater(plugin, 10);
		new BukkitRunnable(){
			public void run() {
				Version.strikeLightning(loc4);
				player.playSound(player.getLocation(), Sound.ZOMBIE_INFECT, 1, 1);
				for(Entity en:player.getWorld().getNearbyEntities(loc4, 2, 10, 2)){
					if(en instanceof Zombie||en instanceof Skeleton){
						((LivingEntity)en).damage(30D,player);
					}
				}
			}
		}.runTaskLater(plugin, 15);
	}

	public void gravityLv1(final Player player,final int x,final int y,final int z,int interval,final int ticks){
		final Block block = player.getTargetBlock((Set<Material>) null, 48);
		final Location loc = block.getLocation();
		loc.add(0, 1, 0);
		//15秒
		new BukkitRunnable(){
			int count = ticks;
			@Override
			public void run() {
				count--;
				Version.particle(loc, "PORTAL", 0.1F, 1F,0.1F, 1, 200);
				for(Entity en : loc.getWorld().getNearbyEntities(loc, x, y, z)){
					if(en instanceof Monster){
						en.setVelocity(block.getLocation().subtract(en.getLocation()).multiply(0.1).toVector());
					}
				}
				if(count<=0){
					this.cancel();
				}
			}
		}.runTaskTimer(plugin, interval, interval);
	}
	public void untigravityLv1(final Player player,final int x,final int y,final int z,int interval,final int ticks){
		final Location loc = player.getTargetBlock((Set<Material>) null, 48).getLocation();
		loc.add(0, 1, 0);
		//15秒
		new BukkitRunnable(){
			int count = ticks;
			@Override
			public void run() {
				count--;
				Version.particle(loc, "PORTAL", 0.1F, 1F,0.1F, 1, 200);
				for(Entity en : loc.getWorld().getNearbyEntities(loc, x, y, z)){
					if(en instanceof Monster){
						en.setVelocity(en.getLocation().subtract(loc).multiply(0.1).toVector());
					}
				}
				if(count<=0){
					this.cancel();
				}
			}
		}.runTaskTimer(plugin, interval, interval);
	}
	protected void magicArrowLv1(final Player player){
		final Location loc = player.getLocation();
		loc.add(loc.getDirection().multiply(4));
		final Location dropLoc = loc.clone().add(0,0.5f,0);

		final ItemStack item = MagicItem.magicArrow;
		item.setAmount(2);
		new BukkitRunnable(){
			int amount = 32;
			@Override
			public void run() {
				if(amount%4 == 0){
					Version.playeffect(loc,"ENCHANTMENT_TABLE");
				}
				player.playSound(loc, Sound.ZOMBIE_INFECT, 0.2f, 1f);
				amount -=2;
				if(amount<=0)
					this.cancel();
				else
					loc.getWorld().dropItemNaturally(dropLoc, item);
			}
		}.runTaskTimer(plugin, 10, 10);
		loc.getWorld().dropItemNaturally(loc,item);
	}
	protected void villagerLv1(Player player) {
		switch(r.nextInt(4)){
		case 0:
			Version.playSound(player.getLocation(), Sound.VILLAGER_YES, 2f, 1f);
			break;
		case 1:
			Version.playSound(player.getLocation(), Sound.VILLAGER_NO, 2f, 1f);
			break;
		case 2:
			Version.playSound(player.getLocation(), Sound.VILLAGER_HAGGLE, 2f, 1f);
			break;
		case 3:
			Version.playSound(player.getLocation(), Sound.VILLAGER_IDLE, 2f, 1f);
			break;
		}

	}
	protected void devideLv1(final Player player){
		player.playSound(player.getLocation(), Sound.SHOOT_ARROW, 1, 1);
		final Arrow originalArrow = player.getWorld().spawnArrow(player.getLocation(),player.getLocation().getDirection(), 0.6F, 12);
		originalArrow.setShooter(player);
		new BukkitRunnable(){
			int count = 0;
			@Override
			public void run() {
				Arrow arrow = player.getWorld().spawnArrow(originalArrow.getLocation(),player.getLocation().getDirection(), 0.6F, 12);
				arrow.setShooter(player);
				count++;
				if(count>10)
					this.cancel();
			}
		}.runTaskTimer(plugin, 20, 2);
	}
	protected void chargeLv1(final Player player) {
		player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);
		new BukkitRunnable(){
			int count = 20;
			@Override
			public void run() {
				count--;
				Version.particle(player.getLocation(), "PORTAL", 0.1F, 1F,0.1F, 1, 100);
				if(count <= 0||player.getLevel()>9){
					this.cancel();
				}
				player.giveExp(2);
			}
		}.runTaskTimer(plugin, 10, 10);
	}
	
}
