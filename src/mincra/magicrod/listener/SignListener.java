  package mincra.magicrod.listener;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignListener implements Listener {
	@EventHandler(priority = EventPriority.LOWEST)
	public void onSignChangeEvent(SignChangeEvent e){
		Chunk chunk = e.getBlock().getLocation().getChunk();
		List<Chunk> chunklist = new ArrayList<Chunk>();
		chunklist.add(chunk.getWorld().getChunkAt(chunk.getX()+1,chunk.getZ()-1));
		chunklist.add(chunk.getWorld().getChunkAt(chunk.getX()+1,chunk.getZ()));
		chunklist.add(chunk.getWorld().getChunkAt(chunk.getX()+1,chunk.getZ()+1));
		chunklist.add(chunk.getWorld().getChunkAt(chunk.getX(),chunk.getZ()+1));
		chunklist.add(chunk);
		chunklist.add(chunk.getWorld().getChunkAt(chunk.getX(),chunk.getZ()-1));
		chunklist.add(chunk.getWorld().getChunkAt(chunk.getX()-1,chunk.getZ()+1));
		chunklist.add(chunk.getWorld().getChunkAt(chunk.getX()-1,chunk.getZ()));
		chunklist.add(chunk.getWorld().getChunkAt(chunk.getX()-1,chunk.getZ()-1));
		int count = 0;
		
		for(Chunk c:chunklist){
			for (int x = 0; x < 16; x++) {
	            for (int y = 0; y < 256; y++) {
	                for (int z = 0; z < 16; z++) {
	                    switch(c.getBlock(x,y,z).getType()){
	                    	case SIGN:
	                    		count++;
	                    		break;
	                    	case WALL_SIGN:
	                    		count++;
	                    		break;
							default:
								break;
	                    }
	                }
	            }
			}
		}
		if(count>81){
			e.getPlayer().sendMessage(ChatColor.GRAY+"近くに看板が密集しているため削除しました.");
			e.setCancelled(true);
			e.getBlock().setType(Material.AIR);
		}
	}
}
