package mincra.magicrod.listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class AnvilListener implements Listener{

	    public AnvilListener() {
	    }
	    @EventHandler
	    public void onInventoryClickEvent(InventoryClickEvent e){
	    	if(e.getInventory().getType().equals(InventoryType.ANVIL)){
	    		switch(e.getAction()){
				case MOVE_TO_OTHER_INVENTORY:
				case PICKUP_ALL:
				case PICKUP_HALF:
				case PICKUP_ONE:
				case PICKUP_SOME:
	    			if(e.getCurrentItem().getItemMeta().hasLore()){
	    				String lore = e.getCurrentItem().getItemMeta().getLore().get(0);
	    				lore = ChatColor.stripColor(lore);
	    				int num = lore.indexOf(":");
	    				if(num!=-1){
	    					Player player;
		    				switch(lore.substring(0, num)){
		    					case"魔法武器番号":
		    					case"魔法アイテム番号":
		    					case"ROD番号":
		    						player = ((Player)e.getWhoClicked());
		    						player.sendMessage(ChatColor.YELLOW+""+ChatColor.BOLD+"魔法の修理はできません.");
		    						e.setCancelled(true);
		    						break;
		    					default:
		    						break;
		    				}
	    				}
    				}
					break;
				default:
					break;
	    		}
	    	}
	    }
}
