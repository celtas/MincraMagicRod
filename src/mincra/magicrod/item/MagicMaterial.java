package mincra.magicrod.item;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import mincra.magicrod.main.Magic;

public class MagicMaterial{
	Magic plugin;

	public static ItemStack blizzard = new ItemStack(Material.GHAST_TEAR,1);
	public static ItemStack fire = new ItemStack(Material.MAGMA_CREAM,1);
	public static ItemStack tauntLv1 = new ItemStack(Material.BONE,1);
	public static ItemStack tauntLv2 = new ItemStack(Material.BONE,1);
	public static ItemStack thunder = new ItemStack(Material.INK_SACK,1,(short) 11);
	public static ItemStack cureLv1 = new ItemStack(Material.EMERALD,1);
	public static ItemStack cureLv2 = new ItemStack(Material.EMERALD,1);
	public static ItemStack walkSpeedLv1 = new ItemStack(Material.EMERALD,1);
	public static ItemStack walkSpeedLv2 = new ItemStack(Material.EMERALD,1);
	public static ItemStack jumpLv1 = new ItemStack(Material.INK_SACK,1,(short) 4);
	public static ItemStack jumpLv2 = new ItemStack(Material.INK_SACK,1,(short) 4);
	public static ItemStack wall = new ItemStack(Material.QUARTZ,1);
	public static ItemStack attack = new ItemStack(Material.BLAZE_POWDER,1);
	public static ItemStack resurrection = new ItemStack(Material.NETHER_STAR,1);
	public static ItemStack boost = new ItemStack(Material.GOLDEN_APPLE,1,(short) 1);
	public static ItemStack holyLv1 = new ItemStack(Material.NETHER_STAR,1);
	public static ItemStack holyLv2 = new ItemStack(Material.NETHER_STAR,1);
	public static ItemStack gravityLv1 = new ItemStack(Material.FIREWORK_CHARGE,1);
	public static ItemStack untigravityLv1 = new ItemStack(Material.FIREWORK_CHARGE,1);
	public static ItemStack villager = new ItemStack(Material.PAPER,1);
	public static ItemStack magicArrow = new ItemStack(Material.ARROW,1);
	public static ItemStack directionLv1 = new ItemStack(Material.QUARTZ,1);
	public static ItemStack devideLv1 = new ItemStack(Material.ARROW,1);
	public static ItemStack chargeLv1 = new ItemStack(Material.MAGMA_CREAM,1);
	public static ItemStack invisiblehandsLv1 = new ItemStack(Material.BOOK, 1);
	public static ItemStack invisiblehandsLv2 = new ItemStack(Material.BOOK, 1);
	public static ItemStack invisiblehandsLv3 = new ItemStack(Material.BOOK, 1);

	//魔法領域-そのうち実装する予定. 魔法領域とはスキルにセットするとき100の持ち点から減らしていく.
	//マテリアルは追加したら,ConnectionManager,MagicApi,MagicItemUseに書き込む.
	public final static List<String> blizzardLore = Arrays.asList(ChatColor.AQUA+			"魔法マテリアル:1"
															,ChatColor.AQUA+				"-氷属性-範囲魔法"
															,ChatColor.AQUA+				"8     +クールタイム"
															,ChatColor.AQUA+				"80%   +MP消費"
															,ChatColor.AQUA+				"3     +魔術師系のみ装備可能"
															,ChatColor.AQUA+				"20    +魔法領域"
															);
	public final static List<String> fireLore = Arrays.asList(ChatColor.RED+				"魔法マテリアル:2"
															,ChatColor.RED+					"-火属性-範囲魔法"
															,ChatColor.RED+					"5     +クールタイム"
															,ChatColor.RED+					"50%   +MP消費"
															,ChatColor.RED+					"3     +魔術師系のみ装備可能"
															,ChatColor.RED+					"10    +魔法領域"
															);
	public final static List<String> tauntLv1Lore = Arrays.asList(ChatColor.LIGHT_PURPLE+	"魔法マテリアル:3"
															,ChatColor.LIGHT_PURPLE+		"-無属性-範囲魔法"
															,ChatColor.LIGHT_PURPLE+		"5     +クールタイム"
															,ChatColor.LIGHT_PURPLE+		"0     +MP消費"
															,ChatColor.LIGHT_PURPLE+		"1     +戦士系のみ装備可能"
															,ChatColor.LIGHT_PURPLE+		"10    +魔法領域"
															);
	public final static List<String> thunderLore = Arrays.asList(ChatColor.YELLOW+			"魔法マテリアル:4"
															,ChatColor.YELLOW+				"-雷属性-範囲魔法-追加効果:麻痺"
															,ChatColor.YELLOW+				"5     +クールタイム"
															,ChatColor.YELLOW+				"50%   +MP消費"
															,ChatColor.YELLOW+				"3     +魔術師系のみ装備可能"
															,ChatColor.YELLOW+				"10    +魔法領域"
															);
	public final static List<String> cureLv1Lore = Arrays.asList(ChatColor.GREEN+			"魔法マテリアル:5"
															,ChatColor.GREEN+				"-範囲魔法-追加効果:回復"
															,ChatColor.GREEN+				"5     +クールタイム"
															,ChatColor.GREEN+				"0     +MP消費"
															,ChatColor.GREEN+				"2     +プリースト系のみ装備可能"
															,ChatColor.GREEN+				"30    +魔法領域"
															);

	public final static List<String> walkSpeedLv1Lore = Arrays.asList(ChatColor.GREEN+		"魔法マテリアル:6"
															,ChatColor.GREEN+				"-強化魔法-追加効果:移動速度上昇"
															,ChatColor.GREEN+				"15    +クールタイム"
															,ChatColor.GREEN+				"0     +MP消費"
															,ChatColor.GREEN+				"0     +全ての職で装備可能"
															,ChatColor.GREEN+				"25    +魔法領域"
															);
	public final static List<String> jumpLv1Lore = Arrays.asList(ChatColor.AQUA+			"魔法マテリアル:7"
															,ChatColor.AQUA+				"-強化魔法"
															,ChatColor.AQUA+				"10    +クールタイム"
															,ChatColor.AQUA+				"0     +MP消費"
															,ChatColor.AQUA+				"0     +全ての職で装備可能"
															,ChatColor.AQUA+				"40    +魔法領域"
															);
	public final static List<String> wallLore = Arrays.asList(ChatColor.WHITE+				"魔法マテリアル:8"
															,ChatColor.WHITE+				"-強化魔法-追加効果:防御力上昇-効果時間:5分"
															,ChatColor.WHITE+				"2     +クールタイム"
															,ChatColor.WHITE+				"5     +MP消費"
															,ChatColor.WHITE+				"1     +戦士系のみ装備可能"
															,ChatColor.WHITE+				"10    +魔法領域"
															);
	public final static List<String> attackLore = Arrays.asList(ChatColor.RED+				"魔法マテリアル:9"
															,ChatColor.RED+					"-強化魔法-追加効果:攻撃力上昇-効果時間:5分"
															,ChatColor.RED+					"2     +クールタイム"
															,ChatColor.RED+					"10    +MP消費"
															,ChatColor.RED+					"1     +戦士系のみ装備可能"
															,ChatColor.RED+					"10    +魔法領域"
															);
	public final static List<String> resurrectionLore = Arrays.asList(ChatColor.WHITE+		"魔法マテリアル:10"
															,ChatColor.WHITE+				"-範囲魔法-追加効果:復活-効果時間:5分"
															,ChatColor.WHITE+				"20    +クールタイム"
															,ChatColor.WHITE+				"10    +MP消費"
															,ChatColor.WHITE+				"2     +職業"
															,ChatColor.WHITE+				"80    +魔法領域"
															);
	public final static List<String> boostLore = Arrays.asList(ChatColor.GOLD+				"魔法マテリアル:11"
															,ChatColor.GOLD+				"-強化魔法-追加効果:最大体力上昇-効果時間:10分"
															,ChatColor.GOLD+				"30    +クールタイム"
															,ChatColor.GOLD+				"10    +MP消費"
															,ChatColor.GOLD+				"1     +戦士系のみ装備可能"
															,ChatColor.GOLD+				"30    +魔法領域"
															);
	public final static List<String> cureLv2Lore = Arrays.asList(ChatColor.GREEN+			"魔法マテリアル:12"
															,ChatColor.GREEN+				"-範囲魔法-追加効果:回復"
															,ChatColor.GREEN+				"5     +クールタイム"
															,ChatColor.GREEN+				"50%   +MP消費"
															,ChatColor.GREEN+				"2     +職業"
															,ChatColor.GREEN+				"50    +魔法領域"
															);
	public final static List<String> tauntLv2Lore = Arrays.asList(ChatColor.LIGHT_PURPLE+	"魔法マテリアル:13"
															,ChatColor.LIGHT_PURPLE+		"-無属性-範囲魔法"
															,ChatColor.LIGHT_PURPLE+		"5     +クールタイム"
															,ChatColor.LIGHT_PURPLE+		"0     +MP消費"
															,ChatColor.LIGHT_PURPLE+		"1     +戦士系のみ装備可能"
															,ChatColor.LIGHT_PURPLE+		"20    +魔法領域"
															);
	public final static List<String> jumpLv2Lore = Arrays.asList(ChatColor.BLUE+			"魔法マテリアル:14"
															,ChatColor.BLUE+				"-強化魔法"
															,ChatColor.BLUE+				"10    +クールタイム"
															,ChatColor.BLUE+				"0     +MP消費"
															,ChatColor.BLUE+				"0     +全ての職で装備可能"
															,ChatColor.BLUE+				"60    +魔法領域"
															);
	public final static List<String> walkSpeedLv2Lore = Arrays.asList(ChatColor.GREEN+		"魔法マテリアル:15"
															,ChatColor.GREEN+				"-強化魔法-追加効果:移動速度上昇"
															,ChatColor.GREEN+				"10    +クールタイム"
															,ChatColor.GREEN+				"0     +MP消費"
															,ChatColor.GREEN+				"0     +全ての職で装備可能"
															,ChatColor.GREEN+				"25    +魔法領域"
															);
	public final static List<String> holyLv1Lore = Arrays.asList(ChatColor.WHITE+			"魔法マテリアル:16"
															,ChatColor.WHITE+				"-光属性"
															,ChatColor.WHITE+				"10    +クールタイム"
															,ChatColor.WHITE+				"0     +MP消費"
															,ChatColor.WHITE+				"2     +プリースト系のみ装備可能"
															,ChatColor.WHITE+				"10    +魔法領域"
															);
	public final static List<String> holyLv2Lore = Arrays.asList(ChatColor.WHITE+			"魔法マテリアル:17"
															,ChatColor.WHITE+				"-光属性-範囲魔法"
															,ChatColor.WHITE+				"15    +クールタイム"
															,ChatColor.WHITE+				"0     +MP消費"
															,ChatColor.WHITE+				"2     +プリースト系のみ装備可能"
															,ChatColor.WHITE+				"20    +魔法領域"
															);
	public final static List<String> gravityLv1Lore = Arrays.asList(ChatColor.DARK_GRAY+	"魔法マテリアル:18"
															,ChatColor.DARK_GRAY+				"-闇属性-追加効果:重力"
															,ChatColor.DARK_GRAY+				"5     +クールタイム"
															,ChatColor.DARK_GRAY+				"3     +MP消費"
															,ChatColor.DARK_GRAY+				"3     +魔術師系のみ装備可能"
															,ChatColor.DARK_GRAY+				"10    +魔法領域"
															);
	public final static List<String> untigravityLv1Lore = Arrays.asList(ChatColor.DARK_GRAY+		"魔法マテリアル:19"
															,ChatColor.DARK_GRAY+				"-闇属性-追加効果:反重力"
															,ChatColor.DARK_GRAY+				"5     +クールタイム"
															,ChatColor.DARK_GRAY+				"3     +MP消費"
															,ChatColor.DARK_GRAY+				"3     +魔術師系のみ装備可能"
															,ChatColor.DARK_GRAY+				"20    +魔法領域"
															);
	public final static List<String> villagerLv1Lore = Arrays.asList(ChatColor.GOLD+		"魔法マテリアル:20"
															,ChatColor.GOLD+				"アプデ作業お疲れ様でした!"
															,ChatColor.GOLD+				"1     +クールタイム"
															,ChatColor.GOLD+				"0     +MP消費"
															,ChatColor.GOLD+				"0     +全ての職で装備可能"
															,ChatColor.GOLD+				"0     +魔法領域"
															);
	public final static List<String> magicArrowLv1Lore = Arrays.asList(ChatColor.WHITE+		"魔法マテリアル:21"
															,ChatColor.WHITE+				"-追加効果:生成"
															,ChatColor.WHITE+				"30    +クールタイム"
															,ChatColor.WHITE+				"20    +MP消費"
															,ChatColor.WHITE+				"4     +アーチャー系のみ装備可能"
															,ChatColor.WHITE+				"50    +魔法領域"
															);
	public final static List<String> directionLv1Lore = Arrays.asList(ChatColor.GREEN+		"魔法マテリアル:22"
															,ChatColor.GREEN+				"-追加効果:矢無効-効果時間:2分"
															,ChatColor.GREEN+				"5     +クールタイム"
															,ChatColor.GREEN+				"3     +MP消費"
															,ChatColor.GREEN+				"4     +アーチャー系のみ装備可能"
															,ChatColor.GREEN+				"10    +魔法領域"
															);
	public final static List<String> devideLv1Lore = Arrays.asList(ChatColor.YELLOW+		"魔法マテリアル:23"
															,ChatColor.YELLOW+				"-強化魔法-追加効果:分裂"
															,ChatColor.YELLOW+				"5     +クールタイム"
															,ChatColor.YELLOW+				"3     +MP消費"
															,ChatColor.YELLOW+				"4     +アーチャー系のみ装備可能"
															,ChatColor.YELLOW+				"10    +魔法領域"
															);
	public final static List<String> chargeLv1Lore = Arrays.asList(ChatColor.LIGHT_PURPLE+		"魔法マテリアル:24"
															,ChatColor.LIGHT_PURPLE+				"-強化魔法-追加効果:MP回復"
															,ChatColor.LIGHT_PURPLE+				"10    +クールタイム"
															,ChatColor.LIGHT_PURPLE+				"0     +MP消費"
															,ChatColor.LIGHT_PURPLE+				"0     +全ての職で装備可能"
															,ChatColor.LIGHT_PURPLE+				"10    +魔法領域"
															);
	public final static List<String> invisiblehandsLv1Lore = Arrays.asList(ChatColor.DARK_GRAY+		"魔法マテリアル:25"
															,ChatColor.DARK_GRAY+				"-陰属性-追加効果:怠惰なる権能"
															,ChatColor.DARK_GRAY+				"10    +クールタイム"
															,ChatColor.DARK_GRAY+				"5     +MP消費"
															,ChatColor.DARK_GRAY+				"0     +全ての職で装備可能"
															,ChatColor.DARK_GRAY+				"5    +魔法領域"
															);
	public final static List<String> invisiblehandsLv2Lore = Arrays.asList(ChatColor.DARK_GRAY+		"魔法マテリアル:26"
															,ChatColor.DARK_GRAY+				"-陰属性-追加効果:怠惰なる権能"
															,ChatColor.DARK_GRAY+				"7    +クールタイム"
															,ChatColor.DARK_GRAY+				"10     +MP消費"
															,ChatColor.DARK_GRAY+				"0     +全ての職で装備可能"
															,ChatColor.DARK_GRAY+				"10    +魔法領域"
															);
	public final static List<String> invisiblehandsLv3Lore = Arrays.asList(ChatColor.DARK_GRAY+		"魔法マテリアル:27"
															,ChatColor.DARK_GRAY+				"-陰属性-追加効果:怠惰なる権能"
															,ChatColor.DARK_GRAY+				"15    +クールタイム"
															,ChatColor.DARK_GRAY+				"10     +MP消費"
															,ChatColor.DARK_GRAY+				"3     +魔術師系のみ装備可能"
															,ChatColor.DARK_GRAY+				"20    +魔法領域"
															);

	public MagicMaterial(Magic _plugin) {
		plugin=_plugin;

		ItemMeta blizzardMeta = blizzard.getItemMeta();
		blizzardMeta.setDisplayName(ChatColor.AQUA+""+ChatColor.BOLD+"ブリザード");
		blizzardMeta.setLore(blizzardLore);
		blizzard.setItemMeta(blizzardMeta);
		blizzard.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS,1);

		ItemMeta fireMeta = fire.getItemMeta();
		fireMeta.setDisplayName(ChatColor.RED+""+ChatColor.BOLD+"ファイア");
		fireMeta.setLore(fireLore);
		fire.setItemMeta(fireMeta);
		fire.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS,1);

		ItemMeta tauntLv1Meta = tauntLv1.getItemMeta();
		tauntLv1Meta.setDisplayName(ChatColor.LIGHT_PURPLE+""+ChatColor.BOLD+"挑発Lv1");
		tauntLv1Meta.setLore(tauntLv1Lore);
		tauntLv1.setItemMeta(tauntLv1Meta);
		tauntLv1.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS,1);

		ItemMeta tauntLv2Meta = tauntLv2.getItemMeta();
		tauntLv2Meta.setDisplayName(ChatColor.LIGHT_PURPLE+""+ChatColor.BOLD+"挑発Lv2");
		tauntLv2Meta.setLore(tauntLv2Lore);
		tauntLv2.setItemMeta(tauntLv2Meta);
		tauntLv2.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS,1);

		ItemMeta thunderMeta = thunder.getItemMeta();
		thunderMeta.setDisplayName(ChatColor.YELLOW+""+ChatColor.BOLD+"サンダー");
		thunderMeta.setLore(thunderLore);
		thunder.setItemMeta(thunderMeta);
		thunder.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS,1);

		ItemMeta cureLv1Meta = cureLv1.getItemMeta();
		cureLv1Meta.setDisplayName(ChatColor.GREEN+""+ChatColor.BOLD+"キュア");
		cureLv1Meta.setLore(cureLv1Lore);
		cureLv1.setItemMeta(cureLv1Meta);
		cureLv1.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS,1);

		ItemMeta cureLv2Meta = cureLv2.getItemMeta();
		cureLv2Meta.setDisplayName(ChatColor.GREEN+""+ChatColor.BOLD+"キュアル");
		cureLv2Meta.setLore(cureLv2Lore);
		cureLv2.setItemMeta(cureLv2Meta);
		cureLv2.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS,1);

		ItemMeta walkSpeedLv1Meta = walkSpeedLv1.getItemMeta();
		walkSpeedLv1Meta.setDisplayName(ChatColor.GREEN+""+ChatColor.BOLD+"緑水晶");
		walkSpeedLv1Meta.setLore(walkSpeedLv1Lore);
		walkSpeedLv1.setItemMeta(walkSpeedLv1Meta);
		walkSpeedLv1.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS,1);

		ItemMeta walkSpeedLv2Meta = walkSpeedLv2.getItemMeta();
		walkSpeedLv2Meta.setDisplayName(ChatColor.GREEN+""+ChatColor.BOLD+"翠水晶");
		walkSpeedLv2Meta.setLore(walkSpeedLv2Lore);
		walkSpeedLv2.setItemMeta(walkSpeedLv2Meta);
		walkSpeedLv2.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS,1);

		ItemMeta jumpLv1Meta = jumpLv1.getItemMeta();
		jumpLv1Meta.setDisplayName(ChatColor.AQUA+""+ChatColor.BOLD+"青水晶");
		jumpLv1Meta.setLore(jumpLv1Lore);
		jumpLv1.setItemMeta(jumpLv1Meta);
		jumpLv1.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS,1);

		ItemMeta jumpLv2Meta = jumpLv2.getItemMeta();
		jumpLv2Meta.setDisplayName(ChatColor.BLUE+""+ChatColor.BOLD+"蒼水晶");
		jumpLv2Meta.setLore(jumpLv2Lore);
		jumpLv2.setItemMeta(jumpLv2Meta);
		jumpLv2.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS,1);

		ItemMeta wallMeta = wall.getItemMeta();
		wallMeta.setDisplayName(ChatColor.WHITE+""+ChatColor.BOLD+"プロテクト");
		wallMeta.setLore(wallLore);
		wall.setItemMeta(wallMeta);
		wall.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS,1);

		ItemMeta attackMeta = attack.getItemMeta();
		attackMeta.setDisplayName(ChatColor.RED+""+ChatColor.BOLD+"バースト");
		attackMeta.setLore(attackLore);
		attack.setItemMeta(attackMeta);
		attack.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS,1);

		ItemMeta resurrectionMeta = resurrection.getItemMeta();
		resurrectionMeta.setDisplayName(ChatColor.WHITE+""+ChatColor.BOLD+"リザレクション");
		resurrectionMeta.setLore(resurrectionLore);
		resurrection.setItemMeta(resurrectionMeta);
		resurrection.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS,1);

		ItemMeta boostMeta = boost.getItemMeta();
		boostMeta.setDisplayName(ChatColor.GOLD+""+ChatColor.BOLD+"ブースト");
		boostMeta.setLore(boostLore);
		boost.setItemMeta(boostMeta);
		boost.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS,1);

		ItemMeta holyLv1Meta = holyLv1.getItemMeta();
		holyLv1Meta.setDisplayName(ChatColor.WHITE+""+ChatColor.BOLD+"ホーリー");
		holyLv1Meta.setLore(holyLv1Lore);
		holyLv1.setItemMeta(holyLv1Meta);
		holyLv1.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS,1);

		ItemMeta holyLv2Meta = holyLv2.getItemMeta();
		holyLv2Meta.setDisplayName(ChatColor.WHITE+""+ChatColor.BOLD+"ホーリラ");
		holyLv2Meta.setLore(holyLv2Lore);
		holyLv2.setItemMeta(holyLv2Meta);
		holyLv2.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS,1);

		ItemMeta gravityLv1Meta = gravityLv1.getItemMeta();
		gravityLv1Meta.setDisplayName(ChatColor.DARK_GRAY+""+ChatColor.BOLD+"グラビティ");
		gravityLv1Meta.setLore(gravityLv1Lore);
		gravityLv1.setItemMeta(gravityLv1Meta);
		gravityLv1.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS,1);

		ItemMeta untigravityLv1Meta = untigravityLv1.getItemMeta();
		untigravityLv1Meta.setDisplayName(ChatColor.DARK_GRAY+""+ChatColor.BOLD+"アンチグラビティ");
		untigravityLv1Meta.setLore(untigravityLv1Lore);
		untigravityLv1.setItemMeta(untigravityLv1Meta);
		untigravityLv1.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS,1);

		ItemMeta villagerMeta = villager.getItemMeta();
		villagerMeta.setDisplayName(ChatColor.GOLD+""+ChatColor.BOLD+"むらびと");
		villagerMeta.setLore(villagerLv1Lore);
		villager.setItemMeta(villagerMeta);
		villager.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS,1);

		ItemMeta magicArrowMeta = magicArrow.getItemMeta();
		magicArrowMeta.setDisplayName(ChatColor.WHITE+""+ChatColor.BOLD+"マジックアロー");
		magicArrowMeta.setLore(magicArrowLv1Lore);
		magicArrow.setItemMeta(magicArrowMeta);
		magicArrow.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS,1);

		ItemMeta directionLv1Meta = directionLv1.getItemMeta();
		directionLv1Meta.setDisplayName(ChatColor.GREEN+""+ChatColor.BOLD+"ディレクション");
		directionLv1Meta.setLore(directionLv1Lore);
		directionLv1.setItemMeta(directionLv1Meta);
		directionLv1.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS,1);

		ItemMeta devideLv1Meta = devideLv1.getItemMeta();
		devideLv1Meta.setDisplayName(ChatColor.YELLOW+""+ChatColor.BOLD+"ディバイド");
		devideLv1Meta.setLore(devideLv1Lore);
		devideLv1.setItemMeta(devideLv1Meta);
		devideLv1.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS,1);

		ItemMeta chargeLv1Meta = chargeLv1.getItemMeta();
		chargeLv1Meta.setDisplayName(ChatColor.LIGHT_PURPLE+""+ChatColor.BOLD+"チャージ");
		chargeLv1Meta.setLore(chargeLv1Lore);
		chargeLv1.setItemMeta(chargeLv1Meta);
		chargeLv1.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS,1);
		ItemMeta invisiblehandsLv1Meta = invisiblehandsLv1.getItemMeta();
		invisiblehandsLv1Meta.setDisplayName(ChatColor.DARK_GRAY+""+ChatColor.BOLD+"見えざる手Lv1");
		invisiblehandsLv1Meta.setLore(invisiblehandsLv1Lore);
		invisiblehandsLv1.setItemMeta(invisiblehandsLv1Meta);
		invisiblehandsLv1.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS,1);

		ItemMeta invisiblehandsLv2Meta = invisiblehandsLv2.getItemMeta();
		invisiblehandsLv2Meta.setDisplayName(ChatColor.DARK_GRAY+""+ChatColor.BOLD+"見えざる手Lv2");
		invisiblehandsLv2Meta.setLore(invisiblehandsLv2Lore);
		invisiblehandsLv2.setItemMeta(invisiblehandsLv2Meta);
		invisiblehandsLv2.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS,1);

		ItemMeta invisiblehandsLv3Meta = invisiblehandsLv3.getItemMeta();
		invisiblehandsLv3Meta.setDisplayName(ChatColor.DARK_GRAY+""+ChatColor.BOLD+"見えざる手Lv3");
		invisiblehandsLv3Meta.setLore(invisiblehandsLv3Lore);
		invisiblehandsLv3.setItemMeta(invisiblehandsLv3Meta);
		invisiblehandsLv3.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS,1);
	}
}
