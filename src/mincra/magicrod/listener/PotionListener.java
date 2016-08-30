package mincra.magicrod.listener;

import mincra.magicrod.bar.Bar;
import mincra.magicrod.item.MagicItem;
import mincra.magicrod.version.Version;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.inventivetalent.bossbar.BossBarAPI;

import java.util.List;


public class PotionListener implements Listener {
	
	public static JavaPlugin plugin;
	@SuppressWarnings("static-access")
	public PotionListener(JavaPlugin plugin) {
	    this.plugin = plugin;
	}
	
	public void glassbottle(ItemStack item){
		item.setType(Material.GLASS_BOTTLE);
		item.setDurability((short)0);
		ItemMeta itemmeta=null;
		item.setItemMeta(itemmeta);
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void PlayerItemConsumeEvent(PlayerItemConsumeEvent event){
		if(event.getItem().getType().equals(Material.POTION)){
			if(!event.getPlayer().getWorld().getName().equals("athletics")){
					Player player = event.getPlayer();
					List<String> lores = event.getItem().getItemMeta().getLore();
					if(lores!=null){
							ItemStack item = event.getPlayer().getItemInHand();
							switch(item.getData().getData()){
								case (byte) 8228:
										if(MagicItem.lores1.get(0).equals(lores.get(0))){
											if(BossBarAPI.hasBar(player)==false){
												event.setCancelled(true);
												//MP消費
										    	float hlv2=Float.valueOf(lores.get(3).substring(2, 6));
										    	int hlv=(int) hlv2;
										    	if(player.getLevel()>=hlv||hlv==0){
										    		player.setLevel(player.getLevel()-hlv);
													String st=lores.get(2);
											    	//クールタイム
											    	Float se=Float.valueOf(st.substring(2, 6));
											    	new Bar(player,"["+item.getItemMeta().getDisplayName()+"]クールタイム",se);
													glassbottle(item);
													event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST,20*30,4));
													event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.HEAL,1,3));
													Version.playSound(player.getLocation(),Sound.ENTITY_PLAYER_LEVELUP, 0.2F, 1F);
										    	}else{
										    		player.sendMessage("私のMP低すぎっ!?");
										    		event.setCancelled(true);
										    	}
											}else{
												event.setCancelled(true);
											}
										}
									break;
								default:
									break;

					}
				}
			}
		}
	}
}
