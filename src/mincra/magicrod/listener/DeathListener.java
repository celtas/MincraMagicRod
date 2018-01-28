package mincra.magicrod.listener;

import java.sql.Timestamp;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.inventivetalent.bossbar.BossBarAPI;

import mincra.magicrod.bar.Bar;
import mincra.magicrod.item.MagicItem;
import mincra.magicrod.version.Version;


public class DeathListener implements Listener {
	public static HashMap<String,Timestamp> resurrectionPlayers = new HashMap<String,Timestamp>();
	public static JavaPlugin plugin;
	@SuppressWarnings("static-access")
	public DeathListener(JavaPlugin plugin) {
	    this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.LOWEST)
    public void PlayerDamageEvent(EntityDamageByEntityEvent e) {
        if(e.getEntity() instanceof Player) {
            Player player = (Player) e.getEntity();
            /*for(Entry<String, Timestamp> e2:DeathListener.resurrectionPlayers.entrySet()){
    			player.sendMessage(e2.getKey()+":"+e2.getValue().toString());
    			player.sendMessage(e2.getKey()+"-GMT:"+e2.getValue().toGMTString());
    		}*/
            if((player.getHealth()-e.getDamage()) <= 0) {
	            if(resurrectionPlayers.containsKey(player.getName())){
					e.setCancelled(true);
					player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()/2);
					player.setNoDamageTicks(100);
					Version.playeffect(player.getLocation(), "FIREWORKS_SPARK");
					Version.effect.playSound(player.getLocation(), Sound.ENTITY_ZOMBIE_INFECT, 1F, 1F);
					player.sendMessage(ChatColor.GRAY+"リザレクションにより復活しました.");
					resurrectionPlayers.remove(player.getName());
				}else{
	    				Inventory inv = player.getInventory();
	    				if(inv.containsAtLeast(MagicItem.phoenixDown, 1)){
	    					ItemStack pd = MagicItem.phoenixDown;
	    					pd.setAmount(1);
	    					inv.removeItem(pd);
	                        e.setCancelled(true);
	                        player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
	                        Version.playeffect(player.getLocation(), "FIREWORKS_SPARK");
	                        player.setNoDamageTicks(100);
	                        Version.effect.playSound(player.getLocation(), Sound.ENTITY_ZOMBIE_INFECT, 1F, 1F);
	                        player.sendMessage(ChatColor.GRAY+"フェニックスの尾を使用しました。");
	                        BossBarAPI.removeBar(player);
	                        new Bar(player,"["+MagicItem.phoenixDown.getItemMeta().getDisplayName()+ChatColor.RESET+"]クールタイム",5);
	                }
				}
            }
        }
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onEntityDamageEvent(EntityDamageEvent e) {
        if(e.getEntity() instanceof Player) {
            Player player = (Player) e.getEntity();
            /*if(e.getCause() == DamageCause.VOID){
        		e.setCancelled(true);
				player.setHealth(player.getMaxHealth());
				player.setFallDistance(0);
				player.sendMessage(ChatColor.GRAY+"奈落に落下したためspawn地点に戻ります.");
				Util.debug(ChatColor.YELLOW, player.getName()+"は奈落に落下したためspawn地点に移動します.");
				Util.debug(ChatColor.YELLOW, player.getLocation().toString());
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "central "+player.getName());
        		return;
        	} 9/11 該当箇所をコメントアウト。死亡がキャンセルされるのみで永遠に奈落に堕ち続ける。エンド死亡時のこともあるのでコメントアウト。*/
			if(!(player.getWorld().getName().equalsIgnoreCase("athletic"))){
                if((player.getHealth()-e.getDamage()) <= 0) {
                	if(resurrectionPlayers.containsKey(player.getName())){
    					e.setCancelled(true);
    					player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()/2);
    					player.setNoDamageTicks(100);
    					Version.playeffect(player.getLocation(), "FIREWORKS_SPARK");
    					Version.effect.playSound(player.getLocation(), Sound.ENTITY_ZOMBIE_INFECT, 1F, 1F);
    					player.sendMessage(ChatColor.GRAY+"リザレクションにより復活しました.");
    					resurrectionPlayers.remove(player.getName());
    					return;
    				}else{
	    				Inventory inv = player.getInventory();
	    				if(inv.containsAtLeast(MagicItem.phoenixDown, 1)){
	    					ItemStack pd = MagicItem.phoenixDown;
	    					pd.setAmount(1);
	    					inv.removeItem(pd);
	                        e.setCancelled(true);
	                        player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
	                        Version.playeffect(player.getLocation(), "FIREWORKS_SPARK");
	                        player.setNoDamageTicks(100);
	                        Version.effect.playSound(player.getLocation(), Sound.ENTITY_ZOMBIE_INFECT, 1F, 1F);
	                        player.sendMessage(ChatColor.GRAY+"フェニックスの尾を使用しました。");
	                        BossBarAPI.removeBar(player);
	                        new Bar(player,"["+MagicItem.phoenixDown.getItemMeta().getDisplayName()+ChatColor.RESET+"]クールタイム",5);
	                        return;
	    				}
    				}
                }
			}
        }
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onEntityDamageByBlockEvent(EntityDamageByBlockEvent e) {
        if(e.getEntity() instanceof Player) {
            Player player = (Player) e.getEntity();
			if(!(player.getWorld().getName().equalsIgnoreCase("athletic"))){
                if((player.getHealth()-e.getDamage()) <= 0) {
    				Inventory inv = player.getInventory();
    				if(inv.containsAtLeast(MagicItem.phoenixDown, 1)){
    					ItemStack pd = MagicItem.phoenixDown;
    					pd.setAmount(1);
    					inv.removeItem(pd);
                        e.setCancelled(true);
                        player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
                        Version.playeffect(player.getLocation(), "FIREWORKS_SPARK");
                        player.setNoDamageTicks(100);
                        Version.effect.playSound(player.getLocation(), Sound.ENTITY_ZOMBIE_INFECT, 1F, 1F);
                        player.sendMessage(ChatColor.GRAY+"フェニックスの尾を使用しました。");
                        BossBarAPI.removeBar(player);
                        new Bar(player,"["+MagicItem.phoenixDown.getItemMeta().getDisplayName()+ChatColor.RESET+"]クールタイム",5);
    				}
                }
			}
        }
	}

}
