package mincra.magicrod.listener;

import java.util.Random;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class GrassDropListener implements Listener{
	Random r = new Random();
	@EventHandler
    /*public void onBlockBreakEvent(BlockBreakEvent e) {
		if(e.getBlock().getWorld().getName().equalsIgnoreCase("world")){
	        if(e.getBlock().getType() == Material.LONG_GRASS || e.getBlock().getType() == Material.DOUBLE_PLANT) {
	        	if(gacha(1f)){
	        		e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), MagicItem.magicWeed);
	        		return;
	        	}
	        }
		}
    }*/
	boolean gacha(float probability){
		float hash = (float) ((r.nextInt(10000)+1)*0.01);
		if(hash<=probability){
			return true;
		}else{
			return false;
		}
	}
}
