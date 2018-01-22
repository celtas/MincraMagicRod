package mincra.magicrod.version.v_1_10_R1;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_10_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import mincra.magicrod.version.EffectInterface;
import net.minecraft.server.v1_10_R1.EntityInsentient;
import net.minecraft.server.v1_10_R1.EnumParticle;
import net.minecraft.server.v1_10_R1.GenericAttributes;
import net.minecraft.server.v1_10_R1.PacketPlayOutWorldParticles;

public class Effect implements EffectInterface {
	public Effect(){


	}

	public void playeffect(Location loc,String _particle){
		EnumParticle particle = EnumParticle.valueOf(_particle);
		float kakeru=1.6F;
		float kakeru2=2F;
		float p=0.2F;
		//BukkitTask task=new Magicsquare(player,s,kakeru,kakeru2,kakeru3,p,loc).runTaskLater(this,40);
		//BukkitTask task2=new Magicsquare2(player,s,kakeru,kakeru2,kakeru3,p,loc).runTaskLater(this,40);
		//BukkitTask task3=new Magicsquare3(player,s,kakeru,kakeru2,kakeru3,p,loc).runTaskLater(this,40);
		for(float i=0;i<360;i=i+2F){
			((CraftWorld) loc.getWorld()).getHandle().a(particle, loc.getX()+Math.sin(Math.toRadians(i))*kakeru, loc.getY()+p, loc.getZ()+Math.cos(Math.toRadians(i))*kakeru, 1, 0, 0, 0, 0);
		}
		/*for(float i=0;i<360;i=i+0.5F){
			((CraftWorld) player.getWorld()).getHandle().a("enchantmenttable", loc.getX()+Math.sin(Math.toRadians(i))*kakeru3, loc.getY()+p, loc.getZ()+Math.cos(Math.toRadians(i))*kakeru3, 1, 0, 0, 0, 0);
		}*/
		for(float i=0;i<360;i=i+2F){
			((CraftWorld) loc.getWorld()).getHandle().a(particle, loc.getX()+Math.sin(Math.toRadians(i))*kakeru2, loc.getY()+p, loc.getZ()+Math.cos(Math.toRadians(i))*kakeru2, 1, 0, 0, 0, 0);
		}
		//1画
		float y=0.309F;
		for(float x=-0.95F;x<0.95F;x=x+0.05F){
			((CraftWorld) loc.getWorld()).getHandle().a(particle, loc.getX()+x*kakeru, loc.getY()+p, loc.getZ()+y*kakeru, 1, 0, 0, 0, 0);
		}
		//2画
		y=0.309F;
		for(float x=0.95F;x>-0.58F;x=x-0.05F){
			((CraftWorld) loc.getWorld()).getHandle().a(particle, loc.getX()+x*kakeru, loc.getY()+p, loc.getZ()+y*kakeru, 1, 0, 0, 0, 0);
			y=y-0.0360F;
		}
		//3画
		y=-0.809F;
		for(float x=-0.58F;x<0F;x=x+0.01F){
			((CraftWorld) loc.getWorld()).getHandle().a(particle, loc.getX()+x*kakeru, loc.getY()+p, loc.getZ()+y*kakeru, 1, 0, 0, 0, 0);
			y=y+0.03007F;
		}
		//4画
		y=1F;
		for(float x=0F;x<0.58F;x=x+0.01F){
			((CraftWorld) loc.getWorld()).getHandle().a(particle, loc.getX()+x*kakeru, loc.getY()+p, loc.getZ()+y*kakeru, 1, 0, 0, 0, 0);
			y=y-0.03007F;
		}
		//5画
		y=-0.809F;
		for(float x=0.5877F;x>-0.951F;x=x-0.05F){
			((CraftWorld) loc.getWorld()).getHandle().a(particle, loc.getX()+x*kakeru, loc.getY()+p, loc.getZ()+y*kakeru, 1, 0, 0, 0, 0);
			y=y+0.0360F;
		}
		//以下から5角形の描画
		//1画
		y=1F;
		for(float x=0F;x<0.95F;x=x+0.05F){
			((CraftWorld) loc.getWorld()).getHandle().a(particle, loc.getX()+x*kakeru, loc.getY()+p, loc.getZ()+y*kakeru, 1, 0, 0, 0, 0);
			y=y-0.0360F;
		}
		//2画
		y=0.309F;
		for(float x=0.95F;x>0.58F;x=x-0.01F){
			((CraftWorld) loc.getWorld()).getHandle().a(particle, loc.getX()+x*kakeru, loc.getY()+p, loc.getZ()+y*kakeru, 1, 0, 0, 0, 0);
			y=y-0.03007F;
		}
		//3画
		y=-0.809F;
		for(float x=0.58F;x>-0.58F;x=x-0.05F){
			((CraftWorld) loc.getWorld()).getHandle().a(particle, loc.getX()+x*kakeru, loc.getY()+p, loc.getZ()+y*kakeru, 1, 0, 0, 0, 0);
		}
		//4画
		y=-0.809F;
		for(float x=-0.58F;x>-0.95F;x=x-0.01F){
			((CraftWorld) loc.getWorld()).getHandle().a(particle, loc.getX()+x*kakeru, loc.getY()+p, loc.getZ()+y*kakeru, 1, 0, 0, 0, 0);
			y=y+0.03007F;
		}
		//5画
		y=0.309F;
		for(float x=-0.95F;x<0F;x=x+0.05F){
			((CraftWorld) loc.getWorld()).getHandle().a(particle, loc.getX()+x*kakeru, loc.getY()+p, loc.getZ()+y*kakeru, 1, 0, 0, 0, 0);
			y=y+0.0360F;
		}
	}
	public void playeffect2(Location loc,String _particle){
		EnumParticle enumparticle = EnumParticle.valueOf(_particle);
		ArrayList<PacketPlayOutWorldParticles> particlelist = new ArrayList<PacketPlayOutWorldParticles>();
		float kakeru=1.6F;
		float p=0.2F;
		//BukkitTask task=new Magicsquare(player,particle,kakeru,kakeru2,kakeru3,p,loc).runTaskLater(thiparticle,40);
		//BukkitTask task2=new Magicsquare2(player,particle,kakeru,kakeru2,kakeru3,p,loc).runTaskLater(thiparticle,40);
		//BukkitTask task3=new Magicsquare3(player,particle,kakeru,kakeru2,kakeru3,p,loc).runTaskLater(thiparticle,40);
		for(float i=0;i<360;i=i+2F){
			//particlelist.add(new PacketPlayOutWorldParticles(enumparticle,true,,0,0,0,0,1));
			particlelist.add(new PacketPlayOutWorldParticles(enumparticle,true,(float) (loc.getX()+Math.sin(Math.toRadians(i))*kakeru),(float) (loc.getY()+p),(float) (loc.getZ()+Math.cos(Math.toRadians(i))*kakeru),0,0,0,0,1));
		}
		/*for(float i=0;i<360;i=i+0.5F){
			((CraftWorld) loc.getWorld()).getHandle().a("enchantmenttable", loc.getX()+Math.sin(Math.toRadians(i))*kakeru3, loc.getY()+p, loc.getZ()+Math.cos(Math.toRadians(i))*kakeru3, 1, 0, 0, 0, 0);
		}*/
		//1画
		float y=0.309F;
		for(float x=-0.95F;x<0.95F;x=x+0.05F){
			((CraftWorld) loc.getWorld()).getHandle().a(enumparticle, loc.getX()+x*kakeru, loc.getY()+p, loc.getZ()+y*kakeru, 1, 0, 0, 0, 0);
		}
		//2画
		y=0.309F;
		for(float x=0.95F;x>-0.58F;x=x-0.05F){
			((CraftWorld) loc.getWorld()).getHandle().a(enumparticle, loc.getX()+x*kakeru, loc.getY()+p, loc.getZ()+y*kakeru, 1, 0, 0, 0, 0);
			y=y-0.0360F;
		}
		//3画
		y=-0.809F;
		for(float x=-0.58F;x<0F;x=x+0.01F){
			((CraftWorld) loc.getWorld()).getHandle().a(enumparticle, loc.getX()+x*kakeru, loc.getY()+p, loc.getZ()+y*kakeru, 1, 0, 0, 0, 0);
			y=y+0.03007F;
		}
		//4画
		y=1F;
		for(float x=0F;x<0.58F;x=x+0.01F){
			((CraftWorld) loc.getWorld()).getHandle().a(enumparticle, loc.getX()+x*kakeru, loc.getY()+p, loc.getZ()+y*kakeru, 1, 0, 0, 0, 0);
			y=y-0.03007F;
		}
		//5画
		y=-0.809F;
		for(float x=0.5877F;x>-0.951F;x=x-0.05F){
			((CraftWorld) loc.getWorld()).getHandle().a(enumparticle, loc.getX()+x*kakeru, loc.getY()+p, loc.getZ()+y*kakeru, 1, 0, 0, 0, 0);
			y=y+0.0360F;
		}
		//以下から5角形の描画
		//1画
		y=1F;
		for(float x=0F;x<0.95F;x=x+0.05F){
			((CraftWorld) loc.getWorld()).getHandle().a(enumparticle, loc.getX()+x*kakeru, loc.getY()+p, loc.getZ()+y*kakeru, 1, 0, 0, 0, 0);
			y=y-0.0360F;
		}
		//2画
		y=0.309F;
		for(float x=0.95F;x>0.58F;x=x-0.01F){
			((CraftWorld) loc.getWorld()).getHandle().a(enumparticle, loc.getX()+x*kakeru, loc.getY()+p, loc.getZ()+y*kakeru, 1, 0, 0, 0, 0);
			y=y-0.03007F;
		}
		//3画
		y=-0.809F;
		for(float x=0.58F;x>-0.58F;x=x-0.05F){
			((CraftWorld) loc.getWorld()).getHandle().a(enumparticle, loc.getX()+x*kakeru, loc.getY()+p, loc.getZ()+y*kakeru, 1, 0, 0, 0, 0);
		}
		//4画
		y=-0.809F;
		for(float x=-0.58F;x>-0.95F;x=x-0.01F){
			((CraftWorld) loc.getWorld()).getHandle().a(enumparticle, loc.getX()+x*kakeru, loc.getY()+p, loc.getZ()+y*kakeru, 1, 0, 0, 0, 0);
			y=y+0.03007F;
		}
		//5画
		y=0.309F;
		for(float x=-0.95F;x<0F;x=x+0.05F){
			((CraftWorld) loc.getWorld()).getHandle().a(enumparticle, loc.getX()+x*kakeru, loc.getY()+p, loc.getZ()+y*kakeru, 1, 0, 0, 0, 0);
			y=y+0.0360F;
		}
	    for(Player player : loc.getWorld().getPlayers()) {
	    	if(loc.getWorld()==player.getWorld()&&loc.distance(player.getLocation())<8){
	    		for(PacketPlayOutWorldParticles particle:particlelist){
	    			((CraftPlayer) player).getHandle().playerConnection.sendPacket(particle);
	    		}
	    	}
	    }
	    particlelist.clear();
	}
	public void playeffect3(Location loc,float eyeheight,String string){
		EnumParticle enumparticle = EnumParticle.valueOf(string);
		float yaw = loc.getYaw();
		if(yaw<0){
			yaw=360+yaw;
		}
		float kakeru=1.9F;
		loc.add(new Vector(-1*Math.sin(yaw*Math.PI / 180.0)*3,eyeheight,Math.cos(yaw*Math.PI / 180.0)*3));
		ArrayList<PacketPlayOutWorldParticles> particlelist = new ArrayList<PacketPlayOutWorldParticles>();
		for(float i=0;i<360;i=i+2F){
			particlelist.add(new PacketPlayOutWorldParticles(enumparticle ,true ,(float) (loc.getX()+(Math.sin(Math.toRadians(i))*Math.cos(Math.toRadians(yaw))*kakeru)),(float) (loc.getY()+Math.cos(Math.toRadians(i))*kakeru),(float) (loc.getZ()+Math.sin(Math.toRadians(i))*Math.sin(Math.toRadians(yaw))*kakeru), 0, 0, 0, 0, 1));
		}
		//1画
		for(float i=-0.95F;i<0.95F;i=i+0.05F){
			particlelist.add(new PacketPlayOutWorldParticles(enumparticle ,true ,(float) (loc.getX()+i*kakeru*Math.cos(Math.toRadians(yaw))),(float) (loc.getY()+0.309),(float) (loc.getZ()+i*kakeru*Math.sin(Math.toRadians(yaw))), 0, 0, 0, 0, 1));
		}
		//2画
		for(float i=-0.5877F;i<0.951F;i=i+0.05F){
			particlelist.add(new PacketPlayOutWorldParticles(enumparticle,true,(float) (loc.getX()+i*kakeru*Math.cos(Math.toRadians(yaw))),(float) (loc.getY()+0.7266*i-0.4763),(float) (loc.getZ()+i*kakeru*Math.sin(Math.toRadians(yaw))),0,0,0,0,1));
		}
		//3画
		for(float i=-0.5877F;i<0.5877F;i=i+0.025F){
			particlelist.add(new PacketPlayOutWorldParticles(enumparticle,true,(float) (loc.getX()+i*kakeru*Math.cos(Math.toRadians(yaw))),(float) (loc.getY()-0.809),(float) (loc.getZ()+i*kakeru*Math.sin(Math.toRadians(yaw))),0,0,0,0,1));
		}
		//4画
		for(float i=0.5877F;i<0.951F;i=i+0.01F){
			particlelist.add(new PacketPlayOutWorldParticles(enumparticle,true,(float) (loc.getX()+i*kakeru*Math.cos(Math.toRadians(yaw))),(float) (loc.getY()+3.0773*i-2.618),(float) (loc.getZ()+i*kakeru*Math.sin(Math.toRadians(yaw))),0,0,0,0,1));
		}
		//5画
		for(float i=0F;i<0.951F;i=i+0.05F){
			particlelist.add(new PacketPlayOutWorldParticles(enumparticle,true,(float) (loc.getX()+i*kakeru*Math.cos(Math.toRadians(yaw))),(float) (loc.getY()-0.7266*i+1),(float) (loc.getZ()+i*kakeru*Math.sin(Math.toRadians(yaw))),0,0,0,0,1));
		}
		//6画
		for(float i=0F;i<0.5877F;i=i+0.01F){
			particlelist.add(new PacketPlayOutWorldParticles(enumparticle,true,(float) (loc.getX()+i*kakeru*Math.cos(Math.toRadians(yaw))),(float) (loc.getY()-3.0781*i+1),(float) (loc.getZ()+i*kakeru*Math.sin(Math.toRadians(yaw))),0,0,0,0,1));
		}
		//7画
		for(float i=-0.951F;i<-0.5877F;i=i+0.01F){
			particlelist.add(new PacketPlayOutWorldParticles(enumparticle,true,(float) (loc.getX()+i*kakeru*Math.cos(Math.toRadians(yaw))),(float) (loc.getY()-3.0773*i-2.618),(float) (loc.getZ()+i*kakeru*Math.sin(Math.toRadians(yaw))),0,0,0,0,1));
		}
		//8画
		for(float i=-0.951F;i<0.5877F;i=i+0.05F){
			particlelist.add(new PacketPlayOutWorldParticles(enumparticle,true,(float) (loc.getX()+i*kakeru*Math.cos(Math.toRadians(yaw))),(float) (loc.getY()-0.7266*i-0.4763),(float) (loc.getZ()+i*kakeru*Math.sin(Math.toRadians(yaw))),0,0,0,0,1));
		}
		//9画
		for(float i=-0.951F;i<0F;i=i+0.05F){
			particlelist.add(new PacketPlayOutWorldParticles(enumparticle,true,(float) (loc.getX()+i*kakeru*Math.cos(Math.toRadians(yaw))),(float) (loc.getY()+0.7266*i+1),(float) (loc.getZ()+i*kakeru*Math.sin(Math.toRadians(yaw))),0,0,0,0,1));
		}
		//10画
		for(float i=-0.5877F;i<0F;i=i+0.025F){
			particlelist.add(new PacketPlayOutWorldParticles(enumparticle,true,(float) (loc.getX()+i*kakeru*Math.cos(Math.toRadians(yaw))),(float) (loc.getY()-3.077*i+1),(float) (loc.getZ()+i*kakeru*Math.sin(Math.toRadians(yaw))),0,0,0,0,1));
		}
        for(Player player : loc.getWorld().getPlayers()) {
        	if(loc.getWorld()==player.getWorld()&&loc.distance(player.getLocation())<8){
        		for(PacketPlayOutWorldParticles particle:particlelist){
        			((CraftPlayer) player).getHandle().playerConnection.sendPacket(particle);
        		}
        	}
        }
        particlelist.clear();
	}
	public void playeffect4(Location loc,float eyeheight,String string){
		EnumParticle enumparticle = EnumParticle.valueOf(string);
		float yaw = loc.getYaw();
		if(yaw<0){
			yaw=360+yaw;
		}
		float kakeru=1.9F;

		loc.add(new Vector(-1*Math.sin(yaw*Math.PI / 180.0)*3,eyeheight,Math.cos(yaw*Math.PI / 180.0)*3));
		ArrayList<PacketPlayOutWorldParticles> particlelist = new ArrayList<PacketPlayOutWorldParticles>();

		for(float i=0;i<360;i=i+2F){
			particlelist.add(new PacketPlayOutWorldParticles(enumparticle ,true ,(float) (loc.getX()+(Math.sin(Math.toRadians(i))*Math.cos(Math.toRadians(yaw))*kakeru)),(float) (loc.getY()+Math.cos(Math.toRadians(i))*kakeru),(float) (loc.getZ()+Math.sin(Math.toRadians(i))*Math.sin(Math.toRadians(yaw))*kakeru), 0, 0, 0, 0, 1));
		}

        particlelist.clear();
	}
	@Override
	public void playSound(Location loc, Sound sound, Float volume, Float pitch) {
		loc.getWorld().playSound(loc, sound, volume, pitch);
	}
	@Override
	public void playSound(Location loc, Sound sound, Integer volume, Integer pitch) {
		loc.getWorld().playSound(loc, sound, volume, pitch);
	}

	@Override
	public void strikeLightning(Location loc) {
		//((CraftWorld)loc.getWorld()).strikeLightning(loc);
		((CraftWorld)loc.getWorld()).strikeLightningEffect(loc);
	}

	public void particle(Location loc){
			ArrayList<PacketPlayOutWorldParticles> particlelist = new ArrayList<PacketPlayOutWorldParticles>();
			particlelist.add(new PacketPlayOutWorldParticles(
		                EnumParticle.BLOCK_CRACK,
		                true,
		                (float) loc.getX(),
		                (float) loc.getY(),
		                (float) loc.getZ(),
		                0,
		                0,
		                0,
		                0,
		                100));
            for(Player player : loc.getWorld().getPlayers()) {
            	if(loc.getWorld()==player.getWorld()&&loc.distance(player.getLocation())<32){
            		for(PacketPlayOutWorldParticles particle:particlelist){
            			((CraftPlayer) player).getHandle().playerConnection.sendPacket(particle);
            		}
            	}
            }
            particlelist.clear();
	}

	@Override
	public void particle(Location loc, String _particle, float x, float y,
			float z, int speed, int count) {
		EnumParticle enumparticle = EnumParticle.valueOf(_particle);
		ArrayList<PacketPlayOutWorldParticles> particlelist = new ArrayList<PacketPlayOutWorldParticles>();
		particlelist.add(new PacketPlayOutWorldParticles(enumparticle,true,(float) loc.getX(),(float) loc.getY(),(float) loc.getZ(),x,y,z,speed,count));
        for(Player player : loc.getWorld().getPlayers()) {
        	if(loc.getWorld()==player.getWorld()&&loc.distance(player.getLocation())<16){
        		for(PacketPlayOutWorldParticles particle:particlelist){
        			((CraftPlayer) player).getHandle().playerConnection.sendPacket(particle);
        		}
        	}
        }
        particlelist.clear();
	}
	@Override
	public void setStats(LivingEntity _entity, double maxhealth, double knockback, double speed, double attack) {
		EntityInsentient entity = (EntityInsentient)((CraftLivingEntity) _entity).getHandle();
		entity.getAttributeInstance(GenericAttributes.maxHealth).setValue(maxhealth);
        entity.setHealth((float) maxhealth);
        entity.getAttributeInstance(GenericAttributes.c).setValue(knockback);
        entity.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(speed);
        entity.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(attack);
	}
	@Override
	public void setStats(LivingEntity _entity,double speed) {
		EntityInsentient entity = (EntityInsentient)((CraftLivingEntity) _entity).getHandle();
        entity.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(speed);
	}
	@Override
	public void setStats(LivingEntity _entity,double speed, double attack) {
		EntityInsentient entity = (EntityInsentient)((CraftLivingEntity) _entity).getHandle();
        entity.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(speed);
        entity.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(attack);
	}
	@Override
	public double getSpeed(LivingEntity _entity) {
		EntityInsentient entity = (EntityInsentient)((CraftLivingEntity) _entity).getHandle();
        return entity.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).getValue();
	}
}
