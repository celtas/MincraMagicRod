package mincra.magicrod.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Util {
	public static void debug(ChatColor ChatColor, String msg) {
		Bukkit.getConsoleSender().sendMessage(ChatColor+"[MincraMagicRod] "+msg);
	}
	public static void debug(String msg) {
		Bukkit.getConsoleSender().sendMessage("[MincraMagicRod] "+msg);
	}
}
