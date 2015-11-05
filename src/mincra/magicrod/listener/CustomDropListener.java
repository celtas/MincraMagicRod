package mincra.magicrod.listener;

import java.util.Random;

import mincra.magicrod.item.MagicItem;
import mincra.magicrod.item.MagicRod;
import mincra.magicrod.item.MagicWeapon;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class CustomDropListener implements Listener {
	Random r;
	public CustomDropListener() {
	    r = new Random();
	}
	@EventHandler
	public void onEntityDeath(EntityDeathEvent e){
		if(e.getEntity().hasMetadata("MagicType")){
			switch(e.getEntity().getMetadata("MagicType").get(0).asString()){
				case"FastZombie":
					if(gacha(5F)){
						e.getDrops().add(MagicItem.magicGoldTicket);
					}
					break;
				case"HealZombie":
					if(gacha(5F)){
						e.getDrops().add(MagicItem.magicGoldTicket);
					}else if(gacha(5F)){
						e.getDrops().add(MagicRod.QureWandLv3);
					}
					break;
				case"HealSkeleton":
					if(gacha(2F)){
						e.getDrops().add(MagicItem.magicGoldTicket);
					}
					e.getDrops().add(MagicItem.iceDrop);
					break;
				case"ColdSkeleton":
					if(gacha(2F)){
						e.getDrops().add(MagicItem.magicGoldTicket);
					}else if(gacha(2F)){
						e.getDrops().add(MagicWeapon.aiceBowLv2);
					}
					break;
				default:
					break;
			}
		}else{
		switch(e.getEntityType()){
			case BAT:
				if(gacha(1F)){
					e.getDrops().add(MagicRod.JumpWandLv2);
				}
				break;
			case BLAZE:
				if(gacha(1F)){
					e.getDrops().add(MagicRod.ExperienceWandLv1);
				}
				break;
			case CAVE_SPIDER:
				if(gacha(1F)){
					e.getDrops().add(MagicItem.magicTicket);
				}
				break;
			case CHICKEN:
				if(gacha(0.5F)){
					e.getDrops().add(MagicItem.phoenixDown);
				}
				break;
			case COW:
				break;
			case CREEPER:
				if(gacha(0.5F)){
					e.getDrops().add(MagicItem.magicTicket);
				}
				if(gacha(0.2F)){
					e.getDrops().add(mincra.magicrod.item.MagicWeapon.creeperBow);
				}
				break;
			case DROPPED_ITEM:
				break;
			case ENDERMAN:
				break;
			case ENDERMITE:
				break;
			case ENDER_DRAGON:
				break;
			case EXPERIENCE_ORB:
				break;
			case FALLING_BLOCK:
				break;
			case GHAST:
				if(gacha(0.75F)){
					e.getDrops().add(MagicRod.InfernoWandLv2);
				}
				break;
			case GIANT:
				break;
			case GUARDIAN:
				break;
			case HORSE:
				if(gacha(1F)){
					e.getDrops().add(MagicRod.MoveWandLv3);
				}
				break;
			case IRON_GOLEM:
				if(gacha(1F)){
					e.getDrops().add(MagicRod.GuardBookLv3);
				}
				break;
			case MAGMA_CUBE:
				if(gacha(1F)){
					e.getDrops().add(MagicRod.InfernoWandLv1);
				}
				break;
			case MUSHROOM_COW:
				break;
			case OCELOT:
				break;
			case PIG:
				break;
			case PIG_ZOMBIE:
				break;
			case RABBIT:
				if(gacha(0.5F)){
					e.getDrops().add(MagicRod.JumpWandLv3);
				}
				break;
			case SHEEP:
				break;
			case SILVERFISH:
				break;
			case SKELETON:
				if(gacha(0.2F)){
					e.getDrops().add(MagicItem.magicTicket);
				}
				if(gacha(0.2F)){
					e.getDrops().add(mincra.magicrod.item.MagicWeapon.aiceBowLv1);
				}else if(gacha(0.2F)){
					e.getDrops().add(mincra.magicrod.item.MagicWeapon.ParalysisBow);
				}
				break;
			case SLIME:
				break;
			case SMALL_FIREBALL:
				break;
			case SNOWBALL:
				break;
			case SNOWMAN:
				if(gacha(0.1F)){
					e.getDrops().add(MagicRod.IceTreeBookLv3);
				}
				break;
			case SPIDER:
				break;
			case SQUID:
				break;
			case VILLAGER:
				break;
			case WITCH:
				if(gacha(2F)){
					e.getDrops().add(MagicItem.magicTicket);
				}else if(gacha(0.3F)){
					e.getDrops().add(MagicRod.InfernoWandLv3);
				}else if(gacha(0.3F)){
					e.getDrops().add(MagicRod.JumpWandLv3);
				}else if(gacha(0.3F)){
					e.getDrops().add(MagicRod.ExperienceWandLv3);
				}
				break;
			case WITHER:
				ItemStack item = MagicItem.darkMatter;
				item.setAmount(9);
				e.getDrops().add(item);
				break;
			case WOLF:
				break;
			case ZOMBIE:
				if(gacha(0.5F)){
					e.getDrops().add(MagicItem.magicTicket);
				}
				break;
			default:
				break;
		}
		}
	}
	Boolean gacha(float probability){
		float hash = (float) ((r.nextInt(10000)+1)*0.01);
		if(hash<=probability){
			return true;
		}else{
			return false;
		}
	}
}
