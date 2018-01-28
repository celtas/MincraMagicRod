package mincra.magicrod.cmd;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import mincra.magicrod.api.MagicApi;
import mincra.magicrod.database.DatabaseManager;
import mincra.magicrod.util.Util;

public class GiftBoxCommands implements CommandExecutor {
	//1番目のパラメータは所有者を現す。nullを指定することによりだれでも利用することが可能.
	//2番目のパラメータは、インベントリ内のスロット。9,18,27,36,45,54が指定可能.
	//3番目のパラメータは、インベントリ名.
	public GiftBoxCommands(){
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		if(cmd.getName().equalsIgnoreCase("giftbox")){
			if(!(sender instanceof Player)){
				return true;
			}
			Player player = (Player) sender;
			if(args.length==0){
				showGiftBox(player);
			}else{
				if(!player.hasPermission("MincraMagicRod.Admin")){
					player.sendMessage(ChatColor.GRAY+"権限を持っていません.");
				}
				Player player2 = Bukkit.getServer().getPlayer(args[0]);
				if(player2 == null){
					player.sendMessage(ChatColor.GRAY+"プレイヤーが存在しません.");
				}

				if(args.length==3){
					if(args[1].equalsIgnoreCase("add")){
						ItemStack material = MagicApi.getMaterial(Integer.valueOf(args[2]));
						if(material!= null){
							int user_id = DatabaseManager.getUserId(player.getUniqueId());
							if(user_id != -1){
								DatabaseManager.addGiftBoxInventory(player2.getPlayer(), user_id, material);
							}else{
								player.sendMessage(ChatColor.GRAY+args[2]+"は存在しないプレイヤーです.");
							}
						}else{
							player.sendMessage(ChatColor.GRAY+args[2]+"は存在しないマテリアル番号です.");
						}
					}
				}else{
					player.sendMessage(ChatColor.GRAY+"パラメタが足りません.");
				}
			}
		}
		return true;
	}
	private void showGiftBox(Player player) {
		int user_id = DatabaseManager.getUserId(player.getUniqueId());
		if(user_id == -1)
			return;

		Inventory inv = DatabaseManager.getGiftBoxInventory(user_id);
		if(inv!=null){
			player.openInventory(inv);
		}else{
			player.sendMessage(ChatColor.GRAY+"ギフトボックスを取得できませんでした.");
			Util.debug(ChatColor.YELLOW,"ギフトボックスを取得できませんでした.");
		}
	}
}
