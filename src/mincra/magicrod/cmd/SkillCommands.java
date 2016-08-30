package mincra.magicrod.cmd;

import mincra.magicrod.database.DatabaseManager;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SkillCommands implements CommandExecutor {
	//1番目のパラメータは所有者を現す。nullを指定することによりだれでも利用することが可能.
	//2番目のパラメータは、インベントリ内のスロット。9,18,27,36,45,54が指定可能.
	//3番目のパラメータは、インベントリ名.
	public SkillCommands(){
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		if(cmd.getName().equalsIgnoreCase("skill")){
			if(!(sender instanceof Player)){
				return true;
			}
			Player player = (Player) sender;
			
			if(args.length==0){
				showSkill((Player) sender);
				return true;
			}else if(args.length==1){
				switch(args[0]){
					case "disable":
						skillDisable(player); 
						return true;
					case "enable":
						skillEnable(player);
						return true;
					case "job":
						int user_id = DatabaseManager.getUserId(player.getUniqueId());
						if(user_id == -1) 
							return true;
						player.sendMessage(ChatColor.GRAY+"あなたは"+DatabaseManager.getJob(user_id)+"クラスです.");
						return true;
					default:
						player.sendMessage(ChatColor.GRAY+"/skill job: クラスを確認する.");
						player.sendMessage(ChatColor.GRAY+"/skill notice on: 通知を受け取る.");
						player.sendMessage(ChatColor.GRAY+"/skill notice off: 通知を受け取らない.");
						player.sendMessage(ChatColor.GRAY+"/skill enable: スキル機能を有効にする.");
						player.sendMessage(ChatColor.GRAY+"/skill disable: スキル機能を無効にする.");
						break;
				}
				return true;
			}else if(args.length>=2){
				if(args[0].equalsIgnoreCase("notice")){
					if(args[1].equalsIgnoreCase("on")){
						int user_id = DatabaseManager.getUserId(player.getUniqueId());
						if(user_id == -1) 
							return true;
						DatabaseManager.setNotice(user_id, true);
						player.sendMessage(ChatColor.GREEN+"通知機能を有効にしました.");
						player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1F, 1F);
						return true;
					}else if(args[1].equalsIgnoreCase("off")){
						int user_id2 = DatabaseManager.getUserId(player.getUniqueId());
						if(user_id2 == -1) 
							return true;
						DatabaseManager.setNotice(user_id2, false);
						player.sendMessage(ChatColor.RED+"※通知機能を無効にしました.");
						player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1F, 1F);
						return true;
					}
				}
				player.sendMessage(ChatColor.GRAY+"/skill job: クラスを確認する.");
				player.sendMessage(ChatColor.GRAY+"/skill notice on: 通知を受け取る.");
				player.sendMessage(ChatColor.GRAY+"/skill notice off: 通知を受け取らない.");
				player.sendMessage(ChatColor.GRAY+"/skill disable: スキル機能を無効にする.");
				player.sendMessage(ChatColor.GRAY+"/skill enable: スキル機能を有効にする.");
				return true;
			}
		}
		return false;
	}
	private void skillEnable(Player player) {
		int user_id = DatabaseManager.getUserId(player.getUniqueId());
		if(user_id == -1) 
			return;
		DatabaseManager.setEnable(user_id, true);
		player.sendMessage(ChatColor.GREEN+"※スキル機能を有効にしました.");
		player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1F, 1F);
	}
	private void skillDisable(Player player) {
		int user_id = DatabaseManager.getUserId(player.getUniqueId());
		if(user_id == -1) 
			return;
		DatabaseManager.setEnable(user_id, false);
		player.sendMessage(ChatColor.RED+"※スキル機能を無効にしました.");
		player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1F, 1F);
	}
	@SuppressWarnings("unused")
	private void showMagicChest(Player player) {
		int user_id = DatabaseManager.getUserId(player.getUniqueId());
		if(user_id == -1) 
			return;
		player.openInventory(DatabaseManager.getSkillInventory(user_id));
	}
	private void showSkill(Player player) {
		int user_id = DatabaseManager.getUserId(player.getUniqueId());
		if(user_id == -1) 
			return;
		
		if(DatabaseManager.replaceSkillSlot(user_id,player)){
			player.sendMessage("マテリアルの更新を行いました。");
			player.openInventory(DatabaseManager.getSkillInventory(user_id));
		}else{
			player.openInventory(DatabaseManager.getSkillInventory(user_id));
		}
	}
	

}
