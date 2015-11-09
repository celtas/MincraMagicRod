package mincra.magicrod.api;


import mincra.magicrod.database.ConnectionManager;
import mincra.magicrod.database.DatabaseManager;
import mincra.magicrod.item.MagicItem;
import mincra.magicrod.item.MagicMaterial;
import mincra.magicrod.item.MagicRod;
import mincra.magicrod.item.MagicWeapon;
import mincra.magicrod.util.Util;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class MagicApi {
	public static String getMagicType(ItemStack item){
		if(item!=null){
			if(item.getItemMeta().hasLore()){
				String lore = item.getItemMeta().getLore().get(0);
				lore = ChatColor.stripColor(lore);
				int num = lore.indexOf(":");
				if(num!=-1){
					return lore.substring(0, num);
				}
			}
		}
		return null;
	}
	public static String getMagicNumber(ItemStack item){
		if(item!=null){
			if(item.getItemMeta().hasLore()){
				String lore = item.getItemMeta().getLore().get(0);
				lore = ChatColor.stripColor(lore);
				int num = lore.indexOf(":");
				if(num!=-1){
					return lore.substring(lore.indexOf(":")+1);
				}
			}
		}
		return null;
	}
	public static Boolean equalsMagicNumber(ItemStack item,String number){
		if(item!=null){
			if(item.getItemMeta().hasLore()){
				String lore = item.getItemMeta().getLore().get(0);
				lore = ChatColor.stripColor(lore);
				int num = lore.indexOf(":");
				if(num!=-1){
					if(number.equalsIgnoreCase(lore.substring(lore.indexOf(":")+1))){
						return true;
					}
				}
			}
		}
		return false;
	}
	public static Boolean equalsMagicNumber(ItemStack item,String type,String number){
		if(item!=null){
			if(item.getItemMeta().hasLore()){
				String lore = item.getItemMeta().getLore().get(0);
				lore = ChatColor.stripColor(lore);
				int num = lore.indexOf(":");
				if(num!=-1){
					if(lore.substring(0, num).equalsIgnoreCase(type)){
						if(number.equalsIgnoreCase(lore.substring(lore.indexOf(":")+1))){
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	public static Boolean isMagic(ItemStack item){
		if(item!=null){
			if(item.hasItemMeta()){
				if(item.getItemMeta().hasLore()){
					String lore = item.getItemMeta().getLore().get(0);
					lore = ChatColor.stripColor(lore);
					int num = lore.indexOf(":");
					if(num!=-1){
						switch(lore.substring(0, num)){
							case"ROD番号":
							case"魔法武器番号":
							case"魔法アイテム番号":
							case"魔法マテリアル":
								return true;
							default:
								return false;
						}
					}
				}
			}
		}
		return false;
	}
	public boolean updateMagic(ItemStack item) {
		if(item.hasItemMeta()){
			if(item.getItemMeta().hasLore()){
				String lore = item.getItemMeta().getLore().get(0);
				lore = ChatColor.stripColor(lore);
				int num = lore.indexOf(":");
				short durability = item.getDurability();
				String number = lore.substring(num+1);
					switch(lore.substring(0,num)){
						case"ROD番号":
							ItemStack rod = getRod(number);
							if(!rod.getItemMeta().getLore().equals(item.getItemMeta().getLore())){
								item.setItemMeta(rod.getItemMeta());
								if(durability>0) item.setDurability(durability);
								return true;
							}else{
								return false;
							}
						case"魔法武器番号":
							ItemStack weapon = getWeapon(number);
							if(!weapon.getItemMeta().getLore().equals(item.getItemMeta().getLore())){
								item.setItemMeta(weapon.getItemMeta());
								if(durability>0) item.setDurability(durability);
								return true;
							}else{
								return false;
							}
						case"魔法アイテム番号":
							ItemStack magicitem = getMagicItem(number);
							if(!magicitem.getItemMeta().getLore().equals(item.getItemMeta().getLore())){
								item.setItemMeta(magicitem.getItemMeta());
								if(durability>0) item.setDurability(durability);
								return true;
							}else{
								return false;
							}
						case"魔法マテリアル":
							ItemStack material = getMaterial(Integer.valueOf(number));
							if(!material.getItemMeta().getLore().equals(item.getItemMeta().getLore())){
								item.setItemMeta(material.getItemMeta());
								if(durability>0) item.setDurability(durability);
								return true;
							}else{
								return false;
							}
						default:
							return false;
					}
			}
		}
		return false;
	}
	public static Boolean isMagicMaterial(ItemStack item){
		if(item.hasItemMeta()){
			if(item.getItemMeta().hasLore()){
				String lore = item.getItemMeta().getLore().get(0);
				lore = ChatColor.stripColor(lore);
				int num = lore.indexOf(":");
				if(num!=-1){
					if(lore.substring(0, num).equals("魔法マテリアル")){
						return true;
					}else{
						return false;
					}
				}
			}
		}
		return false;
	}

	public static boolean isPossibleMaigc(Material material){
		switch(material){
			case ENCHANTED_BOOK:
			case BLAZE_ROD:
			case BLAZE_POWDER:
			case INK_SACK:
			case GHAST_TEAR:
				return true;
			default:
				return false;
		}
	}
	public static int getMaterialNumber(ItemStack item){
		if(item.hasItemMeta()){
			if(item.getItemMeta().hasLore()){
				String lore = item.getItemMeta().getLore().get(0);
				lore = ChatColor.stripColor(lore);
				int num = lore.indexOf(":");
				if(num!=-1){
					if(lore.substring(0, num).equals("魔法マテリアル")){
							return Integer.valueOf(lore.substring(num+1));
					}
				}
			}
		}
		return -1;
	}
	public static int getMagicJob(ItemStack item){
		if(isMagicMaterial(item)){ 
			String lore = item.getItemMeta().getLore().get(4);
			lore = ChatColor.stripColor(lore);
			int num = lore.indexOf("+");
			if(num!=-1){
				return Integer.valueOf(lore.substring(0, num));
			}
		}
		return -1;
	}

	public static ItemStack getMaterial(int material_id){
		switch(material_id){
			case 1:
				return MagicMaterial.blizzard;
			case 2:
				return MagicMaterial.fire;
			case 3:
				return MagicMaterial.tauntLv1;
			case 4:
				return MagicMaterial.thunder;
			case 5:
				return MagicMaterial.cureLv1;
			case 6:
				return MagicMaterial.walkSpeedLv1;
			case 7:
				return MagicMaterial.jumpLv1;
			case 8:
				return MagicMaterial.wall;
			case 9:
				return MagicMaterial.attack;
			case 10:
				return MagicMaterial.resurrection;
	    	case 11:
	    		return MagicMaterial.boost;
	    	case 12:
				return MagicMaterial.cureLv2;
	    	case 13:
				return MagicMaterial.tauntLv2;
	    	case 14:
				return MagicMaterial.jumpLv2;
	    	case 15:
				return MagicMaterial.walkSpeedLv2;
	    	case 16:
				return MagicMaterial.holyLv1;
	    	case 17:
				return MagicMaterial.holyLv2;
	    	case 18:
				return MagicMaterial.gravityLv1;
	    	case 19:
				return MagicMaterial.untigravityLv1;
	    	case 20:
				return MagicMaterial.villager;
	    	case 21:
				return MagicMaterial.magicArrow;
	    	case 22:
				return MagicMaterial.directionLv1;
	    	case 23:
				return MagicMaterial.devideLv1;
	    	case 24:
				return MagicMaterial.chargeLv1;
		    default:
		    	Util.debug(ChatColor.YELLOW+"エラー - getMaterial():"+material_id);
		    	return null;
		}
	}
	public static ItemStack getRod(String rod_id){
		switch(rod_id){
			case "1-1":
				return MagicRod.ExperienceWandLv1;
			case "1-2":
				return MagicRod.ExperienceWandLv2;
			case "1-3":
				return MagicRod.ExperienceWandLv3;
			case "2-1":
				return MagicRod.JumpWandLv1;
			case "2-2":
				return MagicRod.JumpWandLv2;
			case "2-3":
				return MagicRod.JumpWandLv3;
			case "3-1":
				return MagicRod.InfernoWandLv1;
			case "3-2":
				return MagicRod.InfernoWandLv2;
			case "3-3":
				return MagicRod.InfernoWandLv3;
			case "4-1":
				return MagicRod.DestroyWandLv1;
			case "4-2":
				return MagicRod.DestroyWandLv2;
			case "4-3":
				return MagicRod.DestroyWandLv3;
			case "4-4":
				return MagicRod.DestroyWandLv4;
			case "4-5":
				return MagicRod.DestroyPickaxeLv5;
			case "5-1":
				return MagicRod.QureWandLv1;
			case "5-2":
				return MagicRod.QureWandLv2;
			case "5-3":
				return MagicRod.QureWandLv3;
			case "6-1":
				return MagicRod.MoveWandLv1;
			case "6-2":
				return MagicRod.MoveWandLv2;
			case "6-3":
				return MagicRod.MoveWandLv3;
			case "7-1":
				return MagicRod.IceTreeWandLv1;
			case "7-2":
				return MagicRod.IceTreeWandLv2;
			case "7-3":
				return MagicRod.IceTreeBookLv3;
			case "7-4":
				return MagicRod.IceTreeBookLv4;
			case "8-1":
				return MagicRod.GuardWandLv1;
			case "8-2":
				return MagicRod.GuardWandLv2;
			case "8-3":
				return MagicRod.GuardBookLv3;
			case "9-1":
				return MagicRod.WaterWandLv1;
			case "10-1":
				return MagicRod.BeastsBookLv1;
			case "10-2":
				return MagicRod.BeastsBookLv2;
			case "10-3":
				return MagicRod.BeastsBookLv3;
			case "11-1":
				return MagicRod.CartBookLv1;
			case "12-1":
				return MagicRod.LightningBookLv1;
		    default:
		    	Util.debug(ChatColor.YELLOW+"エラー - getRod():"+rod_id);
		    	return null;
		}
	}
	public static ItemStack getMagicItem(String magicitem_id){
		switch(magicitem_id){
			case "1":
				return MagicItem.magicPotion1;
			case "2":
				return MagicItem.iceDrop;
			case "3":
				return MagicItem.phoenixDown;
			case "4":
				return MagicItem.quartz;
			case "5":
				return MagicItem.magicTicket;
			case "5-2":
				return MagicItem.magicGoldTicket;
			case "6":
				return MagicItem.magicWeed;
			case "7":
				return MagicItem.darkMatter;
		    default:
		    	Util.debug(ChatColor.YELLOW+"エラー - getMagicItem():"+magicitem_id);
		    	return null;
		}
	}
	public static ItemStack getWeapon(String weapon_id){
		switch(weapon_id){
			case "1":
				return MagicWeapon.creeperBow;
			case "2":
				return MagicWeapon.creeperBowLv2;
			case "3":
				return MagicWeapon.aiceBowLv1;
			case "3-2":
				return MagicWeapon.aiceBowLv2;
			case "4":
				return MagicWeapon.ParalysisSword;
			case "5":
				return MagicWeapon.ParalysisBow;
			case "6":
				return MagicWeapon.MechanicsSword;
			case "7":
				return MagicWeapon.SleepSword;
			case "8":
				return MagicWeapon.SleepBow;
		    default:
		    	Util.debug(ChatColor.YELLOW+"エラー - getMagicItem():"+weapon_id);
		    	return null;
		}
	}
}
