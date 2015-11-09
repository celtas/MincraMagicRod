package mincra.magicrod.listener;

import java.util.List;
import java.util.Random;

import mincra.magicrod.api.MagicApi;
import mincra.magicrod.api.MagicApi.MagicJob;
import mincra.magicrod.database.DatabaseManager;
import mincra.magicrod.item.MagicWeapon;
import mincra.magicrod.version.Version;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.plugin.java.JavaPlugin;

public class CraftListener implements Listener{

		public static JavaPlugin plugin;
		@SuppressWarnings("static-access")
		public CraftListener(JavaPlugin plugin) {
		    this.plugin = plugin;
		}

	    @EventHandler
	    public void onCraftItem(CraftItemEvent event){
	    	CraftingInventory inv = event.getInventory();
		       if(!inv.getItem(0).getItemMeta().hasLore())
		    	   return;
		       List<String> lore=inv.getItem(0).getItemMeta().getLore();
	    	   Player player = ((Player)event.getWhoClicked());
	    	   int user_id = DatabaseManager.getUserId(player.getUniqueId());
	    	   MagicJob job = DatabaseManager.getMagicJob(user_id);
	    	   switch(ChatColor.stripColor(lore.get(0))){
		    	   case "魔法武器番号:1":
		    	   case "魔法武器番号:4":
		    	   case "魔法武器番号:6":
		    	   case "魔法武器番号:7":
		    	   case "魔法武器番号:9":
		    		   if(job != MagicJob.KNIGHT){
		    			   event.setCancelled(true);
		    			   player.playSound(player.getLocation(),Sound.CLICK, 5F, 2F);
		    			   player.sendMessage(inv.getItem(0).getItemMeta().getDisplayName()+ChatColor.GRAY+"は戦士系のみ作ることができます.");
		    			   return;
		    		   }
		    		   break;
		    	   case "魔法武器番号:2":
		    	   case "魔法武器番号:3":
		    	   case "魔法武器番号:5":
		    	   case "魔法武器番号:8":
		    		   if(job != MagicJob.ARCHER){
		    			   event.setCancelled(true);
		    			   player.playSound(player.getLocation(),Sound.CLICK, 5F, 2F);
		    			   player.sendMessage(inv.getItem(0).getItemMeta().getDisplayName()+ChatColor.GRAY+"はアーチャー系のみ作ることができます.");
		    			   return;
		    		   }
		    		   break;
		    	   case "魔法アイテム番号:3":
		    	   case "ROD番号:5-1":
		    	   case "ROD番号:5-2":
		    	   case "ROD番号:5-3":
		    		   if(job != MagicJob.PRIEST){
		    			   event.setCancelled(true);
		    			   player.playSound(player.getLocation(),Sound.CLICK, 5F, 2F);
		    			   player.sendMessage(inv.getItem(0).getItemMeta().getDisplayName()+ChatColor.GRAY+"はプリースト系のみ作ることができます.");
		    			   return;
		    		   }
		    		   break;
		    	   case "ROD番号:3-1":
		    	   case "ROD番号:3-2":
		    	   case "ROD番号:3-3":
		    	   case "ROD番号:7-1":
		    	   case "ROD番号:7-2":
		    	   case "ROD番号:7-3":
		    	   case "ROD番号:7-4":
		    	   case "ROD番号:12-1":
		    		   if(job != MagicJob.MAGICIAN){
		    			   event.setCancelled(true);
		    			   player.playSound(player.getLocation(),Sound.CLICK, 5F, 2F);
		    			   player.sendMessage(inv.getItem(0).getItemMeta().getDisplayName()+ChatColor.GRAY+"は魔術師系のみ作ることができます.");
		    			   return;
		    		   }
	    	   }
		       switch(inv.getItem(0).getType()){
		       		case BOW:
		       			//魔法武器番号:2-2
		       			if(lore.get(0).equals(MagicWeapon.creeperBowLv2_Lores.get(0))){
		       				if(craftCreeperBowLv2(player,inv)) event.setCancelled(true);
		       			}else if(lore.get(0).equals(MagicWeapon.aiceBowLv1_Lores.get(0))){
		       				if(craftAiceBowLv1(player,inv)) event.setCancelled(true);
		       			}else if(lore.get(0).equals(MagicWeapon.aiceBowLv2_Lores.get(0))){
		       				if(craftAiceBowLv2(player,inv)) event.setCancelled(true);
		       			}
		       			break;
		       		case DIAMOND_SWORD:
		       			if(lore.get(0).equals(MagicWeapon.MechanicsSword_Lore.get(0))){
		       				if(MechanicsSword(player,inv)){
		       					event.setCancelled(true);
		       				}
		       			}else if(lore.get(0).equals(MagicWeapon.MechanicsSwordLv2_Lore.get(0))){
		       				if(MechanicsSwordLv2(player,inv)){
		       					event.setCancelled(true);
		       				}
		       			}
		       			break;
		       		default:
		       			break;
		       }
	    }
	//	"123",
	//	"456",
	//	"789");
	//メカニクスソード
	private boolean MechanicsSword(Player player, CraftingInventory inv) {
		if(MagicApi.equalsMagicNumber(inv.getItem(7), "魔法アイテム番号", "4")){
			Version.playSound(player.getLocation(),Sound.ANVIL_USE, 5F, 1.5F);
	    	return false;
		}
		player.playSound(player.getLocation(),Sound.CLICK, 5F, 2F);
		player.sendMessage(ChatColor.YELLOW+"魔法水晶が足りません。");
    	return true;
	}
	//メカニクスソードLv2
	private boolean MechanicsSwordLv2(Player player, CraftingInventory inv) {
		if(MagicApi.equalsMagicNumber(inv.getItem(5), "魔法武器番号", "6")){
			Version.playSound(player.getLocation(),Sound.ANVIL_USE, 5F, 1.5F);
			player.sendMessage(ChatColor.YELLOW+"メカニクスソードの強化に成功しました!!");
	    	return false;
		}
		player.playSound(player.getLocation(),Sound.CLICK, 5F, 2F);
		player.sendMessage(ChatColor.YELLOW+"メカニクスソードが足りません。");
    	return true;
	}

	//匠ノ弓lv2
    private Boolean craftCreeperBowLv2(Player player,CraftingInventory inv){
    	if(inv.getItem(5).getItemMeta().getLore()!=null){
			if((inv.getItem(5).getItemMeta().getLore().get(0).equals(MagicWeapon.creeperBowLv1_Lores.get(0)))){
				Version.playSound(player.getLocation(),Sound.ANVIL_USE, 5F, 1.5F);
				player.sendMessage(ChatColor.YELLOW+"匠ノ弓の強化に成功しました!!");
		    	return false;
			}
		}
    	player.playSound(player.getLocation(),Sound.CLICK, 5F, 2F);
		player.sendMessage(ChatColor.YELLOW+"匠ノ弓が必要です。");
    	return true;
    }
  //大氷樹の弓Lv1
    private Boolean craftAiceBowLv1(Player player,CraftingInventory inv){
    	if(MagicApi.equalsMagicNumber(inv.getItem(1), "魔法アイテム番号", "2")&&
    			MagicApi.equalsMagicNumber(inv.getItem(2), "魔法アイテム番号", "2")&&
    				MagicApi.equalsMagicNumber(inv.getItem(3), "魔法アイテム番号", "2")&&
    					MagicApi.equalsMagicNumber(inv.getItem(4), "魔法アイテム番号", "2")&&
    						MagicApi.equalsMagicNumber(inv.getItem(6), "魔法アイテム番号", "2")&&
    							MagicApi.equalsMagicNumber(inv.getItem(7), "魔法アイテム番号", "2")&&
    								MagicApi.equalsMagicNumber(inv.getItem(8), "魔法アイテム番号", "2")&&
    									MagicApi.equalsMagicNumber(inv.getItem(9), "魔法アイテム番号", "2")){
				Version.playSound(player.getLocation(),Sound.ANVIL_USE, 5F, 1.5F);
				player.sendMessage(ChatColor.AQUA+"大氷樹の弓の生成に成功しました!!");
		    	return false;
		}
    	player.playSound(player.getLocation(),Sound.CLICK, 5F, 2F);
		player.sendMessage(ChatColor.AQUA+"氷零が必要です。");
    	return true;
    }
	//大氷樹の弓Lv2
    private Boolean craftAiceBowLv2(Player player,CraftingInventory inv){
    	if(MagicApi.equalsMagicNumber(inv.getItem(1), "魔法アイテム番号", "2")&&
    			MagicApi.equalsMagicNumber(inv.getItem(3), "魔法アイテム番号", "2")&&
    				MagicApi.equalsMagicNumber(inv.getItem(7), "魔法アイテム番号", "2")&&
    					MagicApi.equalsMagicNumber(inv.getItem(9), "魔法アイテム番号", "2")){
    		if(MagicApi.equalsMagicNumber(inv.getItem(5), "魔法武器番号", "3")){
				Version.playSound(player.getLocation(),Sound.ANVIL_USE, 5F, 1.5F);
				player.sendMessage(ChatColor.AQUA+"大氷樹の弓の強化に成功しました!!");
		    	return false;
    		}else{
    			player.sendMessage(ChatColor.AQUA+"大氷樹の弓が必要です。");
    		}
		}else{
			player.sendMessage(ChatColor.AQUA+"氷零が必要です。");
		}
    	player.playSound(player.getLocation(),Sound.CLICK, 5F, 2F);
    	return true;
    }
	Boolean probability(float probability){
		float hash = (float) ((new Random().nextInt(10000)+1)*0.01);
		if(hash<=probability){
			return true;
		}else{
			return false;
		}
	}
}
