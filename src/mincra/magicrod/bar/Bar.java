package mincra.magicrod.bar;

import mincra.magicrod.main.Magic;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.inventivetalent.bossbar.BossBarAPI;

public class Bar extends BukkitRunnable {
    float bairitu;
    Player player;
    String message;
    float setPer = 100f;
    float se;
    float subtract = 0.05f;
    
    public Bar(Player player, String message, float cooltime_seconds) {
        this.player = player;
        this.message = message;
        this.se = cooltime_seconds;
        this.bairitu = 100F / cooltime_seconds;
        BossBarAPI.setMessage(this.player, this.message, 100f, -1, 0f);
        
        if(cooltime_seconds<=10){
        	runTaskTimer(Magic.plugin, 1, 1);
        }else{
	        int interval = (((int)cooltime_seconds)/5)-1;
	        subtract *=interval;
	    	runTaskTimer(Magic.plugin, interval, interval);
        }
    }
    public void run() {
        se -= subtract;
        if(se <= 0F)
        {
            BossBarAPI.removeBar(player);
            cancel();
        }
        BossBarAPI.setHealth(player, se * bairitu);
        //BossBarAPI.setMessage(player, message, se * bairitu);
    }
}
