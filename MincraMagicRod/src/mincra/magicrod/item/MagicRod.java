package mincra.magicrod.item;

import java.util.Arrays;
import java.util.List;

import mincra.magicrod.main.Magic;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

public class MagicRod{
	final public static ItemStack QuartzWand= new ItemStack(Material.BLAZE_ROD,1);
	final public static ItemStack RedStoneWand= new ItemStack(Material.BLAZE_ROD,1);
	final public static ItemStack rod0_3= new ItemStack(Material.BLAZE_ROD,1);
	final public static ItemStack rod0_4= new ItemStack(Material.BLAZE_ROD,1);
	final public static ItemStack rod0_5= new ItemStack(Material.BLAZE_ROD,1);
	final public static ItemStack rod0_6= new ItemStack(Material.BLAZE_ROD,1);
	final public static ItemStack rod0_7= new ItemStack(Material.BLAZE_ROD,1);
	final public static ItemStack rod0_8= new ItemStack(Material.BLAZE_ROD,1);
	final public static ItemStack ExperienceWandLv1= new ItemStack(Material.BLAZE_ROD,1);
	final public static ItemStack ExperienceWandLv2= new ItemStack(Material.BLAZE_ROD,1);
	final public static ItemStack ExperienceWandLv3= new ItemStack(Material.BLAZE_ROD,1);
	final public static ItemStack JumpWandLv1= new ItemStack(Material.BLAZE_ROD,1);
	final public static ItemStack JumpWandLv2= new ItemStack(Material.BLAZE_ROD,1);
	final public static ItemStack JumpWandLv3= new ItemStack(Material.BLAZE_ROD,1);
	final public static ItemStack InfernoWandLv1= new ItemStack(Material.BLAZE_ROD,1);
	final public static ItemStack InfernoWandLv2= new ItemStack(Material.BLAZE_ROD,1);
	final public static ItemStack InfernoWandLv3= new ItemStack(Material.BLAZE_ROD,1);
	final public static ItemStack DestroyWandLv1= new ItemStack(Material.BLAZE_ROD,1);
	final public static ItemStack DestroyWandLv2= new ItemStack(Material.BLAZE_ROD,1);
	final public static ItemStack DestroyWandLv3= new ItemStack(Material.BLAZE_ROD,1);
	final public static ItemStack DestroyWandLv4= new ItemStack(Material.ENCHANTED_BOOK,1);
	final public static ItemStack DestroyPickaxeLv5= new ItemStack(Material.DIAMOND_PICKAXE,1);
	final public static ItemStack QureWandLv1= new ItemStack(Material.BLAZE_ROD,1);
	final public static ItemStack QureWandLv2= new ItemStack(Material.BLAZE_ROD,1);
	final public static ItemStack QureWandLv3= new ItemStack(Material.BLAZE_ROD,1);
	final public static ItemStack MoveWandLv1= new ItemStack(Material.BLAZE_ROD,1);
	final public static ItemStack MoveWandLv2= new ItemStack(Material.BLAZE_ROD,1);
	final public static ItemStack MoveWandLv3= new ItemStack(Material.BLAZE_ROD,1);
	final public static ItemStack IceTreeWandLv1= new ItemStack(Material.BLAZE_ROD,1);
	final public static ItemStack IceTreeWandLv2= new ItemStack(Material.BLAZE_ROD,1);
	final public static ItemStack IceTreeBookLv3= new ItemStack(Material.ENCHANTED_BOOK,1);
	final public static ItemStack IceTreeBookLv4= new ItemStack(Material.ENCHANTED_BOOK,1);
	final public static ItemStack GuardWandLv1= new ItemStack(Material.BLAZE_ROD,1);
	final public static ItemStack GuardWandLv2= new ItemStack(Material.BLAZE_ROD,1);
	final public static ItemStack GuardBookLv3= new ItemStack(Material.ENCHANTED_BOOK,1);
	final public static ItemStack WaterWandLv1= new ItemStack(Material.BLAZE_ROD,1);
	final public static ItemStack BeastsBookLv1= new ItemStack(Material.ENCHANTED_BOOK,1);
	final public static ItemStack BeastsBookLv2= new ItemStack(Material.ENCHANTED_BOOK,1);
	final public static ItemStack BeastsBookLv3= new ItemStack(Material.ENCHANTED_BOOK,1);
	final public static ItemStack CartBookLv1= new ItemStack(Material.ENCHANTED_BOOK,1);
	final public static ItemStack LightningBookLv1= new ItemStack(Material.ENCHANTED_BOOK,1);
	
	final public static List<String> QuartzWand_Lore = Arrays.asList
			(ChatColor.AQUA+			"ROD番号:0-1"
			,ChatColor.AQUA+			"攻撃用魔法。"
			,ChatColor.AQUA+			"1     +杖レベル"
			,ChatColor.AQUA+			"1.5   +クールタイム"
			,ChatColor.AQUA+			"0     +MP消費"
			,ChatColor.DARK_RED+		"0.8   +崩壊確率"
			);
	final public static List<String> RedStoneWand_Lore = Arrays.asList
			(ChatColor.AQUA+			"ROD番号:0-2"
			,ChatColor.AQUA+			"攻撃用魔法。"
			,ChatColor.AQUA+			"2     +杖レベル"
			,ChatColor.AQUA+			"1.4   +クールタイム"
			,ChatColor.AQUA+			"2     +MP消費"
			,ChatColor.DARK_RED+		"0.5   +崩壊確率"
			);
	final public static List<String> ExperienceWandLv1_Lore = Arrays.asList
			(ChatColor.AQUA+			"ROD番号:1-1"
			,ChatColor.AQUA+			"MPがある程度まで回復する杖。"
			,ChatColor.AQUA+			"1     +杖レベル"
			,ChatColor.AQUA+			"5     +クールタイム"
			,ChatColor.AQUA+			"0     +MP消費"
			,ChatColor.DARK_RED+		"0.1   +崩壊確率"
			);
	final public static List<String> ExperienceWandLv2_Lore = Arrays.asList
			(ChatColor.AQUA+			"ROD番号:1-2"
			,ChatColor.AQUA+			"MPがある程度まで中回復する杖。"
			,ChatColor.AQUA+			"2     +杖レベル"
			,ChatColor.AQUA+			"5     +クールタイム"
			,ChatColor.AQUA+			"2     +MP消費"
			,ChatColor.DARK_RED+		"0.01  +崩壊確率"
			);
	final public static List<String> ExperienceWandLv3_Lore = Arrays.asList
			(ChatColor.AQUA+			"ROD番号:1-3"
			,ChatColor.AQUA+			"MPが少量回復する杖。"
			,ChatColor.AQUA+			"3     +杖レベル"
			,ChatColor.AQUA+			"0     +クールタイム"
			,ChatColor.AQUA+			"0     +MP消費"
			,ChatColor.DARK_RED+		"0.01  +崩壊確率"
			);
	final public static List<String> JumpWandLv1_Lore = Arrays.asList
			(ChatColor.AQUA+			"ROD番号:2-1"
			,ChatColor.AQUA+			"空中でジャンプができる。"
			,ChatColor.AQUA+			"1     +杖レベル"
			,ChatColor.AQUA+			"0.8   +クールタイム"
			,ChatColor.AQUA+			"0     +MP消費"
			,ChatColor.DARK_RED+		"0.01  +崩壊確率"
			);
	final public static List<String> JumpWandLv2_Lore = Arrays.asList
			(ChatColor.AQUA+			"ROD番号:2-2"
			,ChatColor.AQUA+			"空中でジャンプができる。"
			,ChatColor.AQUA+			"2     +杖レベル"
			,ChatColor.AQUA+			"1.4   +クールタイム"
			,ChatColor.AQUA+			"1     +MP消費"
			,ChatColor.DARK_RED+		"0.01  +崩壊確率"
			);
	final public static List<String> JumpWandLv3_Lore = Arrays.asList
			(ChatColor.AQUA+			"ROD番号:2-3"
			,ChatColor.AQUA+			"空中でジャンプができる。"
			,ChatColor.AQUA+			"3     +杖レベル"
			,ChatColor.AQUA+			"2.0   +クールタイム"
			,ChatColor.AQUA+			"0     +MP消費"
			,ChatColor.DARK_RED+		"0.01  +崩壊確率"
			);
	final public static List<String> InfernoWandLv1_Lore = Arrays.asList
			(ChatColor.AQUA+			"ROD番号:3-1"
			,ChatColor.AQUA+			"灼熱の炎に抱かれろ。"
			,ChatColor.AQUA+			"1     +杖レベル"
			,ChatColor.AQUA+			"1     +クールタイム"
			,ChatColor.AQUA+			"1     +MP消費"
			,ChatColor.DARK_RED+		"0.01  +崩壊確率"
			);
	final public static List<String> InfernoWandLv2_Lore = Arrays.asList
			(ChatColor.AQUA+			"ROD番号:3-2"
			,ChatColor.AQUA+			"灼熱の炎に抱かれろ。"
			,ChatColor.AQUA+			"2     +杖レベル"
			,ChatColor.AQUA+			"2     +クールタイム"
			,ChatColor.AQUA+			"4     +MP消費"
			,ChatColor.DARK_RED+		"0.01  +崩壊確率"
			);
	final public static List<String> InfernoWandLv3_Lore = Arrays.asList
			(ChatColor.AQUA+			"ROD番号:3-3"
			,ChatColor.AQUA+			"灼熱の炎に抱かれろ。"
			,ChatColor.AQUA+			"3     +杖レベル"
			,ChatColor.AQUA+			"3     +クールタイム"
			,ChatColor.AQUA+			"6     +MP消費"
			,ChatColor.DARK_RED+		"0.05  +崩壊確率"
			);
	final public static List<String> DestroyWandLv1_Lore = Arrays.asList
			(ChatColor.AQUA+			"ROD番号:4-1"
			,ChatColor.AQUA+			"採掘速度が少し上がる杖。"
			,ChatColor.AQUA+			"1     +杖レベル"
			,ChatColor.AQUA+			"360   +クールタイム"
			,ChatColor.AQUA+			"5     +MP消費"
			,ChatColor.DARK_RED+		"0.1   +崩壊確率"
			);
	final public static List<String> DestroyWandLv2_Lore = Arrays.asList
			(ChatColor.AQUA+			"ROD番号:4-2"
			,ChatColor.AQUA+			"採掘速度が上がる杖。"
			,ChatColor.AQUA+			"2     +杖レベル"
			,ChatColor.AQUA+			"180   +クールタイム"
			,ChatColor.AQUA+			"5     +MP消費"
			,ChatColor.DARK_RED+		"0.1  +崩壊確率"
			);
	final public static List<String> DestroyWandLv3_Lore = Arrays.asList
			(ChatColor.AQUA+			"ROD番号:4-3"
			,ChatColor.AQUA+			"採掘速度が大きくあがる杖。"
			,ChatColor.AQUA+			"3     +杖レベル"
			,ChatColor.AQUA+			"90    +クールタイム"
			,ChatColor.AQUA+			"5     +MP消費"
			,ChatColor.DARK_RED+		"0.1   +崩壊確率"
			);
	final public static List<String> DestroyBookLv4_Lore = Arrays.asList
			(ChatColor.AQUA+			"ROD番号:4-4"
			,ChatColor.AQUA+			"周囲の味方の採掘速度が大きくあがる書。"
			,ChatColor.AQUA+			"4     +杖レベル"
			,ChatColor.AQUA+			"1     +クールタイム"
			,ChatColor.AQUA+			"10    +MP消費"
			,ChatColor.DARK_RED+		"0.01  +崩壊確率"
			);
	final public static List<String> DestroyPickaxeLv5_Lore = Arrays.asList
			(ChatColor.AQUA+			"ROD番号:4-5"
			,ChatColor.AQUA+			"周囲の味方の採掘速度が大きくあがるつるはし。"
			,ChatColor.AQUA+			"5     +杖レベル"
			,ChatColor.AQUA+			"30    +クールタイム"
			,ChatColor.AQUA+			"3     +MP消費"
			,ChatColor.DARK_RED+		"0.01  +崩壊確率"
			);
	final public static List<String> QureWandLv1_Lore = Arrays.asList
			(ChatColor.AQUA+			"ROD番号:5-1"
			,ChatColor.AQUA+			"周囲3マスの味方の体力を少量回復。"
			,ChatColor.AQUA+			"1     +杖レベル"
			,ChatColor.AQUA+			"2     +クールタイム"
			,ChatColor.AQUA+			"2     +MP消費"
			,ChatColor.DARK_RED+		"0.1   +崩壊確率"
			);
	final public static List<String> QureWandLv2_Lore = Arrays.asList
			(ChatColor.AQUA+			"ROD番号:5-2"
			,ChatColor.AQUA+			"周囲6マスの味方の体力を回復。"
			,ChatColor.AQUA+			"2     +杖レベル"
			,ChatColor.AQUA+			"4    +クールタイム"
			,ChatColor.AQUA+			"2     +MP消費"
			,ChatColor.DARK_RED+		"0.1   +崩壊確率"
			);
	final public static List<String> QureWandLv3_Lore = Arrays.asList
			(ChatColor.AQUA+			"ROD番号:5-3"
			,ChatColor.AQUA+			"周囲9マスの味方の体力を大きく回復。"
			,ChatColor.AQUA+			"3     +杖レベル"
			,ChatColor.AQUA+			"6     +クールタイム"
			,ChatColor.AQUA+			"6     +MP消費"
			,ChatColor.DARK_RED+		"0.1   +崩壊確率"
			);
	final public static List<String> MoveWandLv1_Lore = Arrays.asList
			(ChatColor.AQUA+			"ROD番号:6-1"
			,ChatColor.AQUA+			"自分自身を加速させる。"
			,ChatColor.AQUA+			"1     +杖レベル"
			,ChatColor.AQUA+			"0     +クールタイム"
			,ChatColor.AQUA+			"0     +MP消費"
			,ChatColor.DARK_RED+		"0.01  +崩壊確率"
			);
	final public static List<String> MoveWandLv2_Lore = Arrays.asList
			(ChatColor.AQUA+			"ROD番号:6-2"
			,ChatColor.AQUA+			"自分自身を加速させる。"
			,ChatColor.AQUA+			"2     +杖レベル"
			,ChatColor.AQUA+			"0     +クールタイム"
			,ChatColor.AQUA+			"0     +MP消費"
			,ChatColor.DARK_RED+		"0.01  +崩壊確率"
			);
	final public static List<String> MoveWandLv3_Lore = Arrays.asList
			(ChatColor.AQUA+			"ROD番号:6-3"
			,ChatColor.AQUA+			"自分自身を加速させる。"
			,ChatColor.AQUA+			"3     +杖レベル"
			,ChatColor.AQUA+			"0     +クールタイム"
			,ChatColor.AQUA+			"0     +MP消費"
			,ChatColor.DARK_RED+		"0.01  +崩壊確率"
			);
	final public static List<String> IceTreeWandLv1_Lore = Arrays.asList
			(ChatColor.AQUA+			"ROD番号:7-1"
			,ChatColor.AQUA+			"氷柱で相手にダメージを与える。"
			,ChatColor.AQUA+			"1     +杖レベル"
			,ChatColor.AQUA+			"5     +クールタイム"
			,ChatColor.AQUA+			"0     +MP消費"
			,ChatColor.DARK_RED+		"0.01  +崩壊確率"
			);
	final public static List<String> IceTreeWandLv2_Lore = Arrays.asList
			(ChatColor.AQUA+			"ROD番号:7-2"
			,ChatColor.AQUA+			"氷柱で相手にダメージを与える。"
			,ChatColor.AQUA+			"2     +杖レベル"
			,ChatColor.AQUA+			"10    +クールタイム"
			,ChatColor.AQUA+			"3     +MP消費"
			,ChatColor.DARK_RED+		"0.01  +崩壊確率"
			);
	final public static List<String> IceTreeBookLv3_Lore = Arrays.asList
			(ChatColor.AQUA+			"ROD番号:7-3"
			,ChatColor.AQUA+			"氷柱で相手にダメージを与える。"
			,ChatColor.AQUA+			"3     +杖レベル"
			,ChatColor.AQUA+			"10    +クールタイム"
			,ChatColor.AQUA+			"6    +MP消費"
			,ChatColor.DARK_RED+		"0.01  +崩壊確率"
			);
	final public static List<String> IceTreeBookLv4_Lore = Arrays.asList
			(ChatColor.AQUA+			"ROD番号:7-4"
			,ChatColor.AQUA+			"氷柱で相手にダメージを与える。"
			,ChatColor.AQUA+			"4     +杖レベル"
			,ChatColor.AQUA+			"20    +クールタイム"
			,ChatColor.AQUA+			"9     +MP消費"
			,ChatColor.DARK_RED+		"0.01  +崩壊確率"
			);
	final public static List<String> GuardWandLv1_Lore = Arrays.asList
			(ChatColor.AQUA+			"ROD番号:8-1"
			,ChatColor.AQUA+			"味方の防御力を上昇させる。"
			,ChatColor.AQUA+			"1     +杖レベル"
			,ChatColor.AQUA+			"0     +クールタイム"
			,ChatColor.AQUA+			"3     +MP消費"
			,ChatColor.DARK_RED+		"0.01  +崩壊確率"
			);
	final public static List<String> GuardWandLv2_Lore = Arrays.asList
			(ChatColor.AQUA+			"ROD番号:8-2"
			,ChatColor.AQUA+			"味方の防御力を大きく上昇させる。"
			,ChatColor.AQUA+			"2     +杖レベル"
			,ChatColor.AQUA+			"0     +クールタイム"
			,ChatColor.AQUA+			"6     +MP消費"
			,ChatColor.DARK_RED+		"0.01  +崩壊確率"
			);
	final public static List<String> GuardBookLv3_Lore = Arrays.asList
			(ChatColor.AQUA+			"ROD番号:8-3"
			,ChatColor.AQUA+			"周囲の味方の防御力を大きく上昇させる。"
			,ChatColor.AQUA+			"3     +杖レベル"
			,ChatColor.AQUA+			"0     +クールタイム"
			,ChatColor.AQUA+			"9     +MP消費"
			,ChatColor.DARK_RED+		"0.01  +崩壊確率"
			);
	final public static List<String> WaterWand_Lore = Arrays.asList
			(ChatColor.AQUA+			"ROD番号:9-1"
			,ChatColor.AQUA+			"水中での移動速度が飛躍的に上がる。"
			,ChatColor.AQUA+			"1     +杖レベル"
			,ChatColor.AQUA+			"45    +クールタイム"
			,ChatColor.AQUA+			"0     +MP消費"
			,ChatColor.DARK_RED+		"1     +崩壊確率"
			);
	final public static List<String> BeastsBookLv1_Lore = Arrays.asList
			(ChatColor.AQUA+			"ROD番号:10-1"
			,ChatColor.AQUA+			"幻獣を召還する。"
			,ChatColor.AQUA+			"1     +杖レベル"
			,ChatColor.AQUA+			"10    +クールタイム"
			,ChatColor.AQUA+			"0     +MP消費"
			,ChatColor.DARK_RED+		"0.01  +崩壊確率"
			);
	final public static List<String> BeastsBookLv2_Lore = Arrays.asList
			(ChatColor.AQUA+			"ROD番号:10-2"
			,ChatColor.AQUA+			"幻獣を召還する。"
			,ChatColor.AQUA+			"2     +杖レベル"
			,ChatColor.AQUA+			"25    +クールタイム"
			,ChatColor.AQUA+			"1     +MP消費"
			,ChatColor.DARK_RED+		"0.01  +崩壊確率"
			);
	final public static List<String> BeastsBookLv3_Lore = Arrays.asList
			(ChatColor.AQUA+			"ROD番号:10-3"
			,ChatColor.AQUA+			"幻獣を召還する。"
			,ChatColor.AQUA+			"3     +杖レベル"
			,ChatColor.AQUA+			"40    +クールタイム"
			,ChatColor.AQUA+			"2     +MP消費"
			,ChatColor.DARK_RED+		"0.01  +崩壊確率"
			);
	final public static List<String> CartBook_Lore = Arrays.asList
			(ChatColor.AQUA+			"ROD番号:11-1"
			,ChatColor.AQUA+			"自分が乗っているトロッコを加速させる。"
			,ChatColor.AQUA+			"1     +杖レベル"
			,ChatColor.AQUA+			"3     +クールタイム"
			,ChatColor.AQUA+			"0     +MP消費"
			,ChatColor.DARK_RED+		"0.01  +崩壊確率"
			);
	final public static List<String> LightningBookLv1_Lore = Arrays.asList
			(ChatColor.AQUA+			"ROD番号:12-1"
			,ChatColor.AQUA+			"聖なる力よ我に！"
			,ChatColor.AQUA+			"1     +杖レベル"
			,ChatColor.AQUA+			"5     +クールタイム"
			,ChatColor.AQUA+			"0     +MP消費"
			,ChatColor.DARK_RED+		"0.01  +崩壊確率"
			);

	@SuppressWarnings("deprecation")
	public MagicRod(Magic plugin) {
		ItemMeta QuartzWand_Meta=QuartzWand.getItemMeta();
			QuartzWand_Meta.setDisplayName(ChatColor.BOLD+"クウォーツの杖");
			QuartzWand_Meta.setLore(QuartzWand_Lore);
			QuartzWand.setItemMeta(QuartzWand_Meta);
				ShapedRecipe QuartzWand_Recipe = new ShapedRecipe(QuartzWand);
				QuartzWand_Recipe.shape(
						"  m",
						" m ",
						"r  ");
				QuartzWand_Recipe.setIngredient('m', Material.QUARTZ);
				QuartzWand_Recipe.setIngredient('r', Material.BLAZE_ROD);
				plugin.getServer().addRecipe(QuartzWand_Recipe);
		ItemMeta RedStoneWand_Meta=RedStoneWand.getItemMeta();
			RedStoneWand_Meta.setDisplayName(ChatColor.RED+"レッドストーンの杖");
			RedStoneWand_Meta.setLore(RedStoneWand_Lore);
			RedStoneWand.setItemMeta(RedStoneWand_Meta);
				ShapedRecipe RedStoneWand_Recipe = new ShapedRecipe(RedStoneWand);
				RedStoneWand_Recipe.shape(
						"  m",
						" m ",
						"r  ");
				RedStoneWand_Recipe.setIngredient('m', Material.REDSTONE);
				RedStoneWand_Recipe.setIngredient('r', Material.BLAZE_ROD);
				plugin.getServer().addRecipe(RedStoneWand_Recipe);
		ItemMeta ExperienceWandLv1_Meta=ExperienceWandLv1.getItemMeta();
			ExperienceWandLv1_Meta.setDisplayName(ChatColor.GOLD+""+ChatColor.BOLD+"エクスペ杖");
			ExperienceWandLv1_Meta.setLore(ExperienceWandLv1_Lore);
			ExperienceWandLv1.setItemMeta(ExperienceWandLv1_Meta);
				ShapedRecipe xrod1 = new ShapedRecipe(ExperienceWandLv1);
				xrod1.shape(
						" ed",
						" be",
						"b  ");
				xrod1.setIngredient('e', Material.EXP_BOTTLE);
				xrod1.setIngredient('b', Material.BLAZE_ROD);
				xrod1.setIngredient('d', Material.DIAMOND);
				plugin.getServer().addRecipe(xrod1);
		ItemMeta ExperienceWandLv2_Meta=ExperienceWandLv2.getItemMeta();
			ExperienceWandLv2_Meta.setDisplayName(ChatColor.GOLD+""+ChatColor.BOLD+"エクスペ杖lv2");
			ExperienceWandLv2_Meta.setLore(ExperienceWandLv2_Lore);
			ExperienceWandLv2.setItemMeta(ExperienceWandLv2_Meta);
				ShapedRecipe ExperienceWandLv2_Recipe = new ShapedRecipe(ExperienceWandLv2);
				ExperienceWandLv2_Recipe.shape(
						" de",
						" bd",
						"b  ");
				ExperienceWandLv2_Recipe.setIngredient('e', Material.EXP_BOTTLE);
				ExperienceWandLv2_Recipe.setIngredient('b', Material.BLAZE_ROD);
				ExperienceWandLv2_Recipe.setIngredient('d', Material.DIAMOND);
				plugin.getServer().addRecipe(ExperienceWandLv2_Recipe);
		ItemMeta ExperienceWandLv3_Meta=ExperienceWandLv3.getItemMeta();
			ExperienceWandLv3_Meta.setDisplayName(ChatColor.GOLD+""+ChatColor.BOLD+"エクスペ杖lv3");
			ExperienceWandLv3_Meta.setLore(ExperienceWandLv3_Lore);
			ExperienceWandLv3.setItemMeta(ExperienceWandLv3_Meta);
				ShapedRecipe ExperienceWandLv3_Recipe = new ShapedRecipe(ExperienceWandLv3);
				ExperienceWandLv3_Recipe.shape(
						"bde",
						" bd",
						"b b");
				ExperienceWandLv3_Recipe.setIngredient('e', Material.EXP_BOTTLE);
				ExperienceWandLv3_Recipe.setIngredient('b', Material.BLAZE_ROD);
				ExperienceWandLv3_Recipe.setIngredient('d', Material.DIAMOND_BLOCK);
				plugin.getServer().addRecipe(ExperienceWandLv3_Recipe);
		ItemMeta irod2=JumpWandLv1.getItemMeta();
			irod2.setDisplayName(ChatColor.AQUA+""+ChatColor.BOLD+"青水晶の杖");
			irod2.setLore(JumpWandLv1_Lore);
			JumpWandLv1.setItemMeta(irod2);
				ShapedRecipe xrod2 = new ShapedRecipe(JumpWandLv1);
				xrod2.shape(
						"www",
						"wbw",
						"www");
				xrod2.setIngredient('w', Material.FEATHER);
				xrod2.setIngredient('b', Material.BLAZE_ROD);
				plugin.getServer().addRecipe(xrod2);
		ItemMeta irod2_2=JumpWandLv2.getItemMeta();
			irod2_2.setDisplayName(ChatColor.AQUA+""+ChatColor.BOLD+"蒼水晶の杖lv2");
			irod2_2.setLore(JumpWandLv2_Lore);
			JumpWandLv2.setItemMeta(irod2_2);
				ShapedRecipe xrod2_2 = new ShapedRecipe(JumpWandLv2);
				xrod2_2.shape(
						"wqw",
						"qbq",
						"wqw");
				xrod2_2.setIngredient('w', Material.FEATHER);
				xrod2_2.setIngredient('b', Material.BLAZE_ROD);
				MaterialData Lapis=new MaterialData(Material.INK_SACK);
				Lapis.setData((byte) 4);
				xrod2_2.setIngredient('q', Lapis);
				plugin.getServer().addRecipe(xrod2_2);
		ItemMeta irod2_3=JumpWandLv3.getItemMeta();
			irod2_3.setDisplayName(ChatColor.AQUA+""+ChatColor.BOLD+"蒼水晶の杖lv3");
			irod2_3.setLore(JumpWandLv3_Lore);
			JumpWandLv3.setItemMeta(irod2_3);
				ShapedRecipe xrod2_3 = new ShapedRecipe(JumpWandLv3);
				xrod2_3.shape(
						"ldl",
						"fbf",
						"fbf");
				xrod2_3.setIngredient('f', Material.FEATHER);
				xrod2_3.setIngredient('b', Material.BLAZE_ROD);
				xrod2_3.setIngredient('d', Material.DIAMOND_BLOCK);
				xrod2_3.setIngredient('l', Lapis);
				plugin.getServer().addRecipe(xrod2_3);
		ItemMeta irod3=InfernoWandLv1.getItemMeta();
			irod3.setDisplayName(ChatColor.RED+""+ChatColor.BOLD+"インフェルノ杖");
			irod3.setLore(InfernoWandLv1_Lore);
			InfernoWandLv1.setItemMeta(irod3);
				ShapedRecipe xrod3 = new ShapedRecipe(InfernoWandLv1);
				xrod3.shape(
						"ldl",
						"gbg",
						"gbg");
				xrod3.setIngredient('l', Material.LAVA_BUCKET);
				xrod3.setIngredient('b', Material.BLAZE_ROD);
				xrod3.setIngredient('d', Material.IRON_BLOCK);
				xrod3.setIngredient('g', Material.IRON_HELMET);
				plugin.getServer().addRecipe(xrod3);
		ItemMeta irod3_2=InfernoWandLv2.getItemMeta();
			irod3_2.setDisplayName(ChatColor.RED+""+ChatColor.BOLD+"インフェルノ杖lv2");
			irod3_2.setLore(InfernoWandLv2_Lore);
			InfernoWandLv2.setItemMeta(irod3_2);
				ShapedRecipe xrod3_2 = new ShapedRecipe(InfernoWandLv2);
				xrod3_2.shape(
						"ldl",
						"gbg",
						"gbg");
				xrod3_2.setIngredient('l', Material.LAVA_BUCKET);
				xrod3_2.setIngredient('b', Material.BLAZE_ROD);
				xrod3_2.setIngredient('d', Material.GOLD_BLOCK);
				xrod3_2.setIngredient('g', Material.GOLD_HELMET);
				plugin.getServer().addRecipe(xrod3_2);
		ItemMeta irod3_3=InfernoWandLv3.getItemMeta();
			irod3_3.setDisplayName(ChatColor.RED+""+ChatColor.BOLD+"インフェルノ杖lv3");
			irod3_3.setLore(InfernoWandLv3_Lore);
			InfernoWandLv3.setItemMeta(irod3_3);
				ShapedRecipe xrod3_3 = new ShapedRecipe(InfernoWandLv3);
				xrod3_3.shape(
						"ldl",
						"gbg",
						"gbg");
				xrod3_3.setIngredient('l', Material.LAVA_BUCKET);
				xrod3_3.setIngredient('b', Material.BLAZE_ROD);
				xrod3_3.setIngredient('d', Material.DIAMOND_BLOCK);
				xrod3_3.setIngredient('g', Material.DIAMOND_HELMET);
				plugin.getServer().addRecipe(xrod3_3);
		ItemMeta irod4=DestroyWandLv1.getItemMeta();
			irod4.setDisplayName(ChatColor.YELLOW+""+ChatColor.BOLD+"破壊の杖");
			irod4.setLore(DestroyWandLv1_Lore);
			DestroyWandLv1.setItemMeta(irod4);
				ShapedRecipe xrod4 = new ShapedRecipe(DestroyWandLv1);
				xrod4.shape(
						"iii",
						" b ",
						" b ");
				xrod4.setIngredient('i', Material.IRON_PICKAXE);
				xrod4.setIngredient('b', Material.BLAZE_ROD);
				plugin.getServer().addRecipe(xrod4);
		ItemMeta irod4_2=DestroyWandLv2.getItemMeta();
			irod4_2.setDisplayName(ChatColor.YELLOW+""+ChatColor.BOLD+"破壊の杖lv2");
			irod4_2.setLore(DestroyWandLv2_Lore);
			DestroyWandLv2.setItemMeta(irod4_2);
				ShapedRecipe xrod4_2 = new ShapedRecipe(DestroyWandLv2);
				xrod4_2.shape(
						"iii",
						" b ",
						" b ");
				xrod4_2.setIngredient('i', Material.GOLD_PICKAXE);
				xrod4_2.setIngredient('b', Material.BLAZE_ROD);
				plugin.getServer().addRecipe(xrod4_2);
		ItemMeta irod4_3=DestroyWandLv3.getItemMeta();
			irod4_3.setDisplayName(ChatColor.YELLOW+""+ChatColor.BOLD+"破壊の杖lv3");
			irod4_3.setLore(DestroyWandLv3_Lore);
			DestroyWandLv3.setItemMeta(irod4_3);
				ShapedRecipe xrod4_3 = new ShapedRecipe(DestroyWandLv3);
				xrod4_3.shape(
						"iii",
						" b ",
						" b ");
				xrod4_3.setIngredient('i', Material.DIAMOND_PICKAXE);
				xrod4_3.setIngredient('b', Material.BLAZE_ROD);
				plugin.getServer().addRecipe(xrod4_3);
		ItemMeta irod4_4=DestroyWandLv4.getItemMeta();
			irod4_4.setDisplayName(ChatColor.AQUA+""+ChatColor.BOLD+"破壊の書lv4");
			irod4_4.setLore(DestroyBookLv4_Lore);
			DestroyWandLv4.setItemMeta(irod4_4);
				ShapedRecipe xrod4_4 = new ShapedRecipe(DestroyWandLv4);
				xrod4_4.shape(
						"iii",
						"ibi",
						"iii");
				xrod4_4.setIngredient('i', Material.DIAMOND_PICKAXE);
				xrod4_4.setIngredient('b', Material.BOOK);
				plugin.getServer().addRecipe(xrod4_4);
		ItemMeta irod4_5=DestroyPickaxeLv5.getItemMeta();
			irod4_5.setDisplayName(ChatColor.AQUA+""+ChatColor.BOLD+"破壊のピッケルlv5");
			irod4_5.setLore(DestroyPickaxeLv5_Lore);
			DestroyPickaxeLv5.setItemMeta(irod4_5);
			DestroyPickaxeLv5.addUnsafeEnchantment(Enchantment.DIG_SPEED,5);
			DestroyPickaxeLv5.addUnsafeEnchantment(Enchantment.DURABILITY,9);
			DestroyPickaxeLv5.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS,2);
				ShapedRecipe xrod4_5 = new ShapedRecipe(DestroyPickaxeLv5);
				xrod4_5.shape(
						"ddd",
						" b ",
						" b ");
				xrod4_5.setIngredient('d', Material.DIAMOND_BLOCK);
				xrod4_5.setIngredient('b', Material.STICK);
				plugin.getServer().addRecipe(xrod4_5);
		ItemMeta irod5=QureWandLv1.getItemMeta();
			irod5.setDisplayName(ChatColor.GREEN+""+ChatColor.BOLD+"癒しの杖");
			irod5.setLore(QureWandLv1_Lore);
			QureWandLv1.setItemMeta(irod5);
				ShapedRecipe xrod5 = new ShapedRecipe(QureWandLv1);
				xrod5.shape(
						" bg",
						" bg",
						"b  ");
				xrod5.setIngredient('g', Material.GOLDEN_CARROT);
				xrod5.setIngredient('b', Material.BLAZE_ROD);
				plugin.getServer().addRecipe(xrod5);
		ItemMeta irod5_2=QureWandLv2.getItemMeta();
			irod5_2.setDisplayName(ChatColor.GREEN+""+ChatColor.BOLD+"癒しの杖lv2");
			irod5_2.setLore(QureWandLv2_Lore);
			QureWandLv2.setItemMeta(irod5_2);
				ShapedRecipe xrod5_2 = new ShapedRecipe(QureWandLv2);
				xrod5_2.shape(
						" bg",
						" bg",
						"b  ");
				xrod5_2.setIngredient('g', Material.GOLDEN_APPLE);
				xrod5_2.setIngredient('b', Material.BLAZE_ROD);
				plugin.getServer().addRecipe(xrod5_2);
		ItemMeta irod5_3=QureWandLv3.getItemMeta();
			irod5_3.setDisplayName(ChatColor.GREEN+""+ChatColor.BOLD+"癒しの杖lv3");
			irod5_3.setLore(QureWandLv3_Lore);
			QureWandLv3.setItemMeta(irod5_3);
				ShapedRecipe xrod5_3 = new ShapedRecipe(QureWandLv3);
				xrod5_3.shape(
						" bg",
						" bg",
						"b  ");
				xrod5_3.setIngredient('g', Material.GOLD_BLOCK);
				xrod5_3.setIngredient('b', Material.BLAZE_ROD);
				plugin.getServer().addRecipe(xrod5_3);
		ItemMeta irod6=MoveWandLv1.getItemMeta();
			irod6.setDisplayName(ChatColor.GREEN+""+ChatColor.BOLD+"翠水晶の杖lv1");
			irod6.setLore(MoveWandLv1_Lore);
			MoveWandLv1.setItemMeta(irod6);
				ShapedRecipe xrod6 = new ShapedRecipe(MoveWandLv1);
				xrod6.shape(
						" fe",
						" bf",
						"b  ");
				xrod6.setIngredient('f', Material.FEATHER);
				xrod6.setIngredient('e', Material.EMERALD);
				xrod6.setIngredient('b', Material.BLAZE_ROD);
				plugin.getServer().addRecipe(xrod6);
		ItemMeta irod6_2=MoveWandLv2.getItemMeta();
			irod6_2.setDisplayName(ChatColor.GREEN+""+ChatColor.BOLD+"翠水晶の杖lv2");
			irod6_2.setLore(MoveWandLv2_Lore);
			MoveWandLv2.setItemMeta(irod6_2);
				ShapedRecipe xrod6_2 = new ShapedRecipe(MoveWandLv2);
				xrod6_2.shape(
						" fe",
						" bf",
						"b  ");
				xrod6_2.setIngredient('f', Material.FEATHER);
				xrod6_2.setIngredient('e', Material.EMERALD_BLOCK);
				xrod6_2.setIngredient('b', Material.BLAZE_ROD);
				plugin.getServer().addRecipe(xrod6_2);
		ItemMeta irod6_3=MoveWandLv3.getItemMeta();
			irod6_3.setDisplayName(ChatColor.GREEN+""+ChatColor.BOLD+"翠水晶の杖lv3");
			irod6_3.setLore(MoveWandLv3_Lore);
			MoveWandLv3.setItemMeta(irod6_3);
				ShapedRecipe xrod6_3 = new ShapedRecipe(MoveWandLv3);
				xrod6_3.shape(
						" de",
						" bd",
						"b  ");
				xrod6_3.setIngredient('d', Material.DIAMOND);
				xrod6_3.setIngredient('e', Material.EMERALD_BLOCK);
				xrod6_3.setIngredient('b', Material.BLAZE_ROD);
				plugin.getServer().addRecipe(xrod6_3);
		ItemMeta irod7=IceTreeWandLv1.getItemMeta();
			irod7.setDisplayName(ChatColor.AQUA+""+ChatColor.BOLD+"大氷樹の杖lv1");
			irod7.setLore(IceTreeWandLv1_Lore);
			IceTreeWandLv1.setItemMeta(irod7);
				ShapedRecipe xrod7 = new ShapedRecipe(IceTreeWandLv1);
				xrod7.shape(
						" as",
						" ba",
						"b  ");
				xrod7.setIngredient('s', Material.SNOW_BALL);
				xrod7.setIngredient('a', Material.PACKED_ICE);
				xrod7.setIngredient('b', Material.BLAZE_ROD);
				plugin.getServer().addRecipe(xrod7);
		ItemMeta irod7_2=IceTreeWandLv2.getItemMeta();
			irod7_2.setDisplayName(ChatColor.AQUA+""+ChatColor.BOLD+"大氷樹の杖lv2");
			irod7_2.setLore(IceTreeWandLv2_Lore);
			IceTreeWandLv2.setItemMeta(irod7_2);
				ShapedRecipe xrod7_2 = new ShapedRecipe(IceTreeWandLv2);
				xrod7_2.shape(
						" la",
						" bl",
						"b  ");
				xrod7_2.setIngredient('a', Material.PACKED_ICE);
				xrod7_2.setIngredient('l', Material.LAPIS_BLOCK);
				xrod7_2.setIngredient('b', Material.BLAZE_ROD);
				plugin.getServer().addRecipe(xrod7_2);
		ItemMeta irod7_3=IceTreeBookLv3.getItemMeta();
			irod7_3.setDisplayName(ChatColor.AQUA+""+ChatColor.BOLD+"大氷樹の書lv3");
			irod7_3.setLore(IceTreeBookLv3_Lore);
			IceTreeBookLv3.setItemMeta(irod7_3);
				ShapedRecipe xrod7_3 = new ShapedRecipe(IceTreeBookLv3);
				xrod7_3.shape(
						"aba",
						"dad",
						"ddd");
				xrod7_3.setIngredient('b', Material.BOOK);
				xrod7_3.setIngredient('a', Material.PACKED_ICE);
				xrod7_3.setIngredient('d', Material.DIAMOND_ORE);
				plugin.getServer().addRecipe(xrod7_3);
		ItemMeta irod7_4=IceTreeBookLv4.getItemMeta();
			irod7_4.setDisplayName(ChatColor.AQUA+""+ChatColor.BOLD+"大氷樹の書lv4");
			irod7_4.setLore(IceTreeBookLv4_Lore);
			IceTreeBookLv4.setItemMeta(irod7_4);
				ShapedRecipe xrod7_4 = new ShapedRecipe(IceTreeBookLv4);
				xrod7_4.shape(
						"aba",
						"dad",
						"ddd");
				xrod7_4.setIngredient('b', Material.BOOK);
				xrod7_4.setIngredient('d', Material.PACKED_ICE);
				xrod7_4.setIngredient('a', Material.DIAMOND_BLOCK);
				plugin.getServer().addRecipe(xrod7_4);
		ItemMeta irod8=GuardWandLv1.getItemMeta();
			irod8.setDisplayName(ChatColor.AQUA+""+ChatColor.BOLD+"結界の杖lv1");
			irod8.setLore(GuardWandLv1_Lore);
			GuardWandLv1.setItemMeta(irod8);
				ShapedRecipe xrod8 = new ShapedRecipe(GuardWandLv1);
				xrod8.shape(
						" oc",
						" bo",
						"b  ");
				xrod8.setIngredient('c', Material.IRON_CHESTPLATE);
				xrod8.setIngredient('o', Material.OBSIDIAN);
				xrod8.setIngredient('b', Material.BLAZE_ROD);
				plugin.getServer().addRecipe(xrod8);
		ItemMeta irod8_2=GuardWandLv2.getItemMeta();
			irod8_2.setDisplayName(ChatColor.AQUA+""+ChatColor.BOLD+"結界の杖lv2");
			irod8_2.setLore(GuardWandLv2_Lore);
			GuardWandLv2.setItemMeta(irod8_2);
				ShapedRecipe xrod8_2 = new ShapedRecipe(GuardWandLv2);
				xrod8_2.shape(
						" oc",
						" bo",
						"b  ");
				xrod8_2.setIngredient('c', Material.GOLD_CHESTPLATE);
				xrod8_2.setIngredient('o', Material.OBSIDIAN);
				xrod8_2.setIngredient('b', Material.BLAZE_ROD);
				plugin.getServer().addRecipe(xrod8_2);
		ItemMeta irod8_3=GuardBookLv3.getItemMeta();
			irod8_3.setDisplayName(ChatColor.AQUA+""+ChatColor.BOLD+"結界の書lv3");
			irod8_3.setLore(GuardBookLv3_Lore);
			GuardBookLv3.setItemMeta(irod8_3);
				ShapedRecipe xrod8_3 = new ShapedRecipe(GuardBookLv3);
				xrod8_3.shape(
						"dcd",
						"obo",
						"ooo");
				xrod8_3.setIngredient('c', Material.DIAMOND_CHESTPLATE);
				xrod8_3.setIngredient('o', Material.OBSIDIAN);
				xrod8_3.setIngredient('b', Material.BOOK);
				xrod8_3.setIngredient('d', Material.DIAMOND);
				plugin.getServer().addRecipe(xrod8_3);
		ItemMeta irod9=WaterWandLv1.getItemMeta();
			irod9.setDisplayName(ChatColor.BLUE+""+ChatColor.BOLD+"水杖lv1");
			irod9.setLore(WaterWand_Lore);
			WaterWandLv1.setItemMeta(irod9);
				ShapedRecipe xrod9 = new ShapedRecipe(WaterWandLv1);
				xrod9.shape(
						"www",
						"wbw",
						"www");
				xrod9.setIngredient('w', Material.WATER_BUCKET);
				xrod9.setIngredient('b', Material.BLAZE_ROD);
				plugin.getServer().addRecipe(xrod9);
		ItemMeta irod10=BeastsBookLv1.getItemMeta();
			irod10.setDisplayName(ChatColor.BOLD+"召還獣の書lv1");
			irod10.setLore(BeastsBookLv1_Lore);
			BeastsBookLv1.setItemMeta(irod10);
				ShapedRecipe xrod10 = new ShapedRecipe(BeastsBookLv1);
				xrod10.shape(
						"bab",
						"ghg",
						"aba");
				xrod10.setIngredient('a', Material.GOLDEN_CARROT);
				xrod10.setIngredient('g', Material.ROTTEN_FLESH);
				xrod10.setIngredient('b', Material.BONE);
				xrod10.setIngredient('h', Material.BOOK);
				plugin.getServer().addRecipe(xrod10);
		ItemMeta irod10_2=BeastsBookLv2.getItemMeta();
			irod10_2.setDisplayName(ChatColor.BOLD+"召還獣の書lv2");
			irod10_2.setLore(BeastsBookLv2_Lore);
			BeastsBookLv2.setItemMeta(irod10_2);
				ShapedRecipe xrod10_2 = new ShapedRecipe(BeastsBookLv2);
				xrod10_2.shape(
						"bab",
						"ghg",
						"aba");
				xrod10_2.setIngredient('a', Material.GOLDEN_APPLE);
				xrod10_2.setIngredient('g', Material.ROTTEN_FLESH);
				xrod10_2.setIngredient('b', Material.BONE);
				xrod10_2.setIngredient('h', Material.BOOK);
				plugin.getServer().addRecipe(xrod10_2);
		ItemMeta irod10_3=BeastsBookLv3.getItemMeta();
			irod10_3.setDisplayName(ChatColor.BOLD+"召還獣の書lv3");
			irod10_3.setLore(BeastsBookLv3_Lore);
			BeastsBookLv3.setItemMeta(irod10_3);
				ShapedRecipe xrod10_3 = new ShapedRecipe(BeastsBookLv3);
				xrod10_3.shape(
						"bdb",
						"ghg",
						"dbd");
				xrod10_3.setIngredient('d', Material.DIAMOND);
				xrod10_3.setIngredient('g', Material.ROTTEN_FLESH);
				xrod10_3.setIngredient('b', Material.BONE);
				xrod10_3.setIngredient('h', Material.BOOK);
				plugin.getServer().addRecipe(xrod10_3);
		ItemMeta irod11=CartBookLv1.getItemMeta();
			irod11.setDisplayName(ChatColor.YELLOW+""+ChatColor.BOLD+"トロッコブースターの書lv1");
			irod11.setLore(CartBook_Lore);
			CartBookLv1.setItemMeta(irod11);
				ShapedRecipe xrod11 = new ShapedRecipe(CartBookLv1);
				xrod11.shape(
						"srs",
						"rbr",
						"srs");
				xrod11.setIngredient('s', Material.SUGAR);
				xrod11.setIngredient('b', Material.BOOK);
				xrod11.setIngredient('r', Material.REDSTONE);
				plugin.getServer().addRecipe(xrod11);
		ItemMeta irod12=LightningBookLv1.getItemMeta();
			irod12.setDisplayName(ChatColor.BOLD+"白雷の書lv1");
			irod12.setLore(LightningBookLv1_Lore);
			LightningBookLv1.setItemMeta(irod12);
				ShapedRecipe xrod12 = new ShapedRecipe(LightningBookLv1);
				xrod12.shape(
						"ede",
						"dbd",
						"ppp");
				xrod12.setIngredient('e', Material.EYE_OF_ENDER);
				xrod12.setIngredient('d', Material.DIAMOND);
				xrod12.setIngredient('b', Material.BOOK);
				xrod12.setIngredient('p', Material.BLAZE_POWDER);
				plugin.getServer().addRecipe(xrod12);

	}
}
