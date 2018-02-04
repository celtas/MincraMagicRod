package mincra.magicrod.cmd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import mincra.magicrod.api.MagicApi;
import mincra.magicrod.item.MagicItem;
import mincra.magicrod.main.Magic;
import mincra.magicrod.version.Version;

public class MincraCommands extends MagicApi implements CommandExecutor {
	private HashMap<String,Integer> cmdmap = new HashMap<String,Integer>();
	private HashMap<String,Integer> cmdmap_gold = new HashMap<String,Integer>();
	private HashMap<String,String> givemap = new HashMap<String,String>();
	public MincraCommands(){
	    List<Map<?, ?>> commandlist = Magic.trade.getMapList("CommandMap");
	    for(Map<?, ?> e:commandlist){
	    	for(Entry<?, ?> e2:e.entrySet()){
	    		String cmd = (String) e2.getKey();
	    		String give = (String) e2.getValue();

	    		String receive_cmd = cmd.substring(0, cmd.indexOf(","));
	    		String necessary_amount = cmd.substring(cmd.indexOf(",")+1);
	    		cmdmap.put(receive_cmd, Integer.valueOf(necessary_amount));

	    		String send_cmd = give.substring(0, give.indexOf(","));
	    		String give_amount = give.substring(give.indexOf(",")+1);

	    		givemap.put(receive_cmd+","+necessary_amount, send_cmd+","+give_amount);

	    	}
	    }
	    commandlist = Magic.trade_gold.getMapList("CommandMap");
	    for(Map<?, ?> e:commandlist){
	    	for(Entry<?, ?> e2:e.entrySet()){
	    		String cmd = (String) e2.getKey();
	    		String give = (String) e2.getValue();

	    		String receive_cmd = cmd.substring(0, cmd.indexOf(","));
	    		String necessary_amount = cmd.substring(cmd.indexOf(",")+1);
	    		cmdmap_gold.put(receive_cmd, Integer.valueOf(necessary_amount));

	    		String send_cmd = give.substring(0, give.indexOf(","));
	    		String give_amount = give.substring(give.indexOf(",")+1);

	    		givemap.put(receive_cmd+","+necessary_amount, send_cmd+","+give_amount);

	    	}
	    }
	    //メモリ開放
	    commandlist=null;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		if(args.length<1){
				sender.sendMessage("パラメタが足りません");
				return false;
		}
		if (cmd.getName().equalsIgnoreCase("mincramagicrod")){
			switch(args[0]){
				case"get":
					if (!sender.hasPermission("MincraMagicRod.Admin")) {
		                sender.sendMessage("権限がありません");
		                return true;
		            }
					if(sender instanceof Player&&args.length>=2){
						getMaterial((Player)sender,args[1]);
						return true;
					}else{
						return false;
					}
				case"check":
					if(sender instanceof Player){
						final Player player = (Player) sender;
						player.sendMessage(ChatColor.GRAY+"魔法修復プログラムをリクエストしています...");
						new BukkitRunnable(){
							@Override
							public void run() {
								for(ItemStack is:player.getInventory().getContents()){
									if(isMagic(is)){
										player.sendMessage(is.getItemMeta().getDisplayName()+ChatColor.GRAY+"が検出されました.");
										if(updateMagic(is)){
											player.sendMessage(is.getItemMeta().getDisplayName()+ChatColor.GRAY+"を更新しました.");
										}
									}
								}
								player.sendMessage(ChatColor.GRAY+"魔法修復プログラムは正常に終了しました.");
							}
						}.runTaskLater(Magic.plugin, 30);
				}
				return true;
				case"updatelore":
					if(sender instanceof Player){
						final Player player = (Player) sender;
						player.sendMessage(ChatColor.GRAY+"魔法修復プログラムver2をリクエストしています...");
						new BukkitRunnable(){
							@Override
							public void run() {
								for(ItemStack is:player.getInventory().getContents()){
									if(is!=null&&is.hasItemMeta()){
										if(is.getItemMeta().hasLore()){
										String lore = is.getItemMeta().getLore().get(0);
										lore = ChatColor.stripColor(lore);
										int num = lore.indexOf(":");
										if(num!=-1){
											switch(lore.substring(0, num)){
											case"魔法アイテム番号":
												switch(lore.substring(num+1)){
													case"5":
														player.sendMessage(ChatColor.GRAY+is.getItemMeta().getDisplayName()+ChatColor.GRAY+"が見つかりました.");
														if(!is.getItemMeta().getLore().equals(MagicItem.MagicTicket_lore)){
															ItemMeta itemmeta = is.getItemMeta();
															itemmeta.setLore(MagicItem.MagicTicket_lore);
															is.setItemMeta(itemmeta);
														}
														break;
													default:
														break;
												}
												//player.sendMessage(ChatColor.GRAY+is.getItemMeta().getDisplayName()+ChatColor.GRAY+"は正常に修復されました.");
												break;
											default:
												break;
											}
										}
										}
									}
								}
								player.sendMessage(ChatColor.GRAY+"魔法修復プログラムver2は正常に終了しました.");
							}
						}.runTaskLater(Magic.plugin, 30);
				}
					return true;
				case"reload":
					if (!sender.hasPermission("MincraMagicRod.Admin")) {
		                sender.sendMessage("権限がありません");
		                return true;
		            }
					Bukkit.getServer().getPluginManager().disablePlugin(Magic.plugin);
					Bukkit.getServer().getPluginManager().enablePlugin(Magic.plugin);
					return true;
				case"rodinfo":
					if(sender instanceof Player){
						List<String> li = ((Player) sender).getInventory().getItemInMainHand().getItemMeta().getLore();
						for(String st:li){
						((Player) sender).sendMessage("生の値:"+st);
						}
						for(String st:li){
						st=st.substring(Integer.valueOf(args[0]),Integer.valueOf(args[1]));
						((Player) sender).sendMessage("操作値:"+st);
						}
						return true;
					}else{
						return true;
					}
				case"testplay":
					Version.playeffect3(((Entity)sender).getLocation(),(float) ((LivingEntity)sender).getEyeHeight(),args[1]);
					return true;
				case"trade":
					if(args.length<2){
						sender.sendMessage("パラメタが足りません.");
						return true;
					}
					if(!(sender instanceof Player)){
						sender.sendMessage("プレイヤーから実行してください.");
						return true;
					}
					if(cmdmap.containsKey(args[1])){
						Inventory inv = ((Player) sender).getInventory();
						Integer nessary_amount = cmdmap.get(args[1]);
						if(inv.containsAtLeast(MagicItem.magicTicket, nessary_amount)){
							/*Integer removeamount = nessary_amount;
							for(Entry<Integer, ? extends ItemStack> e:inv.all(Material.PAPER).entrySet()){
								if(ChatColor.stripColor(e.getValue().getItemMeta().getLore().get(0)).equals("魔法アイテム番号:5")){

								}
							}*/
							ItemStack removeitem = MagicItem.magicTicket;
							removeitem.setAmount(nessary_amount);
							inv.removeItem(removeitem);

							String give = givemap.get(args[1]+","+nessary_amount);
							String giveitem = give.substring(0, give.indexOf(","));
							String giveamount = give.substring(give.indexOf(",")+1);
							String command = "item"+" "+"give"+" "+sender.getName()+" "+giveitem+" "+giveamount;
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
							return true;
						}else{
							int amount = 0;
							for(ItemStack a:inv.getContents()){
								if(a!=null){
									if(a.getItemMeta().hasLore()){
									if(ChatColor.stripColor(a.getItemMeta().getLore().get(0)).equals("魔法アイテム番号:5")){
										amount += a.getAmount();
									}
									}
								}
							}
							/*for(Map.Entry<Integer, ? extends ItemStack> set : inv.all(MagicItem.MagicTicket).entrySet()) {
								amount += set.getValue().getAmount();
							}*/
							sender.sendMessage("チケットの枚数が足りません."+ChatColor.GRAY+"("+amount+"/"+nessary_amount+")");
							return true;
						}
					}else{
						sender.sendMessage("データが存在しません.");
						return true;
					}
				case"trade2":
					if(args.length<2){
						sender.sendMessage("パラメタが足りません.");
						return true;
					}
					if(!(sender instanceof Player)){
						sender.sendMessage("プレイヤーから実行してください.");
						return true;
					}
					if(cmdmap_gold.containsKey(args[1])){
						Inventory inv = ((Player) sender).getInventory();
						Integer nessary_amount = cmdmap_gold.get(args[1]);
						if(inv.containsAtLeast(MagicItem.magicGoldTicket, nessary_amount)){
							/*Integer removeamount = nessary_amount;
							for(Entry<Integer, ? extends ItemStack> e:inv.all(Material.PAPER).entrySet()){
								if(ChatColor.stripColor(e.getValue().getItemMeta().getLore().get(0)).equals("魔法アイテム番号:5")){

								}
							}*/
							ItemStack removeitem = MagicItem.magicGoldTicket;
							removeitem.setAmount(nessary_amount);
							inv.removeItem(removeitem);

							String give = givemap.get(args[1]+","+nessary_amount);
							String giveitem = give.substring(0, give.indexOf(","));
							String giveamount = give.substring(give.indexOf(",")+1);
							String command = "item"+" "+"give"+" "+sender.getName()+" "+giveitem+" "+giveamount;
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
							return true;
						}else{
							int amount = 0;
							for(ItemStack a:inv.getContents()){
								if(a!=null){
									if(a.getItemMeta().hasLore()){
									if(ChatColor.stripColor(a.getItemMeta().getLore().get(0)).equals("魔法アイテム番号:5-2")){
										amount += a.getAmount();
									}
									}
								}
							}
							/*for(Map.Entry<Integer, ? extends ItemStack> set : inv.all(MagicItem.MagicTicket).entrySet()) {
								amount += set.getValue().getAmount();
							}*/
							sender.sendMessage("チケットの枚数が足りません."+ChatColor.GRAY+"("+amount+"/"+nessary_amount+")");
							return true;
						}
					}else{
						sender.sendMessage("データが存在しません.");
						return true;
					}
				case"givematerial":
					if(giveMaterial(sender,args)) return true;
					break;
				default:
					return false;
			}
		}
		return false;
	}
	/*private void getWeapon(Player player,String arg){
		switch(arg){


		}
	}
	private void getItem(Player player,String arg){
		switch(arg){


		}
	}
	private void getRod(Player player,String arg){
		switch(arg){


		}
	}*/
	private void getMaterial(Player player,String arg){
		player.getInventory().addItem(MagicApi.getMaterial(Integer.valueOf(arg)));
	}
	private boolean giveMaterial(CommandSender sender, String[] args) {
		if(!(sender.hasPermission("mincra.item.give")||sender.hasPermission("mincra.item.admin"))){
			sender.sendMessage(ChatColor.GRAY+"権限を持っていません.");
			return true;
		}
		if(args.length<3){
			sender.sendMessage(ChatColor.GRAY+"パラメタが足りない.");
			sender.sendMessage(ChatColor.GRAY+"/mmr give [Player名] [登録名] [個数]");
			return true;
		}
		if(Bukkit.getPlayer(args[1])==null){
			sender.sendMessage(ChatColor.GRAY+args[1]+"は存在しないプレイヤーです.");
			return true;
		}
		if(!(args[2].length()>=1&&args[2].length()<=64)){
			sender.sendMessage(ChatColor.GRAY+"登録名は64文字以内にしてください.");
			return true;
		}
		/*fileloop:
		{
			for(String fn:folder.list()){
				if(fn.equals(args[2]+".yml")){
					break fileloop;
				}
			}
			sender.sendMessage(ChatColor.GRAY+args[2]+".yml"+"ファイルは存在しません.");
			return true;
		}*/

		if(args.length>3){
			if(!(Integer.valueOf(args[3])>=1&&Integer.valueOf(args[3])<=9999)){
				sender.sendMessage(ChatColor.GRAY+"個数のパラメタが不正です.");
				sender.sendMessage(ChatColor.GRAY+"/item give [Player名] [登録名] [個数]");
				return true;
			}
		}

		Player giveplayer = Bukkit.getPlayer(args[1]);
		/* 個数指定。まだgetInventory().addItem()をよく理解していない
		Integer amount;
		if(args.length>3){
			amount = Integer.valueOf(args[3]);
		}else{
			amount = 1;
		}*/

		giveplayer.getInventory().addItem(MagicApi.getMaterial(Integer.valueOf(args[2])));



	    /*ItemMeta itemmeta = item.getItemMeta();
	    itemyml.set("Meta.DisplayName", itemmeta.getDisplayName());
	    itemyml.set("Meta.Lore", itemmeta.getLore());
	    //itemyml.set("Meta.Lore", Arrays.asList(itemmeta.getLore()));
	    itemyml.createSection("Meta.EnchantMap", itemmeta.getEnchants());

	    /*for(Entry<Enchantment, Integer> e:itemmeta.getEnchants().entrySet()){
	    	itemyml.set("Meta.EnchantMap", e.getKey()+": "+e.getValue());
	    }*/

		return true;
	}
}
