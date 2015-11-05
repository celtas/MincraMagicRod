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

public class MagicItem{
	Magic plugin;

	public static ItemStack magicPotion1 = new ItemStack(Material.POTION,1,(short) 8228);
	public static ItemStack iceDrop = new ItemStack(Material.BLAZE_POWDER,1);
	public static ItemStack phoenixDown = new ItemStack(Material.FEATHER,1);
	public static ItemStack quartz = new ItemStack(Material.QUARTZ,1);
	public static ItemStack magicTicket = new ItemStack(Material.PAPER,1);
	public static ItemStack magicGoldTicket = new ItemStack(Material.PAPER,1);
	public static ItemStack magicWeed = new ItemStack(Material.LONG_GRASS,1,(short)1);
	public static ItemStack darkMatter = new ItemStack(Material.GLOWSTONE_DUST,1);
	public static ItemStack magicArrow = new ItemStack(Material.ARROW,1);

	public final static List<String> lores1 = Arrays.asList(ChatColor.GREEN+	"魔法アイテム番号:1"
															,ChatColor.GREEN+	"カフェインの取りすぎに注意!"
															,ChatColor.GREEN+	"60    +クールタイム"
															,ChatColor.GREEN+	"0     +MP消費"
															);
	public final static List<String> IceDrop_lore = Arrays.asList(ChatColor.AQUA+	"魔法アイテム番号:2"
															,ChatColor.AQUA+		"氷塊の極限的に凝縮した物"
															);
	public final static List<String> PhoenixDown_lore = Arrays.asList(ChatColor.GOLD+	"魔法アイテム番号:3"
															,ChatColor.GOLD+			"死んだ時に持っていると復活する。"
															);
	public final static List<String> Quartz_lore = Arrays.asList(ChatColor.AQUA+	"魔法アイテム番号:4"
															,ChatColor.AQUA+		"魔法によって加工された水晶"
															);
	public final static List<String> MagicTicket_lore = Arrays.asList(ChatColor.AQUA+""+ChatColor.BOLD+	"魔法アイテム番号:5"
															,ChatColor.AQUA+""+ChatColor.BOLD+			"様々な魔法と交換できる券"
															);
	public final static List<String> MagicGoldTicket_lore = Arrays.asList(ChatColor.GOLD+""+ChatColor.BOLD+	"魔法アイテム番号:5-2"
															,ChatColor.GOLD+""+ChatColor.BOLD+			"様々な魔法と交換できる貴重な券"
															);
	public final static List<String> MagicWeed_lore = Arrays.asList(ChatColor.GREEN+""+ChatColor.BOLD+	"魔法アイテム番号:6"
															,ChatColor.GREEN+""+ChatColor.BOLD+			"魔法の力が宿った草"
															);
	public final static List<String> DarkMatter_lore = Arrays.asList(ChatColor.BLACK+""+ChatColor.BOLD+	"魔法アイテム番号:7"
															,ChatColor.BLACK+""+ChatColor.BOLD+			"自然界には存在しないアイテム"
															);
	public final static List<String> magicArrow_lore = Arrays.asList(ChatColor.WHITE+""+ChatColor.BOLD+	"魔法アイテム番号:8"
															,ChatColor.WHITE+""+ChatColor.BOLD+			"魔法で生成された矢。"
															);

	@SuppressWarnings("deprecation")
	public MagicItem(Magic _plugin) {
		plugin=_plugin;

		MaterialData waterbottle = new MaterialData(Material.POTION);
		waterbottle.setData((byte) 0);
		ItemMeta Metamagicpotion1=magicPotion1.getItemMeta();
		Metamagicpotion1.setDisplayName("モンスターエナジー");
		Metamagicpotion1.setLore(lores1);
		magicPotion1.setItemMeta(Metamagicpotion1);
			ShapedRecipe Recipemagicpotion1 = new ShapedRecipe(magicPotion1);
			Recipemagicpotion1.shape(
					"rsr",
					"sws",
					"rsr");
			Recipemagicpotion1.setIngredient('w', waterbottle);
			Recipemagicpotion1.setIngredient('r', Material.ROTTEN_FLESH);
			Recipemagicpotion1.setIngredient('s', Material.SUGAR);
		plugin.getServer().addRecipe(Recipemagicpotion1);


		ItemMeta MagicItem2Meta = iceDrop.getItemMeta();
		MagicItem2Meta.setDisplayName(ChatColor.AQUA+""+ChatColor.BOLD+"氷零");
		MagicItem2Meta.setLore(IceDrop_lore);
		iceDrop.setItemMeta(MagicItem2Meta);
		iceDrop.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS,1);
			ShapedRecipe RecipeMagicItem2 = new ShapedRecipe(iceDrop);
			RecipeMagicItem2.shape(
					"ppp",
					"pbp",
					"ppp");
			RecipeMagicItem2.setIngredient('p', Material.PACKED_ICE);
			RecipeMagicItem2.setIngredient('b', Material.BLAZE_POWDER);
		plugin.getServer().addRecipe(RecipeMagicItem2);

		ItemMeta MagicItem3Meta = phoenixDown.getItemMeta();
		MagicItem3Meta.setDisplayName(ChatColor.GOLD+""+ChatColor.BOLD+"フェニックスの尾");
		MagicItem3Meta.setLore(PhoenixDown_lore);
		phoenixDown.setItemMeta(MagicItem3Meta);
		phoenixDown.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS,1);
			ShapedRecipe RecipeMagicItem3 = new ShapedRecipe(phoenixDown);
			RecipeMagicItem3.shape(
					"ebe",
					"dfd",
					"beb");
			RecipeMagicItem3.setIngredient('f', Material.FEATHER);
			RecipeMagicItem3.setIngredient('d', Material.DIAMOND);
			RecipeMagicItem3.setIngredient('e', Material.EMERALD);
			RecipeMagicItem3.setIngredient('b', Material.BLAZE_POWDER);
		plugin.getServer().addRecipe(RecipeMagicItem3);

		ItemMeta MagicItem4Meta = quartz.getItemMeta();
		MagicItem4Meta.setDisplayName(ChatColor.AQUA+""+ChatColor.BOLD+"魔法水晶");
		MagicItem4Meta.setLore(Quartz_lore);
		quartz.setItemMeta(MagicItem4Meta);
		quartz.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS,1);
			ShapedRecipe RecipeMagicItem4 = new ShapedRecipe(quartz);
			RecipeMagicItem4.shape(
					"qqq",
					"qqq",
					"qqq");
			RecipeMagicItem4.setIngredient('q', Material.QUARTZ_ORE);
		plugin.getServer().addRecipe(RecipeMagicItem4);

		//レシピがないアイテム
		ItemMeta MagicItem5Meta = magicTicket.getItemMeta();
		MagicItem5Meta.setDisplayName(ChatColor.DARK_PURPLE+""+ChatColor.BOLD+"MagicTicket");
		MagicItem5Meta.setLore(MagicTicket_lore);
		magicTicket.setItemMeta(MagicItem5Meta);
		magicTicket.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS,1);
		ShapedRecipe RecipeMagicItem5 = new ShapedRecipe(magicTicket);
		RecipeMagicItem5.shape(
				"qqq",
				"qaq",
				"qqq");
		RecipeMagicItem5.setIngredient('q', Material.DIAMOND_BLOCK);
		RecipeMagicItem5.setIngredient('a', Material.PAPER);
		plugin.getServer().addRecipe(RecipeMagicItem5);

		//レシピがないアイテム
		ItemMeta MagicItem5_2Meta = magicGoldTicket.getItemMeta();
		MagicItem5_2Meta.setDisplayName(ChatColor.GOLD+""+ChatColor.BOLD+"MagicGoldTicket");
		MagicItem5_2Meta.setLore(MagicGoldTicket_lore);
		magicGoldTicket.setItemMeta(MagicItem5_2Meta);
		magicGoldTicket.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS,1);
		ShapedRecipe RecipeMagicItem5_2 = new ShapedRecipe(magicGoldTicket);
		RecipeMagicItem5_2.shape(
				"qqq",
				"qaq",
				"qqq");
		RecipeMagicItem5_2.setIngredient('q', Material.GOLD_BLOCK);
		RecipeMagicItem5_2.setIngredient('a', Material.PAPER);
		plugin.getServer().addRecipe(RecipeMagicItem5_2);

		ItemMeta MagicItem6Meta = magicWeed.getItemMeta();
		MagicItem6Meta.setDisplayName(ChatColor.GREEN+""+ChatColor.BOLD+"魔法草");
		MagicItem6Meta.setLore(MagicWeed_lore);
		magicWeed.setItemMeta(MagicItem6Meta);
		magicWeed.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS,1);
			ShapedRecipe RecipeMagicItem6 = new ShapedRecipe(magicWeed);
			RecipeMagicItem6.shape(
					"ddd",
					"dwd",
					"ddd");
			RecipeMagicItem6.setIngredient('w', Material.LONG_GRASS,(short)1);
			RecipeMagicItem6.setIngredient('d', Material.DIAMOND_BLOCK);
		plugin.getServer().addRecipe(RecipeMagicItem6);

		ItemMeta DarkMatterMeta = darkMatter.getItemMeta();
		DarkMatterMeta.setDisplayName(ChatColor.BLACK+""+ChatColor.BOLD+"ダークマター");
		DarkMatterMeta.setLore(DarkMatter_lore);
		darkMatter.setItemMeta(DarkMatterMeta);
		darkMatter.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS,1);

		ItemMeta magicArrowMeta = magicArrow.getItemMeta();
		magicArrowMeta.setDisplayName(ChatColor.WHITE+""+ChatColor.BOLD+"魔法の矢");
		magicArrowMeta.setLore(magicArrow_lore);
		magicArrow.setItemMeta(magicArrowMeta);
		magicArrow.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS,1);
	}

}
