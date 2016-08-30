package mincra.magicrod.listener;

import mincra.magicrod.api.MagicApi;
import mincra.magicrod.bar.Bar;
import mincra.magicrod.version.Version;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.inventivetalent.bossbar.BossBarAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ArrowListener implements Listener{
	Random r = new Random();
	private JavaPlugin plugin;
	public ArrowListener(JavaPlugin plugin) {
	    this.plugin = plugin;
	}
	@EventHandler
	public void EntityShootBowEvent(final EntityShootBowEvent event){
		if(event.getEntity() instanceof Player){
			final Player player = (Player) event.getEntity();
			int number = player.getInventory().first(Material.ARROW);
			ItemStack arrow = player.getInventory().getItem(number);
			if(MagicApi.isMagic(arrow)){
				if(!player.hasMetadata("MagicJob")){
					event.setCancelled(true);
					player.sendMessage(ChatColor.GRAY+"初期化処理が終わるまでお待ち下さい.");
					return;
				}
				if(player.getMetadata("MagicJob").get(0).asInt() != 4){
					event.setCancelled(true);
					player.sendMessage(ChatColor.GRAY+"魔法矢はアーチャー系以外は使用できません.");
					return;
				}
				if(BossBarAPI.hasBar(player))
					return;

				event.getProjectile().setMetadata("MagicArrow", new FixedMetadataValue(plugin,true));
				new BukkitRunnable(){
					Entity projectile = event.getProjectile();
					int count = 60;
					@Override
					public void run() {
						Version.particle(projectile.getLocation(), "ENCHANTMENT_TABLE", 0.2F, 0.2F, 0.2F, 0, 14);
						count--;
						if(count<=0||projectile.isDead()){
							this.cancel();
						}
					}
				}.runTaskTimer(plugin, 1, 1);
			}else if(event.getBow().getItemMeta().hasLore()){
				if(BossBarAPI.hasBar(player))
					return;
				List<String> lore=event.getBow().getItemMeta().getLore();
				String lore0 = ChatColor.stripColor(lore.get(0));
				switch(lore0){
					case "魔法武器番号:2":
						shootMagicWeapon2((Player) event.getEntity(),event.getProjectile(),event.getBow());
						break;
					case "魔法武器番号:2-2":
						shootMagicWeapon2_2((Player) event.getEntity(),event.getProjectile(),event.getBow(),event.getForce());
						break;
					case "魔法武器番号:3":
						shootMagicWeapon3((Player) event.getEntity(),event.getProjectile(),event.getBow());
						break;
					case "魔法武器番号:3-2":
						shootMagicWeapon3_2((Player) event.getEntity(),event.getProjectile(),event.getBow());
						break;
					case "魔法武器番号:5":
						shootParalysisBow((Player) event.getEntity(),event.getProjectile(),event.getBow());
						break;
					case "魔法武器番号:8":
						shootSleepBow((Player) event.getEntity(),event.getProjectile(),event.getBow());
						break;
					default:
						break;
				}
			}
		}
	}

	public void shootMagicWeapon2(final Player player,final Entity entity,final ItemStack item){
		List<String> list=new ArrayList<String>();
    	list=item.getItemMeta().getLore();
    	//MP消費
    	float hlv2=Float.valueOf(list.get(3).substring(2, 6));
    	int hlv=(int) hlv2;
    	if(hlv==0||player.getLevel()>=hlv){
    		player.setLevel(player.getLevel()-hlv);
    		Version.playSound(player.getLocation(),Sound.ENTITY_WITHER_SHOOT, 5F, 2F);
	    	String st=list.get(2);
	    	//クールタイム
	    	Float se=Float.valueOf(st.substring(2, 6));
	    	if(se>0F)
	    	new Bar(player,"["+item.getItemMeta().getDisplayName()+ChatColor.RESET+"]クールタイム",se);
	    	//発生確率
	    	String st2=list.get(4);
	    	Float i=Float.valueOf(st2.substring(2, 6));
	    	Random r = new Random();
	    	if((r.nextInt(100)+1)<=i){
					new BukkitRunnable(){
						Location loc;
						@Override
						public void run() {
							if(entity.isDead()){
								this.cancel();
							}
							if(loc==null){
								loc = entity.getLocation();
							}else if(entity.getLocation().distance(loc)==0){
								List<Entity> entitylist = entity.getNearbyEntities(2, 4, 2);
								Boolean isPlayer = false;
								for(Entity entity:entitylist){
									if(entity instanceof Player){
										isPlayer = true;
									}
								}
								if(!isPlayer){
									TNTPrimed tnt = (TNTPrimed)entity.getWorld().spawnEntity(entity.getLocation(), EntityType.PRIMED_TNT);
									tnt.setFuseTicks(0);
									Version.playeffect(entity.getLocation(), "LAVA");
									item.setDurability((short) (item.getDurability()+1));
								}else{
									player.sendMessage(ChatColor.GRAY+"プレイヤーに対しては使用できません。");
								}
								entity.remove();
							}else{
								Version.particle(loc,"SPELL_INSTANT", 0.1F, 0.1F, 0.1F, 1, 5);
								//Version.a(loc.getWorld(),"blockcrack_46_0", loc.getX(), loc.getY(), loc.getZ(), 5, 0.1D, 0.1D, 0.1D, 0);
								loc=entity.getLocation();
							}
						}
					}.runTaskTimer(plugin, 10, 2);
	    	}
    	}else{
    		player.sendMessage("私のMP低すぎっ!?");
    	}
	}
	public void shootMagicWeapon2_2(final Player player,final Entity entity,final ItemStack item,final Float _force){
		List<String> list=new ArrayList<String>();
    	list=item.getItemMeta().getLore();
    	//MP消費
    	float hlv2=Float.valueOf(list.get(3).substring(2, 6));
    	int hlv=(int) hlv2;
    	if(hlv==0||player.getLevel()>=hlv){
    		player.setLevel(player.getLevel()-hlv);
    		Version.playSound(player.getLocation(),Sound.ENTITY_WITHER_SHOOT, 5F, 2F);
	    	String st=list.get(2);
	    	//クールタイム
	    	Float se=Float.valueOf(st.substring(2, 6));
	    	if(se>0F)
	    	new Bar(player,"["+item.getItemMeta().getDisplayName()+ChatColor.RESET+"]クールタイム",se);
	    	//発生確率
	    	String st2=list.get(4);
	    	Float i=Float.valueOf(st2.substring(2, 6));
	    	Random r = new Random();
	    	if((r.nextInt(100)+1)<=i){
					new BukkitRunnable(){
						Location loc;
						@Override
						public void run() {
							if(entity.isDead()){
								this.cancel();
							}
							if(loc==null){
								loc = entity.getLocation();
							}else if(entity.getLocation().distance(loc)==0){
								List<Entity> entitylist = entity.getNearbyEntities(5, 5, 5);
								Boolean isPlayer = false;
								for(Entity entity:entitylist){
									if(entity instanceof Player){
										isPlayer = true;
									}
								}
								if(!isPlayer){
									float force = _force;
									while(force>=0){
										TNTPrimed tnt = (TNTPrimed)entity.getWorld().spawnEntity(entity.getLocation(), EntityType.PRIMED_TNT);
										tnt.setFuseTicks(0);
										force=(float) (force-0.06);
									}
									Version.playeffect(entity.getLocation(),"LAVA");
									item.setDurability((short) (item.getDurability()+1));
								}else{
									player.sendMessage(ChatColor.GRAY+"プレイヤーに対しては使用できません。");
								}
								entity.remove();
							}else{
								Version.particle(loc,"SPELL_INSTANT", 0.1F, 0.1F, 0.1F, 1, 5);
								//Version.a(loc.getWorld(),"blockcrack_46_0", loc.getX(), loc.getY(), loc.getZ(), 5, 0.1D, 0.1D, 0.1D, 0);
								loc=entity.getLocation();
							}
						}
					}.runTaskTimer(plugin, 10, 2);
	    	}
    	}else{
    		player.sendMessage("私のMP低すぎっ!?");
    	}
	}

	public void shootMagicWeapon3(final Player player,final Entity entity,final ItemStack item){
		List<String> list=new ArrayList<String>();
    	list=item.getItemMeta().getLore();
    	//MP消費
    	float hlv2=Float.valueOf(list.get(3).substring(2, 6));
    	int hlv=(int) hlv2;
    	if(hlv==0||player.getLevel()>=hlv){
    		player.setLevel(player.getLevel()-hlv);
    		Version.playSound(player.getLocation(),Sound.ENTITY_WITHER_SHOOT, 5F, 2F);
	    	String st=list.get(2);
	    	//クールタイム
	    	Float se=Float.valueOf(st.substring(2, 6));
	    	if(se>0F)
	    	new Bar(player,"["+item.getItemMeta().getDisplayName()+ChatColor.RESET+"]クールタイム",se);
	    	//発生確率
	    	String st2=list.get(4);
	    	Float i=Float.valueOf(st2.substring(2, 6));
	    	Random r = new Random();
	    	if((r.nextInt(100)+1)<=i){
	    			item.setDurability((short) (item.getDurability()+1));
					new BukkitRunnable(){
						@Override
						public void run() {
							if(entity.isDead()){
								this.cancel();
							}
							if(entity.getVelocity().getY() < -0.2D){
								Version.playeffect(entity.getLocation(),"ENCHANTMENT_TABLE");
								World world = entity.getWorld();
								Location loc = entity.getLocation();
								Random r = new Random();
								Vector v = new Vector(0,0.3F,0);
								MetadataValue arrowmetadata = new FixedMetadataValue(plugin, "3");
					    		Version.playSound(entity.getLocation(),Sound.ENTITY_GENERIC_EXPLODE, 0.5F, 2F);
								for(int i=0;i<=40;i++){
									float speed = r.nextFloat()+0.6F;
									final Arrow arrow = world.spawnArrow(loc, v, speed, 300F);
									arrow.setMetadata("MagicWeapon", arrowmetadata);
									new BukkitRunnable(){
										@Override
										public void run() {
											arrow.remove();
										}
									}.runTaskLater(plugin, 200);
								}
								entity.remove();
							}else{
								Version.particle(entity.getLocation(),"ENCHANTMENT_TABLE", 0.1F, 0.1F, 0.1F, 1, 5);
								//Version.a(entity.getWorld(),"enchantmenttable", entity.getLocation().getX(), entity.getLocation().getY(), entity.getLocation().getZ(), 5, 0.1D, 0.1D, 0.1D, 0);
							}
						}
					}.runTaskTimer(plugin, 10, 2);
	    	}
    	}else{
    		player.sendMessage("私のMP低すぎっ!?");
    	}
	}
	public void shootMagicWeapon3_2(final Player player,final Entity entity,final ItemStack item){
		List<String> list=new ArrayList<String>();
    	list=item.getItemMeta().getLore();
    	//MP消費
    	float hlv2=Float.valueOf(list.get(3).substring(2, 6));
    	int hlv=(int) hlv2;
    	if(hlv==0||player.getLevel()>=hlv){
    		player.setLevel(player.getLevel()-hlv);
    		Version.playSound(player.getLocation(),Sound.ENTITY_WITHER_SHOOT, 10F, 1F);
	    	String st=list.get(2);
	    	//クールタイム
	    	Float se=Float.valueOf(st.substring(2, 6));
	    	if(se>0F)
	    	new Bar(player,"["+item.getItemMeta().getDisplayName()+ChatColor.RESET+"]クールタイム",se);
	    	//発生確率
	    	String st2=list.get(4);
	    	Float i=Float.valueOf(st2.substring(2, 6));
	    	Random r = new Random();
	    	if((r.nextInt(100)+1)<=i){
	    			item.setDurability((short) (item.getDurability()+1));
					new BukkitRunnable(){
						@Override
						public void run() {
							if(entity.isDead()){
								this.cancel();
							}
							if(entity.getVelocity().getY() < -0.2D){
								Version.playeffect2(entity.getLocation(),"ENCHANTMENT_TABLE");
								World world = entity.getWorld();
								Location loc = entity.getLocation();
								Random r = new Random();
								Vector v = new Vector(0,0.3F,0);
								MetadataValue arrowmetadata = new FixedMetadataValue(plugin, "3");
					    		Version.playSound(entity.getLocation(),Sound.ENTITY_GENERIC_EXPLODE, 0.5F, 2F);
								for(int i=0;i<=80;i++){
									float speed = r.nextFloat()+0.6F;
									final Arrow arrow = world.spawnArrow(loc, v, speed, 300F);
									new BukkitRunnable(){
										@Override
										public void run() {
											arrow.remove();
										}
									}.runTaskLater(plugin, 200);
									arrow.setMetadata("MagicWeapon", arrowmetadata);
								}
								entity.remove();
							}else{
								//Version.a(entity.getWorld(),"enchantmenttable", entity.getLocation().getX(), entity.getLocation().getY(), entity.getLocation().getZ(), 5, 0.1D, 0.1D, 0.1D, 0);
								Version.particle(entity.getLocation(),"ENCHANTMENT_TABLE", 0.1F, 0.1F, 0.1F, 1, 5);
							}
						}
					}.runTaskTimer(plugin, 10, 2);
	    	}
    	}else{
    		player.sendMessage("私のMP低すぎっ!?");
    	}
	}
	public void shootParalysisBow(final Player player,final Entity arrow,final ItemStack bow){
		 List<String> list=bow.getItemMeta().getLore();
    	//発生確率
    	String st2=list.get(4);
    	Float i=Float.valueOf(st2.substring(2, 6));
		if(gacha(i)){
			Version.playeffect2(player.getLocation(),"ENCHANTMENT_TABLE");
			bow.setDurability((short) (bow.getDurability()+3));
			arrow.setMetadata("MagicWeapon", new FixedMetadataValue(plugin, "5"));
		}
	}
	private boolean gacha(float probability){
		float hash = (float) ((r.nextInt(10000)+1)*0.01);
		if(hash<=probability){
			return true;
		}else{
			return false;
		}
	}
	private void shootSleepBow(Player player, Entity arrow, ItemStack bow) {
		List<String> list=bow.getItemMeta().getLore();
    	//発生確率
    	String st2=list.get(4);
    	Float i=Float.valueOf(st2.substring(2, 6));
		if(gacha(i)){
			Version.playeffect2(player.getLocation(),"ENCHANTMENT_TABLE");
			bow.setDurability((short) (bow.getDurability()+3));
			arrow.setMetadata("MagicWeapon", new FixedMetadataValue(plugin, "8"));
		}
	}
}
