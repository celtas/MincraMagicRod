package mincra.magicrod.listener;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;

import mincra.magicrod.main.Magic;

public class SoundPacketListener {
	Magic plugin;
	public SoundPacketListener(Magic plugin){
		this.plugin = plugin;
		startSoundPacketListener();
	}

	public void startSoundPacketListener() {
		ProtocolLibrary.getProtocolManager().addPacketListener(
        new PacketAdapter(plugin, ListenerPriority.NORMAL,PacketType.Play.Server.NAMED_SOUND_EFFECT) {
            @Override
            public void onPacketSending(PacketEvent event) {
                if (event.getPacketType() == PacketType.Play.Server.NAMED_SOUND_EFFECT){
                	if(event.getPacket().getFloat().read(0) >= 2.0F){
                		float volume = event.getPacket().getFloat().read(0);
                		String name = event.getPacket().getStrings().read(0);
                		if(volume == 2.0F && name.equals("entity.generic.explode")){
                			event.setCancelled(true);
                		}else if(volume > 8192F && name.equals("entity.lightning.thunder")){
                			event.setCancelled(true);
                		}

                	}
                }
            }
        });
    }
}
