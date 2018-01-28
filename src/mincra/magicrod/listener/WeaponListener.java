package mincra.magicrod.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.inventivetalent.bossbar.BossBarAPI;

import mincra.magicrod.bar.Bar;
import mincra.magicrod.version.Version;

public class WeaponListener implements Listener{
	private Random r = new Random();
	private JavaPlugin plugin;
	public WeaponListener(JavaPlugin plugin) {
	    this.plugin = plugin;
	}

	@EventHandler
	public void EntityDamageByEntityEvent(EntityDamageByEntityEvent event){
		if(event.getCause().equals(DamageCause.ENTITY_ATTACK)&&
				event.getDamager() instanceof Player&&event.getEntity() instanceof Monster){
			Player player=(Player) event.getDamager();
			ItemStack item=player.getInventory().getItemInMainHand();
			if(item==null)
				return;
			if(!item.getItemMeta().hasLore())
				return;
			String lore = item.getItemMeta().getLore().get(0);
			lore = ChatColor.stripColor(lore);
			int num = lore.indexOf(":");
			if(num!=-1){
				switch(lore.substring(0, num)){
				case"魔法武器番号":
					if(BossBarAPI.hasBar(player)==false){
						//1~7まで存在.
						switch(lore.substring(lore.indexOf(":")+1)){
							case"1":
								Version.particle(event.getEntity().getLocation().add(0, ((LivingEntity) event.getEntity()).getEyeHeight(), 0),"SPELL_INSTANT", 0.2F, 0.2F, 0.2F, 1, 25);
								//Version.a(event.getEntity().getWorld(),"iconcrack_372_0", event.getEntity().getLocation().getX(), event.getEntity().getLocation().getY()+1.6, event.getEntity().getLocation().getZ(), 100, 0.2D, 0.4D, 0.2D, 0);
								weapon101(player,item,event.getEntity(),event.getDamage());
								break;
							case"4":
								paralysis(player,item,event.getEntity());
								break;
							case"6":
								mechanics(player,item,event.getEntity());
								break;
							case"7":
								sleep(player,item,(LivingEntity) event.getEntity());
								break;
							case"9":
								mechanicsLv2(player,item,event.getEntity());
								break;
							default:
								break;
						}
					}
					break;
				default:
					break;
				}
			}
		}
	}
	private boolean sleep(Player player,ItemStack item, final LivingEntity le) {
		if(!le.isDead()){
			List<String> list=new ArrayList<String>();
	    	list=item.getItemMeta().getLore();
	    	//発生確率
	    	String st2=list.get(4);
	    	Float i=Float.valueOf(st2.substring(2, 6));
			if(gacha(i)){

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
		return false;
	}
	private boolean paralysis(Player player,ItemStack item, final Entity entity) {
		if(!entity.isDead()){
			List<String> list=new ArrayList<String>();
	    	list=item.getItemMeta().getLore();
	    	//発生確率
	    	String st2=list.get(4);
	    	Float i=Float.valueOf(st2.substring(2, 6));
			if(gacha(i)){
				final double speed = Version.getSpeed((LivingEntity) entity);
				if(speed>0){
					Version.setStats((LivingEntity) entity, 0F);
					item.setDurability((short) (item.getDurability()+1));
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
					}.runTaskTimer(plugin, 0, 20);
				}

			}
		}
		return false;
	}

	private boolean mechanics(Player player,ItemStack item, Entity entity) {
		if(!entity.isDead()){
			if(player.getFallDistance()>0.8F){
				item.setDurability((short) (item.getDurability()+2));
				((Damageable) entity).damage(player.getFallDistance()*5);
				player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ZOMBIE_INFECT, 0.3F, 1.75F);
				Version.particle(entity.getLocation().add(0, ((LivingEntity) entity).getEyeHeight(), 0),"SPELL_INSTANT", 0.02F, -0.2F, 0.02F, 1,(int) (player.getFallDistance()*30));
				return true;
			}
		}
		return false;
	}

	private boolean mechanicsLv2(Player player,ItemStack item, Entity entity) {
		if(!entity.isDead()){
			if(player.getFallDistance()>0.8F){
				item.setDurability((short) (item.getDurability()+2));
				((Damageable) entity).damage(player.getFallDistance()*10);
				player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ZOMBIE_INFECT, 1F, 1.25F);
				Version.particle(entity.getLocation().add(0, ((LivingEntity) entity).getEyeHeight(), 0),"SPELL_INSTANT", 0.02F, -0.2F, 0.02F, 1,(int) (player.getFallDistance()*30));
				return true;
			}
		}
		return false;
	}

	private boolean weapon101(Player player,ItemStack item,Entity entity, double damage){
		List<String> list=new ArrayList<String>();
    	list=item.getItemMeta().getLore();
    	//MP消費
    	float hlv2=Float.valueOf(list.get(3).substring(2, 6));
    	int hlv=(int) hlv2;
    	if(hlv==0||player.getLevel()>=hlv){
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
	    		player.setLevel(player.getLevel()-hlv);
		    	Damageable d = (Damageable) player;
		    	int health=(int) (d.getHealth()+damage*1.25);
		    	if(health>player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue())health=(int) player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
		    	player.setHealth(health);
		    	Version.playeffect(player.getLocation(),"VILLAGER_HAPPY");
		    	Version.playSound(player.getLocation(),Sound.ENTITY_ZOMBIE_INFECT, 3F, 1F);
		    	item.setDurability((short) (item.getDurability()+1));
		    	//((CraftWorld) player.getWorld()).getHandle().a("heart", player.getLocation().getX(), player.getLocation().getY()+2.2, player.getLocation().getZ(), 10, 0.42D, 0.42D, 0.42D, 0);
		    	return true;
	    	}
	    	return false;
    	}else{
    		player.sendMessage("私のMP低すぎっ!?");
    		return false;
    	}
	}
	Boolean gacha(float probability){
		float hash = (float) ((r.nextInt(10000)+1)*0.01);
		if(hash<=probability){
			return true;
		}else{
			return false;
		}
	}
}
