package mincra.magicrod.cmd;

import mincra.magicrod.database.DatabaseManager;
import mincra.magicrod.main.Magic;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MagicChestCommands implements CommandExecutor {
	//1番目のパラメータは所有者を現す。nullを指定することによりだれでも利用することが可能.
	//2番目のパラメータは、インベントリ内のスロット。9,18,27,36,45,54が指定可能.
	//3番目のパラメータは、インベントリ名.
	public MagicChestCommands(Magic magic) {
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		if(cmd.getName().equalsIgnoreCase("magicchest")){
			if(sender instanceof Player){
				Player player = ((Player) sender);
				int user_id = DatabaseManager.getUserId(player.getUniqueId());
				if(user_id == -1) 
					return true;
				player.openInventory(DatabaseManager.getMagicChestInventory(user_id));
				return true;
			}else{
				sender.sendMessage("プレイヤーから実行して下さい。");
				return false;
			}
		}
		return false;
	}
}
