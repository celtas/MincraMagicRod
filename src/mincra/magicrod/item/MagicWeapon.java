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

public class MagicWeapon{
	Magic plugin;
	public static ItemStack bloodSword= new ItemStack(Material.IRON_SWORD,1);
	public static ItemStack creeperBow= new ItemStack(Material.BOW,1);
	public static ItemStack creeperBowLv2= new ItemStack(Material.BOW,1);
	public static ItemStack aiceBowLv1= new ItemStack(Material.BOW,1);
	public static ItemStack aiceBowLv2= new ItemStack(Material.BOW,1);
	public static ItemStack ParalysisSword = new ItemStack(Material.GOLD_SWORD,1);
	public static ItemStack ParalysisBow = new ItemStack(Material.BOW,1);
	public static ItemStack MechanicsSword = new ItemStack(Material.DIAMOND_SWORD,1);
	public static ItemStack MechanicsSwordLv2 = new ItemStack(Material.DIAMOND_SWORD,1);
	public static ItemStack SleepSword = new ItemStack(Material.DIAMOND_SWORD,1);
	public static ItemStack SleepBow = new ItemStack(Material.BOW,1);
	public final static List<String> bloodSwordLores= Arrays.asList(ChatColor.DARK_RED+			"魔法武器番号:1"
														,ChatColor.DARK_RED+		"返り血がついた剣。血を喰らう。"
														,ChatColor.DARK_RED+		"2     +クールタイム"
														,ChatColor.DARK_RED+		"0     +MP消費"
														,ChatColor.DARK_RED+		"20    +発生確率");
	public final static List<String> creeperBowLv1_Lores= Arrays.asList(ChatColor.YELLOW+	"魔法武器番号:2"
														,ChatColor.YELLOW+			"シュー........"
														,ChatColor.YELLOW+			"0     +クールタイム"
														,ChatColor.YELLOW+			"1     +MP消費"
														,ChatColor.YELLOW+			"100   +発生確率");
	public final static List<String> creeperBowLv2_Lores= Arrays.asList(ChatColor.YELLOW+		"魔法武器番号:2-2"
														,ChatColor.YELLOW+			"シュー........"
														,ChatColor.YELLOW+			"0     +クールタイム"
														,ChatColor.YELLOW+			"1     +MP消費"
														,ChatColor.YELLOW+			"100   +発生確率");
	public final static List<String> aiceBowLv1_Lores= Arrays.asList(ChatColor.AQUA+			"魔法武器番号:3"
														,ChatColor.AQUA+			"氷属性の魔法弓。"
														,ChatColor.AQUA+			"0     +クールタイム"
														,ChatColor.AQUA+			"1     +MP消費"
														,ChatColor.AQUA+			"100   +発生確率");
	public final static List<String> aiceBowLv2_Lores= Arrays.asList(ChatColor.AQUA+		"魔法武器番号:3-2"
														,ChatColor.AQUA+			"氷属性の中では最強の弓。"
														,ChatColor.AQUA+			"0     +クールタイム"
														,ChatColor.AQUA+			"2     +MP消費"
														,ChatColor.AQUA+			"100   +発生確率");
	public final static List<String> ParalysisSword_Lore= Arrays.asList(ChatColor.YELLOW+		"魔法武器番号:4"
														,ChatColor.YELLOW+			"麻痺属性の剣。"
														,ChatColor.YELLOW+			"0     +クールタイム"
														,ChatColor.YELLOW+			"0     +MP消費"
														,ChatColor.YELLOW+			"15    +発生確率");
	public final static List<String> ParalysisBow_Lore= Arrays.asList(ChatColor.YELLOW+		"魔法武器番号:5"
														,ChatColor.YELLOW+			"麻痺属性の弓。"
														,ChatColor.YELLOW+			"2     +クールタイム"
														,ChatColor.YELLOW+			"1     +MP消費"
														,ChatColor.YELLOW+			"50    +発生確率");
	public final static List<String> MechanicsSword_Lore= Arrays.asList(ChatColor.YELLOW+		"魔法武器番号:6"
														,ChatColor.YELLOW+			"落下エネルギーをダメージに変える剣。"
														,ChatColor.YELLOW+			"8     +クールタイム"
														,ChatColor.YELLOW+			"3     +MP消費"
														,ChatColor.YELLOW+			"100   +発生確率");
	public final static List<String> SleepSword_Lore= Arrays.asList(ChatColor.LIGHT_PURPLE+	"魔法武器番号:7"
														,ChatColor.LIGHT_PURPLE+			"睡眠属性の剣。"
														,ChatColor.LIGHT_PURPLE+			"0      +クールタイム"
														,ChatColor.LIGHT_PURPLE+			"0      +MP消費"
														,ChatColor.LIGHT_PURPLE+			"20     +発生確率");
	public final static List<String> SleepBow_Lore= Arrays.asList(ChatColor.LIGHT_PURPLE+	"魔法武器番号:8"
														,ChatColor.LIGHT_PURPLE+			"睡眠属性の弓。"
														,ChatColor.LIGHT_PURPLE+			"0      +クールタイム"
														,ChatColor.LIGHT_PURPLE+			"1      +MP消費"
														,ChatColor.LIGHT_PURPLE+			"100    +発生確率");
	public final static List<String> MechanicsSwordLv2_Lore= Arrays.asList(ChatColor.YELLOW+		"魔法武器番号:9"
														,ChatColor.YELLOW+			"落下エネルギーをダメージに変える剣。"
														,ChatColor.YELLOW+			"5     +クールタイム"
														,ChatColor.YELLOW+			"3     +MP消費"
														,ChatColor.YELLOW+			"100   +発生確率");

	public MagicWeapon(Magic _plugin) {
		plugin=_plugin;

		ItemMeta MetaMagicWeapon1_1 = bloodSword.getItemMeta();
		MetaMagicWeapon1_1.setDisplayName(ChatColor.DARK_RED+""+ChatColor.BOLD+"ブラッドソード");
		MetaMagicWeapon1_1.setLore(bloodSwordLores);
		bloodSword.setItemMeta(MetaMagicWeapon1_1);
		bloodSword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL,2);
		bloodSword.addUnsafeEnchantment(Enchantment.DURABILITY,5);
			ShapedRecipe RecipeMagicWeapon1_1 = new ShapedRecipe(bloodSword);
			RecipeMagicWeapon1_1.shape(
					"nin",
					"nin",
					" s ");
			RecipeMagicWeapon1_1.setIngredient('s', Material.STICK);
			RecipeMagicWeapon1_1.setIngredient('n', Material.NETHER_STALK);
			RecipeMagicWeapon1_1.setIngredient('i', Material.IRON_BLOCK);
		plugin.getServer().addRecipe(RecipeMagicWeapon1_1);

		ItemMeta MetaMagicWeapon2_1 = creeperBow.getItemMeta();
		MetaMagicWeapon2_1.setDisplayName(ChatColor.YELLOW+""+ChatColor.BOLD+"匠ノ弓");
		MetaMagicWeapon2_1.setLore(creeperBowLv1_Lores);
		creeperBow.setItemMeta(MetaMagicWeapon2_1);
		creeperBow.addUnsafeEnchantment(Enchantment.DURABILITY,5);
			ShapedRecipe RecipeMagicWeapon2_1 = new ShapedRecipe(creeperBow);
			RecipeMagicWeapon2_1.shape(
					"ttt",
					"tbt",
					"ttt");
			RecipeMagicWeapon2_1.setIngredient('b', Material.BOW);
			RecipeMagicWeapon2_1.setIngredient('t', Material.TNT);
		plugin.getServer().addRecipe(RecipeMagicWeapon2_1);

		ItemMeta MetaMagicWeapon2_2 = creeperBowLv2.getItemMeta();
		MetaMagicWeapon2_2.setDisplayName(ChatColor.YELLOW+""+ChatColor.BOLD+"匠ノ弓lv2");
		MetaMagicWeapon2_2.setLore(creeperBowLv2_Lores);
		creeperBowLv2.setItemMeta(MetaMagicWeapon2_2);
		creeperBowLv2.addUnsafeEnchantment(Enchantment.DURABILITY,6);
			ShapedRecipe RecipeMagicWeapon2_2 = new ShapedRecipe(creeperBowLv2);
			RecipeMagicWeapon2_2.shape(
					"ddd",
					"dbd",
					"ddd");
			RecipeMagicWeapon2_2.setIngredient('b', Material.BOW);
			RecipeMagicWeapon2_2.setIngredient('d', Material.DIAMOND);
		plugin.getServer().addRecipe(RecipeMagicWeapon2_2);

		ItemMeta MetaMagicWeapon3 = aiceBowLv1.getItemMeta();
		MetaMagicWeapon3.setDisplayName(ChatColor.AQUA+""+ChatColor.BOLD+"大氷樹の弓");
		MetaMagicWeapon3.setLore(aiceBowLv1_Lores);
		aiceBowLv1.setItemMeta(MetaMagicWeapon3);
		aiceBowLv1.addUnsafeEnchantment(Enchantment.DURABILITY,5);
			ShapedRecipe RecipeMagicWeapon3 = new ShapedRecipe(aiceBowLv1);
			RecipeMagicWeapon3.shape(
					"ddd",
					"dbd",
					"ddd");
			RecipeMagicWeapon3.setIngredient('b', Material.BOW);
			RecipeMagicWeapon3.setIngredient('d', Material.BLAZE_POWDER);
		plugin.getServer().addRecipe(RecipeMagicWeapon3);

		ItemMeta MetaMagicWeapon3_2 = aiceBowLv2.getItemMeta();
		MetaMagicWeapon3_2.setDisplayName(ChatColor.AQUA+""+ChatColor.BOLD+"大氷樹の弓lv2");
		MetaMagicWeapon3_2.setLore(aiceBowLv2_Lores);
		aiceBowLv2.setItemMeta(MetaMagicWeapon3_2);
		aiceBowLv2.addUnsafeEnchantment(Enchantment.DURABILITY,5);
			ShapedRecipe RecipeMagicWeapon3_2 = new ShapedRecipe(aiceBowLv2);
			RecipeMagicWeapon3_2.shape(
					"dxd",
					"ebe",
					"dxd");
			RecipeMagicWeapon3_2.setIngredient('b', Material.BOW);
			RecipeMagicWeapon3_2.setIngredient('e', Material.EMERALD_BLOCK);
			RecipeMagicWeapon3_2.setIngredient('d', Material.BLAZE_POWDER);
			RecipeMagicWeapon3_2.setIngredient('x', Material.DIAMOND_BLOCK);
		plugin.getServer().addRecipe(RecipeMagicWeapon3_2);

		ItemMeta ParalysisBow_Meta = ParalysisBow.getItemMeta();
		ParalysisBow_Meta.setDisplayName(ChatColor.YELLOW+""+ChatColor.BOLD+"麻痺弓");
		ParalysisBow_Meta.setLore(ParalysisBow_Lore);
		ParalysisBow.setItemMeta(ParalysisBow_Meta);
		ParalysisBow.addUnsafeEnchantment(Enchantment.DURABILITY,5);
			ShapedRecipe RecipeParalysisBow = new ShapedRecipe(ParalysisBow);
			RecipeParalysisBow.shape(
					"sss",
					"sbs",
					"sss");
			RecipeParalysisBow.setIngredient('b', Material.BOW);
			RecipeParalysisBow.setIngredient('s', Material.SLIME_BLOCK);
		plugin.getServer().addRecipe(RecipeParalysisBow);

		ItemMeta ParalysisSword_Meta = ParalysisSword.getItemMeta();
		ParalysisSword_Meta.setDisplayName(ChatColor.YELLOW+""+ChatColor.BOLD+"麻痺剣");
		ParalysisSword_Meta.setLore(ParalysisSword_Lore);
		ParalysisSword.setItemMeta(ParalysisSword_Meta);
		ParalysisSword.addUnsafeEnchantment(Enchantment.DURABILITY,5);
			ShapedRecipe RecipeParalysisSword = new ShapedRecipe(ParalysisSword);
			RecipeParalysisSword.shape(
					" s ",
					"sgs",
					" s ");
			RecipeParalysisSword.setIngredient('s', Material.SLIME_BLOCK);
			RecipeParalysisSword.setIngredient('g', Material.GOLD_SWORD);
		plugin.getServer().addRecipe(RecipeParalysisSword);

		ItemMeta MechanicsSword_Meta = MechanicsSword.getItemMeta();
		MechanicsSword_Meta.setDisplayName(ChatColor.YELLOW+""+ChatColor.BOLD+"メカニクスソード");
		MechanicsSword_Meta.setLore(MechanicsSword_Lore);
		MechanicsSword.setItemMeta(MechanicsSword_Meta);
		MechanicsSword.addUnsafeEnchantment(Enchantment.DURABILITY,5);
			ShapedRecipe RecipeMechanicsSword = new ShapedRecipe(MechanicsSword);
			RecipeMechanicsSword.shape(
					"  d",
					" d ",
					"s  ");
			RecipeMechanicsSword.setIngredient('s', Material.QUARTZ);
			RecipeMechanicsSword.setIngredient('d', Material.DIAMOND);
		plugin.getServer().addRecipe(RecipeMechanicsSword);

		ItemMeta MechanicsSwordLv2_Meta = MechanicsSwordLv2.getItemMeta();
		MechanicsSwordLv2_Meta.setDisplayName(ChatColor.YELLOW+""+ChatColor.BOLD+"メカニクスソードLv2");
		MechanicsSwordLv2_Meta.setLore(MechanicsSwordLv2_Lore);
		MechanicsSwordLv2.setItemMeta(MechanicsSwordLv2_Meta);
		MechanicsSwordLv2.addUnsafeEnchantment(Enchantment.DURABILITY,5);
			ShapedRecipe RecipeMechanicsSwordLv2 = new ShapedRecipe(MechanicsSwordLv2);
			RecipeMechanicsSwordLv2.shape(
					"ddd",
					"dsd",
					"ddd");
			RecipeMechanicsSwordLv2.setIngredient('s', Material.DIAMOND_SWORD);
			RecipeMechanicsSwordLv2.setIngredient('d', Material.DIAMOND);
		plugin.getServer().addRecipe(RecipeMechanicsSwordLv2);

		ItemMeta SleepSword_Meta = SleepSword.getItemMeta();
		SleepSword_Meta.setDisplayName(ChatColor.LIGHT_PURPLE+""+ChatColor.BOLD+"睡眠剣");
		SleepSword_Meta.setLore(SleepSword_Lore);
		SleepSword.setItemMeta(SleepSword_Meta);
		SleepSword.addUnsafeEnchantment(Enchantment.DURABILITY,5);
			ShapedRecipe RecipeSleepSword = new ShapedRecipe(SleepSword);
			RecipeSleepSword.shape(
					"ggg",
					"gsg",
					"ggg");
			RecipeSleepSword.setIngredient('g', Material.GHAST_TEAR);
			RecipeSleepSword.setIngredient('s', Material.DIAMOND_SWORD);
		plugin.getServer().addRecipe(RecipeSleepSword);

		ItemMeta SleepBow_Meta = SleepBow.getItemMeta();
		SleepBow_Meta.setDisplayName(ChatColor.LIGHT_PURPLE+""+ChatColor.BOLD+"睡眠弓");
		SleepBow_Meta.setLore(SleepBow_Lore);
		SleepBow.setItemMeta(SleepBow_Meta);
		SleepBow.addUnsafeEnchantment(Enchantment.DURABILITY,5);
			ShapedRecipe RecipeSleepBow = new ShapedRecipe(SleepBow);
			RecipeSleepBow.shape(
					"ggg",
					"gbg",
					"ggg");
			RecipeSleepBow.setIngredient('b', Material.BOW);
			RecipeSleepBow.setIngredient('g', Material.GHAST_TEAR);
		plugin.getServer().addRecipe(RecipeSleepBow);
	}

}
