package mincra.magicrod.listener;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.metadata.FixedMetadataValue;

import mincra.magicrod.ai.MonsterAI;
import mincra.magicrod.main.Magic;
import mincra.magicrod.version.Version;

public class SpawnListener extends MonsterAI implements Listener{
	private Magic plugin;
	private Random random;
	private ItemStack greenhelmet = new ItemStack(Material.LEATHER_HELMET);
	private ItemStack greenchestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
	private ItemStack greenleggings = new ItemStack(Material.LEATHER_LEGGINGS);
	private ItemStack greenboots = new ItemStack(Material.LEATHER_BOOTS);
	private ItemStack redhelmet = new ItemStack(Material.LEATHER_HELMET);
	private ItemStack redchestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
	private ItemStack redleggings = new ItemStack(Material.LEATHER_LEGGINGS);
	private ItemStack redboots = new ItemStack(Material.LEATHER_BOOTS);
	private ItemStack bluehelmet = new ItemStack(Material.LEATHER_HELMET);
	private ItemStack bluechestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
	private ItemStack blueleggings = new ItemStack(Material.LEATHER_LEGGINGS);
	private ItemStack blueboots = new ItemStack(Material.LEATHER_BOOTS);
	public SpawnListener(Magic plugin) {
		super(plugin);
	    this.plugin = plugin;
		LeatherArmorMeta greenhelmetmeta = ((LeatherArmorMeta) greenhelmet.getItemMeta());
		greenhelmetmeta.setColor(Color.GREEN);
		greenhelmet.setItemMeta(greenhelmetmeta);
		LeatherArmorMeta greenchestplatemeta = ((LeatherArmorMeta) greenchestplate.getItemMeta());
		greenchestplatemeta.setColor(Color.GREEN);
		greenchestplate.setItemMeta(greenchestplatemeta);
		LeatherArmorMeta greenleggingsmeta = ((LeatherArmorMeta) greenleggings.getItemMeta());
		greenleggingsmeta.setColor(Color.GREEN);
		greenleggings.setItemMeta(greenleggingsmeta);
		LeatherArmorMeta greenbootsmeta = ((LeatherArmorMeta) greenboots.getItemMeta());
		greenbootsmeta.setColor(Color.GREEN);
		greenboots.setItemMeta(greenbootsmeta);
		LeatherArmorMeta redhelmetmeta = ((LeatherArmorMeta) redhelmet.getItemMeta());
		redhelmetmeta.setColor(Color.RED);
		redhelmet.setItemMeta(redhelmetmeta);
		LeatherArmorMeta redchestplatemeta = ((LeatherArmorMeta) redchestplate.getItemMeta());
		redchestplatemeta.setColor(Color.RED);
		redchestplate.setItemMeta(redchestplatemeta);
		LeatherArmorMeta redleggingsmeta = ((LeatherArmorMeta) redleggings.getItemMeta());
		redleggingsmeta.setColor(Color.RED);
		redleggings.setItemMeta(redleggingsmeta);
		LeatherArmorMeta redbootsmeta = ((LeatherArmorMeta) redboots.getItemMeta());
		redbootsmeta.setColor(Color.RED);
		redboots.setItemMeta(redbootsmeta);
		LeatherArmorMeta bluehelmetmeta = ((LeatherArmorMeta) bluehelmet.getItemMeta());
		bluehelmetmeta.setColor(Color.BLUE);
		bluehelmet.setItemMeta(bluehelmetmeta);
		LeatherArmorMeta bluechestplatemeta = ((LeatherArmorMeta) bluechestplate.getItemMeta());
		bluechestplatemeta.setColor(Color.BLUE);
		bluechestplate.setItemMeta(bluechestplatemeta);
		LeatherArmorMeta blueleggingsmeta = ((LeatherArmorMeta) blueleggings.getItemMeta());
		blueleggingsmeta.setColor(Color.BLUE);
		blueleggings.setItemMeta(blueleggingsmeta);
		LeatherArmorMeta bluebootsmeta = ((LeatherArmorMeta) blueboots.getItemMeta());
		bluebootsmeta.setColor(Color.BLUE);
		blueboots.setItemMeta(bluebootsmeta);
		random = new Random();
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void CreatureSpawnEvent(CreatureSpawnEvent event) {
		if(event.getEntity().getWorld().getName().equals("world")){
			LivingEntity mob = event.getEntity();
			/*
			 if(hash<0.5){
					}else if(hash<0.80){
					}else if(hash<0.92){
					}else if(hash<0.96){
					}else if(hash<=1.0){
					}
			 */
			float hash = (float) ((random.nextInt(10000)+1)*0.01);
			switch(event.getEntityType()){
				case CREEPER:
					mob.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40);
					mob.setHealth(40);
					((Creeper)mob).setPowered(true);
					Version.setStats(mob,0.24D,30D);
					break;
				case ZOMBIE:
					if(hash<50){
						mob.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(75);
						mob.setHealth(75);
						Version.setStats(mob,0.360D,10D);
					}else{
						if(((Zombie)mob).isBaby()) ((Zombie)mob).setBaby(false);
						if(hash<88){
							mob.getEquipment().setHelmet(new ItemStack(Material.CHAINMAIL_HELMET));
							mob.getEquipment().setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
							mob.getEquipment().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
							mob.getEquipment().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
							mob.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(70);
							mob.setHealth(70);
							mob.setCustomName("Chain Zombie");
							mob.getEquipment().setItemInMainHand(new ItemStack(Material.IRON_SWORD));
							Version.setStats(mob,0.340D);
						}else if(hash<95){
							mob.getEquipment().setHelmet(new ItemStack(Material.GOLD_HELMET));
							mob.getEquipment().setChestplate(new ItemStack(Material.GOLD_CHESTPLATE));
							mob.getEquipment().setLeggings(new ItemStack(Material.GOLD_LEGGINGS));
							mob.getEquipment().setBoots(new ItemStack(Material.GOLD_BOOTS));
							mob.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(70);
							mob.setHealth(70);
							Version.setStats(mob,0.320D);
							mob.setCustomName("Gold Zombie");
							mob.getEquipment().setItemInMainHand(new ItemStack(Material.GOLD_SWORD));
						}else if(hash<99){
							mob.getEquipment().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
							mob.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
							mob.getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
							mob.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
							Version.setStats(mob,0.300D);
							mob.setCustomName("Diamond Zombie");
							mob.getEquipment().setItemInMainHand(new ItemStack(Material.DIAMOND_SWORD));
						}else if(hash<99.5){
							if(((Zombie)mob).isBaby()) ((Zombie)mob).setBaby(false);
							mob.getEquipment().setBoots(new ItemStack(Material.LEATHER_BOOTS));
							Version.setStats(mob,0.420D);
							mob.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(140);
							mob.setHealth(140);
							mob.setCustomName(ChatColor.WHITE+""+ChatColor.BOLD+"Fast Zombie");
							mob.getEquipment().setItemInMainHand(new ItemStack(Material.BLAZE_ROD));
							mob.setMetadata("MagicType", new FixedMetadataValue(plugin, "FastZombie"));
							mob.getWorld().playSound(mob.getLocation(), Sound.BLOCK_NOTE_BASEDRUM, 8F, 1);
						}else if(hash<=100){
							mob.getEquipment().setHelmet(greenhelmet);
							mob.getEquipment().setChestplate(greenchestplate);
							mob.getEquipment().setLeggings(greenleggings);
							mob.getEquipment().setBoots(greenboots);
							Version.setStats(mob,0.300D);
							mob.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(220);
							mob.setHealth(220);
							mob.setCustomName(ChatColor.GREEN+""+ChatColor.BOLD+"Heal Zombie");
							mob.getEquipment().setItemInMainHand(new ItemStack(Material.BLAZE_ROD));
							mob.setMetadata("MagicType", new FixedMetadataValue(plugin, "HealZombie"));
							heal(mob);
							mob.getWorld().playSound(mob.getLocation(), Sound.BLOCK_NOTE_BASEDRUM, 8F, 1);
						}
					}
					break;
				case SKELETON:
					if(hash<55){
						Version.setStats(mob,0.360D);
						mob.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(70);
						mob.setHealth(70);
					}else if(hash<88){
						mob.getEquipment().setHelmet(new ItemStack(Material.CHAINMAIL_HELMET));
						mob.getEquipment().setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
						mob.getEquipment().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
						mob.getEquipment().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
						mob.setCustomName("Chain Skeleton");
						ItemStack weapon = new ItemStack(Material.BOW);
						weapon.addEnchantment(new EnchantmentWrapper(48), 2);
						mob.getEquipment().setItemInMainHand(weapon);
						mob.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(70);
						mob.setHealth(70);
						Version.setStats(mob,0.340D);
					}else if(hash<95){
						mob.getEquipment().setHelmet(new ItemStack(Material.GOLD_HELMET));
						mob.getEquipment().setChestplate(new ItemStack(Material.GOLD_CHESTPLATE));
						mob.getEquipment().setLeggings(new ItemStack(Material.GOLD_LEGGINGS));
						mob.getEquipment().setBoots(new ItemStack(Material.GOLD_BOOTS));
						mob.setCustomName("Gold Skeleton");
						ItemStack weapon = new ItemStack(Material.BOW);
						weapon.addEnchantment(new EnchantmentWrapper(48), 3);
						mob.getEquipment().setItemInMainHand(weapon);
						mob.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(70);
						mob.setHealth(70);
						Version.setStats(mob,0.320D);
					}else if(hash<98.5){
						mob.getEquipment().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
						mob.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
						mob.getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
						mob.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
						mob.setCustomName("Diamond Skeleton");
						ItemStack weapon = new ItemStack(Material.BOW);
						weapon.addEnchantment(new EnchantmentWrapper(48), 5);
						mob.getEquipment().setItemInMainHand(weapon);
						mob.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(70);
						mob.setHealth(70);
						Version.setStats(mob,0.300D);
					}else if(hash<99){
						mob.getEquipment().setHelmet(bluehelmet);
						mob.getEquipment().setChestplate(bluechestplate);
						mob.getEquipment().setLeggings(blueleggings);
						mob.getEquipment().setBoots(blueboots);
						mob.setCustomName(ChatColor.BLUE+""+ChatColor.BOLD+"Cold Skeleton");
						ItemStack weapon = new ItemStack(Material.BOW);
						weapon.addUnsafeEnchantment(new EnchantmentWrapper(48), 5);
						mob.getEquipment().setItemInMainHand(weapon);
						mob.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(300);
						mob.setHealth(300);
						Version.setStats(mob,0.200D);
						blizard(mob);
						mob.getWorld().playSound(mob.getLocation(), Sound.BLOCK_NOTE_BASEDRUM, 8F, 1);
					}else if(hash<99.5){
						mob.getEquipment().setHelmet(redhelmet);
						mob.getEquipment().setChestplate(redchestplate);
						mob.getEquipment().setLeggings(redleggings);
						mob.getEquipment().setBoots(redboots);
						mob.setCustomName(ChatColor.RED+""+ChatColor.BOLD+"Fire Skeleton");
						ItemStack weapon = new ItemStack(Material.BOW);
						weapon.addUnsafeEnchantment(new EnchantmentWrapper(48), 5);
						mob.getEquipment().setItemInMainHand(weapon);
						mob.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(300);
						mob.setHealth(300);
						Version.setStats(mob,0.200D);
						fire(mob);
						mob.getWorld().playSound(mob.getLocation(), Sound.BLOCK_NOTE_BASEDRUM, 8F, 1);
					}else if(hash<=100){
						mob.getEquipment().setHelmet(greenhelmet);
						mob.getEquipment().setChestplate(greenchestplate);
						mob.getEquipment().setLeggings(greenleggings);
						mob.getEquipment().setBoots(greenboots);
						mob.setCustomName(ChatColor.GREEN+""+ChatColor.BOLD+"Heal Skeleton");
						mob.getEquipment().setItemInMainHand(new ItemStack(Material.BLAZE_ROD));
						mob.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(400);
						mob.setHealth(400);
						Version.setStats(mob,0.268D);
						mob.setMetadata("MagicType", new FixedMetadataValue(plugin, "HealSkeleton"));
						heal(mob);
						mob.getWorld().playSound(mob.getLocation(), Sound.BLOCK_NOTE_BASEDRUM, 8F, 1);
					}
					break;
				case SPIDER:
					mob.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(70);
					mob.setHealth(70);
					Version.setStats(mob,0.368D,10D);
					break;
				case CAVE_SPIDER:
					mob.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(70);
					mob.setHealth(70);
					break;
				case BAT:
					break;
				case BLAZE:
					break;
				case CHICKEN:
					break;
				case COW:
					break;
				case ENDERMITE:
					mob.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(35);
					mob.setHealth(35);
					break;
				case ENDERMAN:
					mob.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(120);
					mob.setHealth(120);
					break;
				case GHAST:
					break;
				case GIANT:
					break;
				case GUARDIAN:
					mob.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(60);
					mob.setHealth(60);
					break;
				case ELDER_GUARDIAN:
					mob.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(120);
					mob.setHealth(120);
					break;
				case HORSE:
					break;
				case MAGMA_CUBE:
					break;
				case OCELOT:
					break;
				case PIG:
					break;
				case PIG_ZOMBIE:
					mob.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30);
					mob.setHealth(30);
					break;
				case RABBIT:
					break;
				case SHEEP:
					break;
				case SILVERFISH:
					break;
				case SLIME:
					mob.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40);
					mob.setHealth(40);
					break;
				case SQUID:
					break;
				case WITCH:
					mob.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(125);
					mob.setHealth(125);
					break;
				case WITHER:
					break;
				case WOLF:
					break;
				default:
					break;
			}
		}else if(event.getEntity().getWorld().getName().equals("world_the_end")){
			LivingEntity mob=event.getEntity();
			switch(event.getEntityType()){
				case ENDERMAN:
					mob.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(100);
					mob.setHealth(100);
					break;
				default:
					break;
			}
		}else if(event.getEntity().getWorld().getName().equals("world_nether")){
			LivingEntity mob=event.getEntity();
			float hash = (float) ((random.nextInt(10000)+1)*0.01);
			switch(event.getEntityType()){
				case PIG_ZOMBIE:
					if(hash<85){
						Version.setStats(mob,0.260D);
						mob.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(50);
						mob.setHealth(50);
						ItemStack weapon = new ItemStack(Material.GOLD_SWORD);
						weapon.addEnchantment(new EnchantmentWrapper(16), 1);
						mob.getEquipment().setItemInMainHand(weapon);
					}else if(hash<99){
						mob.getEquipment().setHelmet(new ItemStack(Material.CHAINMAIL_HELMET));
						mob.getEquipment().setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
						mob.getEquipment().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
						mob.getEquipment().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
						mob.setCustomName("Chain Pigman");
						ItemStack weapon = new ItemStack(Material.GOLD_SWORD);
						weapon.addEnchantment(new EnchantmentWrapper(16), 2);
						mob.getEquipment().setItemInMainHand(weapon);
						mob.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(80);
						mob.setHealth(80);
						Version.setStats(mob,0.270D);
					}else{
						mob.getEquipment().setHelmet(greenhelmet);
						mob.getEquipment().setChestplate(greenchestplate);
						mob.getEquipment().setLeggings(greenleggings);
						mob.getEquipment().setBoots(greenboots);
						mob.setCustomName(ChatColor.GREEN+""+ChatColor.BOLD+"Heal Pigman");
						mob.getEquipment().setItemInMainHand(new ItemStack(Material.BLAZE_ROD));
						mob.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(200);
						mob.setHealth(200);
						Version.setStats(mob,0.230D);
						mob.setMetadata("MagicType", new FixedMetadataValue(plugin, "HealPigman"));
						heal(mob);
						mob.getWorld().playSound(mob.getLocation(), Sound.BLOCK_NOTE_BASEDRUM, 8F, 1);
					}
					break;
				case BLAZE:
					mob.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(50);
					mob.setHealth(50);
					break;
				case SKELETON:
					mob.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(100);
					mob.setHealth(100);
					break;
				case GHAST:
					mob.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(100);
					mob.setHealth(100);
					break;
				default:
					break;
			}
		}
	}

}

