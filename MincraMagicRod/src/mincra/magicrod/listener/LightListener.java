package mincra.magicrod.listener;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;

public class LightListener implements Listener{
	public LightListener() {
	}
	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerBucketEmptyEvent(PlayerBucketEmptyEvent e){
		if(e.getPlayer().getWorld().getName().equals("world")){
			if(e.getBucket().equals(Material.LAVA_BUCKET)){
				e.getPlayer().sendMessage(ChatColor.GRAY+"50M以上には光源を設置できません.");
				e.setCancelled(true);
			}
		}
	}
	@EventHandler(priority = EventPriority.LOWEST)
	public void onBlockPlaceEvent(BlockPlaceEvent e) {
		if(e.getPlayer().getWorld().getName().equals("world")){
		//Location loc = e.getBlock().getLocation();
		if(e.getBlock().getY()>=50){
			switch(e.getBlock().getType()){
				case TORCH:
					e.getPlayer().sendMessage(ChatColor.GRAY+"50M以上には光源を設置できません.");
					e.setCancelled(true);
					break;
				case GLOWSTONE:
					e.getPlayer().sendMessage(ChatColor.GRAY+"50M以上には光源を設置できません.");
					e.setCancelled(true);
					break;
				case JACK_O_LANTERN:
					e.getPlayer().sendMessage(ChatColor.GRAY+"50M以上には光源を設置できません.");
					e.setCancelled(true);
					break;
				case REDSTONE_TORCH_ON:
					e.getPlayer().sendMessage(ChatColor.GRAY+"50M以上には光源を設置できません.");
					e.setCancelled(true);
					break;
				case REDSTONE_LAMP_OFF:
					e.getPlayer().sendMessage(ChatColor.GRAY+"50M以上には光源を設置できません.");
					e.setCancelled(true);
					break;
				case SEA_LANTERN:
					e.getPlayer().sendMessage(ChatColor.GRAY+"50M以上には光源を設置できません.");
					e.setCancelled(true);
					break;
				case LAVA:
					e.getPlayer().sendMessage(ChatColor.GRAY+"50M以上には光源を設置できません.");
					e.setCancelled(true);
					break;
				default:
					break;
			}
			}
		}
	}
}
